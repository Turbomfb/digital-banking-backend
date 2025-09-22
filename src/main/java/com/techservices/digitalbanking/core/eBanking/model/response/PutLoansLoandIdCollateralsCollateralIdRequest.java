/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PutLoansLoandIdCollateralsCollateralIdRequest */
@Schema(name = "PutLoansLoandIdCollateralsCollateralIdRequest", description = "PutLoansLoandIdCollateralsCollateralIdRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PutLoansLoandIdCollateralsCollateralIdRequest {

	private String description;

	public PutLoansLoandIdCollateralsCollateralIdRequest description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 */
	@Schema(name = "description", example = "22 Carat Gold chain weighing 12 grams", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PutLoansLoandIdCollateralsCollateralIdRequest putLoansLoandIdCollateralsCollateralIdRequest = (PutLoansLoandIdCollateralsCollateralIdRequest) o;
		return Objects.equals(this.description, putLoansLoandIdCollateralsCollateralIdRequest.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(description);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PutLoansLoandIdCollateralsCollateralIdRequest {\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
