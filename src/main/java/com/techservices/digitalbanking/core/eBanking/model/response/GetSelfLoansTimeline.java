/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetSelfLoansTimeline */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetSelfLoansTimeline {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate expectedDisbursementDate;

	public GetSelfLoansTimeline expectedDisbursementDate(LocalDate expectedDisbursementDate) {
		this.expectedDisbursementDate = expectedDisbursementDate;
		return this;
	}

	/**
	 * Get expectedDisbursementDate
	 *
	 * @return expectedDisbursementDate
	 */
	@Valid
	@Schema(name = "expectedDisbursementDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("expectedDisbursementDate")
	public LocalDate getExpectedDisbursementDate() {
		return expectedDisbursementDate;
	}

	public void setExpectedDisbursementDate(LocalDate expectedDisbursementDate) {
		this.expectedDisbursementDate = expectedDisbursementDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetSelfLoansTimeline getSelfLoansTimeline = (GetSelfLoansTimeline) o;
		return Objects.equals(this.expectedDisbursementDate, getSelfLoansTimeline.expectedDisbursementDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(expectedDisbursementDate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetSelfLoansTimeline {\n");
		sb.append("    expectedDisbursementDate: ").append(toIndentedString(expectedDisbursementDate)).append("\n");
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
