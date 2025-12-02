/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdTransactionsTransactionIdResponse */
@Schema(name = "GetLoansLoanIdTransactionsTransactionIdResponse", description = "GetLoansLoanIdTransactionsTransactionIdResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdTransactionsTransactionIdResponse {

	private Double amount;

	private GetLoansCurrency currency;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;

	private Integer id;

	private Double interestPortion;

	private Boolean manuallyReversed;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate submittedOnDate;

	private GetLoansType type;

	public GetLoansLoanIdTransactionsTransactionIdResponse amount(Double amount) {

		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 */
	@Schema(name = "amount", example = "559.88", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amount")
	public Double getAmount() {

		return amount;
	}

	public void setAmount(Double amount) {

		this.amount = amount;
	}

	public GetLoansLoanIdTransactionsTransactionIdResponse currency(GetLoansCurrency currency) {

		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 */
	@Valid
	@Schema(name = "currency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("currency")
	public GetLoansCurrency getCurrency() {

		return currency;
	}

	public void setCurrency(GetLoansCurrency currency) {

		this.currency = currency;
	}

	public GetLoansLoanIdTransactionsTransactionIdResponse date(LocalDate date) {

		this.date = date;
		return this;
	}

	/**
	 * Get date
	 *
	 * @return date
	 */
	@Valid
	@Schema(name = "date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("date")
	public LocalDate getDate() {

		return date;
	}

	public void setDate(LocalDate date) {

		this.date = date;
	}

	public GetLoansLoanIdTransactionsTransactionIdResponse id(Integer id) {

		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public GetLoansLoanIdTransactionsTransactionIdResponse interestPortion(Double interestPortion) {

		this.interestPortion = interestPortion;
		return this;
	}

	/**
	 * Get interestPortion
	 *
	 * @return interestPortion
	 */
	@Schema(name = "interestPortion", example = "559.88", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestPortion")
	public Double getInterestPortion() {

		return interestPortion;
	}

	public void setInterestPortion(Double interestPortion) {

		this.interestPortion = interestPortion;
	}

	public GetLoansLoanIdTransactionsTransactionIdResponse manuallyReversed(Boolean manuallyReversed) {

		this.manuallyReversed = manuallyReversed;
		return this;
	}

	/**
	 * Get manuallyReversed
	 *
	 * @return manuallyReversed
	 */
	@Schema(name = "manuallyReversed", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("manuallyReversed")
	public Boolean getManuallyReversed() {

		return manuallyReversed;
	}

	public void setManuallyReversed(Boolean manuallyReversed) {

		this.manuallyReversed = manuallyReversed;
	}

	public GetLoansLoanIdTransactionsTransactionIdResponse submittedOnDate(LocalDate submittedOnDate) {

		this.submittedOnDate = submittedOnDate;
		return this;
	}

	/**
	 * Get submittedOnDate
	 *
	 * @return submittedOnDate
	 */
	@Valid
	@Schema(name = "submittedOnDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("submittedOnDate")
	public LocalDate getSubmittedOnDate() {

		return submittedOnDate;
	}

	public void setSubmittedOnDate(LocalDate submittedOnDate) {

		this.submittedOnDate = submittedOnDate;
	}

	public GetLoansLoanIdTransactionsTransactionIdResponse type(GetLoansType type) {

		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 */
	@Valid
	@Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("type")
	public GetLoansType getType() {

		return type;
	}

	public void setType(GetLoansType type) {

		this.type = type;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansLoanIdTransactionsTransactionIdResponse getLoansLoanIdTransactionsTransactionIdResponse = (GetLoansLoanIdTransactionsTransactionIdResponse) o;
		return Objects.equals(this.amount, getLoansLoanIdTransactionsTransactionIdResponse.amount)
				&& Objects.equals(this.currency, getLoansLoanIdTransactionsTransactionIdResponse.currency)
				&& Objects.equals(this.date, getLoansLoanIdTransactionsTransactionIdResponse.date)
				&& Objects.equals(this.id, getLoansLoanIdTransactionsTransactionIdResponse.id)
				&& Objects.equals(this.interestPortion, getLoansLoanIdTransactionsTransactionIdResponse.interestPortion)
				&& Objects.equals(this.manuallyReversed,
						getLoansLoanIdTransactionsTransactionIdResponse.manuallyReversed)
				&& Objects.equals(this.submittedOnDate, getLoansLoanIdTransactionsTransactionIdResponse.submittedOnDate)
				&& Objects.equals(this.type, getLoansLoanIdTransactionsTransactionIdResponse.type);
	}

	@Override
	public int hashCode() {

		return Objects.hash(amount, currency, date, id, interestPortion, manuallyReversed, submittedOnDate, type);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdTransactionsTransactionIdResponse {\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    interestPortion: ").append(toIndentedString(interestPortion)).append("\n");
		sb.append("    manuallyReversed: ").append(toIndentedString(manuallyReversed)).append("\n");
		sb.append("    submittedOnDate: ").append(toIndentedString(submittedOnDate)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
