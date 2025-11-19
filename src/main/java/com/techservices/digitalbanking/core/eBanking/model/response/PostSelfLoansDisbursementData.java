/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PostSelfLoansDisbursementData */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostSelfLoansDisbursementData {

	private Long approvedPrincipal;

	private String expectedDisbursementDate;

	private Long principal;

	public PostSelfLoansDisbursementData approvedPrincipal(Long approvedPrincipal) {

		this.approvedPrincipal = approvedPrincipal;
		return this;
	}

	/**
	 * Get approvedPrincipal
	 *
	 * @return approvedPrincipal
	 */
	@Schema(name = "approvedPrincipal", example = "22000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("approvedPrincipal")
	public Long getApprovedPrincipal() {

		return approvedPrincipal;
	}

	public void setApprovedPrincipal(Long approvedPrincipal) {

		this.approvedPrincipal = approvedPrincipal;
	}

	public PostSelfLoansDisbursementData expectedDisbursementDate(String expectedDisbursementDate) {

		this.expectedDisbursementDate = expectedDisbursementDate;
		return this;
	}

	/**
	 * Get expectedDisbursementDate
	 *
	 * @return expectedDisbursementDate
	 */
	@Schema(name = "expectedDisbursementDate", example = "01 November 2013", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("expectedDisbursementDate")
	public String getExpectedDisbursementDate() {

		return expectedDisbursementDate;
	}

	public void setExpectedDisbursementDate(String expectedDisbursementDate) {

		this.expectedDisbursementDate = expectedDisbursementDate;
	}

	public PostSelfLoansDisbursementData principal(Long principal) {

		this.principal = principal;
		return this;
	}

	/**
	 * Get principal
	 *
	 * @return principal
	 */
	@Schema(name = "principal", example = "22000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principal")
	public Long getPrincipal() {

		return principal;
	}

	public void setPrincipal(Long principal) {

		this.principal = principal;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostSelfLoansDisbursementData postSelfLoansDisbursementData = (PostSelfLoansDisbursementData) o;
		return Objects.equals(this.approvedPrincipal, postSelfLoansDisbursementData.approvedPrincipal)
				&& Objects.equals(this.expectedDisbursementDate, postSelfLoansDisbursementData.expectedDisbursementDate)
				&& Objects.equals(this.principal, postSelfLoansDisbursementData.principal);
	}

	@Override
	public int hashCode() {

		return Objects.hash(approvedPrincipal, expectedDisbursementDate, principal);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PostSelfLoansDisbursementData {\n");
		sb.append("    approvedPrincipal: ").append(toIndentedString(approvedPrincipal)).append("\n");
		sb.append("    expectedDisbursementDate: ").append(toIndentedString(expectedDisbursementDate)).append("\n");
		sb.append("    principal: ").append(toIndentedString(principal)).append("\n");
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
