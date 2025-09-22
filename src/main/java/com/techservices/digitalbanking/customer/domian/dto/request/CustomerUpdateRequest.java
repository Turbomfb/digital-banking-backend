/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.domian.dto.request;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techservices.digitalbanking.core.eBanking.model.request.PostClientsAddressRequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerUpdateRequest {
	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("middlename")
	private String middleName;

	@JsonProperty("fullname")
	private String fullName;

	@JsonProperty("emailAddress")
	private String emailAddress;

	@JsonProperty("bvn")
	private String bvn;

	@JsonProperty("nin")
	private String nin;

	private String tin;

	@JsonProperty("externalId")
	private String externalId;

	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	private String rcNumber;
	private String businessName;

	private Long kycTier;

	private Boolean proofOfAddress;


	@Valid
	private List<@Valid PostClientsAddressRequest> address = Collections.emptyList();
}
