/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdRepaymentPeriod */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
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

	private BigDecimal principalDue;

	private BigDecimal principalLoanBalanceOutstanding;

	private BigDecimal principalOriginalDue;

	private BigDecimal principalOutstanding;
	private BigDecimal principalDisbursed;

	private BigDecimal principalPaid;

	private Long principalWrittenOff;

	private Long totalActualCostOfLoanForPeriod;

	private BigDecimal totalDueForPeriod;

	private BigDecimal totalInstallmentAmountForPeriod;

	private BigDecimal totalOriginalDueForPeriod;

	private BigDecimal totalOutstandingForPeriod;

	private BigDecimal totalPaidForPeriod;

	private BigDecimal totalPaidInAdvanceForPeriod;

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

    public GetLoansLoanIdRepaymentPeriod principalDue(BigDecimal principalDue) {
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
	public BigDecimal getPrincipalDue() {
		return principalDue;
	}

    public GetLoansLoanIdRepaymentPeriod principalLoanBalanceOutstanding(BigDecimal principalLoanBalanceOutstanding) {
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
	public BigDecimal getPrincipalLoanBalanceOutstanding() {
		return principalLoanBalanceOutstanding;
	}

    public GetLoansLoanIdRepaymentPeriod principalOriginalDue(BigDecimal principalOriginalDue) {
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
	public BigDecimal getPrincipalOriginalDue() {
		return principalOriginalDue;
	}

    public GetLoansLoanIdRepaymentPeriod principalOutstanding(BigDecimal principalOutstanding) {
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
	public BigDecimal getPrincipalOutstanding() {
		return principalOutstanding;
	}

    public GetLoansLoanIdRepaymentPeriod principalPaid(BigDecimal principalPaid) {
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
	public BigDecimal getPrincipalPaid() {
		return principalPaid;
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

    public GetLoansLoanIdRepaymentPeriod totalDueForPeriod(BigDecimal totalDueForPeriod) {
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
	public BigDecimal getTotalDueForPeriod() {
		return totalDueForPeriod;
	}

    public GetLoansLoanIdRepaymentPeriod totalInstallmentAmountForPeriod(BigDecimal totalInstallmentAmountForPeriod) {
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
	public BigDecimal getTotalInstallmentAmountForPeriod() {
		return totalInstallmentAmountForPeriod;
	}

    public GetLoansLoanIdRepaymentPeriod totalOriginalDueForPeriod(BigDecimal totalOriginalDueForPeriod) {
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
	public BigDecimal getTotalOriginalDueForPeriod() {
		return totalOriginalDueForPeriod;
	}

    public GetLoansLoanIdRepaymentPeriod totalOutstandingForPeriod(BigDecimal totalOutstandingForPeriod) {
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
	public BigDecimal getTotalOutstandingForPeriod() {
		return totalOutstandingForPeriod;
	}

    public GetLoansLoanIdRepaymentPeriod totalPaidForPeriod(BigDecimal totalPaidForPeriod) {
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
	public BigDecimal getTotalPaidForPeriod() {
		return totalPaidForPeriod;
	}

    public GetLoansLoanIdRepaymentPeriod totalPaidInAdvanceForPeriod(BigDecimal totalPaidInAdvanceForPeriod) {
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
	public BigDecimal getTotalPaidInAdvanceForPeriod() {
		return totalPaidInAdvanceForPeriod;
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
