/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PutClientClientIdAddressesRequest {

	private Long addressId;

	private String street;

	private String addressLine1;

	private String addressLine2;

	private String addressLine3;

	private String city;

	private Integer countryId;

	private Long postalCode;

	private Integer stateProvinceId;

	private Boolean isActive;

	private String locale;
}
