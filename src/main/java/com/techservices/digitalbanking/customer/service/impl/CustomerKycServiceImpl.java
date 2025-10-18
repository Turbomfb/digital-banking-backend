/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.data.model.Address;
import com.techservices.digitalbanking.core.domain.data.model.IndustrySector;
import com.techservices.digitalbanking.core.domain.data.repository.AddressRepository;
import com.techservices.digitalbanking.core.domain.data.repository.IndustrySectorRepository;
import com.techservices.digitalbanking.core.domain.data.repository.KycTierRepository;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.CustomerDto;
import com.techservices.digitalbanking.core.domain.dto.CustomerFilterDto;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.KycTierDto;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.dto.response.BusinessDataResponse;
import com.techservices.digitalbanking.core.domain.dto.response.CustomerIdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.AddressType;
import com.techservices.digitalbanking.core.domain.enums.IdentityVerificationDataType;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.eBanking.model.request.FlexInvestmentCreationRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostClientsAddressRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.FlexInvestmentCreationResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetClientsClientIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostClientsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutClientsClientIdResponse;
import com.techservices.digitalbanking.core.eBanking.service.FlexDepositAccountService;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.eBanking.model.request.PutDataTableRequest;
import com.techservices.digitalbanking.core.eBanking.service.AccountService;
import com.techservices.digitalbanking.core.eBanking.service.ClientService;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.IdentityVerificationService;
import com.techservices.digitalbanking.core.util.AppUtil;
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

