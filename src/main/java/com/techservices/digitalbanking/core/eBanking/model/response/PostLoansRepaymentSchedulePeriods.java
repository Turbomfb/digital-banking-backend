/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** PostLoansRepaymentSchedulePeriods */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoansRepaymentSchedulePeriods {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dueDate;

	private Long feeChargesDue;

	private Long feeChargesOutstanding;

	private Integer period;

	private Long principalDisbursed;

	private Long principalLoanBalanceOutstanding;

	private Long totalActualCostOfLoanForPeriod;

	private Long totalDueForPeriod;

	private Long totalOriginalDueForPeriod;

	private Long totalOutstandingForPeriod;

	private Long totalOverdue;

	public PostLoansRepaymentSchedulePeriods dueDate(LocalDate dueDate) {

		this.dueDate = dueDate;
		return this;
	}

	/**
	 * Get dueDate
	 *
	 * @return dueDate
	 */
	@Valid
	@Schema(name = "dueDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("dueDate")
	public LocalDate getDueDate() {

		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {

		this.dueDate = dueDate;
	}

	public PostLoansRepaymentSchedulePeriods feeChargesDue(Long feeChargesDue) {

		this.feeChargesDue = feeChargesDue;
		return this;
	}

	/**
	 * Get feeChargesDue
	 *
	 * @return feeChargesDue
	 */
	@Schema(name = "feeChargesDue", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesDue")
	public Long getFeeChargesDue() {

		return feeChargesDue;
	}

	public void setFeeChargesDue(Long feeChargesDue) {

		this.feeChargesDue = feeChargesDue;
	}

	public PostLoansRepaymentSchedulePeriods feeChargesOutstanding(Long feeChargesOutstanding) {

		this.feeChargesOutstanding = feeChargesOutstanding;
		return this;
	}

	/**
	 * Get feeChargesOutstanding
	 *
	 * @return feeChargesOutstanding
	 */
	@Schema(name = "feeChargesOutstanding", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesOutstanding")
	public Long getFeeChargesOutstanding() {

		return feeChargesOutstanding;
	}

	public void setFeeChargesOutstanding(Long feeChargesOutstanding) {

		this.feeChargesOutstanding = feeChargesOutstanding;
	}

	public PostLoansRepaymentSchedulePeriods period(Integer period) {

		this.period = period;
		return this;
	}

	/**
	 * Get period
	 *
	 * @return period
	 */
	@Schema(name = "period", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("period")
	public Integer getPeriod() {

		return period;
	}

	public void setPeriod(Integer period) {

		this.period = period;
	}

	public PostLoansRepaymentSchedulePeriods principalDisbursed(Long principalDisbursed) {

		this.principalDisbursed = principalDisbursed;
		return this;
	}

	/**
	 * Get principalDisbursed
	 *
	 * @return principalDisbursed
	 */
	@Schema(name = "principalDisbursed", example = "100000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalDisbursed")
	public Long getPrincipalDisbursed() {

		return principalDisbursed;
	}

	public void setPrincipalDisbursed(Long principalDisbursed) {

		this.principalDisbursed = principalDisbursed;
	}

	public PostLoansRepaymentSchedulePeriods principalLoanBalanceOutstanding(Long principalLoanBalanceOutstanding) {

		this.principalLoanBalanceOutstanding = principalLoanBalanceOutstanding;
		return this;
	}

	/**
	 * Get principalLoanBalanceOutstanding
	 *
	 * @return principalLoanBalanceOutstanding
	 */
	@Schema(name = "principalLoanBalanceOutstanding", example = "100000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalLoanBalanceOutstanding")
	public Long getPrincipalLoanBalanceOutstanding() {

		return principalLoanBalanceOutstanding;
	}

	public void setPrincipalLoanBalanceOutstanding(Long principalLoanBalanceOutstanding) {

		this.principalLoanBalanceOutstanding = principalLoanBalanceOutstanding;
	}

	public PostLoansRepaymentSchedulePeriods totalActualCostOfLoanForPeriod(Long totalActualCostOfLoanForPeriod) {

		this.totalActualCostOfLoanForPeriod = totalActualCostOfLoanForPeriod;
		return this;
	}

	/**
	 * Get totalActualCostOfLoanForPeriod
	 *
	 * @return totalActualCostOfLoanForPeriod
	 */
	@Schema(name = "totalActualCostOfLoanForPeriod", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalActualCostOfLoanForPeriod")
	public Long getTotalActualCostOfLoanForPeriod() {

		return totalActualCostOfLoanForPeriod;
	}

	public void setTotalActualCostOfLoanForPeriod(Long totalActualCostOfLoanForPeriod) {

		this.totalActualCostOfLoanForPeriod = totalActualCostOfLoanForPeriod;
	}

	public PostLoansRepaymentSchedulePeriods totalDueForPeriod(Long totalDueForPeriod) {

		this.totalDueForPeriod = totalDueForPeriod;
		return this;
	}

	/**
	 * Get totalDueForPeriod
	 *
	 * @return totalDueForPeriod
	 */
	@Schema(name = "totalDueForPeriod", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalDueForPeriod")
	public Long getTotalDueForPeriod() {

		return totalDueForPeriod;
	}

	public void setTotalDueForPeriod(Long totalDueForPeriod) {

		this.totalDueForPeriod = totalDueForPeriod;
	}

	public PostLoansRepaymentSchedulePeriods totalOriginalDueForPeriod(Long totalOriginalDueForPeriod) {

		this.totalOriginalDueForPeriod = totalOriginalDueForPeriod;
		return this;
	}

	/**
	 * Get totalOriginalDueForPeriod
	 *
	 * @return totalOriginalDueForPeriod
	 */
	@Schema(name = "totalOriginalDueForPeriod", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalOriginalDueForPeriod")
	public Long getTotalOriginalDueForPeriod() {

		return totalOriginalDueForPeriod;
	}

	public void setTotalOriginalDueForPeriod(Long totalOriginalDueForPeriod) {

		this.totalOriginalDueForPeriod = totalOriginalDueForPeriod;
	}

	public PostLoansRepaymentSchedulePeriods totalOutstandingForPeriod(Long totalOutstandingForPeriod) {

		this.totalOutstandingForPeriod = totalOutstandingForPeriod;
		return this;
	}

	/**
	 * Get totalOutstandingForPeriod
	 *
	 * @return totalOutstandingForPeriod
	 */
	@Schema(name = "totalOutstandingForPeriod", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalOutstandingForPeriod")
	public Long getTotalOutstandingForPeriod() {

		return totalOutstandingForPeriod;
	}

	public void setTotalOutstandingForPeriod(Long totalOutstandingForPeriod) {

		this.totalOutstandingForPeriod = totalOutstandingForPeriod;
	}

	public PostLoansRepaymentSchedulePeriods totalOverdue(Long totalOverdue) {

		this.totalOverdue = totalOverdue;
		return this;
	}

	/**
	 * Get totalOverdue
	 *
	 * @return totalOverdue
	 */
	@Schema(name = "totalOverdue", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalOverdue")
	public Long getTotalOverdue() {

		return totalOverdue;
	}

	public void setTotalOverdue(Long totalOverdue) {

		this.totalOverdue = totalOverdue;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostLoansRepaymentSchedulePeriods postLoansRepaymentSchedulePeriods = (PostLoansRepaymentSchedulePeriods) o;
		return Objects.equals(this.dueDate, postLoansRepaymentSchedulePeriods.dueDate)
				&& Objects.equals(this.feeChargesDue, postLoansRepaymentSchedulePeriods.feeChargesDue)
				&& Objects.equals(this.feeChargesOutstanding, postLoansRepaymentSchedulePeriods.feeChargesOutstanding)
				&& Objects.equals(this.period, postLoansRepaymentSchedulePeriods.period)
				&& Objects.equals(this.principalDisbursed, postLoansRepaymentSchedulePeriods.principalDisbursed)
				&& Objects.equals(this.principalLoanBalanceOutstanding,
						postLoansRepaymentSchedulePeriods.principalLoanBalanceOutstanding)
				&& Objects.equals(this.totalActualCostOfLoanForPeriod,
						postLoansRepaymentSchedulePeriods.totalActualCostOfLoanForPeriod)
				&& Objects.equals(this.totalDueForPeriod, postLoansRepaymentSchedulePeriods.totalDueForPeriod)
				&& Objects.equals(this.totalOriginalDueForPeriod,
						postLoansRepaymentSchedulePeriods.totalOriginalDueForPeriod)
				&& Objects.equals(this.totalOutstandingForPeriod,
						postLoansRepaymentSchedulePeriods.totalOutstandingForPeriod)
				&& Objects.equals(this.totalOverdue, postLoansRepaymentSchedulePeriods.totalOverdue);
	}

	@Override
	public int hashCode() {

		return Objects.hash(dueDate, feeChargesDue, feeChargesOutstanding, period, principalDisbursed,
				principalLoanBalanceOutstanding, totalActualCostOfLoanForPeriod, totalDueForPeriod,
				totalOriginalDueForPeriod, totalOutstandingForPeriod, totalOverdue);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoansRepaymentSchedulePeriods {\n");
		sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
		sb.append("    feeChargesDue: ").append(toIndentedString(feeChargesDue)).append("\n");
		sb.append("    feeChargesOutstanding: ").append(toIndentedString(feeChargesOutstanding)).append("\n");
		sb.append("    period: ").append(toIndentedString(period)).append("\n");
		sb.append("    principalDisbursed: ").append(toIndentedString(principalDisbursed)).append("\n");
		sb.append("    principalLoanBalanceOutstanding: ").append(toIndentedString(principalLoanBalanceOutstanding))
				.append("\n");
		sb.append("    totalActualCostOfLoanForPeriod: ").append(toIndentedString(totalActualCostOfLoanForPeriod))
				.append("\n");
		sb.append("    totalDueForPeriod: ").append(toIndentedString(totalDueForPeriod)).append("\n");
		sb.append("    totalOriginalDueForPeriod: ").append(toIndentedString(totalOriginalDueForPeriod)).append("\n");
		sb.append("    totalOutstandingForPeriod: ").append(toIndentedString(totalOutstandingForPeriod)).append("\n");
		sb.append("    totalOverdue: ").append(toIndentedString(totalOverdue)).append("\n");
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
