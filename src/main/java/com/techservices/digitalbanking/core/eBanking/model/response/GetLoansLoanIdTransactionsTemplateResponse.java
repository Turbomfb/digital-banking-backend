/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdTransactionsTemplateResponse */
@Schema(name = "GetLoansLoanIdTransactionsTemplateResponse", description = "GetLoansLoanIdTransactionsTemplateResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdTransactionsTemplateResponse {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;

	private GetLoansTotal total;

	private GetLoansTransactionType transactionType;

	public GetLoansLoanIdTransactionsTemplateResponse date(LocalDate date) {

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

	public GetLoansLoanIdTransactionsTemplateResponse total(GetLoansTotal total) {

		this.total = total;
		return this;
	}

	/**
	 * Get total
	 *
	 * @return total
	 */
	@Valid
	@Schema(name = "total", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("total")
	public GetLoansTotal getTotal() {

		return total;
	}

	public void setTotal(GetLoansTotal total) {

		this.total = total;
	}

	public GetLoansLoanIdTransactionsTemplateResponse transactionType(GetLoansTransactionType transactionType) {

		this.transactionType = transactionType;
		return this;
	}

	/**
	 * Get transactionType
	 *
	 * @return transactionType
	 */
	@Valid
	@Schema(name = "transactionType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("transactionType")
	public GetLoansTransactionType getTransactionType() {

		return transactionType;
	}

	public void setTransactionType(GetLoansTransactionType transactionType) {

		this.transactionType = transactionType;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansLoanIdTransactionsTemplateResponse getLoansLoanIdTransactionsTemplateResponse = (GetLoansLoanIdTransactionsTemplateResponse) o;
		return Objects.equals(this.date, getLoansLoanIdTransactionsTemplateResponse.date)
				&& Objects.equals(this.total, getLoansLoanIdTransactionsTemplateResponse.total)
				&& Objects.equals(this.transactionType, getLoansLoanIdTransactionsTemplateResponse.transactionType);
	}

	@Override
	public int hashCode() {

		return Objects.hash(date, total, transactionType);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdTransactionsTemplateResponse {\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    total: ").append(toIndentedString(total)).append("\n");
		sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
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