import static com.techservices.digitalbanking.common.domain.enums.UserType.CORPORATE;
import static com.techservices.digitalbanking.common.domain.enums.UserType.RETAIL;
import static com.techservices.digitalbanking.core.domain.enums.IdentityVerificationDataType.BVN;
import static com.techservices.digitalbanking.core.domain.enums.IdentityVerificationDataType.NIN;
import static com.techservices.digitalbanking.core.domain.enums.IdentityVerificationDataType.RC_NUMBER;
import static com.techservices.digitalbanking.core.domain.enums.IdentityVerificationDataType.TIN;
import static com.techservices.digitalbanking.core.util.AppUtil.EXTERNAL;
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
    private final AddressRepository addressRepository;
    private final IndustrySectorRepository industrySectorRepository;
    private final KycTierRepository kycTierRepository;
    private final FlexDepositAccountService flexDepositAccountService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BaseAppResponse updateCustomerKyc(CustomerKycRequest customerKycRequest, Long customerId, String command) {
        log.info("customerKycRequest: {}", customerKycRequest);

        Customer foundCustomer = customerService.getCustomerById(customerId);

        if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
            return handleOtpGeneration(customerKycRequest, foundCustomer, command);
        } else if (VERIFY_OTP_COMMAND.equalsIgnoreCase(command)) {
            return handleOtpVerification(customerKycRequest, customerId, foundCustomer, command);
        } else {
            throw new ValidationException("invalid.command", "Invalid command provided: " + command);
        }
    }

    @Override
    public BasePageResponse<KycTierDto> retrieveAllKycTier() {
        return BasePageResponse.instance(
                kycTierRepository.findAll().stream().map(KycTierDto::parse).toList()
        );
    }

    private BaseAppResponse handleOtpGeneration(CustomerKycRequest customerKycRequest, Customer foundCustomer, String command) {
        IdentityVerificationResponse verificationResponse = validateKycParameters(customerKycRequest, foundCustomer, command);
        if (verificationResponse != null) {
            return processKycOtpGeneration(customerKycRequest, verificationResponse, foundCustomer.getUserType());
        }
        throw new ValidationException("error.generating_otp", "Kyc upgrade failed. Please contact support");
    }

    private GenericApiResponse processKycOtpGeneration(CustomerKycRequest customerKycRequest, IdentityVerificationResponse verificationResponse, UserType userType) {
        NotificationRequestDto notificationRequestDto = new NotificationRequestDto(verificationResponse);
        OtpDto otpDto = redisService.generateOtpRequest(customerKycRequest, OtpType.KYC_UPGRADE, notificationRequestDto, null);
        String message = notificationRequestDto.getOtpResponseMessage();
        return new GenericApiResponse(otpDto.getUniqueId(), message, "success", null);
    }

    private void validateOtpGenerationData(IdentityVerificationResponse verificationResponse, IdentityVerificationDataType dataType, Customer foundCustomer) {
        if (verificationResponse.getData() != null) {
            IdentityVerificationResponse.IdentityVerificationResponseData data = verificationResponse.getData();
            if ("EXTERNAL".equalsIgnoreCase(verificationResponse.getDataSource())) {
                boolean isValidCorporateData = StringUtils.isNoneBlank(data.getName(), data.getMobile());
                boolean isValidIndividualData = StringUtils.isNoneBlank(data.getFirstName(), data.getLastName(), data.getMobile());

                if ((dataType.isIndividual() && !isValidIndividualData) || (dataType.isCorporate() && !isValidCorporateData)) {
                    log.error("Data source: {}, Identity verification failed for customer id: {}. Null fields were identified",
                            verificationResponse.getDataSource(), data);
                    throw new ValidationException("validation.error.exists",
                            "We're unable to complete your tier upgrade because the information provided does not match our records. " +
                                    "Please confirm that your " + dataType + " details are correct or contact your bank customer support for assistance.");
                }
            }
        }
    }

    private IdentityVerificationDataType determineDataType(CustomerKycRequest customerKycRequest) {
        if (StringUtils.isNotBlank(customerKycRequest.getBvn())) return BVN;
        if (StringUtils.isNotBlank(customerKycRequest.getNin())) return NIN;
        if (StringUtils.isNotBlank(customerKycRequest.getRcNumber())) return RC_NUMBER;
        return TIN;
    }

    private CustomerDtoResponse handleOtpVerification(CustomerKycRequest customerKycRequest, Long customerId, Customer foundCustomer, String command) {
        String uniqueId = customerKycRequest.getUniqueId();
        String otp = customerKycRequest.getOtp();

        validateVerificationInput(customerKycRequest);

        OtpDto otpDto = retrieveAndValidateOtp(customerKycRequest, uniqueId, otp);
        customerKycRequest = (CustomerKycRequest) otpDto.getData();
        if (customerKycRequest == null) {
            throw new ValidationException("otp.expired", "Verification has expired or does not exist. Please initiate a new one.");
        }

        IdentityVerificationResponse verificationResponse = validateKycParameters(customerKycRequest, foundCustomer, command);
        CustomerKycTier customerKycTier = getCustomerKycTier(foundCustomer, customerKycRequest);

        processCustomerActivationOrUpdate(foundCustomer, customerKycRequest, customerId, customerKycTier, verificationResponse);
        updateCustomerAddressIfNeeded(foundCustomer, verificationResponse);

        foundCustomer.setKycTier(customerKycTier);
        foundCustomer = customerRepository.save(foundCustomer);
        redisService.delete(uniqueId);

        return CustomerDtoResponse.parse(foundCustomer);
    }

    private void validateVerificationInput(CustomerKycRequest customerKycRequest) {
        if (StringUtils.isBlank(customerKycRequest.getBase64Image()) && StringUtils.isBlank(customerKycRequest.getOtp())) {
            throw new ValidationException("validation.error.exists", "Please provide an otp or a base64Image");
        }
    }

    private OtpDto retrieveAndValidateOtp(CustomerKycRequest customerKycRequest, String uniqueId, String otp) {
        OtpDto otpDto = redisService.retrieveOtpDto(uniqueId);
        if (otpDto == null) {
            throw new ValidationException("otp.expired", "Verification has expired or does not exist. Please initiate a new one.");
        }

        if (StringUtils.isNotBlank(customerKycRequest.getBase64Image())) {
            log.info("customerKycRequest.getBase64Image(): {}", customerKycRequest.getBase64Image());
            CustomerKycRequest originalRequest = (CustomerKycRequest) otpDto.getData();
            identityVerificationService.validateImageMismatch(
                    customerKycRequest.getBase64Image(),
                    originalRequest.getBvn(),
                    originalRequest.getNin()
            );
            log.info("Image mismatch validated successfully");
        } else {
            otpDto = redisService.validateOtpWithoutDeletingRecord(uniqueId, otp, OtpType.KYC_UPGRADE);
        }

        return otpDto;
    }

    private void processCustomerActivationOrUpdate(Customer foundCustomer, CustomerKycRequest customerKycRequest,
                                                   Long customerId, CustomerKycTier customerKycTier,
                                                   IdentityVerificationResponse verificationResponse) {
        if (!foundCustomer.isActive()) {
            activateCustomer(foundCustomer, customerKycRequest, customerKycTier, verificationResponse.getData());
        } else {
            updateCustomerDetails(foundCustomer, customerKycRequest, customerId, customerKycTier);
        }
    }

    private void updateCustomerAddressIfNeeded(Customer foundCustomer, IdentityVerificationResponse verificationResponse) {
        if (verificationResponse != null && verificationResponse.getData() != null &&
                verificationResponse.getData().getAddress() != null &&
                StringUtils.isNotBlank(foundCustomer.getExternalId())) {
            updateCustomerAddress(foundCustomer, verificationResponse.getData().getAddress());
        }
    }

    private IdentityVerificationResponse validateKycParameters(CustomerKycRequest customerKycRequest, Customer foundCustomer, String command) {
        validateBasicKycRequirements(customerKycRequest, foundCustomer);

        if (StringUtils.isNoneBlank(customerKycRequest.getBvn(), customerKycRequest.getNin())) {
            return handleBvnNinValidation(customerKycRequest, foundCustomer, command);
        }

        if (StringUtils.isNotBlank(customerKycRequest.getRcNumber()) && foundCustomer.isCorporateUser()) {
            return handleRcNumberValidation(customerKycRequest, foundCustomer, command);
        }

        if (StringUtils.isNotBlank(customerKycRequest.getTin()) && foundCustomer.isCorporateUser()) {
            return handleTinValidation(customerKycRequest, foundCustomer, command);
        }

        return null;
    }

    private void validateBasicKycRequirements(CustomerKycRequest customerKycRequest, Customer foundCustomer) {
        boolean includesNoKycParameter = StringUtils.isAllBlank(
                customerKycRequest.getBvn(), customerKycRequest.getNin(),
                customerKycRequest.getRcNumber(), customerKycRequest.getTin()
        );

        if (!foundCustomer.isActive() && includesNoKycParameter) {
            throw new ValidationException("no.kyc.parameter.provided",
                    "At least one KYC parameter (BVN, NIN, TIN or RCNUMBER) must be provided.");
        }

        if (!foundCustomer.isCorporateUser()) {
            if (StringUtils.isBlank(customerKycRequest.getBvn()) || StringUtils.isBlank(customerKycRequest.getNin())) {
                throw new ValidationException("individual.kyc.parameters.required",
                        "Both BVN and NIN are required for individual customers.");
            }
        }

        if (foundCustomer.isCorporateUser()) {
            if (!foundCustomer.isActive()) {
                if (StringUtils.isBlank(customerKycRequest.getBvn()) || StringUtils.isBlank(customerKycRequest.getNin())) {
                    throw new ValidationException("corporate.customer.not.a.tier1",
                            "Both BVN and NIN are required to upgrade from a non-tier 1 corporate customer.");
                }
            }
        }
    }

    private IdentityVerificationResponse handleBvnNinValidation(CustomerKycRequest customerKycRequest, Customer foundCustomer, String command) {
        validateExistingBvnNin(foundCustomer);
        validateBvnUniqueness(customerKycRequest, foundCustomer);
        validateNinUniqueness(customerKycRequest, foundCustomer);

        if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
            IdentityVerificationResponse ninVerificationResponse = identityVerificationService.retrieveNinData(customerKycRequest.getNin());
            validateOtpGenerationData(ninVerificationResponse, NIN, foundCustomer);
            IdentityVerificationResponse bvnVerificationResponse = identityVerificationService.retrieveBvnData(customerKycRequest.getBvn());
            validateOtpGenerationData(bvnVerificationResponse, BVN, foundCustomer);
            return bvnVerificationResponse;

        } else {
            return processNinBvnVerification(customerKycRequest, foundCustomer);
        }
    }

    private void validateExistingBvnNin(Customer foundCustomer) {
        if (StringUtils.isNotBlank(foundCustomer.getNin()) && StringUtils.isNotBlank(foundCustomer.getBvn())) {
            throw new ValidationException("bvn.and.nin.validation.exists", "Both BVN and NIN have already been provided.");
        }

        if (StringUtils.isNotBlank(foundCustomer.getBvn())) {
            throw new ValidationException("bvn.validation.exists", "BVN already exists on your profile.");
        }

        if (StringUtils.isNotBlank(foundCustomer.getNin())) {
            throw new ValidationException("nin.validation.exists", "NIN already exists on your profile.");
        }
    }

    private void validateBvnUniqueness(CustomerKycRequest customerKycRequest, Customer foundCustomer) {
        log.info("Validating BVN: {}", customerKycRequest.getBvn());
        customerRepository.findByBvnAndUserType(customerKycRequest.getBvn(), foundCustomer.getUserType())
                .ifPresent(existedCustomer -> {
                    log.error("BVN {} already exists for customer id: {}", customerKycRequest.getBvn(), existedCustomer.getId());
                    throw new ValidationException("bvn.already.exists", "You have entered an invalid BVN");
                });
    }

    private void validateNinUniqueness(CustomerKycRequest customerKycRequest, Customer foundCustomer) {
        log.info("Validating NIN: {}", customerKycRequest.getNin());
        customerRepository.findByNinAndUserType(customerKycRequest.getNin(), foundCustomer.getUserType())
                .ifPresent(existingCustomer -> {
                    log.error("NIN {} already exists for customer id: {}", customerKycRequest.getNin(), existingCustomer.getId());
                    throw new ValidationException("nin.already.exists", "You have entered an invalid NIN.");
                });
    }

    private IdentityVerificationResponse processNinBvnVerification(CustomerKycRequest customerKycRequest, Customer foundCustomer) {
        CustomerIdentityVerificationResponse customerIdentityVerificationResponse =
                identityVerificationService.verifyNin(customerKycRequest.getNin(), foundCustomer);
        foundCustomer.setNin(customerKycRequest.getNin());
        finalizeValidations(customerIdentityVerificationResponse, IdentityVerificationDataType.NIN);

        customerIdentityVerificationResponse = identityVerificationService.verifyBvn(customerKycRequest.getBvn(), foundCustomer);
        foundCustomer.setBvn(customerKycRequest.getBvn());
        finalizeValidations(customerIdentityVerificationResponse, BVN);
        IdentityVerificationResponse bvnData = identityVerificationService.retrieveBvnData(customerKycRequest.getBvn());
        if (bvnData.getData().getAddress() == null) {
            IdentityVerificationResponse ninData = identityVerificationService.retrieveNinData(customerKycRequest.getNin());
            bvnData.getData().setAddress(ninData.getData().getAddress());
        }
        return bvnData;
    }

    private IdentityVerificationResponse handleRcNumberValidation(CustomerKycRequest customerKycRequest, Customer foundCustomer, String command) {
        log.info("Validating RC Number: {}", customerKycRequest.getRcNumber());
        BusinessDataResponse businessData = identityVerificationService.retrieveRcNumberData(customerKycRequest.getRcNumber());
        IdentityVerificationResponse rcNumberIdentityVerificationResponse = IdentityVerificationResponse.parse(businessData.getData(), EXTERNAL);
        if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
            validateOtpGenerationData(rcNumberIdentityVerificationResponse, RC_NUMBER, foundCustomer);
            return rcNumberIdentityVerificationResponse;
        }

        CustomerIdentityVerificationResponse customerIdentityVerificationResponse =
                identityVerificationService.verifyRcNumber(customerKycRequest.getRcNumber(), foundCustomer);
        foundCustomer.setRcNumber(customerKycRequest.getRcNumber());
        finalizeValidations(customerIdentityVerificationResponse, IdentityVerificationDataType.RC_NUMBER);
        return rcNumberIdentityVerificationResponse;
    }

    private IdentityVerificationResponse handleTinValidation(CustomerKycRequest customerKycRequest, Customer foundCustomer, String command) {
        BusinessDataResponse businessData = identityVerificationService.retrieveTinData(customerKycRequest.getTin());
        IdentityVerificationResponse tinIdentityVerificationResponse = IdentityVerificationResponse.parse(businessData.getData(), EXTERNAL);
        if (GENERATE_OTP_COMMAND.equalsIgnoreCase(command)) {
            validateOtpGenerationData(tinIdentityVerificationResponse, TIN, foundCustomer);
            return tinIdentityVerificationResponse;
        }

        CustomerIdentityVerificationResponse customerIdentityVerificationResponse =
                identityVerificationService.verifyTin(customerKycRequest.getTin(), foundCustomer);
        foundCustomer.setTin(customerKycRequest.getTin());
        finalizeValidations(customerIdentityVerificationResponse, IdentityVerificationDataType.TIN);
        return tinIdentityVerificationResponse;
    }

    private static void finalizeValidations(CustomerIdentityVerificationResponse customerIdentityVerificationResponse, IdentityVerificationDataType dataType) {
        log.error("{} verification status: {}", dataType.name(), customerIdentityVerificationResponse);
        if (!customerIdentityVerificationResponse.isValid()) {
            throw new ValidationException(dataType);
        }
    }

    private void activateCustomer(Customer foundCustomer, CustomerKycRequest customerKycRequest,
                                  CustomerKycTier customerKycTier, IdentityVerificationResponse.IdentityVerificationResponseData verificationResponse) {
        CustomerDto customer = null;
        CustomerFilterDto customerFilterDto = new CustomerFilterDto().bvn(customerKycRequest.getBvn());
        customer = clientService.searchCustomer(customerFilterDto).stream().findAny().orElse(null);
        log.info("Client found: {}", customer);
        String externalId = createOrUpgradeClient(foundCustomer, customerKycRequest, customerKycTier, customer, verificationResponse);

        if (StringUtils.isNotBlank(externalId)) {
            AccountDto savingsAccount = createOrRetrieveSavingsAccount(externalId, verificationResponse, customerKycTier);
            log.info("Savings account found: {}", savingsAccount);
            String flexAccountNo = createOrRetrieveFlexInvestmentAccount(externalId, verificationResponse);
            log.info("Flex account found: {}", flexAccountNo);
            updateCustomerWithAccountDetails(foundCustomer, externalId, savingsAccount.getAccountNumber(), flexAccountNo, savingsAccount.getNuban());
        }
    }

    private String createOrUpgradeClient(Customer foundCustomer, CustomerKycRequest customerKycRequest,
                                         CustomerKycTier customerKycTier, CustomerDto customer, IdentityVerificationResponse.IdentityVerificationResponseData verificationResponse) {
        CreateCustomerRequest createCustomerRequest = buildCreateCustomerRequest(foundCustomer, customerKycRequest, customerKycTier, verificationResponse);
        PostClientsResponse createCustomerResponse;

        if (customer == null) {
            createCustomerResponse = clientService.createCustomer(createCustomerRequest);
            return createCustomerResponse.getCustomerId();
        } else {
            if (foundCustomer.isCorporateUser()) {
                createCustomerRequest.setExternalId(customer.getId());
                createCustomerResponse = clientService.createCustomer(createCustomerRequest);
                upgradeClientTier(customerKycTier, customer.getId(), foundCustomer, customerKycRequest);
                return createCustomerResponse.getCustomerId();
            }
            upgradeClientTier(customerKycTier, customer.getId(), foundCustomer, customerKycRequest);
        }

        return customer.getId();
    }

    private AccountDto createOrRetrieveSavingsAccount(String externalId, IdentityVerificationResponse.IdentityVerificationResponseData verificationResponse,
                                                  CustomerKycTier customerKycTier) {
        log.info("Client ID found: {}", externalId);
        List<@Valid AccountDto> clientAccounts = clientService.getAllWalletAccountByExternalId(externalId);
        Optional<@Valid AccountDto> savingsAccount = clientAccounts.stream().findAny();
        log.info("Savings account found: {}", savingsAccount);

        if (savingsAccount.isPresent()) {
            return savingsAccount.get();
        } else {
            String gender = verificationResponse.getGender();
            PostSavingsAccountsResponse savingsAccountsResponse = accountService.createSavingsAccount(
                    externalId, gender, customerKycTier.getCode()
            );
            return clientService.getCustomerWalletAccount(externalId, savingsAccountsResponse.getAccountNumber());
        }
    }

    private String createOrRetrieveFlexInvestmentAccount(String externalId, IdentityVerificationResponse.IdentityVerificationResponseData verificationResponse) {
        log.info("Client ID found: {}", externalId);
        List<@Valid AccountDto> clientAccounts = clientService.getAllFlexAccountByExternalId(externalId);
        Optional<@Valid AccountDto> savingsAccount = clientAccounts.stream().findAny();
        log.info("Savings account found: {}", savingsAccount);

        if (savingsAccount.isPresent()) {
            return savingsAccount.get().getAccountNumber();
        } else {
            FlexInvestmentCreationRequest flexInvestmentCreationRequest = new FlexInvestmentCreationRequest();
            flexInvestmentCreationRequest.setExternalId(externalId);
            String gender = verificationResponse.getGender();
            flexInvestmentCreationRequest.setGender(gender);
            FlexInvestmentCreationResponse response = flexDepositAccountService.submitApplication(flexInvestmentCreationRequest);
            return response != null ? response.getAccountNumber() : null;
        }
    }

    private void updateCustomerWithAccountDetails(Customer foundCustomer, String clientId, String savingsAccountNo, String flexAccountNo, String nuban) {
        foundCustomer.setExternalId(clientId);
        foundCustomer.setAccountId(savingsAccountNo);
        foundCustomer.setActive(true);
        foundCustomer.setFlexAccountId(flexAccountNo);
        foundCustomer.setNuban(nuban);
    }

    private void updateCustomerDetails(Customer foundCustomer, CustomerKycRequest customerKycRequest,
                                       Long customerId, CustomerKycTier customerKycTier) {
        boolean customerIsACorporateUser = foundCustomer.getUserType() == CORPORATE;

        CustomerUpdateRequest customerUpdateRequest = buildUpdateRequest(customerKycRequest, customerKycTier, customerIsACorporateUser);
        customerService.updateCustomer(customerUpdateRequest, customerId, foundCustomer, !customerIsACorporateUser);
    }

    private CustomerUpdateRequest buildUpdateRequest(CustomerKycRequest customerKycRequest, CustomerKycTier customerKycTier, boolean isCorpoarate) {
        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest();

        if (customerKycRequest != null && !isCorpoarate) {
            customerUpdateRequest.setBvn(customerKycRequest.getBvn());
            customerUpdateRequest.setNin(customerKycRequest.getNin());
        }

        if (customerKycTier != null) {
            customerUpdateRequest.setKycTier(customerKycTier.getCode());
            customerUpdateRequest.setTin(customerKycRequest.getTin());
            customerUpdateRequest.setRcNumber(customerKycRequest.getRcNumber());
        }

        return customerUpdateRequest;
    }

    private CustomerKycTier getCustomerKycTier(Customer foundCustomer, CustomerKycRequest customerKycRequest) {
        CustomerKycTier customerKycTier = CustomerKycTier.INVALID;
        if (StringUtils.isAllBlank(foundCustomer.getNin(), foundCustomer.getBvn())) {
            throw new ValidationException("invalid.kyc.tier", "Unable to determine KYC tier for the customer.");
        }

        if (StringUtils.isNoneBlank(foundCustomer.getBvn(), foundCustomer.getNin())) {
            customerKycTier = CustomerKycTier.TIER_2;
        } else if (StringUtils.isNotBlank(foundCustomer.getBvn()) || StringUtils.isNotBlank(foundCustomer.getNin())) {
            customerKycTier = CustomerKycTier.TIER_1;
        }

        if (customerKycTier == CustomerKycTier.TIER_2) {
            if (foundCustomer.isCorporateUser()) {
                if (StringUtils.isNotBlank(customerKycRequest.getRcNumber()) || StringUtils.isNotBlank(customerKycRequest.getTin())) {
                    customerKycTier = CustomerKycTier.TIER_3;
                }
            }
        }

        if (customerKycTier == CustomerKycTier.INVALID) {
            throw new ValidationException("invalid.kyc.tier", "Unable to determine KYC tier for the customer.");
        }

        return customerKycTier;
    }

    private CreateCustomerRequest buildCreateCustomerRequest(Customer foundCustomer, CustomerKycRequest customerKycRequest, CustomerKycTier customerKycTier, IdentityVerificationResponse.IdentityVerificationResponseData verificationResponse) {
        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setCustomerType(foundCustomer.getUserType());

        if (foundCustomer.getUserType() == CORPORATE) {
            setupCorporateCustomerRequest(createCustomerRequest, foundCustomer);
        } else {
            setupIndividualCustomerRequest(createCustomerRequest);
        }

        setupCommonCustomerFields(createCustomerRequest, foundCustomer, customerKycTier, verificationResponse, customerKycRequest);
        return createCustomerRequest;
    }

    private void setupCorporateCustomerRequest(CreateCustomerRequest createCustomerRequest, Customer foundCustomer) {
        createCustomerRequest.setBusinessName(foundCustomer.getBusinessName());
        createCustomerRequest.setRcNumber(foundCustomer.getRcNumber());
        IndustrySector industrySector = industrySectorRepository.findById(Long.valueOf(foundCustomer.getIndustryId())).orElse(new IndustrySector());
        createCustomerRequest.setBusinessSector(industrySector.getName());
        createCustomerRequest.setCustomerType(CORPORATE);
    }

    private void setupIndividualCustomerRequest(CreateCustomerRequest createCustomerRequest) {
        createCustomerRequest.setCustomerType(RETAIL);
    }

    private void setupCommonCustomerFields(CreateCustomerRequest createCustomerRequest, Customer foundCustomer, CustomerKycTier customerKycTier, IdentityVerificationResponse.IdentityVerificationResponseData verificationResponse, CustomerKycRequest customerKycRequest) {
        createCustomerRequest.setFirstname(foundCustomer.getFirstname());
        createCustomerRequest.setLastname(foundCustomer.getLastname());
        createCustomerRequest.setEmailAddress(foundCustomer.getEmailAddress());
        createCustomerRequest.setPhoneNumber(foundCustomer.getPhoneNumber());
        createCustomerRequest.setGender(verificationResponse.getGender());
        createCustomerRequest.setDateOfBirth(verificationResponse.getDateOfBirth());
        createCustomerRequest.setBvn(customerKycRequest.getBvn());
        createCustomerRequest.setNin(customerKycRequest.getNin());
        if (verificationResponse.getAddress() != null) {
            IdentityVerificationResponse.IdentityVerificationResponseData.Address address = verificationResponse.getAddress();
            PostClientsAddressRequest postClientsAddressRequest = PostClientsAddressRequest.parse(address);
            createCustomerRequest.setAddress(List.of(postClientsAddressRequest));
        }

        if (customerKycTier != null) {
            createCustomerRequest.setKycTier(customerKycTier.getCode());
        }
    }

    private void updateCustomerAddress(Customer foundCustomer, IdentityVerificationResponse.IdentityVerificationResponseData.Address address) {
        List<Address> customerAddress = addressRepository.findByCustomerId(foundCustomer.getId());
        if (!customerAddress.isEmpty()) {
            return;
        }

        Address newAddress = createNewAddress(foundCustomer.getId(), address);
        addressRepository.save(newAddress);
    }

    private Address createNewAddress(Long customerId, IdentityVerificationResponse.IdentityVerificationResponseData.Address address) {
        Address newAddress = new Address();
        newAddress.setCustomerId(customerId);
        newAddress.setType(AddressType.RESIDENTIAL.toString());
        newAddress.setAddressLine(address.getAddressLine());
        newAddress.setTown(address.getTown());
        newAddress.setLga(address.getLga());
        newAddress.setState(address.getState());
        return newAddress;
    }

    private PutClientsClientIdResponse upgradeClientTier(CustomerKycTier customerKycTier, String clientId, Customer foundCustomer, CustomerKycRequest customerKycRequest) {
        CustomerUpdateRequest updateRequest = new CustomerUpdateRequest();
        updateRequest.setKycTier(customerKycTier.getCode());
        updateRequest.setNin(customerKycRequest.getNin());
        updateRequest.setTin(customerKycRequest.getTin());
        return clientService.updateCustomer(updateRequest, clientId, foundCustomer.getUserType());
    }

    private PutDataTableRequest buildDataTableRequest(Customer foundCustomer) {
        PutDataTableRequest request = new PutDataTableRequest();
        request.setBvn(foundCustomer.getBvn());
        request.setNin(foundCustomer.getNin());
        request.setFirstName(foundCustomer.getFirstname());
        request.setLastName(foundCustomer.getLastname());
        request.setEmailAddress(foundCustomer.getEmailAddress());
        request.setMobileNumber(foundCustomer.getPhoneNumber());
        request.setLocale(DEFAULT_LOCALE);
        return request;
    }

    private static void validateEntityClient(GetClientsClientIdResponse client) {
        if (client != null && client.getLegalForm() != null && client.getLegalForm().getId() == 2) {
            throw new ValidationException("validation.error.exists",
                    "This profile is currently linked to a corporate entity and is not permitted to perform any operations.");
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
}
