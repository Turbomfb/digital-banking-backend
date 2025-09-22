/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.CustomerDto;
import com.techservices.digitalbanking.core.domain.dto.CustomerFilterDto;
import com.techservices.digitalbanking.core.domain.dto.KycTierDto;
import com.techservices.digitalbanking.core.eBanking.api.ClientApiClientV2;
import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.request.*;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.api.ClientApiClient;
import com.techservices.digitalbanking.core.eBanking.configuration.FineractProperty;
import com.techservices.digitalbanking.core.util.DateUtil;
import com.techservices.digitalbanking.customer.domian.dto.request.ClientKycRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerUpdateRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.DateUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

	private final ClientApiClient clientApiClient;
	private final ClientApiClientV2 clientApiClientV2;
	private final FineractProperty fineractProperty;

	public PostClientsResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
		PostClientsRequest postClientsRequest = this.processCustomerCreation(createCustomerRequest);
		return clientApiClient.create(postClientsRequest);
	}

	private PostClientsRequest processCustomerCreation(CreateCustomerRequest createCustomerRequest) {
		PostClientsRequest postClientsRequest = new PostClientsRequest();
		postClientsRequest.setOfficeId(1L);
		postClientsRequest.setEmailAddress(createCustomerRequest.getEmailAddress());
		postClientsRequest.setActive(true);
		postClientsRequest.setActivationDate(getCurrentDate());
		postClientsRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		postClientsRequest.setLocale("en");
		postClientsRequest.setSavingsProductId(createCustomerRequest.getSavingsProductId());
		postClientsRequest.setAddress(createCustomerRequest.getAddress());
		postClientsRequest.setDatatables(createCustomerRequest.getDatatables());
		postClientsRequest.setDateOfBirth(createCustomerRequest.getDateOfBirth());
		postClientsRequest.setExternalId(createCustomerRequest.getExternalId());
		if (createCustomerRequest.getCustomerType() == UserType.CORPORATE) {
			postClientsRequest.setRcNumber(createCustomerRequest.getRcNumber());
			postClientsRequest.setBusinessName(createCustomerRequest.getBusinessName());
			postClientsRequest.setLegalFormId(2L);
			postClientsRequest.setBusinessSector(createCustomerRequest.getBusinessSector());
			postClientsRequest.setFullname(createCustomerRequest.getBusinessName());
		}
		postClientsRequest.setFirstname(createCustomerRequest.getFirstname());
		postClientsRequest.setLastname(createCustomerRequest.getLastname());
		postClientsRequest.setCustomerType(createCustomerRequest.getCustomerType().name());
		postClientsRequest.setMobileNo(createCustomerRequest.getPhoneNumber());
		postClientsRequest.setGender(createCustomerRequest.getGender());
		postClientsRequest.setGender(createCustomerRequest.getGender());
		postClientsRequest.setNin(createCustomerRequest.getNin());
		postClientsRequest.setBvn(createCustomerRequest.getBvn());
		postClientsRequest.setPepStatus(createCustomerRequest.getPepStatus());
		postClientsRequest.setSourceSystem(createCustomerRequest.getSourceSystem());
		postClientsRequest.setClientTierId(createCustomerRequest.getClientTierId());
		postClientsRequest.setSavingsAccountNumber(createCustomerRequest.getSavingsAccountNumber());
		postClientsRequest.setAccountNo(createCustomerRequest.getAccountNo());
		postClientsRequest.setClientNonPersonDetails(createCustomerRequest.getClientNonPersonDetails());
		postClientsRequest.setClientTypeId(createCustomerRequest.getClientTypeId());
		postClientsRequest.setClientClassificationId(createCustomerRequest.getClientClassificationId());
		postClientsRequest.setProofOfAddress(createCustomerRequest.getProofOfAddress());
		postClientsRequest.setMerchantId(createCustomerRequest.getMerchantId());
		postClientsRequest.setAlternateAccountNumber(createCustomerRequest.getAlternateAccountNumber());
		return postClientsRequest;
	}

	private static PostClientsDatatable getClientKycDatatable(ClientKycRequest clientKyc) {
		return retrieveClientKycData(clientKyc.getValidId(), clientKyc.getBvn(), clientKyc.getUtilityBill(),
				clientKyc.getPassportPhoto(), clientKyc.getCertificateOfRegistration(), clientKyc.getBusinessOwnerId(),
				clientKyc.getIncorporationDocuments(), clientKyc.getTaxIdentificationNumber(),
				clientKyc.getBvnOfDirectorsOrOwners(), clientKyc.getBvnOfSignatoriesOrDirectors(),
				clientKyc.getBoardResolutionForCorporateAccounts(), clientKyc.getMaximumBalance(),
				clientKyc.getMaximumDailyDepositLimit(), clientKyc.getMaximumSingleDepositLimit(),
				clientKyc.getMaximumDailyWithdrawalLimit(), clientKyc.getMaximumSingleWithdrawalLimit());
	}

	private static PostClientsDatatable retrieveClientKycData(String validId, String bvn, String utilityBill,
			String passportPhoto, String certificateOfRegistration, String businessOwnerId,
			String incorporationDocuments, String taxIdentificationNumber, String bvnOfDirectorsOrOwners,
			String bvnOfSignatoriesOrDirectors, String boardResolutionForCorporateAccounts, BigDecimal maximumBalance,
			BigDecimal maximumDailyDepositLimit, BigDecimal maximumSingleDepositLimit,
			BigDecimal maximumDailyWithdrawalLimit, BigDecimal maximumSingleWithdrawalLimit) {
		PostClientsDatatable postClientsDatatable = new PostClientsDatatable();
		postClientsDatatable.setRegisteredTableName("client_kyc");
		Map<String, Object> kycData = new HashMap<String, Object>();
		if (validId != null) {
			kycData.put("valid_id", validId);
		}
		if (bvn != null) {
			kycData.put("bvn", bvn);
		}
		if (utilityBill != null) {
			kycData.put("utility_bill", utilityBill);
		}
		if (passportPhoto != null) {
			kycData.put("passport_photo", passportPhoto);
		}
		if (certificateOfRegistration != null) {
			kycData.put("certificate_of_registration", certificateOfRegistration);
		}
		if (businessOwnerId != null) {
			kycData.put("business_owner_id", businessOwnerId);
		}
		if (incorporationDocuments != null) {
			kycData.put("incorporation_documents", incorporationDocuments);
		}
		if (taxIdentificationNumber != null) {
			kycData.put("tax_identification_number", taxIdentificationNumber);
		}
		if (bvnOfDirectorsOrOwners != null) {
			kycData.put("bvn_of_directors_or_owners", bvnOfDirectorsOrOwners);
		}
		if (bvnOfSignatoriesOrDirectors != null) {
			kycData.put("bvn_of_signatories_or_directors", bvnOfSignatoriesOrDirectors);
		}
		if (boardResolutionForCorporateAccounts != null) {
			kycData.put("board_resolution_for_corporate_accounts", boardResolutionForCorporateAccounts);
		}
		if (maximumBalance != null) {
			kycData.put("maximum_balance", maximumBalance);
		}
		if (maximumDailyDepositLimit != null) {
			kycData.put("maximum_daily_deposit_limit", maximumDailyDepositLimit);
		}
		if (maximumSingleDepositLimit != null) {
			kycData.put("maximum_single_deposit_limit", maximumSingleDepositLimit);
		}
		if (maximumDailyWithdrawalLimit != null) {
			kycData.put("maximum_daily_withdrawal_limit", maximumDailyWithdrawalLimit);
		}
		if (maximumSingleWithdrawalLimit != null) {
			kycData.put("maximum_single_withdrawal_limit", maximumSingleWithdrawalLimit);
		}
		postClientsDatatable.setData(kycData);
		return postClientsDatatable;
	}

	public PutClientsClientIdResponse updateCustomer(CustomerUpdateRequest customerUpdateRequest, String clientId, UserType userType) {
		PutClientsClientIdRequest putClientsClientIdRequest = this.retrieveUpdateRequest(customerUpdateRequest, userType);
		return clientApiClient.updateClient(clientId, putClientsClientIdRequest);
	}

	private PutClientsClientIdRequest retrieveUpdateRequest(CustomerUpdateRequest customerUpdateRequest, UserType userType) {
		PutClientsClientIdRequest putClientsClientIdRequest = new PutClientsClientIdRequest();
		putClientsClientIdRequest.setActivationDate(getCurrentDate());
		putClientsClientIdRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		putClientsClientIdRequest.setLocale(DEFAULT_LOCALE);
		putClientsClientIdRequest.setAddress(customerUpdateRequest.getAddress());
		putClientsClientIdRequest.setDateOfBirth(customerUpdateRequest.getDateOfBirth());
		putClientsClientIdRequest.setExternalId(customerUpdateRequest.getExternalId());
		putClientsClientIdRequest.setFirstname(customerUpdateRequest.getFirstname());
		putClientsClientIdRequest.setLastname(customerUpdateRequest.getLastname());
		putClientsClientIdRequest.setMiddleName(customerUpdateRequest.getMiddleName());
		putClientsClientIdRequest.setFullName(StringUtils.isNotBlank(customerUpdateRequest.getFullName()) ?
				customerUpdateRequest.getFullName() : customerUpdateRequest.getBusinessName());
		putClientsClientIdRequest.setEmailAddress(customerUpdateRequest.getEmailAddress());
		putClientsClientIdRequest.setMobileNo(customerUpdateRequest.getPhoneNumber());
		putClientsClientIdRequest.setNin(customerUpdateRequest.getNin());
		putClientsClientIdRequest.setBvn(customerUpdateRequest.getBvn());
		putClientsClientIdRequest.setKycTier(customerUpdateRequest.getKycTier());
		putClientsClientIdRequest.setBvn(customerUpdateRequest.getBvn());
		putClientsClientIdRequest.setProofOfAddress(customerUpdateRequest.getProofOfAddress());

		putClientsClientIdRequest.setDateFormat(DateUtil.getDefaultDateFormat());
		if (userType == UserType.CORPORATE) {
			putClientsClientIdRequest.setTin(customerUpdateRequest.getTin());
			putClientsClientIdRequest.setRcNumber(customerUpdateRequest.getRcNumber());
			putClientsClientIdRequest.setClientNonPersonDetails(new PostClientNonPersonDetails());
		}
		return putClientsClientIdRequest;
	}

	public GetClientsClientIdResponse getCustomerById(Long customerId) {
		return clientApiClient.retrieveOne(customerId, false);
	}

	public GetClientsClientIdResponse searchClients(String nin, String bvn, String emailAddress, String phoneNumber, UserType userType) {
		return clientApiClient.retrieveAll(null, 5, nin, bvn, emailAddress, phoneNumber)
				.getPageItems()
				.stream()
				.filter(client -> client.getLegalForm().getId() == userType.getId())
				.findAny()
				.orElse(null);
	}

	public GetClientsClientIdAccountsResponse getClientAccountsByClientId(String clientId, String accountType) {
			return clientApiClient.retrieveAssociatedAccounts(clientId, accountType);
	}

	public Set<@Valid GetClientsSavingsAccounts> getClientSavingsAccountsByClientId(String clientId) {
			return clientApiClient.retrieveCustomerSavingsAccounts(clientId);
	}

	public PostClientsClientIdResponse manageCustomer(String command, Long customerId) {
		PostClientsClientIdRequest postClientsClientIdRequest = new PostClientsClientIdRequest();
		if ("activate".equalsIgnoreCase(command)) {
			postClientsClientIdRequest.setActivationDate(getCurrentDate());
		}
		postClientsClientIdRequest.setLocale(DEFAULT_LOCALE);
		postClientsClientIdRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		return clientApiClient.manageClient(customerId, postClientsClientIdRequest, command);
	}

	public FineractPageResponse<KycTierDto> retrieveAllKycTier() {
		List<KycTierDto> kycTierDtos = clientApiClient.retrieveAllKycTier();
        return new FineractPageResponse<>(kycTierDtos);
	}

	public PutClientsClientIdResponse postAClientDataTable(
			String registeredTableName,
			Long clientId,
			PutDataTableRequest putDataTableRequest
	) {
		return clientApiClient.postAClientDataTable(registeredTableName, clientId, putDataTableRequest);
	}

	public PutClientsClientIdResponse updateAClientDataTable(
			String registeredTableName,
			Long clientId,
			Long datatableId,
			PutDataTableRequest putDataTableRequest
	) {
		return clientApiClient.updateAClientDataTable(registeredTableName, clientId, datatableId, putDataTableRequest);
	}

	public List<GetDataTablesResponse> retrieveClientDataTables(
			String registeredTableName,
			Long clientId
	) {
		return clientApiClient.getClientDataTables(registeredTableName, clientId, false);
	}

	public List<CustomerDto> searchCustomer(CustomerFilterDto customerFilterDto) {
		return clientApiClient.searchCustomer(customerFilterDto);
	}
}
