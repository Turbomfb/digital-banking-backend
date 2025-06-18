/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdRepaymentPeriod */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdRepaymentPeriod {

	private Boolean complete;

	private Long daysInPeriod;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dueDate;

	private Long feeChargesDue;

	private Long feeChargesOutstanding;

	private Long feeChargesPaid;

	private Long feeChargesWaived;

	private Long feeChargesWrittenOff;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fromDate;

	private Long interestDue;

	private Long interestOriginalDue;

	private Long interestOutstanding;

	private Long interestPaid;

	private Long interestWaived;

	private Long interestWrittenOff;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate obligationsMetOnDate;

	private Long penaltyChargesDue;

	private Long penaltyChargesOutstanding;

	private Long penaltyChargesPaid;

	private Long penaltyChargesWaived;

	private Long penaltyChargesWrittenOff;

	private Integer period;

	private Double principalDue;

	private Double principalLoanBalanceOutstanding;

	private Double principalOriginalDue;

	private Double principalOutstanding;

	private Double principalPaid;

	private Long principalWrittenOff;

	private Long totalActualCostOfLoanForPeriod;

	private Double totalDueForPeriod;

	private Double totalInstallmentAmountForPeriod;

	private Double totalOriginalDueForPeriod;

	private Double totalOutstandingForPeriod;

	private Double totalPaidForPeriod;

	private Double totalPaidInAdvanceForPeriod;

	private Long totalPaidLateForPeriod;

	private Long totalWaivedForPeriod;

	private Long totalWrittenOffForPeriod;

	public GetLoansLoanIdRepaymentPeriod complete(Boolean complete) {
		this.complete = complete;
		return this;
	}

	/**
	 * Get complete
	 *
	 * @return complete
	 */
	@Schema(name = "complete", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("complete")
	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public GetLoansLoanIdRepaymentPeriod daysInPeriod(Long daysInPeriod) {
		this.daysInPeriod = daysInPeriod;
		return this;
	}

	/**
	 * Get daysInPeriod
	 *
	 * @return daysInPeriod
	 */
	@Schema(name = "daysInPeriod", example = "30", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("daysInPeriod")
	public Long getDaysInPeriod() {
		return daysInPeriod;
	}

	public void setDaysInPeriod(Long daysInPeriod) {
		this.daysInPeriod = daysInPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod dueDate(LocalDate dueDate) {
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

	public GetLoansLoanIdRepaymentPeriod feeChargesDue(Long feeChargesDue) {
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

	public GetLoansLoanIdRepaymentPeriod feeChargesOutstanding(Long feeChargesOutstanding) {
		this.feeChargesOutstanding = feeChargesOutstanding;
		return this;
	}

	/**
	 * Get feeChargesOutstanding
	 *
	 * @return feeChargesOutstanding
	 */
	@Schema(name = "feeChargesOutstanding", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesOutstanding")
	public Long getFeeChargesOutstanding() {
		return feeChargesOutstanding;
	}

	public void setFeeChargesOutstanding(Long feeChargesOutstanding) {
		this.feeChargesOutstanding = feeChargesOutstanding;
	}

	public GetLoansLoanIdRepaymentPeriod feeChargesPaid(Long feeChargesPaid) {
		this.feeChargesPaid = feeChargesPaid;
		return this;
	}

	/**
	 * Get feeChargesPaid
	 *
	 * @return feeChargesPaid
	 */
	@Schema(name = "feeChargesPaid", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesPaid")
	public Long getFeeChargesPaid() {
		return feeChargesPaid;
	}

	public void setFeeChargesPaid(Long feeChargesPaid) {
		this.feeChargesPaid = feeChargesPaid;
	}

	public GetLoansLoanIdRepaymentPeriod feeChargesWaived(Long feeChargesWaived) {
		this.feeChargesWaived = feeChargesWaived;
		return this;
	}

	/**
	 * Get feeChargesWaived
	 *
	 * @return feeChargesWaived
	 */
	@Schema(name = "feeChargesWaived", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesWaived")
	public Long getFeeChargesWaived() {
		return feeChargesWaived;
	}

	public void setFeeChargesWaived(Long feeChargesWaived) {
		this.feeChargesWaived = feeChargesWaived;
	}

	public GetLoansLoanIdRepaymentPeriod feeChargesWrittenOff(Long feeChargesWrittenOff) {
		this.feeChargesWrittenOff = feeChargesWrittenOff;
		return this;
	}

	/**
	 * Get feeChargesWrittenOff
	 *
	 * @return feeChargesWrittenOff
	 */
	@Schema(name = "feeChargesWrittenOff", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesWrittenOff")
	public Long getFeeChargesWrittenOff() {
		return feeChargesWrittenOff;
	}

	public void setFeeChargesWrittenOff(Long feeChargesWrittenOff) {
		this.feeChargesWrittenOff = feeChargesWrittenOff;
	}

	public GetLoansLoanIdRepaymentPeriod fromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
		return this;
	}

	/**
	 * Get fromDate
	 *
	 * @return fromDate
	 */
	@Valid
	@Schema(name = "fromDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("fromDate")
	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public GetLoansLoanIdRepaymentPeriod interestDue(Long interestDue) {
		this.interestDue = interestDue;
		return this;
	}

	/**
	 * Get interestDue
	 *
	 * @return interestDue
	 */
	@Schema(name = "interestDue", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestDue")
	public Long getInterestDue() {
		return interestDue;
	}

	public void setInterestDue(Long interestDue) {
		this.interestDue = interestDue;
	}

	public GetLoansLoanIdRepaymentPeriod interestOriginalDue(Long interestOriginalDue) {
		this.interestOriginalDue = interestOriginalDue;
		return this;
	}

	/**
	 * Get interestOriginalDue
	 *
	 * @return interestOriginalDue
	 */
	@Schema(name = "interestOriginalDue", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestOriginalDue")
	public Long getInterestOriginalDue() {
		return interestOriginalDue;
	}

	public void setInterestOriginalDue(Long interestOriginalDue) {
		this.interestOriginalDue = interestOriginalDue;
	}

	public GetLoansLoanIdRepaymentPeriod interestOutstanding(Long interestOutstanding) {
		this.interestOutstanding = interestOutstanding;
		return this;
	}

	/**
	 * Get interestOutstanding
	 *
	 * @return interestOutstanding
	 */
	@Schema(name = "interestOutstanding", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestOutstanding")
	public Long getInterestOutstanding() {
		return interestOutstanding;
	}

	public void setInterestOutstanding(Long interestOutstanding) {
		this.interestOutstanding = interestOutstanding;
	}

	public GetLoansLoanIdRepaymentPeriod interestPaid(Long interestPaid) {
		this.interestPaid = interestPaid;
		return this;
	}

	/**
	 * Get interestPaid
	 *
	 * @return interestPaid
	 */
	@Schema(name = "interestPaid", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestPaid")
	public Long getInterestPaid() {
		return interestPaid;
	}

	public void setInterestPaid(Long interestPaid) {
		this.interestPaid = interestPaid;
	}

	public GetLoansLoanIdRepaymentPeriod interestWaived(Long interestWaived) {
		this.interestWaived = interestWaived;
		return this;
	}

	/**
	 * Get interestWaived
	 *
	 * @return interestWaived
	 */
	@Schema(name = "interestWaived", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestWaived")
	public Long getInterestWaived() {
		return interestWaived;
	}

	public void setInterestWaived(Long interestWaived) {
		this.interestWaived = interestWaived;
	}

	public GetLoansLoanIdRepaymentPeriod interestWrittenOff(Long interestWrittenOff) {
		this.interestWrittenOff = interestWrittenOff;
		return this;
	}

	/**
	 * Get interestWrittenOff
	 *
	 * @return interestWrittenOff
	 */
	@Schema(name = "interestWrittenOff", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestWrittenOff")
	public Long getInterestWrittenOff() {
		return interestWrittenOff;
	}

	public void setInterestWrittenOff(Long interestWrittenOff) {
		this.interestWrittenOff = interestWrittenOff;
	}

	public GetLoansLoanIdRepaymentPeriod obligationsMetOnDate(LocalDate obligationsMetOnDate) {
		this.obligationsMetOnDate = obligationsMetOnDate;
		return this;
	}

	/**
	 * Get obligationsMetOnDate
	 *
	 * @return obligationsMetOnDate
	 */
	@Valid
	@Schema(name = "obligationsMetOnDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("obligationsMetOnDate")
	public LocalDate getObligationsMetOnDate() {
		return obligationsMetOnDate;
	}

	public void setObligationsMetOnDate(LocalDate obligationsMetOnDate) {
		this.obligationsMetOnDate = obligationsMetOnDate;
	}

	public GetLoansLoanIdRepaymentPeriod penaltyChargesDue(Long penaltyChargesDue) {
		this.penaltyChargesDue = penaltyChargesDue;
		return this;
	}

	/**
	 * Get penaltyChargesDue
	 *
	 * @return penaltyChargesDue
	 */
	@Schema(name = "penaltyChargesDue", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesDue")
	public Long getPenaltyChargesDue() {
		return penaltyChargesDue;
	}

	public void setPenaltyChargesDue(Long penaltyChargesDue) {
		this.penaltyChargesDue = penaltyChargesDue;
	}

	public GetLoansLoanIdRepaymentPeriod penaltyChargesOutstanding(Long penaltyChargesOutstanding) {
		this.penaltyChargesOutstanding = penaltyChargesOutstanding;
		return this;
	}

	/**
	 * Get penaltyChargesOutstanding
	 *
	 * @return penaltyChargesOutstanding
	 */
	@Schema(name = "penaltyChargesOutstanding", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesOutstanding")
	public Long getPenaltyChargesOutstanding() {
		return penaltyChargesOutstanding;
	}

	public void setPenaltyChargesOutstanding(Long penaltyChargesOutstanding) {
		this.penaltyChargesOutstanding = penaltyChargesOutstanding;
	}

	public GetLoansLoanIdRepaymentPeriod penaltyChargesPaid(Long penaltyChargesPaid) {
		this.penaltyChargesPaid = penaltyChargesPaid;
		return this;
	}

	/**
	 * Get penaltyChargesPaid
	 *
	 * @return penaltyChargesPaid
	 */
	@Schema(name = "penaltyChargesPaid", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesPaid")
	public Long getPenaltyChargesPaid() {
		return penaltyChargesPaid;
	}

	public void setPenaltyChargesPaid(Long penaltyChargesPaid) {
		this.penaltyChargesPaid = penaltyChargesPaid;
	}

	public GetLoansLoanIdRepaymentPeriod penaltyChargesWaived(Long penaltyChargesWaived) {
		this.penaltyChargesWaived = penaltyChargesWaived;
		return this;
	}

	/**
	 * Get penaltyChargesWaived
	 *
	 * @return penaltyChargesWaived
	 */
	@Schema(name = "penaltyChargesWaived", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesWaived")
	public Long getPenaltyChargesWaived() {
		return penaltyChargesWaived;
	}

	public void setPenaltyChargesWaived(Long penaltyChargesWaived) {
		this.penaltyChargesWaived = penaltyChargesWaived;
	}

	public GetLoansLoanIdRepaymentPeriod penaltyChargesWrittenOff(Long penaltyChargesWrittenOff) {
		this.penaltyChargesWrittenOff = penaltyChargesWrittenOff;
		return this;
	}

	/**
	 * Get penaltyChargesWrittenOff
	 *
	 * @return penaltyChargesWrittenOff
	 */
	@Schema(name = "penaltyChargesWrittenOff", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesWrittenOff")
	public Long getPenaltyChargesWrittenOff() {
		return penaltyChargesWrittenOff;
	}

	public void setPenaltyChargesWrittenOff(Long penaltyChargesWrittenOff) {
		this.penaltyChargesWrittenOff = penaltyChargesWrittenOff;
	}

	public GetLoansLoanIdRepaymentPeriod period(Integer period) {
		this.period = period;
		return this;
	}

	/**
	 * Get period
	 *
	 * @return period
	 */
	@Schema(name = "period", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("period")
	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public GetLoansLoanIdRepaymentPeriod principalDue(Double principalDue) {
		this.principalDue = principalDue;
		return this;
	}

	/**
	 * Get principalDue
	 *
	 * @return principalDue
	 */
	@Schema(name = "principalDue", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalDue")
	public Double getPrincipalDue() {
		return principalDue;
	}

	public void setPrincipalDue(Double principalDue) {
		this.principalDue = principalDue;
	}

	public GetLoansLoanIdRepaymentPeriod principalLoanBalanceOutstanding(Double principalLoanBalanceOutstanding) {
		this.principalLoanBalanceOutstanding = principalLoanBalanceOutstanding;
		return this;
	}

	/**
	 * Get principalLoanBalanceOutstanding
	 *
	 * @return principalLoanBalanceOutstanding
	 */
	@Schema(name = "principalLoanBalanceOutstanding", example = "20.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalLoanBalanceOutstanding")
	public Double getPrincipalLoanBalanceOutstanding() {
		return principalLoanBalanceOutstanding;
	}

	public void setPrincipalLoanBalanceOutstanding(Double principalLoanBalanceOutstanding) {
		this.principalLoanBalanceOutstanding = principalLoanBalanceOutstanding;
	}

	public GetLoansLoanIdRepaymentPeriod principalOriginalDue(Double principalOriginalDue) {
		this.principalOriginalDue = principalOriginalDue;
		return this;
	}

	/**
	 * Get principalOriginalDue
	 *
	 * @return principalOriginalDue
	 */
	@Schema(name = "principalOriginalDue", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalOriginalDue")
	public Double getPrincipalOriginalDue() {
		return principalOriginalDue;
	}

	public void setPrincipalOriginalDue(Double principalOriginalDue) {
		this.principalOriginalDue = principalOriginalDue;
	}

	public GetLoansLoanIdRepaymentPeriod principalOutstanding(Double principalOutstanding) {
		this.principalOutstanding = principalOutstanding;
		return this;
	}

	/**
	 * Get principalOutstanding
	 *
	 * @return principalOutstanding
	 */
	@Schema(name = "principalOutstanding", example = "20.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalOutstanding")
	public Double getPrincipalOutstanding() {
		return principalOutstanding;
	}

	public void setPrincipalOutstanding(Double principalOutstanding) {
		this.principalOutstanding = principalOutstanding;
	}

	public GetLoansLoanIdRepaymentPeriod principalPaid(Double principalPaid) {
		this.principalPaid = principalPaid;
		return this;
	}

	/**
	 * Get principalPaid
	 *
	 * @return principalPaid
	 */
	@Schema(name = "principalPaid", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalPaid")
	public Double getPrincipalPaid() {
		return principalPaid;
	}

	public void setPrincipalPaid(Double principalPaid) {
		this.principalPaid = principalPaid;
	}

	public GetLoansLoanIdRepaymentPeriod principalWrittenOff(Long principalWrittenOff) {
		this.principalWrittenOff = principalWrittenOff;
		return this;
	}

	/**
	 * Get principalWrittenOff
	 *
	 * @return principalWrittenOff
	 */
	@Schema(name = "principalWrittenOff", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalWrittenOff")
	public Long getPrincipalWrittenOff() {
		return principalWrittenOff;
	}

	public void setPrincipalWrittenOff(Long principalWrittenOff) {
		this.principalWrittenOff = principalWrittenOff;
	}

	public GetLoansLoanIdRepaymentPeriod totalActualCostOfLoanForPeriod(Long totalActualCostOfLoanForPeriod) {
		this.totalActualCostOfLoanForPeriod = totalActualCostOfLoanForPeriod;
		return this;
	}

	/**
	 * Get totalActualCostOfLoanForPeriod
	 *
	 * @return totalActualCostOfLoanForPeriod
	 */
	@Schema(name = "totalActualCostOfLoanForPeriod", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalActualCostOfLoanForPeriod")
	public Long getTotalActualCostOfLoanForPeriod() {
		return totalActualCostOfLoanForPeriod;
	}

	public void setTotalActualCostOfLoanForPeriod(Long totalActualCostOfLoanForPeriod) {
		this.totalActualCostOfLoanForPeriod = totalActualCostOfLoanForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalDueForPeriod(Double totalDueForPeriod) {
		this.totalDueForPeriod = totalDueForPeriod;
		return this;
	}

	/**
	 * Get totalDueForPeriod
	 *
	 * @return totalDueForPeriod
	 */
	@Schema(name = "totalDueForPeriod", example = "20.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalDueForPeriod")
	public Double getTotalDueForPeriod() {
		return totalDueForPeriod;
	}

	public void setTotalDueForPeriod(Double totalDueForPeriod) {
		this.totalDueForPeriod = totalDueForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalInstallmentAmountForPeriod(Double totalInstallmentAmountForPeriod) {
		this.totalInstallmentAmountForPeriod = totalInstallmentAmountForPeriod;
		return this;
	}

	/**
	 * Get totalInstallmentAmountForPeriod
	 *
	 * @return totalInstallmentAmountForPeriod
	 */
	@Schema(name = "totalInstallmentAmountForPeriod", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalInstallmentAmountForPeriod")
	public Double getTotalInstallmentAmountForPeriod() {
		return totalInstallmentAmountForPeriod;
	}

	public void setTotalInstallmentAmountForPeriod(Double totalInstallmentAmountForPeriod) {
		this.totalInstallmentAmountForPeriod = totalInstallmentAmountForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalOriginalDueForPeriod(Double totalOriginalDueForPeriod) {
		this.totalOriginalDueForPeriod = totalOriginalDueForPeriod;
		return this;
	}

	/**
	 * Get totalOriginalDueForPeriod
	 *
	 * @return totalOriginalDueForPeriod
	 */
	@Schema(name = "totalOriginalDueForPeriod", example = "20.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalOriginalDueForPeriod")
	public Double getTotalOriginalDueForPeriod() {
		return totalOriginalDueForPeriod;
	}

	public void setTotalOriginalDueForPeriod(Double totalOriginalDueForPeriod) {
		this.totalOriginalDueForPeriod = totalOriginalDueForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalOutstandingForPeriod(Double totalOutstandingForPeriod) {
		this.totalOutstandingForPeriod = totalOutstandingForPeriod;
		return this;
	}

	/**
	 * Get totalOutstandingForPeriod
	 *
	 * @return totalOutstandingForPeriod
	 */
	@Schema(name = "totalOutstandingForPeriod", example = "200.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalOutstandingForPeriod")
	public Double getTotalOutstandingForPeriod() {
		return totalOutstandingForPeriod;
	}

	public void setTotalOutstandingForPeriod(Double totalOutstandingForPeriod) {
		this.totalOutstandingForPeriod = totalOutstandingForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalPaidForPeriod(Double totalPaidForPeriod) {
		this.totalPaidForPeriod = totalPaidForPeriod;
		return this;
	}

	/**
	 * Get totalPaidForPeriod
	 *
	 * @return totalPaidForPeriod
	 */
	@Schema(name = "totalPaidForPeriod", example = "20.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPaidForPeriod")
	public Double getTotalPaidForPeriod() {
		return totalPaidForPeriod;
	}

	public void setTotalPaidForPeriod(Double totalPaidForPeriod) {
		this.totalPaidForPeriod = totalPaidForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalPaidInAdvanceForPeriod(Double totalPaidInAdvanceForPeriod) {
		this.totalPaidInAdvanceForPeriod = totalPaidInAdvanceForPeriod;
		return this;
	}

	/**
	 * Get totalPaidInAdvanceForPeriod
	 *
	 * @return totalPaidInAdvanceForPeriod
	 */
	@Schema(name = "totalPaidInAdvanceForPeriod", example = "20.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPaidInAdvanceForPeriod")
	public Double getTotalPaidInAdvanceForPeriod() {
		return totalPaidInAdvanceForPeriod;
	}

	public void setTotalPaidInAdvanceForPeriod(Double totalPaidInAdvanceForPeriod) {
		this.totalPaidInAdvanceForPeriod = totalPaidInAdvanceForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalPaidLateForPeriod(Long totalPaidLateForPeriod) {
		this.totalPaidLateForPeriod = totalPaidLateForPeriod;
		return this;
	}

	/**
	 * Get totalPaidLateForPeriod
	 *
	 * @return totalPaidLateForPeriod
	 */
	@Schema(name = "totalPaidLateForPeriod", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalPaidLateForPeriod")
	public Long getTotalPaidLateForPeriod() {
		return totalPaidLateForPeriod;
	}

	public void setTotalPaidLateForPeriod(Long totalPaidLateForPeriod) {
		this.totalPaidLateForPeriod = totalPaidLateForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalWaivedForPeriod(Long totalWaivedForPeriod) {
		this.totalWaivedForPeriod = totalWaivedForPeriod;
		return this;
	}

	/**
	 * Get totalWaivedForPeriod
	 *
	 * @return totalWaivedForPeriod
	 */
	@Schema(name = "totalWaivedForPeriod", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalWaivedForPeriod")
	public Long getTotalWaivedForPeriod() {
		return totalWaivedForPeriod;
	}

	public void setTotalWaivedForPeriod(Long totalWaivedForPeriod) {
		this.totalWaivedForPeriod = totalWaivedForPeriod;
	}

	public GetLoansLoanIdRepaymentPeriod totalWrittenOffForPeriod(Long totalWrittenOffForPeriod) {
		this.totalWrittenOffForPeriod = totalWrittenOffForPeriod;
		return this;
	}

	/**
	 * Get totalWrittenOffForPeriod
	 *
	 * @return totalWrittenOffForPeriod
	 */
	@Schema(name = "totalWrittenOffForPeriod", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalWrittenOffForPeriod")
	public Long getTotalWrittenOffForPeriod() {
		return totalWrittenOffForPeriod;
	}

	public void setTotalWrittenOffForPeriod(Long totalWrittenOffForPeriod) {
		this.totalWrittenOffForPeriod = totalWrittenOffForPeriod;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansLoanIdRepaymentPeriod getLoansLoanIdRepaymentPeriod = (GetLoansLoanIdRepaymentPeriod) o;
		return Objects.equals(this.complete, getLoansLoanIdRepaymentPeriod.complete)
				&& Objects.equals(this.daysInPeriod, getLoansLoanIdRepaymentPeriod.daysInPeriod)
				&& Objects.equals(this.dueDate, getLoansLoanIdRepaymentPeriod.dueDate)
				&& Objects.equals(this.feeChargesDue, getLoansLoanIdRepaymentPeriod.feeChargesDue)
				&& Objects.equals(this.feeChargesOutstanding, getLoansLoanIdRepaymentPeriod.feeChargesOutstanding)
				&& Objects.equals(this.feeChargesPaid, getLoansLoanIdRepaymentPeriod.feeChargesPaid)
				&& Objects.equals(this.feeChargesWaived, getLoansLoanIdRepaymentPeriod.feeChargesWaived)
				&& Objects.equals(this.feeChargesWrittenOff, getLoansLoanIdRepaymentPeriod.feeChargesWrittenOff)
				&& Objects.equals(this.fromDate, getLoansLoanIdRepaymentPeriod.fromDate)
				&& Objects.equals(this.interestDue, getLoansLoanIdRepaymentPeriod.interestDue)
				&& Objects.equals(this.interestOriginalDue, getLoansLoanIdRepaymentPeriod.interestOriginalDue)
				&& Objects.equals(this.interestOutstanding, getLoansLoanIdRepaymentPeriod.interestOutstanding)
				&& Objects.equals(this.interestPaid, getLoansLoanIdRepaymentPeriod.interestPaid)
				&& Objects.equals(this.interestWaived, getLoansLoanIdRepaymentPeriod.interestWaived)
				&& Objects.equals(this.interestWrittenOff, getLoansLoanIdRepaymentPeriod.interestWrittenOff)
				&& Objects.equals(this.obligationsMetOnDate, getLoansLoanIdRepaymentPeriod.obligationsMetOnDate)
				&& Objects.equals(this.penaltyChargesDue, getLoansLoanIdRepaymentPeriod.penaltyChargesDue)
				&& Objects.equals(this.penaltyChargesOutstanding,
						getLoansLoanIdRepaymentPeriod.penaltyChargesOutstanding)
				&& Objects.equals(this.penaltyChargesPaid, getLoansLoanIdRepaymentPeriod.penaltyChargesPaid)
				&& Objects.equals(this.penaltyChargesWaived, getLoansLoanIdRepaymentPeriod.penaltyChargesWaived)
				&& Objects.equals(this.penaltyChargesWrittenOff, getLoansLoanIdRepaymentPeriod.penaltyChargesWrittenOff)
				&& Objects.equals(this.period, getLoansLoanIdRepaymentPeriod.period)
				&& Objects.equals(this.principalDue, getLoansLoanIdRepaymentPeriod.principalDue)
				&& Objects.equals(this.principalLoanBalanceOutstanding,
						getLoansLoanIdRepaymentPeriod.principalLoanBalanceOutstanding)
				&& Objects.equals(this.principalOriginalDue, getLoansLoanIdRepaymentPeriod.principalOriginalDue)
				&& Objects.equals(this.principalOutstanding, getLoansLoanIdRepaymentPeriod.principalOutstanding)
				&& Objects.equals(this.principalPaid, getLoansLoanIdRepaymentPeriod.principalPaid)
				&& Objects.equals(this.principalWrittenOff, getLoansLoanIdRepaymentPeriod.principalWrittenOff)
				&& Objects.equals(this.totalActualCostOfLoanForPeriod,
						getLoansLoanIdRepaymentPeriod.totalActualCostOfLoanForPeriod)
				&& Objects.equals(this.totalDueForPeriod, getLoansLoanIdRepaymentPeriod.totalDueForPeriod)
				&& Objects.equals(this.totalInstallmentAmountForPeriod,
						getLoansLoanIdRepaymentPeriod.totalInstallmentAmountForPeriod)
				&& Objects.equals(this.totalOriginalDueForPeriod,
						getLoansLoanIdRepaymentPeriod.totalOriginalDueForPeriod)
				&& Objects.equals(this.totalOutstandingForPeriod,
						getLoansLoanIdRepaymentPeriod.totalOutstandingForPeriod)
				&& Objects.equals(this.totalPaidForPeriod, getLoansLoanIdRepaymentPeriod.totalPaidForPeriod)
				&& Objects.equals(this.totalPaidInAdvanceForPeriod,
						getLoansLoanIdRepaymentPeriod.totalPaidInAdvanceForPeriod)
				&& Objects.equals(this.totalPaidLateForPeriod, getLoansLoanIdRepaymentPeriod.totalPaidLateForPeriod)
				&& Objects.equals(this.totalWaivedForPeriod, getLoansLoanIdRepaymentPeriod.totalWaivedForPeriod)
				&& Objects.equals(this.totalWrittenOffForPeriod,
						getLoansLoanIdRepaymentPeriod.totalWrittenOffForPeriod);
	}

	@Override
	public int hashCode() {
		return Objects.hash(complete, daysInPeriod, dueDate, feeChargesDue, feeChargesOutstanding, feeChargesPaid,
				feeChargesWaived, feeChargesWrittenOff, fromDate, interestDue, interestOriginalDue, interestOutstanding,
				interestPaid, interestWaived, interestWrittenOff, obligationsMetOnDate, penaltyChargesDue,
				penaltyChargesOutstanding, penaltyChargesPaid, penaltyChargesWaived, penaltyChargesWrittenOff, period,
				principalDue, principalLoanBalanceOutstanding, principalOriginalDue, principalOutstanding,
				principalPaid, principalWrittenOff, totalActualCostOfLoanForPeriod, totalDueForPeriod,
				totalInstallmentAmountForPeriod, totalOriginalDueForPeriod, totalOutstandingForPeriod,
				totalPaidForPeriod, totalPaidInAdvanceForPeriod, totalPaidLateForPeriod, totalWaivedForPeriod,
				totalWrittenOffForPeriod);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdRepaymentPeriod {\n");
		sb.append("    complete: ").append(toIndentedString(complete)).append("\n");
		sb.append("    daysInPeriod: ").append(toIndentedString(daysInPeriod)).append("\n");
		sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
		sb.append("    feeChargesDue: ").append(toIndentedString(feeChargesDue)).append("\n");
		sb.append("    feeChargesOutstanding: ").append(toIndentedString(feeChargesOutstanding)).append("\n");
		sb.append("    feeChargesPaid: ").append(toIndentedString(feeChargesPaid)).append("\n");
		sb.append("    feeChargesWaived: ").append(toIndentedString(feeChargesWaived)).append("\n");
		sb.append("    feeChargesWrittenOff: ").append(toIndentedString(feeChargesWrittenOff)).append("\n");
		sb.append("    fromDate: ").append(toIndentedString(fromDate)).append("\n");
		sb.append("    interestDue: ").append(toIndentedString(interestDue)).append("\n");
		sb.append("    interestOriginalDue: ").append(toIndentedString(interestOriginalDue)).append("\n");
		sb.append("    interestOutstanding: ").append(toIndentedString(interestOutstanding)).append("\n");
		sb.append("    interestPaid: ").append(toIndentedString(interestPaid)).append("\n");
		sb.append("    interestWaived: ").append(toIndentedString(interestWaived)).append("\n");
		sb.append("    interestWrittenOff: ").append(toIndentedString(interestWrittenOff)).append("\n");
		sb.append("    obligationsMetOnDate: ").append(toIndentedString(obligationsMetOnDate)).append("\n");
		sb.append("    penaltyChargesDue: ").append(toIndentedString(penaltyChargesDue)).append("\n");
		sb.append("    penaltyChargesOutstanding: ").append(toIndentedString(penaltyChargesOutstanding)).append("\n");
		sb.append("    penaltyChargesPaid: ").append(toIndentedString(penaltyChargesPaid)).append("\n");
		sb.append("    penaltyChargesWaived: ").append(toIndentedString(penaltyChargesWaived)).append("\n");
		sb.append("    penaltyChargesWrittenOff: ").append(toIndentedString(penaltyChargesWrittenOff)).append("\n");
		sb.append("    period: ").append(toIndentedString(period)).append("\n");
		sb.append("    principalDue: ").append(toIndentedString(principalDue)).append("\n");
		sb.append("    principalLoanBalanceOutstanding: ").append(toIndentedString(principalLoanBalanceOutstanding))
				.append("\n");
		sb.append("    principalOriginalDue: ").append(toIndentedString(principalOriginalDue)).append("\n");
		sb.append("    principalOutstanding: ").append(toIndentedString(principalOutstanding)).append("\n");
		sb.append("    principalPaid: ").append(toIndentedString(principalPaid)).append("\n");
		sb.append("    principalWrittenOff: ").append(toIndentedString(principalWrittenOff)).append("\n");
		sb.append("    totalActualCostOfLoanForPeriod: ").append(toIndentedString(totalActualCostOfLoanForPeriod))
				.append("\n");
		sb.append("    totalDueForPeriod: ").append(toIndentedString(totalDueForPeriod)).append("\n");
		sb.append("    totalInstallmentAmountForPeriod: ").append(toIndentedString(totalInstallmentAmountForPeriod))
				.append("\n");
		sb.append("    totalOriginalDueForPeriod: ").append(toIndentedString(totalOriginalDueForPeriod)).append("\n");
		sb.append("    totalOutstandingForPeriod: ").append(toIndentedString(totalOutstandingForPeriod)).append("\n");
		sb.append("    totalPaidForPeriod: ").append(toIndentedString(totalPaidForPeriod)).append("\n");
		sb.append("    totalPaidInAdvanceForPeriod: ").append(toIndentedString(totalPaidInAdvanceForPeriod))
				.append("\n");
		sb.append("    totalPaidLateForPeriod: ").append(toIndentedString(totalPaidLateForPeriod)).append("\n");
		sb.append("    totalWaivedForPeriod: ").append(toIndentedString(totalWaivedForPeriod)).append("\n");
		sb.append("    totalWrittenOffForPeriod: ").append(toIndentedString(totalWrittenOffForPeriod)).append("\n");
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
