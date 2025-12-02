/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** LoanProductTrancheDetails */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class LoanProductTrancheDetails {

	private Boolean multiDisburseLoan;

	public LoanProductTrancheDetails multiDisburseLoan(Boolean multiDisburseLoan) {

		this.multiDisburseLoan = multiDisburseLoan;
		return this;
	}

	/**
	 * Get multiDisburseLoan
	 *
	 * @return multiDisburseLoan
	 */
	@Schema(name = "multiDisburseLoan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("multiDisburseLoan")
	public Boolean getMultiDisburseLoan() {

		return multiDisburseLoan;
	}

	public void setMultiDisburseLoan(Boolean multiDisburseLoan) {

		this.multiDisburseLoan = multiDisburseLoan;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoanProductTrancheDetails loanProductTrancheDetails = (LoanProductTrancheDetails) o;
		return Objects.equals(this.multiDisburseLoan, loanProductTrancheDetails.multiDisburseLoan);
	}

	@Override
	public int hashCode() {

		return Objects.hash(multiDisburseLoan);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class LoanProductTrancheDetails {\n");
		sb.append("    multiDisburseLoan: ").append(toIndentedString(multiDisburseLoan)).append("\n");
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
