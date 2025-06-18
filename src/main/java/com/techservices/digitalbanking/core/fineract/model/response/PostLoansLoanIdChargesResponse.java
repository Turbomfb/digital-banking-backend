/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PostLoansLoanIdChargesResponse */
@Schema(name = "PostLoansLoanIdChargesResponse", description = " PostLoansLoanIdChargesResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoansLoanIdChargesResponse {

	private Long clientId;

	private Long loanId;

	private Long officeId;

	private Integer resourceId;

	public PostLoansLoanIdChargesResponse clientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}

	/**
	 * Get clientId
	 *
	 * @return clientId
	 */
	@Schema(name = "clientId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("clientId")
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public PostLoansLoanIdChargesResponse loanId(Long loanId) {
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
	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public PostLoansLoanIdChargesResponse officeId(Long officeId) {
		this.officeId = officeId;
		return this;
	}

	/**
	 * Get officeId
	 *
	 * @return officeId
	 */
	@Schema(name = "officeId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("officeId")
	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public PostLoansLoanIdChargesResponse resourceId(Integer resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	/**
	 * Get resourceId
	 *
	 * @return resourceId
	 */
	@Schema(name = "resourceId", example = "31", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
		PostLoansLoanIdChargesResponse postLoansLoanIdChargesResponse = (PostLoansLoanIdChargesResponse) o;
		return Objects.equals(this.clientId, postLoansLoanIdChargesResponse.clientId)
				&& Objects.equals(this.loanId, postLoansLoanIdChargesResponse.loanId)
				&& Objects.equals(this.officeId, postLoansLoanIdChargesResponse.officeId)
				&& Objects.equals(this.resourceId, postLoansLoanIdChargesResponse.resourceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, loanId, officeId, resourceId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoansLoanIdChargesResponse {\n");
		sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
		sb.append("    loanId: ").append(toIndentedString(loanId)).append("\n");
		sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
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
