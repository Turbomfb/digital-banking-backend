/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import lombok.Data;

/** PostLoansLoanIdTransactionsRequest */
@Data
@Schema(name = "PostLoansLoanIdTransactionsRequest", description = "PostLoansLoanIdTransactionsRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoansLoanIdTransactionsRequest {

	private String dateFormat;

	private String externalId;

	private String locale;

	private String note;

	private Integer paymentTypeId;

	private BigDecimal transactionAmount;

	private String transactionDate;

	private String accountNumber;

	private String checkNumber;

	private String routingCode;

	private String receiptNumber;

	private String bankNumber;

	public PostLoansLoanIdTransactionsRequest dateFormat(String dateFormat) {
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

	public PostLoansLoanIdTransactionsRequest externalId(String externalId) {
		this.externalId = externalId;
		return this;
	}

	/**
	 * Get externalId
	 *
	 * @return externalId
	 */
	@Schema(name = "externalId", example = "3e7791ce-aa10-11ec-b909-0242ac120002", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("externalId")
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public PostLoansLoanIdTransactionsRequest locale(String locale) {
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

	public PostLoansLoanIdTransactionsRequest note(String note) {
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

	public PostLoansLoanIdTransactionsRequest paymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
		return this;
	}

	/**
	 * Get paymentTypeId
	 *
	 * @return paymentTypeId
	 */
	@Schema(name = "paymentTypeId", example = "3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("paymentTypeId")
	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public PostLoansLoanIdTransactionsRequest transactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
		return this;
	}

	/**
	 * Get transactionAmount
	 *
	 * @return transactionAmount
	 */
	@Schema(name = "transactionAmount", example = "50000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("transactionAmount")
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public PostLoansLoanIdTransactionsRequest transactionDate(String transactionDate) {
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
		PostLoansLoanIdTransactionsRequest postLoansLoanIdTransactionsRequest = (PostLoansLoanIdTransactionsRequest) o;
		return Objects.equals(this.dateFormat, postLoansLoanIdTransactionsRequest.dateFormat)
				&& Objects.equals(this.externalId, postLoansLoanIdTransactionsRequest.externalId)
				&& Objects.equals(this.locale, postLoansLoanIdTransactionsRequest.locale)
				&& Objects.equals(this.note, postLoansLoanIdTransactionsRequest.note)
				&& Objects.equals(this.paymentTypeId, postLoansLoanIdTransactionsRequest.paymentTypeId)
				&& Objects.equals(this.transactionAmount, postLoansLoanIdTransactionsRequest.transactionAmount)
				&& Objects.equals(this.transactionDate, postLoansLoanIdTransactionsRequest.transactionDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateFormat, externalId, locale, note, paymentTypeId, transactionAmount, transactionDate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoansLoanIdTransactionsRequest {\n");
		sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
		sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
		sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
		sb.append("    note: ").append(toIndentedString(note)).append("\n");
		sb.append("    paymentTypeId: ").append(toIndentedString(paymentTypeId)).append("\n");
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
