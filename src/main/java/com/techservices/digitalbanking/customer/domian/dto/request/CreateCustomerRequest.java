/* (C)2024 */
package com.techservices.digitalbanking.customer.domian.dto.request;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.eBanking.model.request.PostClientNonPersonDetails;
import com.techservices.digitalbanking.core.eBanking.model.request.PostClientsAddressRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostClientsDatatable;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.ToString;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CreateCustomerRequest {
	private String uniqueId;
	private String otp;
	@Valid
	private List<@Valid PostClientsAddressRequest> address = Collections.emptyList();

	@Valid
	private List<@Valid PostClientsDatatable> datatables = Collections.emptyList();

	private String dateOfBirth;
	private String externalId;
	private String nin;
	private String bvn;
	private String pepStatus;
	private String sourceSystem;
	private String clientTierId;
	private String savingsAccountNumber;
	private String accountNo;
	private PostClientNonPersonDetails clientNonPersonDetails;
	private Long clientTypeId;
	private Long clientClassificationId;
	private Boolean proofOfAddress;
	private Long merchantId;
	private String alternateAccountNumber;
	private String transactionPin;

	private Long savingsProductId;

	private String phoneNumber;
	private String gender;
	private Long kycTier;
	private String emailAddress;
	private String firstname;
	private String lastname;
	private String referralCode;
  private String password;
  private boolean isOtpValidated;

	private String businessName;
	private Long industryId;
	private String businessSector;
	private String rcNumber;
	private UserType customerType;
}
