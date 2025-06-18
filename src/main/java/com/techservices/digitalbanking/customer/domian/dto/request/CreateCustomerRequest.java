/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.domian.dto.request;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientNonPersonDetails;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientsAddressRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientsDatatable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCustomerRequest {
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

	private Long savingsProductId;

	@NotEmpty(message = "password cannot be empty")
	private String password;
	@NotEmpty(message = "phoneNumber cannot be empty")
	private String phoneNumber;
	@NotEmpty(message = "emailAddress cannot be empty")
	private String emailAddress;
	@NotEmpty(message = "firstname cannot be empty")
	private String firstname;
	@NotEmpty(message = "lastname cannot be empty")
	private String lastname;
	private String referralCode;
}
