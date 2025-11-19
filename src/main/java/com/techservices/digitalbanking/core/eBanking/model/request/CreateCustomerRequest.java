/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCustomerRequest {

	private String activationDate;
	private Boolean active;

	private String dateFormat;
	private String dateOfBirth;
	private String externalId;
	private String firstname;
	private Integer groupId;
	private String lastname;
	private String fullname;
	private Long legalFormId;
	private String locale;
	private String mobileNo;
	private String emailAddress;
	private Long officeId;
	private String nin;
	private String bvn;
	private String pepStatus;
	private String sourceSystem;
	private String clientTierId;
	private Long savingsProductId;
	private String savingsAccountNumber;
	private String accountNo;
	private Long clientTypeId;
	private Long clientClassificationId;
	private Boolean proofOfAddress;
	private Long merchantId;
	private String alternateAccountNumber;
	private String customerType;

	// Corporate customer details
	private String remarks;
	private Long constitutionId;
	private String incorpNumber;
	private Long mainBusinessLineId;
	private String incorpValidityTillDate;
}
