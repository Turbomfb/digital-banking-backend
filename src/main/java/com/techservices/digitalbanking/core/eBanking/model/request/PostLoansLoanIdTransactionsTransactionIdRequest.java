/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PostLoansLoanIdTransactionsTransactionIdRequest */
@Schema(name = "PostLoansLoanIdTransactionsTransactionIdRequest", description = "PostLoansLoanIdTransactionsTransactionIdRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoansLoanIdTransactionsTransactionIdRequest {

	private String dateFormat;

	private String locale;

	private String note;

	private Double transactionAmount;

	private String transactionDate;

	public PostLoansLoanIdTransactionsTransactionIdRequest dateFormat(String dateFormat) {

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

	public PostLoansLoanIdTransactionsTransactionIdRequest locale(String locale) {

		this.locale = locale;
		return this;
	}

	/**
	 * Get locale
	 *
	 * @return locale
	 */
	@Schema(name = "locale", example = "en_GB", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("locale")
	public String getLocale() {

		return locale;
	}

	public void setLocale(String locale) {

		this.locale = locale;
	}

	public PostLoansLoanIdTransactionsTransactionIdRequest note(String note) {

		this.note = note;
		return this;
	}

	/**
	 * Get note
	 *
	 * @return note
	 */
	@Schema(name = "note", example = "An optional note about why your adjusting or changing the transaction.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("note")
	public String getNote() {

		return note;
	}

	public void setNote(String note) {

		this.note = note;
	}

	public PostLoansLoanIdTransactionsTransactionIdRequest transactionAmount(Double transactionAmount) {

		this.transactionAmount = transactionAmount;
		return this;
	}

	/**
	 * Get transactionAmount
	 *
	 * @return transactionAmount
	 */
	@Schema(name = "transactionAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("transactionAmount")
	public Double getTransactionAmount() {

		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {

		this.transactionAmount = transactionAmount;
	}

	public PostLoansLoanIdTransactionsTransactionIdRequest transactionDate(String transactionDate) {

		this.transactionDate = transactionDate;
		return this;
	}

	/**
	 * Get transactionDate
	 *
	 * @return transactionDate
	 */
	@Schema(name = "transactionDate", example = "28 June 2022", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
		PostLoansLoanIdTransactionsTransactionIdRequest postLoansLoanIdTransactionsTransactionIdRequest = (PostLoansLoanIdTransactionsTransactionIdRequest) o;
		return Objects.equals(this.dateFormat, postLoansLoanIdTransactionsTransactionIdRequest.dateFormat)
				&& Objects.equals(this.locale, postLoansLoanIdTransactionsTransactionIdRequest.locale)
				&& Objects.equals(this.note, postLoansLoanIdTransactionsTransactionIdRequest.note)
				&& Objects.equals(this.transactionAmount,
						postLoansLoanIdTransactionsTransactionIdRequest.transactionAmount)
				&& Objects.equals(this.transactionDate,
						postLoansLoanIdTransactionsTransactionIdRequest.transactionDate);
	}

	@Override
	public int hashCode() {

		return Objects.hash(dateFormat, locale, note, transactionAmount, transactionDate);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoansLoanIdTransactionsTransactionIdRequest {\n");
		sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
		sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
		sb.append("    note: ").append(toIndentedString(note)).append("\n");
		sb.append("    transactionAmount: ").append(toIndentedString(transactionAmount)).append("\n");
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
