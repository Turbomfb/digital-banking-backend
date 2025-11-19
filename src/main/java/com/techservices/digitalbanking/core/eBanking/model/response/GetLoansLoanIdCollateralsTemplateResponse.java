/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdCollateralsTemplateResponse */
@Schema(name = "GetLoansLoanIdCollateralsTemplateResponse", description = "GetLoansLoanIdCollateralsTemplateResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdCollateralsTemplateResponse {

	@Valid
	private Set<@Valid Object> allowedCollateralTypes = new LinkedHashSet<>();

	public GetLoansLoanIdCollateralsTemplateResponse allowedCollateralTypes(Set<@Valid Object> allowedCollateralTypes) {

		this.allowedCollateralTypes = allowedCollateralTypes;
		return this;
	}

	public GetLoansLoanIdCollateralsTemplateResponse addAllowedCollateralTypesItem(Object allowedCollateralTypesItem) {

		if (this.allowedCollateralTypes == null) {
			this.allowedCollateralTypes = new LinkedHashSet<>();
		}
		this.allowedCollateralTypes.add(allowedCollateralTypesItem);
		return this;
	}

	/**
	 * Get allowedCollateralTypes
	 *
	 * @return allowedCollateralTypes
	 */
	@Valid
	@Schema(name = "allowedCollateralTypes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("allowedCollateralTypes")
	public Set<@Valid Object> getAllowedCollateralTypes() {

		return allowedCollateralTypes;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setAllowedCollateralTypes(Set<@Valid Object> allowedCollateralTypes) {

		this.allowedCollateralTypes = allowedCollateralTypes;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansLoanIdCollateralsTemplateResponse getLoansLoanIdCollateralsTemplateResponse = (GetLoansLoanIdCollateralsTemplateResponse) o;
		return Objects.equals(this.allowedCollateralTypes,
				getLoansLoanIdCollateralsTemplateResponse.allowedCollateralTypes);
	}

	@Override
	public int hashCode() {

		return Objects.hash(allowedCollateralTypes);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdCollateralsTemplateResponse {\n");
		sb.append("    allowedCollateralTypes: ").append(toIndentedString(allowedCollateralTypes)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {

		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
