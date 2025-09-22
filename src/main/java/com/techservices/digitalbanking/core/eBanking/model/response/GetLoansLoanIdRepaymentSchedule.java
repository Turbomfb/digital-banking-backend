/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdRepaymentSchedule */
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdRepaymentSchedule {

	private GetLoansLoanIdCurrency currency;

	private Long loanTermInDays;

	@Valid
	private Set<@Valid GetLoansLoanIdRepaymentPeriod> periods = new LinkedHashSet<>();

	private Double totalFeeChargesCharged;

	private Double totalInterestCharged;

	private Double totalOutstanding;

	private Double totalPaidInAdvance;

	private Double totalPaidLate;

	private Double totalPenaltyChargesCharged;

	private Double totalPrincipalDisbursed;

	private Double totalPrincipalExpected;

	private Double totalPrincipalPaid;

	private Double totalRepaymentExpected;

	private Double totalWaived;

	private Double totalWrittenOff;

	public GetLoansLoanIdRepaymentSchedule currency(GetLoansLoanIdCurrency currency) {
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
	public GetLoansLoanIdCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(GetLoansLoanIdCurrency currency) {
		this.currency = currency;
	}

	public GetLoansLoanIdRepaymentSchedule loanTermInDays(Long loanTermInDays) {
		this.loanTermInDays = loanTermInDays;
		return this;
	}

	/**
	 * Get loanTermInDays
	 *
	 * @return loanTermInDays
	 */
	@Schema(name = "loanTermInDays", example = "30", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("loanTermInDays")
	public Long getLoanTermInDays() {
		return loanTermInDays;
	}

	public void setLoanTermInDays(Long loanTermInDays) {
		this.loanTermInDays = loanTermInDays;
	}

	public GetLoansLoanIdRepaymentSchedule periods(Set<@Valid GetLoansLoanIdRepaymentPeriod> periods) {
		this.periods = periods;
		return this;
	}

	public GetLoansLoanIdRepaymentSchedule addPeriodsItem(GetLoansLoanIdRepaymentPeriod periodsItem) {
		if (this.periods == null) {
			this.periods = new LinkedHashSet<>();
		}
		this.periods.add(periodsItem);
		return this;
	}

	/**
	 * Get periods
	 *
	 * @return periods
	 */
	@Valid
	@Schema(name = "periods", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("periods")
	public Set<@Valid GetLoansLoanIdRepaymentPeriod> getPeriods() {
		return periods;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setPeriods(Set<@Valid GetLoansLoanIdRepaymentPeriod> periods) {
		this.periods = periods;
	}

	public GetLoansLoanIdRepaymentSchedule totalFeeChargesCharged(Double totalFeeChargesCharged) {
		this.totalFeeChargesCharged = totalFeeChargesCharged;
		return this;
	}

	/**
	 * Get totalFeeChargesCharged
	 *
	 * @return totalFeeChargesCharged
	 */
	@Schema(name = "totalFeeChargesCharged", example = "0.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalFeeChargesCharged")
	public Double getTotalFeeChargesCharged() {
		return totalFeeChargesCharged;
	}

	public void setTotalFeeChargesCharged(Double totalFeeChargesCharged) {
		this.totalFeeChargesCharged = totalFeeChargesCharged;
	}

	public GetLoansLoanIdRepaymentSchedule totalInterestCharged(Double totalInterestCharged) {
		this.totalInterestCharged = totalInterestCharged;
		return this;
	}

	/**
	 * Get totalInterestCharged
	 *
	 * @return totalInterestCharged
	 */
	@Schema(name = "totalInterestCharged", example = "0.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalInterestCharged")
	public Double getTotalInterestCharged() {
		return totalInterestCharged;
	}

	public void setTotalInterestCharged(Double totalInterestCharged) {
		this.totalInterestCharged = totalInterestCharged;
	}

	public GetLoansLoanIdRepaymentSchedule totalOutstanding(Double totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
		return this;
	}

	/**
	 * Get totalOutstanding
	 *
	 * @return totalOutstanding
	 */
	@Schema(name = "totalOutstanding", example = "0.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalOutstanding")
	public Double getTotalOutstanding() {
		return totalOutstanding;
	}

	public void setTotalOutstanding(Double totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

	public GetLoansLoanIdRepaymentSchedule totalPaidInAdvance(Double totalPaidInAdvance) {
		this.totalPaidInAdvance = totalPaidInAdvance;
		return this;
	}

	/**
	 * Get totalPaidInAdvance
	 *
	 * @return totalPaidInAdvance
	 */
	@Schema(name = "totalPaidInAdvance", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPaidInAdvance")
	public Double getTotalPaidInAdvance() {
		return totalPaidInAdvance;
	}

	public void setTotalPaidInAdvance(Double totalPaidInAdvance) {
		this.totalPaidInAdvance = totalPaidInAdvance;
	}

	public GetLoansLoanIdRepaymentSchedule totalPaidLate(Double totalPaidLate) {
		this.totalPaidLate = totalPaidLate;
		return this;
	}

	/**
	 * Get totalPaidLate
	 *
	 * @return totalPaidLate
	 */
	@Schema(name = "totalPaidLate", example = "0.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPaidLate")
	public Double getTotalPaidLate() {
		return totalPaidLate;
	}

	public void setTotalPaidLate(Double totalPaidLate) {
		this.totalPaidLate = totalPaidLate;
	}

	public GetLoansLoanIdRepaymentSchedule totalPenaltyChargesCharged(Double totalPenaltyChargesCharged) {
		this.totalPenaltyChargesCharged = totalPenaltyChargesCharged;
		return this;
	}

	/**
	 * Get totalPenaltyChargesCharged
	 *
	 * @return totalPenaltyChargesCharged
	 */
	@Schema(name = "totalPenaltyChargesCharged", example = "0.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPenaltyChargesCharged")
	public Double getTotalPenaltyChargesCharged() {
		return totalPenaltyChargesCharged;
	}

	public void setTotalPenaltyChargesCharged(Double totalPenaltyChargesCharged) {
		this.totalPenaltyChargesCharged = totalPenaltyChargesCharged;
	}

	public GetLoansLoanIdRepaymentSchedule totalPrincipalDisbursed(Double totalPrincipalDisbursed) {
		this.totalPrincipalDisbursed = totalPrincipalDisbursed;
		return this;
	}

	/**
	 * Get totalPrincipalDisbursed
	 *
	 * @return totalPrincipalDisbursed
	 */
	@Schema(name = "totalPrincipalDisbursed", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPrincipalDisbursed")
	public Double getTotalPrincipalDisbursed() {
		return totalPrincipalDisbursed;
	}

	public void setTotalPrincipalDisbursed(Double totalPrincipalDisbursed) {
		this.totalPrincipalDisbursed = totalPrincipalDisbursed;
	}

	public GetLoansLoanIdRepaymentSchedule totalPrincipalExpected(Double totalPrincipalExpected) {
		this.totalPrincipalExpected = totalPrincipalExpected;
		return this;
	}

	/**
	 * Get totalPrincipalExpected
	 *
	 * @return totalPrincipalExpected
	 */
	@Schema(name = "totalPrincipalExpected", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPrincipalExpected")
	public Double getTotalPrincipalExpected() {
		return totalPrincipalExpected;
	}

	public void setTotalPrincipalExpected(Double totalPrincipalExpected) {
		this.totalPrincipalExpected = totalPrincipalExpected;
	}

	public GetLoansLoanIdRepaymentSchedule totalPrincipalPaid(Double totalPrincipalPaid) {
		this.totalPrincipalPaid = totalPrincipalPaid;
		return this;
	}

	/**
	 * Get totalPrincipalPaid
	 *
	 * @return totalPrincipalPaid
	 */
	@Schema(name = "totalPrincipalPaid", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPrincipalPaid")
	public Double getTotalPrincipalPaid() {
		return totalPrincipalPaid;
	}

	public void setTotalPrincipalPaid(Double totalPrincipalPaid) {
		this.totalPrincipalPaid = totalPrincipalPaid;
	}

	public GetLoansLoanIdRepaymentSchedule totalRepaymentExpected(Double totalRepaymentExpected) {
		this.totalRepaymentExpected = totalRepaymentExpected;
		return this;
	}

	/**
	 * Get totalRepaymentExpected
	 *
	 * @return totalRepaymentExpected
	 */
	@Schema(name = "totalRepaymentExpected", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalRepaymentExpected")
	public Double getTotalRepaymentExpected() {
		return totalRepaymentExpected;
	}

	public void setTotalRepaymentExpected(Double totalRepaymentExpected) {
		this.totalRepaymentExpected = totalRepaymentExpected;
	}

	public GetLoansLoanIdRepaymentSchedule totalWaived(Double totalWaived) {
		this.totalWaived = totalWaived;
		return this;
	}

	/**
	 * Get totalWaived
	 *
	 * @return totalWaived
	 */
	@Schema(name = "totalWaived", example = "0.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalWaived")
	public Double getTotalWaived() {
		return totalWaived;
	}

	public void setTotalWaived(Double totalWaived) {
		this.totalWaived = totalWaived;
	}

	public GetLoansLoanIdRepaymentSchedule totalWrittenOff(Double totalWrittenOff) {
		this.totalWrittenOff = totalWrittenOff;
		return this;
	}

	/**
	 * Get totalWrittenOff
	 *
	 * @return totalWrittenOff
	 */
	@Schema(name = "totalWrittenOff", example = "0.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalWrittenOff")
	public Double getTotalWrittenOff() {
		return totalWrittenOff;
	}

	public void setTotalWrittenOff(Double totalWrittenOff) {
		this.totalWrittenOff = totalWrittenOff;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansLoanIdRepaymentSchedule getLoansLoanIdRepaymentSchedule = (GetLoansLoanIdRepaymentSchedule) o;
		return Objects.equals(this.currency, getLoansLoanIdRepaymentSchedule.currency)
				&& Objects.equals(this.loanTermInDays, getLoansLoanIdRepaymentSchedule.loanTermInDays)
				&& Objects.equals(this.periods, getLoansLoanIdRepaymentSchedule.periods)
				&& Objects.equals(this.totalFeeChargesCharged, getLoansLoanIdRepaymentSchedule.totalFeeChargesCharged)
				&& Objects.equals(this.totalInterestCharged, getLoansLoanIdRepaymentSchedule.totalInterestCharged)
				&& Objects.equals(this.totalOutstanding, getLoansLoanIdRepaymentSchedule.totalOutstanding)
				&& Objects.equals(this.totalPaidInAdvance, getLoansLoanIdRepaymentSchedule.totalPaidInAdvance)
				&& Objects.equals(this.totalPaidLate, getLoansLoanIdRepaymentSchedule.totalPaidLate)
				&& Objects.equals(this.totalPenaltyChargesCharged,
						getLoansLoanIdRepaymentSchedule.totalPenaltyChargesCharged)
				&& Objects.equals(this.totalPrincipalDisbursed, getLoansLoanIdRepaymentSchedule.totalPrincipalDisbursed)
				&& Objects.equals(this.totalPrincipalExpected, getLoansLoanIdRepaymentSchedule.totalPrincipalExpected)
				&& Objects.equals(this.totalPrincipalPaid, getLoansLoanIdRepaymentSchedule.totalPrincipalPaid)
				&& Objects.equals(this.totalRepaymentExpected, getLoansLoanIdRepaymentSchedule.totalRepaymentExpected)
				&& Objects.equals(this.totalWaived, getLoansLoanIdRepaymentSchedule.totalWaived)
				&& Objects.equals(this.totalWrittenOff, getLoansLoanIdRepaymentSchedule.totalWrittenOff);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, loanTermInDays, periods, totalFeeChargesCharged, totalInterestCharged,
				totalOutstanding, totalPaidInAdvance, totalPaidLate, totalPenaltyChargesCharged,
				totalPrincipalDisbursed, totalPrincipalExpected, totalPrincipalPaid, totalRepaymentExpected,
				totalWaived, totalWrittenOff);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdRepaymentSchedule {\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    loanTermInDays: ").append(toIndentedString(loanTermInDays)).append("\n");
		sb.append("    periods: ").append(toIndentedString(periods)).append("\n");
		sb.append("    totalFeeChargesCharged: ").append(toIndentedString(totalFeeChargesCharged)).append("\n");
		sb.append("    totalInterestCharged: ").append(toIndentedString(totalInterestCharged)).append("\n");
		sb.append("    totalOutstanding: ").append(toIndentedString(totalOutstanding)).append("\n");
		sb.append("    totalPaidInAdvance: ").append(toIndentedString(totalPaidInAdvance)).append("\n");
		sb.append("    totalPaidLate: ").append(toIndentedString(totalPaidLate)).append("\n");
		sb.append("    totalPenaltyChargesCharged: ").append(toIndentedString(totalPenaltyChargesCharged)).append("\n");
		sb.append("    totalPrincipalDisbursed: ").append(toIndentedString(totalPrincipalDisbursed)).append("\n");
		sb.append("    totalPrincipalExpected: ").append(toIndentedString(totalPrincipalExpected)).append("\n");
		sb.append("    totalPrincipalPaid: ").append(toIndentedString(totalPrincipalPaid)).append("\n");
		sb.append("    totalRepaymentExpected: ").append(toIndentedString(totalRepaymentExpected)).append("\n");
		sb.append("    totalWaived: ").append(toIndentedString(totalWaived)).append("\n");
		sb.append("    totalWrittenOff: ").append(toIndentedString(totalWrittenOff)).append("\n");
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
