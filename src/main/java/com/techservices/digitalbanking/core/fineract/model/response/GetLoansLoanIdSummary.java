/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdSummary */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdSummary {

	private Boolean canDisburse;

	private GetLoansLoanIdCurrency currency;

	@Valid
	private Set<@Valid GetLoansLoanIdDisbursementDetails> disbursementDetails = new LinkedHashSet<>();

	@Valid
	private Set<@Valid Object> emiAmountVariations = new LinkedHashSet<>();

	private Long feeChargesCharged;

	private Long feeChargesDueAtDisbursementCharged;

	private Long feeChargesOutstanding;

	private Long feeChargesOverdue;

	private Long feeChargesPaid;

	private Long feeChargesWaived;

	private Long feeChargesWrittenOff;

	private Double fixedEmiAmount;

	private Boolean inArrears;

	private Long interestCharged;

	private Long interestOutstanding;

	private Long interestOverdue;

	private Long interestPaid;

	private Long interestWaived;

	private Long interestWrittenOff;

	private Boolean isNPA;

	private GetLoansLoanIdLinkedAccount linkedAccount;

	private Long maxOutstandingLoanBalance;

	@Valid
	private Set<@Valid GetLoansLoanIdOverdueCharges> overdueCharges = new LinkedHashSet<>();

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate overdueSinceDate;

	private Long penaltyChargesCharged;

	private Long penaltyChargesOutstanding;

	private Long penaltyChargesOverdue;

	private Long penaltyChargesPaid;

	private Long penaltyChargesWaived;

	private Long penaltyChargesWrittenOff;

	private Long principalDisbursed;

	private Long principalOutstanding;

	private Double principalOverdue;

	private Long principalPaid;

	private Long principalWrittenOff;

	private Long totalCostOfLoan;

	private Long totalExpectedCostOfLoan;

	private Long totalExpectedRepayment;

	private Long totalOutstanding;

	private Double totalOverdue;

	private Long totalRepayment;

	private Long totalWaived;

	private Long totalWrittenOff;

	public GetLoansLoanIdSummary canDisburse(Boolean canDisburse) {
		this.canDisburse = canDisburse;
		return this;
	}

	/**
	 * Get canDisburse
	 *
	 * @return canDisburse
	 */
	@Schema(name = "canDisburse", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("canDisburse")
	public Boolean getCanDisburse() {
		return canDisburse;
	}

	public void setCanDisburse(Boolean canDisburse) {
		this.canDisburse = canDisburse;
	}

	public GetLoansLoanIdSummary currency(GetLoansLoanIdCurrency currency) {
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

	public GetLoansLoanIdSummary disbursementDetails(
			Set<@Valid GetLoansLoanIdDisbursementDetails> disbursementDetails) {
		this.disbursementDetails = disbursementDetails;
		return this;
	}

	public GetLoansLoanIdSummary addDisbursementDetailsItem(GetLoansLoanIdDisbursementDetails disbursementDetailsItem) {
		if (this.disbursementDetails == null) {
			this.disbursementDetails = new LinkedHashSet<>();
		}
		this.disbursementDetails.add(disbursementDetailsItem);
		return this;
	}

	/**
	 * Get disbursementDetails
	 *
	 * @return disbursementDetails
	 */
	@Valid
	@Schema(name = "disbursementDetails", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("disbursementDetails")
	public Set<@Valid GetLoansLoanIdDisbursementDetails> getDisbursementDetails() {
		return disbursementDetails;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setDisbursementDetails(Set<@Valid GetLoansLoanIdDisbursementDetails> disbursementDetails) {
		this.disbursementDetails = disbursementDetails;
	}

	public GetLoansLoanIdSummary emiAmountVariations(Set<@Valid Object> emiAmountVariations) {
		this.emiAmountVariations = emiAmountVariations;
		return this;
	}

	public GetLoansLoanIdSummary addEmiAmountVariationsItem(Object emiAmountVariationsItem) {
		if (this.emiAmountVariations == null) {
			this.emiAmountVariations = new LinkedHashSet<>();
		}
		this.emiAmountVariations.add(emiAmountVariationsItem);
		return this;
	}

	/**
	 * Get emiAmountVariations
	 *
	 * @return emiAmountVariations
	 */
	@Schema(name = "emiAmountVariations", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("emiAmountVariations")
	public Set<@Valid Object> getEmiAmountVariations() {
		return emiAmountVariations;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setEmiAmountVariations(Set<@Valid Object> emiAmountVariations) {
		this.emiAmountVariations = emiAmountVariations;
	}

	public GetLoansLoanIdSummary feeChargesCharged(Long feeChargesCharged) {
		this.feeChargesCharged = feeChargesCharged;
		return this;
	}

	/**
	 * Get feeChargesCharged
	 *
	 * @return feeChargesCharged
	 */
	@Schema(name = "feeChargesCharged", example = "18000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesCharged")
	public Long getFeeChargesCharged() {
		return feeChargesCharged;
	}

	public void setFeeChargesCharged(Long feeChargesCharged) {
		this.feeChargesCharged = feeChargesCharged;
	}

	public GetLoansLoanIdSummary feeChargesDueAtDisbursementCharged(Long feeChargesDueAtDisbursementCharged) {
		this.feeChargesDueAtDisbursementCharged = feeChargesDueAtDisbursementCharged;
		return this;
	}

	/**
	 * Get feeChargesDueAtDisbursementCharged
	 *
	 * @return feeChargesDueAtDisbursementCharged
	 */
	@Schema(name = "feeChargesDueAtDisbursementCharged", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesDueAtDisbursementCharged")
	public Long getFeeChargesDueAtDisbursementCharged() {
		return feeChargesDueAtDisbursementCharged;
	}

	public void setFeeChargesDueAtDisbursementCharged(Long feeChargesDueAtDisbursementCharged) {
		this.feeChargesDueAtDisbursementCharged = feeChargesDueAtDisbursementCharged;
	}

	public GetLoansLoanIdSummary feeChargesOutstanding(Long feeChargesOutstanding) {
		this.feeChargesOutstanding = feeChargesOutstanding;
		return this;
	}

	/**
	 * Get feeChargesOutstanding
	 *
	 * @return feeChargesOutstanding
	 */
	@Schema(name = "feeChargesOutstanding", example = "18000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesOutstanding")
	public Long getFeeChargesOutstanding() {
		return feeChargesOutstanding;
	}

	public void setFeeChargesOutstanding(Long feeChargesOutstanding) {
		this.feeChargesOutstanding = feeChargesOutstanding;
	}

	public GetLoansLoanIdSummary feeChargesOverdue(Long feeChargesOverdue) {
		this.feeChargesOverdue = feeChargesOverdue;
		return this;
	}

	/**
	 * Get feeChargesOverdue
	 *
	 * @return feeChargesOverdue
	 */
	@Schema(name = "feeChargesOverdue", example = "15000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesOverdue")
	public Long getFeeChargesOverdue() {
		return feeChargesOverdue;
	}

	public void setFeeChargesOverdue(Long feeChargesOverdue) {
		this.feeChargesOverdue = feeChargesOverdue;
	}

	public GetLoansLoanIdSummary feeChargesPaid(Long feeChargesPaid) {
		this.feeChargesPaid = feeChargesPaid;
		return this;
	}

	/**
	 * Get feeChargesPaid
	 *
	 * @return feeChargesPaid
	 */
	@Schema(name = "feeChargesPaid", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesPaid")
	public Long getFeeChargesPaid() {
		return feeChargesPaid;
	}

	public void setFeeChargesPaid(Long feeChargesPaid) {
		this.feeChargesPaid = feeChargesPaid;
	}

	public GetLoansLoanIdSummary feeChargesWaived(Long feeChargesWaived) {
		this.feeChargesWaived = feeChargesWaived;
		return this;
	}

	/**
	 * Get feeChargesWaived
	 *
	 * @return feeChargesWaived
	 */
	@Schema(name = "feeChargesWaived", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesWaived")
	public Long getFeeChargesWaived() {
		return feeChargesWaived;
	}

	public void setFeeChargesWaived(Long feeChargesWaived) {
		this.feeChargesWaived = feeChargesWaived;
	}

	public GetLoansLoanIdSummary feeChargesWrittenOff(Long feeChargesWrittenOff) {
		this.feeChargesWrittenOff = feeChargesWrittenOff;
		return this;
	}

	/**
	 * Get feeChargesWrittenOff
	 *
	 * @return feeChargesWrittenOff
	 */
	@Schema(name = "feeChargesWrittenOff", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeChargesWrittenOff")
	public Long getFeeChargesWrittenOff() {
		return feeChargesWrittenOff;
	}

	public void setFeeChargesWrittenOff(Long feeChargesWrittenOff) {
		this.feeChargesWrittenOff = feeChargesWrittenOff;
	}

	public GetLoansLoanIdSummary fixedEmiAmount(Double fixedEmiAmount) {
		this.fixedEmiAmount = fixedEmiAmount;
		return this;
	}

	/**
	 * Get fixedEmiAmount
	 *
	 * @return fixedEmiAmount
	 */
	@Schema(name = "fixedEmiAmount", example = "1100.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("fixedEmiAmount")
	public Double getFixedEmiAmount() {
		return fixedEmiAmount;
	}

	public void setFixedEmiAmount(Double fixedEmiAmount) {
		this.fixedEmiAmount = fixedEmiAmount;
	}

	public GetLoansLoanIdSummary inArrears(Boolean inArrears) {
		this.inArrears = inArrears;
		return this;
	}

	/**
	 * Get inArrears
	 *
	 * @return inArrears
	 */
	@Schema(name = "inArrears", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("inArrears")
	public Boolean getInArrears() {
		return inArrears;
	}

	public void setInArrears(Boolean inArrears) {
		this.inArrears = inArrears;
	}

	public GetLoansLoanIdSummary interestCharged(Long interestCharged) {
		this.interestCharged = interestCharged;
		return this;
	}

	/**
	 * Get interestCharged
	 *
	 * @return interestCharged
	 */
	@Schema(name = "interestCharged", example = "240000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestCharged")
	public Long getInterestCharged() {
		return interestCharged;
	}

	public void setInterestCharged(Long interestCharged) {
		this.interestCharged = interestCharged;
	}

	public GetLoansLoanIdSummary interestOutstanding(Long interestOutstanding) {
		this.interestOutstanding = interestOutstanding;
		return this;
	}

	/**
	 * Get interestOutstanding
	 *
	 * @return interestOutstanding
	 */
	@Schema(name = "interestOutstanding", example = "240000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestOutstanding")
	public Long getInterestOutstanding() {
		return interestOutstanding;
	}

	public void setInterestOutstanding(Long interestOutstanding) {
		this.interestOutstanding = interestOutstanding;
	}

	public GetLoansLoanIdSummary interestOverdue(Long interestOverdue) {
		this.interestOverdue = interestOverdue;
		return this;
	}

	/**
	 * Get interestOverdue
	 *
	 * @return interestOverdue
	 */
	@Schema(name = "interestOverdue", example = "200000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestOverdue")
	public Long getInterestOverdue() {
		return interestOverdue;
	}

	public void setInterestOverdue(Long interestOverdue) {
		this.interestOverdue = interestOverdue;
	}

	public GetLoansLoanIdSummary interestPaid(Long interestPaid) {
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

	public GetLoansLoanIdSummary interestWaived(Long interestWaived) {
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

	public GetLoansLoanIdSummary interestWrittenOff(Long interestWrittenOff) {
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

	public GetLoansLoanIdSummary isNPA(Boolean isNPA) {
		this.isNPA = isNPA;
		return this;
	}

	/**
	 * Get isNPA
	 *
	 * @return isNPA
	 */
	@Schema(name = "isNPA", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("isNPA")
	public Boolean getIsNPA() {
		return isNPA;
	}

	public void setIsNPA(Boolean isNPA) {
		this.isNPA = isNPA;
	}

	public GetLoansLoanIdSummary linkedAccount(GetLoansLoanIdLinkedAccount linkedAccount) {
		this.linkedAccount = linkedAccount;
		return this;
	}

	/**
	 * Get linkedAccount
	 *
	 * @return linkedAccount
	 */
	@Valid
	@Schema(name = "linkedAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("linkedAccount")
	public GetLoansLoanIdLinkedAccount getLinkedAccount() {
		return linkedAccount;
	}

	public void setLinkedAccount(GetLoansLoanIdLinkedAccount linkedAccount) {
		this.linkedAccount = linkedAccount;
	}

	public GetLoansLoanIdSummary maxOutstandingLoanBalance(Long maxOutstandingLoanBalance) {
		this.maxOutstandingLoanBalance = maxOutstandingLoanBalance;
		return this;
	}

	/**
	 * Get maxOutstandingLoanBalance
	 *
	 * @return maxOutstandingLoanBalance
	 */
	@Schema(name = "maxOutstandingLoanBalance", example = "35000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("maxOutstandingLoanBalance")
	public Long getMaxOutstandingLoanBalance() {
		return maxOutstandingLoanBalance;
	}

	public void setMaxOutstandingLoanBalance(Long maxOutstandingLoanBalance) {
		this.maxOutstandingLoanBalance = maxOutstandingLoanBalance;
	}

	public GetLoansLoanIdSummary overdueCharges(Set<@Valid GetLoansLoanIdOverdueCharges> overdueCharges) {
		this.overdueCharges = overdueCharges;
		return this;
	}

	public GetLoansLoanIdSummary addOverdueChargesItem(GetLoansLoanIdOverdueCharges overdueChargesItem) {
		if (this.overdueCharges == null) {
			this.overdueCharges = new LinkedHashSet<>();
		}
		this.overdueCharges.add(overdueChargesItem);
		return this;
	}

	/**
	 * Get overdueCharges
	 *
	 * @return overdueCharges
	 */
	@Valid
	@Schema(name = "overdueCharges", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("overdueCharges")
	public Set<@Valid GetLoansLoanIdOverdueCharges> getOverdueCharges() {
		return overdueCharges;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setOverdueCharges(Set<@Valid GetLoansLoanIdOverdueCharges> overdueCharges) {
		this.overdueCharges = overdueCharges;
	}

	public GetLoansLoanIdSummary overdueSinceDate(LocalDate overdueSinceDate) {
		this.overdueSinceDate = overdueSinceDate;
		return this;
	}

	/**
	 * Get overdueSinceDate
	 *
	 * @return overdueSinceDate
	 */
	@Valid
	@Schema(name = "overdueSinceDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("overdueSinceDate")
	public LocalDate getOverdueSinceDate() {
		return overdueSinceDate;
	}

	public void setOverdueSinceDate(LocalDate overdueSinceDate) {
		this.overdueSinceDate = overdueSinceDate;
	}

	public GetLoansLoanIdSummary penaltyChargesCharged(Long penaltyChargesCharged) {
		this.penaltyChargesCharged = penaltyChargesCharged;
		return this;
	}

	/**
	 * Get penaltyChargesCharged
	 *
	 * @return penaltyChargesCharged
	 */
	@Schema(name = "penaltyChargesCharged", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesCharged")
	public Long getPenaltyChargesCharged() {
		return penaltyChargesCharged;
	}

	public void setPenaltyChargesCharged(Long penaltyChargesCharged) {
		this.penaltyChargesCharged = penaltyChargesCharged;
	}

	public GetLoansLoanIdSummary penaltyChargesOutstanding(Long penaltyChargesOutstanding) {
		this.penaltyChargesOutstanding = penaltyChargesOutstanding;
		return this;
	}

	/**
	 * Get penaltyChargesOutstanding
	 *
	 * @return penaltyChargesOutstanding
	 */
	@Schema(name = "penaltyChargesOutstanding", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesOutstanding")
	public Long getPenaltyChargesOutstanding() {
		return penaltyChargesOutstanding;
	}

	public void setPenaltyChargesOutstanding(Long penaltyChargesOutstanding) {
		this.penaltyChargesOutstanding = penaltyChargesOutstanding;
	}

	public GetLoansLoanIdSummary penaltyChargesOverdue(Long penaltyChargesOverdue) {
		this.penaltyChargesOverdue = penaltyChargesOverdue;
		return this;
	}

	/**
	 * Get penaltyChargesOverdue
	 *
	 * @return penaltyChargesOverdue
	 */
	@Schema(name = "penaltyChargesOverdue", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesOverdue")
	public Long getPenaltyChargesOverdue() {
		return penaltyChargesOverdue;
	}

	public void setPenaltyChargesOverdue(Long penaltyChargesOverdue) {
		this.penaltyChargesOverdue = penaltyChargesOverdue;
	}

	public GetLoansLoanIdSummary penaltyChargesPaid(Long penaltyChargesPaid) {
		this.penaltyChargesPaid = penaltyChargesPaid;
		return this;
	}

	/**
	 * Get penaltyChargesPaid
	 *
	 * @return penaltyChargesPaid
	 */
	@Schema(name = "penaltyChargesPaid", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesPaid")
	public Long getPenaltyChargesPaid() {
		return penaltyChargesPaid;
	}

	public void setPenaltyChargesPaid(Long penaltyChargesPaid) {
		this.penaltyChargesPaid = penaltyChargesPaid;
	}

	public GetLoansLoanIdSummary penaltyChargesWaived(Long penaltyChargesWaived) {
		this.penaltyChargesWaived = penaltyChargesWaived;
		return this;
	}

	/**
	 * Get penaltyChargesWaived
	 *
	 * @return penaltyChargesWaived
	 */
	@Schema(name = "penaltyChargesWaived", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesWaived")
	public Long getPenaltyChargesWaived() {
		return penaltyChargesWaived;
	}

	public void setPenaltyChargesWaived(Long penaltyChargesWaived) {
		this.penaltyChargesWaived = penaltyChargesWaived;
	}

	public GetLoansLoanIdSummary penaltyChargesWrittenOff(Long penaltyChargesWrittenOff) {
		this.penaltyChargesWrittenOff = penaltyChargesWrittenOff;
		return this;
	}

	/**
	 * Get penaltyChargesWrittenOff
	 *
	 * @return penaltyChargesWrittenOff
	 */
	@Schema(name = "penaltyChargesWrittenOff", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penaltyChargesWrittenOff")
	public Long getPenaltyChargesWrittenOff() {
		return penaltyChargesWrittenOff;
	}

	public void setPenaltyChargesWrittenOff(Long penaltyChargesWrittenOff) {
		this.penaltyChargesWrittenOff = penaltyChargesWrittenOff;
	}

	public GetLoansLoanIdSummary principalDisbursed(Long principalDisbursed) {
		this.principalDisbursed = principalDisbursed;
		return this;
	}

	/**
	 * Get principalDisbursed
	 *
	 * @return principalDisbursed
	 */
	@Schema(name = "principalDisbursed", example = "1000000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalDisbursed")
	public Long getPrincipalDisbursed() {
		return principalDisbursed;
	}

	public void setPrincipalDisbursed(Long principalDisbursed) {
		this.principalDisbursed = principalDisbursed;
	}

	public GetLoansLoanIdSummary principalOutstanding(Long principalOutstanding) {
		this.principalOutstanding = principalOutstanding;
		return this;
	}

	/**
	 * Get principalOutstanding
	 *
	 * @return principalOutstanding
	 */
	@Schema(name = "principalOutstanding", example = "1000000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalOutstanding")
	public Long getPrincipalOutstanding() {
		return principalOutstanding;
	}

	public void setPrincipalOutstanding(Long principalOutstanding) {
		this.principalOutstanding = principalOutstanding;
	}

	public GetLoansLoanIdSummary principalOverdue(Double principalOverdue) {
		this.principalOverdue = principalOverdue;
		return this;
	}

	/**
	 * Get principalOverdue
	 *
	 * @return principalOverdue
	 */
	@Schema(name = "principalOverdue", example = "833333.3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalOverdue")
	public Double getPrincipalOverdue() {
		return principalOverdue;
	}

	public void setPrincipalOverdue(Double principalOverdue) {
		this.principalOverdue = principalOverdue;
	}

	public GetLoansLoanIdSummary principalPaid(Long principalPaid) {
		this.principalPaid = principalPaid;
		return this;
	}

	/**
	 * Get principalPaid
	 *
	 * @return principalPaid
	 */
	@Schema(name = "principalPaid", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalPaid")
	public Long getPrincipalPaid() {
		return principalPaid;
	}

	public void setPrincipalPaid(Long principalPaid) {
		this.principalPaid = principalPaid;
	}

	public GetLoansLoanIdSummary principalWrittenOff(Long principalWrittenOff) {
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

	public GetLoansLoanIdSummary totalCostOfLoan(Long totalCostOfLoan) {
		this.totalCostOfLoan = totalCostOfLoan;
		return this;
	}

	/**
	 * Get totalCostOfLoan
	 *
	 * @return totalCostOfLoan
	 */
	@Schema(name = "totalCostOfLoan", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalCostOfLoan")
	public Long getTotalCostOfLoan() {
		return totalCostOfLoan;
	}

	public void setTotalCostOfLoan(Long totalCostOfLoan) {
		this.totalCostOfLoan = totalCostOfLoan;
	}

	public GetLoansLoanIdSummary totalExpectedCostOfLoan(Long totalExpectedCostOfLoan) {
		this.totalExpectedCostOfLoan = totalExpectedCostOfLoan;
		return this;
	}

	/**
	 * Get totalExpectedCostOfLoan
	 *
	 * @return totalExpectedCostOfLoan
	 */
	@Schema(name = "totalExpectedCostOfLoan", example = "258000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalExpectedCostOfLoan")
	public Long getTotalExpectedCostOfLoan() {
		return totalExpectedCostOfLoan;
	}

	public void setTotalExpectedCostOfLoan(Long totalExpectedCostOfLoan) {
		this.totalExpectedCostOfLoan = totalExpectedCostOfLoan;
	}

	public GetLoansLoanIdSummary totalExpectedRepayment(Long totalExpectedRepayment) {
		this.totalExpectedRepayment = totalExpectedRepayment;
		return this;
	}

	/**
	 * Get totalExpectedRepayment
	 *
	 * @return totalExpectedRepayment
	 */
	@Schema(name = "totalExpectedRepayment", example = "1258000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalExpectedRepayment")
	public Long getTotalExpectedRepayment() {
		return totalExpectedRepayment;
	}

	public void setTotalExpectedRepayment(Long totalExpectedRepayment) {
		this.totalExpectedRepayment = totalExpectedRepayment;
	}

	public GetLoansLoanIdSummary totalOutstanding(Long totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
		return this;
	}

	/**
	 * Get totalOutstanding
	 *
	 * @return totalOutstanding
	 */
	@Schema(name = "totalOutstanding", example = "1258000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalOutstanding")
	public Long getTotalOutstanding() {
		return totalOutstanding;
	}

	public void setTotalOutstanding(Long totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}

	public GetLoansLoanIdSummary totalOverdue(Double totalOverdue) {
		this.totalOverdue = totalOverdue;
		return this;
	}

	/**
	 * Get totalOverdue
	 *
	 * @return totalOverdue
	 */
	@Schema(name = "totalOverdue", example = "1048333.3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalOverdue")
	public Double getTotalOverdue() {
		return totalOverdue;
	}

	public void setTotalOverdue(Double totalOverdue) {
		this.totalOverdue = totalOverdue;
	}

	public GetLoansLoanIdSummary totalRepayment(Long totalRepayment) {
		this.totalRepayment = totalRepayment;
		return this;
	}

	/**
	 * Get totalRepayment
	 *
	 * @return totalRepayment
	 */
	@Schema(name = "totalRepayment", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalRepayment")
	public Long getTotalRepayment() {
		return totalRepayment;
	}

	public void setTotalRepayment(Long totalRepayment) {
		this.totalRepayment = totalRepayment;
	}

	public GetLoansLoanIdSummary totalWaived(Long totalWaived) {
		this.totalWaived = totalWaived;
		return this;
	}

	/**
	 * Get totalWaived
	 *
	 * @return totalWaived
	 */
	@Schema(name = "totalWaived", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalWaived")
	public Long getTotalWaived() {
		return totalWaived;
	}

	public void setTotalWaived(Long totalWaived) {
		this.totalWaived = totalWaived;
	}

	public GetLoansLoanIdSummary totalWrittenOff(Long totalWrittenOff) {
		this.totalWrittenOff = totalWrittenOff;
		return this;
	}

	/**
	 * Get totalWrittenOff
	 *
	 * @return totalWrittenOff
	 */
	@Schema(name = "totalWrittenOff", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("totalWrittenOff")
	public Long getTotalWrittenOff() {
		return totalWrittenOff;
	}

	public void setTotalWrittenOff(Long totalWrittenOff) {
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
		GetLoansLoanIdSummary getLoansLoanIdSummary = (GetLoansLoanIdSummary) o;
		return Objects.equals(this.canDisburse, getLoansLoanIdSummary.canDisburse)
				&& Objects.equals(this.currency, getLoansLoanIdSummary.currency)
				&& Objects.equals(this.disbursementDetails, getLoansLoanIdSummary.disbursementDetails)
				&& Objects.equals(this.emiAmountVariations, getLoansLoanIdSummary.emiAmountVariations)
				&& Objects.equals(this.feeChargesCharged, getLoansLoanIdSummary.feeChargesCharged)
				&& Objects.equals(this.feeChargesDueAtDisbursementCharged,
						getLoansLoanIdSummary.feeChargesDueAtDisbursementCharged)
				&& Objects.equals(this.feeChargesOutstanding, getLoansLoanIdSummary.feeChargesOutstanding)
				&& Objects.equals(this.feeChargesOverdue, getLoansLoanIdSummary.feeChargesOverdue)
				&& Objects.equals(this.feeChargesPaid, getLoansLoanIdSummary.feeChargesPaid)
				&& Objects.equals(this.feeChargesWaived, getLoansLoanIdSummary.feeChargesWaived)
				&& Objects.equals(this.feeChargesWrittenOff, getLoansLoanIdSummary.feeChargesWrittenOff)
				&& Objects.equals(this.fixedEmiAmount, getLoansLoanIdSummary.fixedEmiAmount)
				&& Objects.equals(this.inArrears, getLoansLoanIdSummary.inArrears)
				&& Objects.equals(this.interestCharged, getLoansLoanIdSummary.interestCharged)
				&& Objects.equals(this.interestOutstanding, getLoansLoanIdSummary.interestOutstanding)
				&& Objects.equals(this.interestOverdue, getLoansLoanIdSummary.interestOverdue)
				&& Objects.equals(this.interestPaid, getLoansLoanIdSummary.interestPaid)
				&& Objects.equals(this.interestWaived, getLoansLoanIdSummary.interestWaived)
				&& Objects.equals(this.interestWrittenOff, getLoansLoanIdSummary.interestWrittenOff)
				&& Objects.equals(this.isNPA, getLoansLoanIdSummary.isNPA)
				&& Objects.equals(this.linkedAccount, getLoansLoanIdSummary.linkedAccount)
				&& Objects.equals(this.maxOutstandingLoanBalance, getLoansLoanIdSummary.maxOutstandingLoanBalance)
				&& Objects.equals(this.overdueCharges, getLoansLoanIdSummary.overdueCharges)
				&& Objects.equals(this.overdueSinceDate, getLoansLoanIdSummary.overdueSinceDate)
				&& Objects.equals(this.penaltyChargesCharged, getLoansLoanIdSummary.penaltyChargesCharged)
				&& Objects.equals(this.penaltyChargesOutstanding, getLoansLoanIdSummary.penaltyChargesOutstanding)
				&& Objects.equals(this.penaltyChargesOverdue, getLoansLoanIdSummary.penaltyChargesOverdue)
				&& Objects.equals(this.penaltyChargesPaid, getLoansLoanIdSummary.penaltyChargesPaid)
				&& Objects.equals(this.penaltyChargesWaived, getLoansLoanIdSummary.penaltyChargesWaived)
				&& Objects.equals(this.penaltyChargesWrittenOff, getLoansLoanIdSummary.penaltyChargesWrittenOff)
				&& Objects.equals(this.principalDisbursed, getLoansLoanIdSummary.principalDisbursed)
				&& Objects.equals(this.principalOutstanding, getLoansLoanIdSummary.principalOutstanding)
				&& Objects.equals(this.principalOverdue, getLoansLoanIdSummary.principalOverdue)
				&& Objects.equals(this.principalPaid, getLoansLoanIdSummary.principalPaid)
				&& Objects.equals(this.principalWrittenOff, getLoansLoanIdSummary.principalWrittenOff)
				&& Objects.equals(this.totalCostOfLoan, getLoansLoanIdSummary.totalCostOfLoan)
				&& Objects.equals(this.totalExpectedCostOfLoan, getLoansLoanIdSummary.totalExpectedCostOfLoan)
				&& Objects.equals(this.totalExpectedRepayment, getLoansLoanIdSummary.totalExpectedRepayment)
				&& Objects.equals(this.totalOutstanding, getLoansLoanIdSummary.totalOutstanding)
				&& Objects.equals(this.totalOverdue, getLoansLoanIdSummary.totalOverdue)
				&& Objects.equals(this.totalRepayment, getLoansLoanIdSummary.totalRepayment)
				&& Objects.equals(this.totalWaived, getLoansLoanIdSummary.totalWaived)
				&& Objects.equals(this.totalWrittenOff, getLoansLoanIdSummary.totalWrittenOff);
	}

	@Override
	public int hashCode() {
		return Objects.hash(canDisburse, currency, disbursementDetails, emiAmountVariations, feeChargesCharged,
				feeChargesDueAtDisbursementCharged, feeChargesOutstanding, feeChargesOverdue, feeChargesPaid,
				feeChargesWaived, feeChargesWrittenOff, fixedEmiAmount, inArrears, interestCharged, interestOutstanding,
				interestOverdue, interestPaid, interestWaived, interestWrittenOff, isNPA, linkedAccount,
				maxOutstandingLoanBalance, overdueCharges, overdueSinceDate, penaltyChargesCharged,
				penaltyChargesOutstanding, penaltyChargesOverdue, penaltyChargesPaid, penaltyChargesWaived,
				penaltyChargesWrittenOff, principalDisbursed, principalOutstanding, principalOverdue, principalPaid,
				principalWrittenOff, totalCostOfLoan, totalExpectedCostOfLoan, totalExpectedRepayment, totalOutstanding,
				totalOverdue, totalRepayment, totalWaived, totalWrittenOff);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdSummary {\n");
		sb.append("    canDisburse: ").append(toIndentedString(canDisburse)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    disbursementDetails: ").append(toIndentedString(disbursementDetails)).append("\n");
		sb.append("    emiAmountVariations: ").append(toIndentedString(emiAmountVariations)).append("\n");
		sb.append("    feeChargesCharged: ").append(toIndentedString(feeChargesCharged)).append("\n");
		sb.append("    feeChargesDueAtDisbursementCharged: ")
				.append(toIndentedString(feeChargesDueAtDisbursementCharged)).append("\n");
		sb.append("    feeChargesOutstanding: ").append(toIndentedString(feeChargesOutstanding)).append("\n");
		sb.append("    feeChargesOverdue: ").append(toIndentedString(feeChargesOverdue)).append("\n");
		sb.append("    feeChargesPaid: ").append(toIndentedString(feeChargesPaid)).append("\n");
		sb.append("    feeChargesWaived: ").append(toIndentedString(feeChargesWaived)).append("\n");
		sb.append("    feeChargesWrittenOff: ").append(toIndentedString(feeChargesWrittenOff)).append("\n");
		sb.append("    fixedEmiAmount: ").append(toIndentedString(fixedEmiAmount)).append("\n");
		sb.append("    inArrears: ").append(toIndentedString(inArrears)).append("\n");
		sb.append("    interestCharged: ").append(toIndentedString(interestCharged)).append("\n");
		sb.append("    interestOutstanding: ").append(toIndentedString(interestOutstanding)).append("\n");
		sb.append("    interestOverdue: ").append(toIndentedString(interestOverdue)).append("\n");
		sb.append("    interestPaid: ").append(toIndentedString(interestPaid)).append("\n");
		sb.append("    interestWaived: ").append(toIndentedString(interestWaived)).append("\n");
		sb.append("    interestWrittenOff: ").append(toIndentedString(interestWrittenOff)).append("\n");
		sb.append("    isNPA: ").append(toIndentedString(isNPA)).append("\n");
		sb.append("    linkedAccount: ").append(toIndentedString(linkedAccount)).append("\n");
		sb.append("    maxOutstandingLoanBalance: ").append(toIndentedString(maxOutstandingLoanBalance)).append("\n");
		sb.append("    overdueCharges: ").append(toIndentedString(overdueCharges)).append("\n");
		sb.append("    overdueSinceDate: ").append(toIndentedString(overdueSinceDate)).append("\n");
		sb.append("    penaltyChargesCharged: ").append(toIndentedString(penaltyChargesCharged)).append("\n");
		sb.append("    penaltyChargesOutstanding: ").append(toIndentedString(penaltyChargesOutstanding)).append("\n");
		sb.append("    penaltyChargesOverdue: ").append(toIndentedString(penaltyChargesOverdue)).append("\n");
		sb.append("    penaltyChargesPaid: ").append(toIndentedString(penaltyChargesPaid)).append("\n");
		sb.append("    penaltyChargesWaived: ").append(toIndentedString(penaltyChargesWaived)).append("\n");
		sb.append("    penaltyChargesWrittenOff: ").append(toIndentedString(penaltyChargesWrittenOff)).append("\n");
		sb.append("    principalDisbursed: ").append(toIndentedString(principalDisbursed)).append("\n");
		sb.append("    principalOutstanding: ").append(toIndentedString(principalOutstanding)).append("\n");
		sb.append("    principalOverdue: ").append(toIndentedString(principalOverdue)).append("\n");
		sb.append("    principalPaid: ").append(toIndentedString(principalPaid)).append("\n");
		sb.append("    principalWrittenOff: ").append(toIndentedString(principalWrittenOff)).append("\n");
		sb.append("    totalCostOfLoan: ").append(toIndentedString(totalCostOfLoan)).append("\n");
		sb.append("    totalExpectedCostOfLoan: ").append(toIndentedString(totalExpectedCostOfLoan)).append("\n");
		sb.append("    totalExpectedRepayment: ").append(toIndentedString(totalExpectedRepayment)).append("\n");
		sb.append("    totalOutstanding: ").append(toIndentedString(totalOutstanding)).append("\n");
		sb.append("    totalOverdue: ").append(toIndentedString(totalOverdue)).append("\n");
		sb.append("    totalRepayment: ").append(toIndentedString(totalRepayment)).append("\n");
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
