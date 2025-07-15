/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import lombok.ToString;

/** PostLoansLoanIdTransactionsResponse */
@Schema(name = "PostLoansLoanIdTransactionsResponse", description = "PostLoansLoanIdTransactionsResponse")
@ToString
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoansLoanIdTransactionsResponse {

	private Integer clientId;

	private Integer officeId;

	private Integer resourceId;

	public PostLoansLoanIdTransactionsResponse clientId(Integer clientId) {
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
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public PostLoansLoanIdTransactionsResponse officeId(Integer officeId) {
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
	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public PostLoansLoanIdTransactionsResponse resourceId(Integer resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	/**
	 * Get resourceId
	 *
	 * @return resourceId
	 */
	@Schema(name = "resourceId", example = "22", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
		PostLoansLoanIdTransactionsResponse postLoansLoanIdTransactionsResponse = (PostLoansLoanIdTransactionsResponse) o;
		return Objects.equals(this.clientId, postLoansLoanIdTransactionsResponse.clientId)
				&& Objects.equals(this.officeId, postLoansLoanIdTransactionsResponse.officeId)
				&& Objects.equals(this.resourceId, postLoansLoanIdTransactionsResponse.resourceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, officeId, resourceId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoansLoanIdTransactionsResponse {\n");
		sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
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
