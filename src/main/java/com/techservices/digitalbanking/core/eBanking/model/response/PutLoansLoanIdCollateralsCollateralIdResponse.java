/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** PutLoansLoanIdCollateralsCollateralIdResponse */
@Schema(name = "PutLoansLoanIdCollateralsCollateralIdResponse", description = "PutLoansLoanIdCollateralsCollateralIdResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PutLoansLoanIdCollateralsCollateralIdResponse {

	private PutLoansLoandIdCollateralsCollateralIdRequest changes;

	private Integer loanId;

	private Integer resourceId;

	public PutLoansLoanIdCollateralsCollateralIdResponse changes(
			PutLoansLoandIdCollateralsCollateralIdRequest changes) {
		this.changes = changes;
		return this;
	}

	/**
	 * Get changes
	 *
	 * @return changes
	 */
	@Valid
	@Schema(name = "changes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("changes")
	public PutLoansLoandIdCollateralsCollateralIdRequest getChanges() {
		return changes;
	}

	public void setChanges(PutLoansLoandIdCollateralsCollateralIdRequest changes) {
		this.changes = changes;
	}

	public PutLoansLoanIdCollateralsCollateralIdResponse loanId(Integer loanId) {
		this.loanId = loanId;
		return this;
	}

	/**
	 * Get loanId
	 *
	 * @return loanId
	 */
	@Schema(name = "loanId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("loanId")
	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public PutLoansLoanIdCollateralsCollateralIdResponse resourceId(Integer resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	/**
	 * Get resourceId
	 *
	 * @return resourceId
	 */
	@Schema(name = "resourceId", example = "12", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("resourceId")
	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PutLoansLoanIdCollateralsCollateralIdResponse putLoansLoanIdCollateralsCollateralIdResponse = (PutLoansLoanIdCollateralsCollateralIdResponse) o;
		return Objects.equals(this.changes, putLoansLoanIdCollateralsCollateralIdResponse.changes)
				&& Objects.equals(this.loanId, putLoansLoanIdCollateralsCollateralIdResponse.loanId)
				&& Objects.equals(this.resourceId, putLoansLoanIdCollateralsCollateralIdResponse.resourceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(changes, loanId, resourceId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PutLoansLoanIdCollateralsCollateralIdResponse {\n");
		sb.append("    changes: ").append(toIndentedString(changes)).append("\n");
		sb.append("    loanId: ").append(toIndentedString(loanId)).append("\n");
		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
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
