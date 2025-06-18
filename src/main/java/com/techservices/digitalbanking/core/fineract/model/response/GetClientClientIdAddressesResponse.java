/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientClientIdAddressesResponse {

	@JsonProperty("clientID")
	private Long clientID;

	private String addressType;
	private Long addressId;
	private Long addressTypeId;

	@JsonProperty("isActive")
	private boolean isActive;

	private String street;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String townVillage;
	private String city;
	private String countyDistrict;
	private Long stateProvinceId;
	private String countryName;
	private String stateName;
	private Long countryId;
	private String postalCode;
	private double latitude;
	private double longitude;
	private String createdBy;
	private List<Integer> createdOn;
	private String updatedBy;
	private List<Integer> updatedOn;
}
