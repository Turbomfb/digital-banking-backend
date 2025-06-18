/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoanProductsProductIdResponse */
@Schema(name = "GetLoanProductsProductIdResponse", description = "GetLoanProductsProductIdResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanProductsProductIdResponse {

	private GetLoanAccountingMappings accountingMappings;

	private GetLoanProductsAccountingRule accountingRule;

	private GetLoanProductsAmortizationType amortizationType;

	private Double annualInterestRate;

	@Valid
	private List<Integer> charges = new ArrayList<>();

	private GetLoanProductsCurrency currency;

	@Valid
	private Set<@Valid GetLoanFeeToIncomeAccountMappings> feeToIncomeAccountMappings = new LinkedHashSet<>();

	private BigDecimal fixedPrincipalPercentagePerInstallment;

	private Integer id;

	private Boolean includeInBorrowerCycle;

	private GetLoansProductsInterestCalculationPeriodType interestCalculationPeriodType;

	private GetLoanProductsInterestRateFrequencyType interestRateFrequencyType;

	private Double interestRatePerPeriod;

	@Valid
	private List<Integer> interestRateVariationsForBorrowerCycle = new ArrayList<>();

	private GetLoanProductsInterestTemplateType interestType;

	private Double maxPrincipal;

	private Integer maxTrancheCount;

	private Double minPrincipal;

	private Boolean multiDisburseLoan;

	private String name;

	@Valid
	private List<Integer> numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<>();

	private Integer numberOfRepayments;

	private Double outstandingLoanBalance;

	private Integer overdueDaysForNPA;

	@Valid
	private Set<@Valid GetLoanPaymentChannelToFundSourceMappings> paymentChannelToFundSourceMappings = new LinkedHashSet<>();

	private Double principal;

	private Integer principalThresholdForLastInstalment;

	@Valid
	private Set<@Valid GetLoanProductsPrincipalVariationsForBorrowerCycle> productsPrincipalVariationsForBorrowerCycle = new LinkedHashSet<>();

	private Integer repaymentEvery;

	private GetLoanProductsRepaymentFrequencyType repaymentFrequencyType;

	private String shortName;

	private String status;

	private Integer transactionProcessingStrategyId;

	private String transactionProcessingStrategyName;

	private Boolean useBorrowerCycle;

	public GetLoanProductsProductIdResponse accountingMappings(GetLoanAccountingMappings accountingMappings) {
		this.accountingMappings = accountingMappings;
		return this;
	}

	/**
	 * Get accountingMappings
	 *
	 * @return accountingMappings
	 */
	@Valid
	@Schema(name = "accountingMappings", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountingMappings")
	public GetLoanAccountingMappings getAccountingMappings() {
		return accountingMappings;
	}

	public void setAccountingMappings(GetLoanAccountingMappings accountingMappings) {
		this.accountingMappings = accountingMappings;
	}

	public GetLoanProductsProductIdResponse accountingRule(GetLoanProductsAccountingRule accountingRule) {
		this.accountingRule = accountingRule;
		return this;
	}

	/**
	 * Get accountingRule
	 *
	 * @return accountingRule
	 */
	@Valid
	@Schema(name = "accountingRule", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountingRule")
	public GetLoanProductsAccountingRule getAccountingRule() {
		return accountingRule;
	}

	public void setAccountingRule(GetLoanProductsAccountingRule accountingRule) {
		this.accountingRule = accountingRule;
	}

	public GetLoanProductsProductIdResponse amortizationType(GetLoanProductsAmortizationType amortizationType) {
		this.amortizationType = amortizationType;
		return this;
	}

	/**
	 * Get amortizationType
	 *
	 * @return amortizationType
	 */
	@Valid
	@Schema(name = "amortizationType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amortizationType")
	public GetLoanProductsAmortizationType getAmortizationType() {
		return amortizationType;
	}

	public void setAmortizationType(GetLoanProductsAmortizationType amortizationType) {
		this.amortizationType = amortizationType;
	}

	public GetLoanProductsProductIdResponse annualInterestRate(Double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
		return this;
	}

	/**
	 * Get annualInterestRate
	 *
	 * @return annualInterestRate
	 */
	@Schema(name = "annualInterestRate", example = "60.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("annualInterestRate")
	public Double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(Double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public GetLoanProductsProductIdResponse charges(List<Integer> charges) {
		this.charges = charges;
		return this;
	}

	public GetLoanProductsProductIdResponse addChargesItem(Integer chargesItem) {
		if (this.charges == null) {
			this.charges = new ArrayList<>();
		}
		this.charges.add(chargesItem);
		return this;
	}

	/**
	 * Get charges
	 *
	 * @return charges
	 */
	@Schema(name = "charges", example = "[]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("charges")
	public List<Integer> getCharges() {
		return charges;
	}

	public void setCharges(List<Integer> charges) {
		this.charges = charges;
	}

	public GetLoanProductsProductIdResponse currency(GetLoanProductsCurrency currency) {
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
	public GetLoanProductsCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(GetLoanProductsCurrency currency) {
		this.currency = currency;
	}

	public GetLoanProductsProductIdResponse feeToIncomeAccountMappings(
			Set<@Valid GetLoanFeeToIncomeAccountMappings> feeToIncomeAccountMappings) {
		this.feeToIncomeAccountMappings = feeToIncomeAccountMappings;
		return this;
	}

	public GetLoanProductsProductIdResponse addFeeToIncomeAccountMappingsItem(
			GetLoanFeeToIncomeAccountMappings feeToIncomeAccountMappingsItem) {
		if (this.feeToIncomeAccountMappings == null) {
			this.feeToIncomeAccountMappings = new LinkedHashSet<>();
		}
		this.feeToIncomeAccountMappings.add(feeToIncomeAccountMappingsItem);
		return this;
	}

	/**
	 * Get feeToIncomeAccountMappings
	 *
	 * @return feeToIncomeAccountMappings
	 */
	@Valid
	@Schema(name = "feeToIncomeAccountMappings", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeToIncomeAccountMappings")
	public Set<@Valid GetLoanFeeToIncomeAccountMappings> getFeeToIncomeAccountMappings() {
		return feeToIncomeAccountMappings;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setFeeToIncomeAccountMappings(
			Set<@Valid GetLoanFeeToIncomeAccountMappings> feeToIncomeAccountMappings) {
		this.feeToIncomeAccountMappings = feeToIncomeAccountMappings;
	}

	public GetLoanProductsProductIdResponse fixedPrincipalPercentagePerInstallment(
			BigDecimal fixedPrincipalPercentagePerInstallment) {
		this.fixedPrincipalPercentagePerInstallment = fixedPrincipalPercentagePerInstallment;
		return this;
	}

	/**
	 * Get fixedPrincipalPercentagePerInstallment
	 *
	 * @return fixedPrincipalPercentagePerInstallment
	 */
	@Valid
	@Schema(name = "fixedPrincipalPercentagePerInstallment", example = "5.5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("fixedPrincipalPercentagePerInstallment")
	public BigDecimal getFixedPrincipalPercentagePerInstallment() {
		return fixedPrincipalPercentagePerInstallment;
	}

	public void setFixedPrincipalPercentagePerInstallment(BigDecimal fixedPrincipalPercentagePerInstallment) {
		this.fixedPrincipalPercentagePerInstallment = fixedPrincipalPercentagePerInstallment;
	}

	public GetLoanProductsProductIdResponse id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "11", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GetLoanProductsProductIdResponse includeInBorrowerCycle(Boolean includeInBorrowerCycle) {
		this.includeInBorrowerCycle = includeInBorrowerCycle;
		return this;
	}

	/**
	 * Get includeInBorrowerCycle
	 *
	 * @return includeInBorrowerCycle
	 */
	@Schema(name = "includeInBorrowerCycle", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("includeInBorrowerCycle")
	public Boolean getIncludeInBorrowerCycle() {
		return includeInBorrowerCycle;
	}

	public void setIncludeInBorrowerCycle(Boolean includeInBorrowerCycle) {
		this.includeInBorrowerCycle = includeInBorrowerCycle;
	}

	public GetLoanProductsProductIdResponse interestCalculationPeriodType(
			GetLoansProductsInterestCalculationPeriodType interestCalculationPeriodType) {
		this.interestCalculationPeriodType = interestCalculationPeriodType;
		return this;
	}

	/**
	 * Get interestCalculationPeriodType
	 *
	 * @return interestCalculationPeriodType
	 */
	@Valid
	@Schema(name = "interestCalculationPeriodType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestCalculationPeriodType")
	public GetLoansProductsInterestCalculationPeriodType getInterestCalculationPeriodType() {
		return interestCalculationPeriodType;
	}

	public void setInterestCalculationPeriodType(
			GetLoansProductsInterestCalculationPeriodType interestCalculationPeriodType) {
		this.interestCalculationPeriodType = interestCalculationPeriodType;
	}

	public GetLoanProductsProductIdResponse interestRateFrequencyType(
			GetLoanProductsInterestRateFrequencyType interestRateFrequencyType) {
		this.interestRateFrequencyType = interestRateFrequencyType;
		return this;
	}

	/**
	 * Get interestRateFrequencyType
	 *
	 * @return interestRateFrequencyType
	 */
	@Valid
	@Schema(name = "interestRateFrequencyType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRateFrequencyType")
	public GetLoanProductsInterestRateFrequencyType getInterestRateFrequencyType() {
		return interestRateFrequencyType;
	}

	public void setInterestRateFrequencyType(GetLoanProductsInterestRateFrequencyType interestRateFrequencyType) {
		this.interestRateFrequencyType = interestRateFrequencyType;
	}

	public GetLoanProductsProductIdResponse interestRatePerPeriod(Double interestRatePerPeriod) {
		this.interestRatePerPeriod = interestRatePerPeriod;
		return this;
	}

	/**
	 * Get interestRatePerPeriod
	 *
	 * @return interestRatePerPeriod
	 */
	@Schema(name = "interestRatePerPeriod", example = "5.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRatePerPeriod")
	public Double getInterestRatePerPeriod() {
		return interestRatePerPeriod;
	}

	public void setInterestRatePerPeriod(Double interestRatePerPeriod) {
		this.interestRatePerPeriod = interestRatePerPeriod;
	}

	public GetLoanProductsProductIdResponse interestRateVariationsForBorrowerCycle(
			List<Integer> interestRateVariationsForBorrowerCycle) {
		this.interestRateVariationsForBorrowerCycle = interestRateVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsProductIdResponse addInterestRateVariationsForBorrowerCycleItem(
			Integer interestRateVariationsForBorrowerCycleItem) {
		if (this.interestRateVariationsForBorrowerCycle == null) {
			this.interestRateVariationsForBorrowerCycle = new ArrayList<>();
		}
		this.interestRateVariationsForBorrowerCycle.add(interestRateVariationsForBorrowerCycleItem);
		return this;
	}

	/**
	 * Get interestRateVariationsForBorrowerCycle
	 *
	 * @return interestRateVariationsForBorrowerCycle
	 */
	@Schema(name = "interestRateVariationsForBorrowerCycle", example = "[]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRateVariationsForBorrowerCycle")
	public List<Integer> getInterestRateVariationsForBorrowerCycle() {
		return interestRateVariationsForBorrowerCycle;
	}

	public void setInterestRateVariationsForBorrowerCycle(List<Integer> interestRateVariationsForBorrowerCycle) {
		this.interestRateVariationsForBorrowerCycle = interestRateVariationsForBorrowerCycle;
	}

	public GetLoanProductsProductIdResponse interestType(GetLoanProductsInterestTemplateType interestType) {
		this.interestType = interestType;
		return this;
	}

	/**
	 * Get interestType
	 *
	 * @return interestType
	 */
	@Valid
	@Schema(name = "interestType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestType")
	public GetLoanProductsInterestTemplateType getInterestType() {
		return interestType;
	}

	public void setInterestType(GetLoanProductsInterestTemplateType interestType) {
		this.interestType = interestType;
	}

	public GetLoanProductsProductIdResponse maxPrincipal(Double maxPrincipal) {
		this.maxPrincipal = maxPrincipal;
		return this;
	}

	/**
	 * Get maxPrincipal
	 *
	 * @return maxPrincipal
	 */
	@Schema(name = "maxPrincipal", example = "15000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("maxPrincipal")
	public Double getMaxPrincipal() {
		return maxPrincipal;
	}

	public void setMaxPrincipal(Double maxPrincipal) {
		this.maxPrincipal = maxPrincipal;
	}

	public GetLoanProductsProductIdResponse maxTrancheCount(Integer maxTrancheCount) {
		this.maxTrancheCount = maxTrancheCount;
		return this;
	}

	/**
	 * Get maxTrancheCount
	 *
	 * @return maxTrancheCount
	 */
	@Schema(name = "maxTrancheCount", example = "3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("maxTrancheCount")
	public Integer getMaxTrancheCount() {
		return maxTrancheCount;
	}

	public void setMaxTrancheCount(Integer maxTrancheCount) {
		this.maxTrancheCount = maxTrancheCount;
	}

	public GetLoanProductsProductIdResponse minPrincipal(Double minPrincipal) {
		this.minPrincipal = minPrincipal;
		return this;
	}

	/**
	 * Get minPrincipal
	 *
	 * @return minPrincipal
	 */
	@Schema(name = "minPrincipal", example = "2000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("minPrincipal")
	public Double getMinPrincipal() {
		return minPrincipal;
	}

	public void setMinPrincipal(Double minPrincipal) {
		this.minPrincipal = minPrincipal;
	}

	public GetLoanProductsProductIdResponse multiDisburseLoan(Boolean multiDisburseLoan) {
		this.multiDisburseLoan = multiDisburseLoan;
		return this;
	}

	/**
	 * Get multiDisburseLoan
	 *
	 * @return multiDisburseLoan
	 */
	@Schema(name = "multiDisburseLoan", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("multiDisburseLoan")
	public Boolean getMultiDisburseLoan() {
		return multiDisburseLoan;
	}

	public void setMultiDisburseLoan(Boolean multiDisburseLoan) {
		this.multiDisburseLoan = multiDisburseLoan;
	}

	public GetLoanProductsProductIdResponse name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "advanced accounting", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GetLoanProductsProductIdResponse numberOfRepaymentVariationsForBorrowerCycle(
			List<Integer> numberOfRepaymentVariationsForBorrowerCycle) {
		this.numberOfRepaymentVariationsForBorrowerCycle = numberOfRepaymentVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsProductIdResponse addNumberOfRepaymentVariationsForBorrowerCycleItem(
			Integer numberOfRepaymentVariationsForBorrowerCycleItem) {
		if (this.numberOfRepaymentVariationsForBorrowerCycle == null) {
			this.numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<>();
		}
		this.numberOfRepaymentVariationsForBorrowerCycle.add(numberOfRepaymentVariationsForBorrowerCycleItem);
		return this;
	}

	/**
	 * Get numberOfRepaymentVariationsForBorrowerCycle
	 *
	 * @return numberOfRepaymentVariationsForBorrowerCycle
	 */
	@Schema(name = "numberOfRepaymentVariationsForBorrowerCycle", example = "[]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("numberOfRepaymentVariationsForBorrowerCycle")
	public List<Integer> getNumberOfRepaymentVariationsForBorrowerCycle() {
		return numberOfRepaymentVariationsForBorrowerCycle;
	}

	public void setNumberOfRepaymentVariationsForBorrowerCycle(
			List<Integer> numberOfRepaymentVariationsForBorrowerCycle) {
		this.numberOfRepaymentVariationsForBorrowerCycle = numberOfRepaymentVariationsForBorrowerCycle;
	}

	public GetLoanProductsProductIdResponse numberOfRepayments(Integer numberOfRepayments) {
		this.numberOfRepayments = numberOfRepayments;
		return this;
	}

	/**
	 * Get numberOfRepayments
	 *
	 * @return numberOfRepayments
	 */
	@Schema(name = "numberOfRepayments", example = "7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("numberOfRepayments")
	public Integer getNumberOfRepayments() {
		return numberOfRepayments;
	}

	public void setNumberOfRepayments(Integer numberOfRepayments) {
		this.numberOfRepayments = numberOfRepayments;
	}

	public GetLoanProductsProductIdResponse outstandingLoanBalance(Double outstandingLoanBalance) {
		this.outstandingLoanBalance = outstandingLoanBalance;
		return this;
	}

	/**
	 * Get outstandingLoanBalance
	 *
	 * @return outstandingLoanBalance
	 */
	@Schema(name = "outstandingLoanBalance", example = "36000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("outstandingLoanBalance")
	public Double getOutstandingLoanBalance() {
		return outstandingLoanBalance;
	}

	public void setOutstandingLoanBalance(Double outstandingLoanBalance) {
		this.outstandingLoanBalance = outstandingLoanBalance;
	}

	public GetLoanProductsProductIdResponse overdueDaysForNPA(Integer overdueDaysForNPA) {
		this.overdueDaysForNPA = overdueDaysForNPA;
		return this;
	}

	/**
	 * Get overdueDaysForNPA
	 *
	 * @return overdueDaysForNPA
	 */
	@Schema(name = "overdueDaysForNPA", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("overdueDaysForNPA")
	public Integer getOverdueDaysForNPA() {
		return overdueDaysForNPA;
	}

	public void setOverdueDaysForNPA(Integer overdueDaysForNPA) {
		this.overdueDaysForNPA = overdueDaysForNPA;
	}

	public GetLoanProductsProductIdResponse paymentChannelToFundSourceMappings(
			Set<@Valid GetLoanPaymentChannelToFundSourceMappings> paymentChannelToFundSourceMappings) {
		this.paymentChannelToFundSourceMappings = paymentChannelToFundSourceMappings;
		return this;
	}

	public GetLoanProductsProductIdResponse addPaymentChannelToFundSourceMappingsItem(
			GetLoanPaymentChannelToFundSourceMappings paymentChannelToFundSourceMappingsItem) {
		if (this.paymentChannelToFundSourceMappings == null) {
			this.paymentChannelToFundSourceMappings = new LinkedHashSet<>();
		}
		this.paymentChannelToFundSourceMappings.add(paymentChannelToFundSourceMappingsItem);
		return this;
	}

	/**
	 * Get paymentChannelToFundSourceMappings
	 *
	 * @return paymentChannelToFundSourceMappings
	 */
	@Valid
	@Schema(name = "paymentChannelToFundSourceMappings", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("paymentChannelToFundSourceMappings")
	public Set<@Valid GetLoanPaymentChannelToFundSourceMappings> getPaymentChannelToFundSourceMappings() {
		return paymentChannelToFundSourceMappings;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setPaymentChannelToFundSourceMappings(
			Set<@Valid GetLoanPaymentChannelToFundSourceMappings> paymentChannelToFundSourceMappings) {
		this.paymentChannelToFundSourceMappings = paymentChannelToFundSourceMappings;
	}

	public GetLoanProductsProductIdResponse principal(Double principal) {
		this.principal = principal;
		return this;
	}

	/**
	 * Get principal
	 *
	 * @return principal
	 */
	@Schema(name = "principal", example = "10000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principal")
	public Double getPrincipal() {
		return principal;
	}

	public void setPrincipal(Double principal) {
		this.principal = principal;
	}

	public GetLoanProductsProductIdResponse principalThresholdForLastInstalment(
			Integer principalThresholdForLastInstalment) {
		this.principalThresholdForLastInstalment = principalThresholdForLastInstalment;
		return this;
	}

	/**
	 * Get principalThresholdForLastInstalment
	 *
	 * @return principalThresholdForLastInstalment
	 */
	@Schema(name = "principalThresholdForLastInstalment", example = "50", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalThresholdForLastInstalment")
	public Integer getPrincipalThresholdForLastInstalment() {
		return principalThresholdForLastInstalment;
	}

	public void setPrincipalThresholdForLastInstalment(Integer principalThresholdForLastInstalment) {
		this.principalThresholdForLastInstalment = principalThresholdForLastInstalment;
	}

	public GetLoanProductsProductIdResponse productsPrincipalVariationsForBorrowerCycle(
			Set<@Valid GetLoanProductsPrincipalVariationsForBorrowerCycle> productsPrincipalVariationsForBorrowerCycle) {
		this.productsPrincipalVariationsForBorrowerCycle = productsPrincipalVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsProductIdResponse addProductsPrincipalVariationsForBorrowerCycleItem(
			GetLoanProductsPrincipalVariationsForBorrowerCycle productsPrincipalVariationsForBorrowerCycleItem) {
		if (this.productsPrincipalVariationsForBorrowerCycle == null) {
			this.productsPrincipalVariationsForBorrowerCycle = new LinkedHashSet<>();
		}
		this.productsPrincipalVariationsForBorrowerCycle.add(productsPrincipalVariationsForBorrowerCycleItem);
		return this;
	}

	/**
	 * Get productsPrincipalVariationsForBorrowerCycle
	 *
	 * @return productsPrincipalVariationsForBorrowerCycle
	 */
	@Valid
	@Schema(name = "productsPrincipalVariationsForBorrowerCycle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("productsPrincipalVariationsForBorrowerCycle")
	public Set<@Valid GetLoanProductsPrincipalVariationsForBorrowerCycle> getProductsPrincipalVariationsForBorrowerCycle() {
		return productsPrincipalVariationsForBorrowerCycle;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setProductsPrincipalVariationsForBorrowerCycle(
			Set<@Valid GetLoanProductsPrincipalVariationsForBorrowerCycle> productsPrincipalVariationsForBorrowerCycle) {
		this.productsPrincipalVariationsForBorrowerCycle = productsPrincipalVariationsForBorrowerCycle;
	}

	public GetLoanProductsProductIdResponse repaymentEvery(Integer repaymentEvery) {
		this.repaymentEvery = repaymentEvery;
		return this;
	}

	/**
	 * Get repaymentEvery
	 *
	 * @return repaymentEvery
	 */
	@Schema(name = "repaymentEvery", example = "7", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("repaymentEvery")
	public Integer getRepaymentEvery() {
		return repaymentEvery;
	}

	public void setRepaymentEvery(Integer repaymentEvery) {
		this.repaymentEvery = repaymentEvery;
	}

	public GetLoanProductsProductIdResponse repaymentFrequencyType(
			GetLoanProductsRepaymentFrequencyType repaymentFrequencyType) {
		this.repaymentFrequencyType = repaymentFrequencyType;
		return this;
	}

	/**
	 * Get repaymentFrequencyType
	 *
	 * @return repaymentFrequencyType
	 */
	@Valid
	@Schema(name = "repaymentFrequencyType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("repaymentFrequencyType")
	public GetLoanProductsRepaymentFrequencyType getRepaymentFrequencyType() {
		return repaymentFrequencyType;
	}

	public void setRepaymentFrequencyType(GetLoanProductsRepaymentFrequencyType repaymentFrequencyType) {
		this.repaymentFrequencyType = repaymentFrequencyType;
	}

	public GetLoanProductsProductIdResponse shortName(String shortName) {
		this.shortName = shortName;
		return this;
	}

	/**
	 * Get shortName
	 *
	 * @return shortName
	 */
	@Schema(name = "shortName", example = "ad11", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("shortName")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public GetLoanProductsProductIdResponse status(String status) {
		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 */
	@Schema(name = "status", example = "loanProduct.active", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public GetLoanProductsProductIdResponse transactionProcessingStrategyId(Integer transactionProcessingStrategyId) {
		this.transactionProcessingStrategyId = transactionProcessingStrategyId;
		return this;
	}

	/**
	 * Get transactionProcessingStrategyId
	 *
	 * @return transactionProcessingStrategyId
	 */
	@Schema(name = "transactionProcessingStrategyId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("transactionProcessingStrategyId")
	public Integer getTransactionProcessingStrategyId() {
		return transactionProcessingStrategyId;
	}

	public void setTransactionProcessingStrategyId(Integer transactionProcessingStrategyId) {
		this.transactionProcessingStrategyId = transactionProcessingStrategyId;
	}

	public GetLoanProductsProductIdResponse transactionProcessingStrategyName(
			String transactionProcessingStrategyName) {
		this.transactionProcessingStrategyName = transactionProcessingStrategyName;
		return this;
	}

	/**
	 * Get transactionProcessingStrategyName
	 *
	 * @return transactionProcessingStrategyName
	 */
	@Schema(name = "transactionProcessingStrategyName", example = "Mifos style", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("transactionProcessingStrategyName")
	public String getTransactionProcessingStrategyName() {
		return transactionProcessingStrategyName;
	}

	public void setTransactionProcessingStrategyName(String transactionProcessingStrategyName) {
		this.transactionProcessingStrategyName = transactionProcessingStrategyName;
	}

	public GetLoanProductsProductIdResponse useBorrowerCycle(Boolean useBorrowerCycle) {
		this.useBorrowerCycle = useBorrowerCycle;
		return this;
	}

	/**
	 * Get useBorrowerCycle
	 *
	 * @return useBorrowerCycle
	 */
	@Schema(name = "useBorrowerCycle", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("useBorrowerCycle")
	public Boolean getUseBorrowerCycle() {
		return useBorrowerCycle;
	}

	public void setUseBorrowerCycle(Boolean useBorrowerCycle) {
		this.useBorrowerCycle = useBorrowerCycle;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanProductsProductIdResponse getLoanProductsProductIdResponse = (GetLoanProductsProductIdResponse) o;
		return Objects.equals(this.accountingMappings, getLoanProductsProductIdResponse.accountingMappings)
				&& Objects.equals(this.accountingRule, getLoanProductsProductIdResponse.accountingRule)
				&& Objects.equals(this.amortizationType, getLoanProductsProductIdResponse.amortizationType)
				&& Objects.equals(this.annualInterestRate, getLoanProductsProductIdResponse.annualInterestRate)
				&& Objects.equals(this.charges, getLoanProductsProductIdResponse.charges)
				&& Objects.equals(this.currency, getLoanProductsProductIdResponse.currency)
				&& Objects.equals(this.feeToIncomeAccountMappings,
						getLoanProductsProductIdResponse.feeToIncomeAccountMappings)
				&& Objects.equals(this.fixedPrincipalPercentagePerInstallment,
						getLoanProductsProductIdResponse.fixedPrincipalPercentagePerInstallment)
				&& Objects.equals(this.id, getLoanProductsProductIdResponse.id)
				&& Objects.equals(this.includeInBorrowerCycle, getLoanProductsProductIdResponse.includeInBorrowerCycle)
				&& Objects.equals(this.interestCalculationPeriodType,
						getLoanProductsProductIdResponse.interestCalculationPeriodType)
				&& Objects.equals(this.interestRateFrequencyType,
						getLoanProductsProductIdResponse.interestRateFrequencyType)
				&& Objects.equals(this.interestRatePerPeriod, getLoanProductsProductIdResponse.interestRatePerPeriod)
				&& Objects.equals(this.interestRateVariationsForBorrowerCycle,
						getLoanProductsProductIdResponse.interestRateVariationsForBorrowerCycle)
				&& Objects.equals(this.interestType, getLoanProductsProductIdResponse.interestType)
				&& Objects.equals(this.maxPrincipal, getLoanProductsProductIdResponse.maxPrincipal)
				&& Objects.equals(this.maxTrancheCount, getLoanProductsProductIdResponse.maxTrancheCount)
				&& Objects.equals(this.minPrincipal, getLoanProductsProductIdResponse.minPrincipal)
				&& Objects.equals(this.multiDisburseLoan, getLoanProductsProductIdResponse.multiDisburseLoan)
				&& Objects.equals(this.name, getLoanProductsProductIdResponse.name)
				&& Objects.equals(this.numberOfRepaymentVariationsForBorrowerCycle,
						getLoanProductsProductIdResponse.numberOfRepaymentVariationsForBorrowerCycle)
				&& Objects.equals(this.numberOfRepayments, getLoanProductsProductIdResponse.numberOfRepayments)
				&& Objects.equals(this.outstandingLoanBalance, getLoanProductsProductIdResponse.outstandingLoanBalance)
				&& Objects.equals(this.overdueDaysForNPA, getLoanProductsProductIdResponse.overdueDaysForNPA)
				&& Objects.equals(this.paymentChannelToFundSourceMappings,
						getLoanProductsProductIdResponse.paymentChannelToFundSourceMappings)
				&& Objects.equals(this.principal, getLoanProductsProductIdResponse.principal)
				&& Objects.equals(this.principalThresholdForLastInstalment,
						getLoanProductsProductIdResponse.principalThresholdForLastInstalment)
				&& Objects.equals(this.productsPrincipalVariationsForBorrowerCycle,
						getLoanProductsProductIdResponse.productsPrincipalVariationsForBorrowerCycle)
				&& Objects.equals(this.repaymentEvery, getLoanProductsProductIdResponse.repaymentEvery)
				&& Objects.equals(this.repaymentFrequencyType, getLoanProductsProductIdResponse.repaymentFrequencyType)
				&& Objects.equals(this.shortName, getLoanProductsProductIdResponse.shortName)
				&& Objects.equals(this.status, getLoanProductsProductIdResponse.status)
				&& Objects.equals(this.transactionProcessingStrategyId,
						getLoanProductsProductIdResponse.transactionProcessingStrategyId)
				&& Objects.equals(this.transactionProcessingStrategyName,
						getLoanProductsProductIdResponse.transactionProcessingStrategyName)
				&& Objects.equals(this.useBorrowerCycle, getLoanProductsProductIdResponse.useBorrowerCycle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountingMappings, accountingRule, amortizationType, annualInterestRate, charges, currency,
				feeToIncomeAccountMappings, fixedPrincipalPercentagePerInstallment, id, includeInBorrowerCycle,
				interestCalculationPeriodType, interestRateFrequencyType, interestRatePerPeriod,
				interestRateVariationsForBorrowerCycle, interestType, maxPrincipal, maxTrancheCount, minPrincipal,
				multiDisburseLoan, name, numberOfRepaymentVariationsForBorrowerCycle, numberOfRepayments,
				outstandingLoanBalance, overdueDaysForNPA, paymentChannelToFundSourceMappings, principal,
				principalThresholdForLastInstalment, productsPrincipalVariationsForBorrowerCycle, repaymentEvery,
				repaymentFrequencyType, shortName, status, transactionProcessingStrategyId,
				transactionProcessingStrategyName, useBorrowerCycle);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanProductsProductIdResponse {\n");
		sb.append("    accountingMappings: ").append(toIndentedString(accountingMappings)).append("\n");
		sb.append("    accountingRule: ").append(toIndentedString(accountingRule)).append("\n");
		sb.append("    amortizationType: ").append(toIndentedString(amortizationType)).append("\n");
		sb.append("    annualInterestRate: ").append(toIndentedString(annualInterestRate)).append("\n");
		sb.append("    charges: ").append(toIndentedString(charges)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    feeToIncomeAccountMappings: ").append(toIndentedString(feeToIncomeAccountMappings)).append("\n");
		sb.append("    fixedPrincipalPercentagePerInstallment: ")
				.append(toIndentedString(fixedPrincipalPercentagePerInstallment)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    includeInBorrowerCycle: ").append(toIndentedString(includeInBorrowerCycle)).append("\n");
		sb.append("    interestCalculationPeriodType: ").append(toIndentedString(interestCalculationPeriodType))
				.append("\n");
		sb.append("    interestRateFrequencyType: ").append(toIndentedString(interestRateFrequencyType)).append("\n");
		sb.append("    interestRatePerPeriod: ").append(toIndentedString(interestRatePerPeriod)).append("\n");
		sb.append("    interestRateVariationsForBorrowerCycle: ")
				.append(toIndentedString(interestRateVariationsForBorrowerCycle)).append("\n");
		sb.append("    interestType: ").append(toIndentedString(interestType)).append("\n");
		sb.append("    maxPrincipal: ").append(toIndentedString(maxPrincipal)).append("\n");
		sb.append("    maxTrancheCount: ").append(toIndentedString(maxTrancheCount)).append("\n");
		sb.append("    minPrincipal: ").append(toIndentedString(minPrincipal)).append("\n");
		sb.append("    multiDisburseLoan: ").append(toIndentedString(multiDisburseLoan)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    numberOfRepaymentVariationsForBorrowerCycle: ")
				.append(toIndentedString(numberOfRepaymentVariationsForBorrowerCycle)).append("\n");
		sb.append("    numberOfRepayments: ").append(toIndentedString(numberOfRepayments)).append("\n");
		sb.append("    outstandingLoanBalance: ").append(toIndentedString(outstandingLoanBalance)).append("\n");
		sb.append("    overdueDaysForNPA: ").append(toIndentedString(overdueDaysForNPA)).append("\n");
		sb.append("    paymentChannelToFundSourceMappings: ")
				.append(toIndentedString(paymentChannelToFundSourceMappings)).append("\n");
		sb.append("    principal: ").append(toIndentedString(principal)).append("\n");
		sb.append("    principalThresholdForLastInstalment: ")
				.append(toIndentedString(principalThresholdForLastInstalment)).append("\n");
		sb.append("    productsPrincipalVariationsForBorrowerCycle: ")
				.append(toIndentedString(productsPrincipalVariationsForBorrowerCycle)).append("\n");
		sb.append("    repaymentEvery: ").append(toIndentedString(repaymentEvery)).append("\n");
		sb.append("    repaymentFrequencyType: ").append(toIndentedString(repaymentFrequencyType)).append("\n");
		sb.append("    shortName: ").append(toIndentedString(shortName)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    transactionProcessingStrategyId: ").append(toIndentedString(transactionProcessingStrategyId))
				.append("\n");
		sb.append("    transactionProcessingStrategyName: ").append(toIndentedString(transactionProcessingStrategyName))
				.append("\n");
		sb.append("    useBorrowerCycle: ").append(toIndentedString(useBorrowerCycle)).append("\n");
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
