/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.domian.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientNonPersonDetails;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientsAddressRequest;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerKycRequest {
	@JsonProperty("nin")
	private String nin;

	@JsonProperty("bvn")
	private String bvn;

	private String uniqueId;
	private String otp;
	private String base64Image;
}
