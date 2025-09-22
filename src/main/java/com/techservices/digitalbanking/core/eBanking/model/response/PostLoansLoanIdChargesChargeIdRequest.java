/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PostLoansLoanIdChargesChargeIdRequest */
@Schema(name = "PostLoansLoanIdChargesChargeIdRequest", description = "PostLoansLoanIdChargesChargeIdRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoansLoanIdChargesChargeIdRequest {

	private String dateFormat;

	private String locale;

	private String transactionDate;

	public PostLoansLoanIdChargesChargeIdRequest dateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
		return this;
	}

	/**
	 * Get dateFormat
	 *
	 * @return dateFormat
	 */
	@Schema(name = "dateFormat", example = "dd MMMM yyyy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("dateFormat")
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public PostLoansLoanIdChargesChargeIdRequest locale(String locale) {
		this.locale = locale;
		return this;
	}

	/**
	 * Get locale
	 *
	 * @return locale
	 */
	@Schema(name = "locale", example = "en", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("locale")
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public PostLoansLoanIdChargesChargeIdRequest transactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
		return this;
	}

	/**
	 * Get transactionDate
	 *
	 * @return transactionDate
	 */
	@Schema(name = "transactionDate", example = "19 September 2013", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("transactionDate")
	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostLoansLoanIdChargesChargeIdRequest postLoansLoanIdChargesChargeIdRequest = (PostLoansLoanIdChargesChargeIdRequest) o;
		return Objects.equals(this.dateFormat, postLoansLoanIdChargesChargeIdRequest.dateFormat)
				&& Objects.equals(this.locale, postLoansLoanIdChargesChargeIdRequest.locale)
				&& Objects.equals(this.transactionDate, postLoansLoanIdChargesChargeIdRequest.transactionDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateFormat, locale, transactionDate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoansLoanIdChargesChargeIdRequest {\n");
		sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
		sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
		sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
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
