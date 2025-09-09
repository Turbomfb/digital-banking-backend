/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.data.model.Address;
import com.techservices.digitalbanking.core.domain.data.repository.AddressRepository;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.IdentityVerificationRequest;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.AddressType;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.configuration.FineractProperty;
import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.request.KycTier;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientNonPersonDetails;
import com.techservices.digitalbanking.core.fineract.model.request.PutDataTableRequest;
import com.techservices.digitalbanking.core.fineract.model.response.*;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.customer.domian.CustomerKycTier;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerKycRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.customer.service.CustomerKycService;
import com.techservices.digitalbanking.customer.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.techservices.digitalbanking.core.util.AppUtil.DIRECTORS_DATATABLE_NAME;
import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.CommandUtil.VERIFY_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerKycServiceImpl implements CustomerKycService {

    private final CustomerService customerService;
    private final ClientService clientService;
    private final CustomerRepository customerRepository;
    private final IdentityVerificationService identityVerificationService;
    private final RedisService redisService;
    private final AccountService accountService;
    private final FineractProperty fineractProperty;
    private final AddressRepository addressRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseAppResponse updateCustomerKyc(CustomerKycRequest customerKycRequest, Long customerId, String command) {
        log.info("customerKycRequest: {}", customerKycRequest);
        Customer foundCustomer = this.customerService.getCustomerById(customerId);
        String uniqueId = customerKycRequest.getUniqueId();
        String otp = customerKycRequest.getOtp();

        if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
            IdentityVerificationResponse verificationResponse = this.validateKycParameters(customerKycRequest, foundCustomer, command);
            if (verificationResponse != null) {
                return processKycOtpGeneration(customerKycRequest, verificationResponse);
            }
        }

        else if (VERIFY_OTP_COMMAND.equalsIgnoreCase(command)) {
            return processKycVerification(customerKycRequest, customerId, otp, uniqueId, foundCustomer, command);
        }
        throw new ValidationException("invalid.command", "Invalid command provided.");
    }

    private CustomerDtoResponse processKycVerification(CustomerKycRequest customerKycRequest, Long customerId, String otp, String uniqueId, Customer foundCustomer, String command) {
        if (StringUtils.isBlank(customerKycRequest.getBase64Image()) && StringUtils.isBlank(otp)) {
            throw new ValidationException("validation.error.exists", "Please provide an otp or a base64Image");
        }
        OtpDto otpDto = this.redisService.retrieveOtpDto(uniqueId);
        if (otpDto == null) {
            throw new ValidationException("otp.expired", "Verification has expired or does not exist. Please initiate a new one.");
        }
        if (StringUtils.isNotBlank(customerKycRequest.getBase64Image())) {
            log.info("customerKycRequest.getBase64Image(): {}", customerKycRequest.getBase64Image());
            customerKycRequest = (CustomerKycRequest) otpDto.getData();
            this.identityVerificationService.validateImageMismatch(customerKycRequest.getBase64Image(), customerKycRequest.getBvn(), customerKycRequest.getNin());
            log.info("Image mismatch validated successfully");
        } else {
            otpDto = this.redisService.validateOtpWithoutDeletingRecord(uniqueId, otp, OtpType.KYC_UPGRADE);
            customerKycRequest = (CustomerKycRequest) otpDto.getData();
        }
        IdentityVerificationResponse verificationResponse = this.validateKycParameters(customerKycRequest, foundCustomer, command);


        CustomerKycTier customerKycTier = this.getCustomerKycTier(foundCustomer);
        if (!foundCustomer.isActive()) {
            this.activateCustomer(foundCustomer, customerKycRequest, customerKycTier);
        } else {
            this.updateCustomerDetails(foundCustomer, customerKycRequest, customerId, customerKycTier);
        }
        if (verificationResponse != null && verificationResponse.getData() != null && verificationResponse.getData().getAddress() != null && StringUtils.isNotBlank(foundCustomer.getExternalId())) {
            updateCustomerAddress(foundCustomer, verificationResponse.getData().getAddress());
        }

        foundCustomer.setKycTier(customerKycTier);
        foundCustomer = this.customerRepository.save(foundCustomer);
        this.redisService.delete(uniqueId);
        return CustomerDtoResponse.parse(foundCustomer);
    }

    private GenericApiResponse processKycOtpGeneration(CustomerKycRequest customerKycRequest, IdentityVerificationResponse verificationResponse) {
        if (verificationResponse.getData() != null) {
            IdentityVerificationResponse.IdentityVerificationResponseData data = verificationResponse.getData();
            if ("EXTERNAL".equalsIgnoreCase(verificationResponse.getDataSource()) && (StringUtils.isBlank(data.getMobile()) || StringUtils.isBlank(data.getFirstName()) || StringUtils.isBlank(data.getLastName()))) {
                log.error("Data source: {}, Identity verification failed for customer id: {}. Null fields were identified", verificationResponse.getDataSource(), data);
                String dataType = StringUtils.isNotBlank(customerKycRequest.getBvn()) ? "BVN" : "NIN";
                throw new ValidationException("validation.error.exists", "Unable to validate your " + dataType + " at the moment. Please try again later.");
            }
        }
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto(verificationResponse);
        OtpDto otpDto = this.redisService.generateOtpRequest(customerKycRequest, OtpType.KYC_UPGRADE, notificationRequestDto, null);
        String message = notificationRequestDto.getOtpResponseMessage();
        return new GenericApiResponse(otpDto.getUniqueId(), message, "success", null);
    }

    private void updateCustomerAddress(Customer foundCustomer, IdentityVerificationResponse.IdentityVerificationResponseData.Address address) {
        List<Address> customerAddress = addressRepository.findByCustomerId(foundCustomer.getId());
        if (!customerAddress.isEmpty()) {
            return;
        }
        Address newAddress = new Address();
        newAddress.setCustomerId(foundCustomer.getId());
        newAddress.setType(AddressType.RESIDENTIAL.toString());
        newAddress.setAddressLine(address.getAddressLine());
        newAddress.setTown(address.getTown());
        newAddress.setLga(address.getLga());
        newAddress.setState(address.getState());
        addressRepository.save(newAddress);
    }

    @Override
    public FineractPageResponse<KycTier> retrieveAllKycTier() {
        return clientService.retrieveAllKycTier();
    }

    private IdentityVerificationResponse validateKycParameters(CustomerKycRequest customerKycRequest, Customer foundCustomer, String command) {
        boolean includesNoKycParameter = StringUtils.isAllBlank(customerKycRequest.getBvn(), customerKycRequest.getNin());
        if (!foundCustomer.isActive() && includesNoKycParameter) {
            throw new ValidationException("no.kyc.parameter.provided", "At least one KYC parameter (BVN or NIN) must be provided.");
        }

        if (StringUtils.isNotBlank(customerKycRequest.getBvn())) {
            log.info("Validating BVN: {}", customerKycRequest.getBvn());
            customerRepository.findByBvnAndUserType(customerKycRequest.getBvn(), foundCustomer.getUserType())
                    .ifPresent(existedCustomer -> {
                        log.error("BVN {} already exists for customer id: {}", customerKycRequest.getBvn(), existedCustomer.getId());
                        throw new ValidationException("bvn.already.exists", "You have entered an invalid BVN");
                    });

            if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
                GetClientsClientIdResponse client = clientService.getCustomerByBvn(customerKycRequest.getBvn());
                if (client != null) {
                    return IdentityVerificationResponse.parse(client);
                }
                return this.identityVerificationService.retrieveBvnData(customerKycRequest.getBvn());
            }

            IdentityVerificationRequest identityVerificationRequest = IdentityVerificationRequest.parse(foundCustomer);
            CustomerIdentityVerificationResponse customerIdentityVerificationResponse = this.identityVerificationService.verifyBvn(customerKycRequest.getBvn(), identityVerificationRequest, foundCustomer);
            foundCustomer.setBvn(customerKycRequest.getBvn());
            finalizeValidations(customerIdentityVerificationResponse, "BVN");
        }

        if (StringUtils.isNotBlank(customerKycRequest.getNin())) {
            log.info("Validating NIN: {}", customerKycRequest.getNin());
            customerRepository.findByNinAndUserType(customerKycRequest.getNin(), foundCustomer.getUserType())
                    .ifPresent(existingCustomer -> {
                        log.error("NIN {} already exists for customer id: {}", customerKycRequest.getNin(), existingCustomer.getId());
                        throw new ValidationException("nin.already.exists", "You have entered an invalid NIN.");
                    });

            if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
                GetClientsClientIdResponse client = clientService.getCustomerByNin(customerKycRequest.getNin());
                if (client != null) {
                    return IdentityVerificationResponse.parse(client);
                }
                return this.identityVerificationService.retrieveNinData(customerKycRequest.getNin());
            }

            IdentityVerificationRequest identityVerificationRequest = IdentityVerificationRequest.parse(foundCustomer);
            CustomerIdentityVerificationResponse customerIdentityVerificationResponse = identityVerificationService.verifyNin(customerKycRequest.getNin(), identityVerificationRequest, foundCustomer);
            foundCustomer.setNin(customerKycRequest.getNin());
            finalizeValidations(customerIdentityVerificationResponse, "NIN");
        }
        return null;
    }

    private static void finalizeValidations(CustomerIdentityVerificationResponse customerIdentityVerificationResponse, String dataType) {
        log.error("{} verification failed: {}", dataType, customerIdentityVerificationResponse);
        if (!customerIdentityVerificationResponse.isValid()) {
            throw new ValidationException(dataType+".verification.failed", "We're unable to complete your tier upgrade because the information provided does not match our records. " +
                    "Please confirm that your "+dataType+" details are correct or contact your bank customer support for assistance.");
        }
    }

    private static void validateEntityClient(GetClientsClientIdResponse client) {
        if (client != null && client.getLegalForm() != null && client.getLegalForm().getId() == 2) {
            throw new ValidationException("validation.error.exists", "This profile is currently linked to a corporate entity and is not permitted to perform any operations.");
        }
    }

    private void activateCustomer(Customer foundCustomer, CustomerKycRequest customerKycRequest, CustomerKycTier customerKycTier) {
        GetClientsClientIdResponse client = clientService.searchClients(customerKycRequest.getNin(), customerKycRequest.getBvn(), null, null, foundCustomer.getUserType());
        log.info("Client found: {}", client);
        if (client == null) {
            client = fetchCbaClientByEmailOrPhoneNumber(foundCustomer);
        }
        CreateCustomerRequest createCustomerRequest = buildCreateCustomerRequest(foundCustomer, customerKycRequest, customerKycTier);
        PostClientsResponse createCustomerResponse = null;
        if (client == null) {
            createCustomerResponse = clientService.createCustomer(createCustomerRequest);
            if (foundCustomer.getUserType() == UserType.CORPORATE && createCustomerResponse != null) {
                this.postDirectorDataTable(foundCustomer, createCustomerResponse.getClientId());
            }
        } else {
            PutClientsClientIdResponse response = this.upgradeClientTier(customerKycTier, client.getId(), foundCustomer);
            if (foundCustomer.getUserType() == UserType.CORPORATE && response != null) {
                this.updateDirectorDataTable(foundCustomer, client.getId());
            }
        }

        Long clientId = client != null && client.getId() != null ? client.getId() : createCustomerResponse != null ? createCustomerResponse.getClientId() : null;
        if (clientId != null) {
            log.info("Client ID found: {}", clientId);
            @Valid Set<@Valid GetClientsSavingsAccounts> clientAccounts = clientService.getClientAccountsByClientId(String.valueOf(clientId), "savingsAccounts").getSavingsAccounts();
            Optional<@Valid GetClientsSavingsAccounts> savingsAccount = clientAccounts.stream().findAny();
            log.info("Savings account found: {}", savingsAccount);
            Long savingsAccountId = null;
            if (savingsAccount.isPresent()) {
                savingsAccountId = savingsAccount.get().getId();
            } else {
                PostSavingsAccountsResponse savingsAccountsResponse = accountService.createSavingsAccount(
                        clientId, fineractProperty.getDefaultSavingsProductId(), null, null, null,
                        null, true, fineractProperty.getSavingsAccountNominalAnnualInterestRate()
                );
                if (savingsAccountsResponse != null) {
                    savingsAccountId = savingsAccountsResponse.getSavingsId();
                }
            }
            if (savingsAccountId != null) {
                foundCustomer.setExternalId(String.valueOf(clientId));
                foundCustomer.setAccountId(String.valueOf(savingsAccountId));
                foundCustomer.setActive(true);
            }
        }
    }

    private void updateDirectorDataTable(Customer foundCustomer, Long externalId) {
        List<GetDataTablesResponse> datatableResponse = clientService.retrieveClientDataTables(DIRECTORS_DATATABLE_NAME, externalId);
        if (datatableResponse != null && !datatableResponse.isEmpty()) {
            log.info("Directors datatable already exists for client ID {}", externalId);
            GetDataTablesResponse directorsTable = datatableResponse.stream()
                    .filter(data -> foundCustomer.getFirstname().equalsIgnoreCase(data.getFirstName()))
                    .findFirst()
                    .orElse(null);
            if (directorsTable != null) {
                PutDataTableRequest request = new PutDataTableRequest();
                request.setBvn(foundCustomer.getBvn());
                request.setNin(foundCustomer.getNin());
                request.setFirstName(foundCustomer.getFirstname());
                request.setLastName(foundCustomer.getLastname());
                request.setEmailAddress(foundCustomer.getEmailAddress());
                request.setMobileNumber(foundCustomer.getPhoneNumber());
                request.setLocale(DEFAULT_LOCALE);
                try {
                    clientService.updateAClientDataTable(DIRECTORS_DATATABLE_NAME, externalId, directorsTable.getId(), request);
                } catch (Exception e) {
                    log.error("Error updating director to datatable for client ID {}: {}", externalId, e.getMessage());
                }
            } else {
                this.postDirectorDataTable(foundCustomer, externalId);
            }
        } else {
            this.postDirectorDataTable(foundCustomer, externalId);
        }
    }

    private void postDirectorDataTable(Customer foundCustomer, Long clientId) {
        PutDataTableRequest request = new PutDataTableRequest();
        request.setBvn(foundCustomer.getBvn());
        request.setNin(foundCustomer.getNin());
        request.setFirstName(foundCustomer.getFirstname());
        request.setLastName(foundCustomer.getLastname());
        request.setEmailAddress(foundCustomer.getEmailAddress());
        request.setMobileNumber(foundCustomer.getPhoneNumber());
        request.setLocale(DEFAULT_LOCALE);
        try {
            clientService.postAClientDataTable(DIRECTORS_DATATABLE_NAME, clientId, request);
        } catch (Exception e) {
            log.error("Error adding director to datatable for client ID {}: {}", clientId, e.getMessage());
        }
    }

    private GetClientsClientIdResponse fetchCbaClientByEmailOrPhoneNumber(Customer foundCustomer) {
        GetClientsClientIdResponse client;
        client = clientService.searchClients(null, null, foundCustomer.getEmailAddress(), null, foundCustomer.getUserType());
        if (client == null) {
            client = clientService.searchClients(null, null, null, foundCustomer.getPhoneNumber(), foundCustomer.getUserType());
        }
        return client;
    }

    private PutClientsClientIdResponse upgradeClientTier(CustomerKycTier customerKycTier, Long clientId, Customer foundCustomer) {
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest();
        updateRequest.setKycTier(customerKycTier.getCode());
        return clientService.updateCustomer(updateRequest, clientId, foundCustomer.getUserType());
    }

    private CustomerKycTier getCustomerKycTier(Customer foundCustomer) {
        if (!StringUtils.isAllBlank(foundCustomer.getNin(), foundCustomer.getBvn())) {
            if (StringUtils.isNoneBlank(foundCustomer.getBvn(), foundCustomer.getNin())) {
                return CustomerKycTier.TIER_2;
            }
            return CustomerKycTier.TIER_1;
        }
        return null;
    }

    private CreateCustomerRequest buildCreateCustomerRequest(Customer foundCustomer, CustomerKycRequest customerKycRequest, CustomerKycTier customerKycTier) {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setCustomerType(foundCustomer.getUserType());
        if (foundCustomer.getUserType() == UserType.CORPORATE) {
            createCustomerRequest.setBusinessName(foundCustomer.getBusinessName());
            PostClientNonPersonDetails postClientNonPersonDetails = new PostClientNonPersonDetails();
            postClientNonPersonDetails.setIncorpNumber(foundCustomer.getRcNumber());
            postClientNonPersonDetails.setConstitutionId(23L);
            postClientNonPersonDetails.setMainBusinessLineId(Long.valueOf(foundCustomer.getIndustryId()));
            createCustomerRequest.setClientNonPersonDetails(postClientNonPersonDetails);
        } else {
            createCustomerRequest.setBvn(customerKycRequest.getBvn());
            createCustomerRequest.setNin(customerKycRequest.getNin());
            createCustomerRequest.setFirstname(createCustomerRequest.getFirstname());
            createCustomerRequest.setLastname(createCustomerRequest.getLastname());
        }
        createCustomerRequest.setFirstname(foundCustomer.getFirstname());
        createCustomerRequest.setLastname(foundCustomer.getLastname());
        createCustomerRequest.setEmailAddress(foundCustomer.getEmailAddress());
        createCustomerRequest.setPhoneNumber(foundCustomer.getPhoneNumber());
        if (customerKycTier != null) {
            createCustomerRequest.setKycTier(customerKycTier.getCode());
        }
        return createCustomerRequest;
    }

    private void updateCustomerDetails(Customer foundCustomer, CustomerKycRequest customerKycRequest, Long customerId, CustomerKycTier customerKycTier) {
        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest();
        if (customerKycRequest != null) {
            customerUpdateRequest.setBvn(customerKycRequest.getBvn());
            customerUpdateRequest.setNin(customerKycRequest.getNin());
        }
        if (customerKycTier != null) {
            customerUpdateRequest.setKycTier(customerKycTier.getCode());
        }
        customerService.updateCustomer(customerUpdateRequest, customerId, foundCustomer);
        if (foundCustomer.getUserType() == UserType.CORPORATE && StringUtils.isNotBlank(foundCustomer.getExternalId())) {
            this.updateDirectorDataTable(foundCustomer, Long.valueOf(foundCustomer.getExternalId()));
        }
    }
}
