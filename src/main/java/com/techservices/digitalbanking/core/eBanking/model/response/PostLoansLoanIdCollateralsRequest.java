/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PostLoansLoanIdCollateralsRequest */
@Schema(name = "PostLoansLoanIdCollateralsRequest", description = "PostLoansLoanIdCollateralsRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoansLoanIdCollateralsRequest {

	private Integer collateralTypeId;

	public PostLoansLoanIdCollateralsRequest collateralTypeId(Integer collateralTypeId) {
		this.collateralTypeId = collateralTypeId;
		return this;
	}

	/**
	 * Get collateralTypeId
	 *
	 * @return collateralTypeId
	 */
	@Schema(name = "collateralTypeId", example = "9", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("collateralTypeId")
	public Integer getCollateralTypeId() {
		return collateralTypeId;
	}

	public void setCollateralTypeId(Integer collateralTypeId) {
		this.collateralTypeId = collateralTypeId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostLoansLoanIdCollateralsRequest postLoansLoanIdCollateralsRequest = (PostLoansLoanIdCollateralsRequest) o;
		return Objects.equals(this.collateralTypeId, postLoansLoanIdCollateralsRequest.collateralTypeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(collateralTypeId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoansLoanIdCollateralsRequest {\n");
		sb.append("    collateralTypeId: ").append(toIndentedString(collateralTypeId)).append("\n");
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
