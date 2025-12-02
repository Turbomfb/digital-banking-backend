/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PostSelfLoansResponse */
@Schema(name = "PostSelfLoansResponse", description = "PostSelfLoansResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostSelfLoansResponse {

	private Integer clientId;

	private Integer loanId;

	private Integer officeId;

	private Integer resourceId;

	public PostSelfLoansResponse clientId(Integer clientId) {

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

	public PostSelfLoansResponse loanId(Integer loanId) {

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

	public PostSelfLoansResponse officeId(Integer officeId) {

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

	public PostSelfLoansResponse resourceId(Integer resourceId) {

		this.resourceId = resourceId;
		return this;
	}

	/**
	 * Get resourceId
	 *
	 * @return resourceId
	 */
	@Schema(name = "resourceId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
		PostSelfLoansResponse postSelfLoansResponse = (PostSelfLoansResponse) o;
		return Objects.equals(this.clientId, postSelfLoansResponse.clientId)
				&& Objects.equals(this.loanId, postSelfLoansResponse.loanId)
				&& Objects.equals(this.officeId, postSelfLoansResponse.officeId)
				&& Objects.equals(this.resourceId, postSelfLoansResponse.resourceId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(clientId, loanId, officeId, resourceId);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PostSelfLoansResponse {\n");
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
