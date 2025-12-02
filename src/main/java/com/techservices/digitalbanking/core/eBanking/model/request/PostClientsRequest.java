/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostClientsRequest {

	private String activationDate;
	private Boolean active;

	@Valid
	private List<@Valid PostClientsAddressRequest> address = Collections.emptyList();

	@Valid
	private List<@Valid PostClientsDatatable> datatables = Collections.emptyList();

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
	private String customerType;
	private Long officeId;
	private String nin;
	private String bvn;
	private String pepStatus;
	private String sourceSystem;
	private String gender;
	private String clientTierId;
	private String emailAddress;
	private String businessSector;
	private Long savingsProductId;
	private String savingsAccountNumber;
	private String accountNo;
	private PostClientNonPersonDetails clientNonPersonDetails;
	private Long clientTypeId;
	private Long clientClassificationId;
	private Boolean proofOfAddress;
	private Long merchantId;
	private String alternateAccountNumber;
	private String businessName;
	private String rcNumber;
}
