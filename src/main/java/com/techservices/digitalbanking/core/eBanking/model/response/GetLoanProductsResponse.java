/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoanProductsResponse */
@Schema(name = "GetLoanProductsResponse", description = "GetLoanProductsResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanProductsResponse {

	private GetLoanProductsAccountingRule accountingRule;

	private GetLoanProductsAmortizationType amortizationType;

	private Double annualInterestRate;

	private GetLoanProductsCurrency currency;

	private GetLoansProductsDaysInMonthType daysInMonthType;

	private GetLoansProductsDaysInYearType daysInYearType;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endDate;

	private BigDecimal fixedPrincipalPercentagePerInstallment;

	private Integer id;

	private Boolean includeInBorrowerCycle;

	private GetLoansProductsInterestCalculationPeriodType interestCalculationPeriodType;

	private GetLoanProductsInterestRateFrequencyType interestRateFrequencyType;

	private Double interestRatePerPeriod;

	@Valid
	private List<Integer> interestRateVariationsForBorrowerCycle = new ArrayList<>();

	private GetLoanProductsInterestRecalculationData interestRecalculationData;

	private GetLoanProductsInterestType interestType;

	private Boolean isInterestRecalculationEnabled;

	private Integer maxNumberOfRepayments;

	private Double maxPrincipal;

	private Integer minNumberOfRepayments;

	private Double minPrincipal;

	private String name;

	@Valid
	private List<Integer> numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<>();

	private Integer numberOfRepayments;

	private Double principal;

	private Integer principalThresholdForLastInstalment;

	@Valid
	private List<Integer> principalVariationsForBorrowerCycle = new ArrayList<>();

	private Integer repaymentEvery;

	private GetLoanProductsRepaymentFrequencyType repaymentFrequencyType;

	private String shortName;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;

	private String status;

	private Integer transactionProcessingStrategyId;

	private String transactionProcessingStrategyName;

	private Boolean useBorrowerCycle;

	public GetLoanProductsResponse accountingRule(GetLoanProductsAccountingRule accountingRule) {
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

	public GetLoanProductsResponse amortizationType(GetLoanProductsAmortizationType amortizationType) {
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

	public GetLoanProductsResponse annualInterestRate(Double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
		return this;
	}

	/**
	 * Get annualInterestRate
	 *
	 * @return annualInterestRate
	 */
	@Schema(name = "annualInterestRate", example = "15.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("annualInterestRate")
	public Double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(Double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public GetLoanProductsResponse currency(GetLoanProductsCurrency currency) {
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

	public GetLoanProductsResponse daysInMonthType(GetLoansProductsDaysInMonthType daysInMonthType) {
		this.daysInMonthType = daysInMonthType;
		return this;
	}

	/**
	 * Get daysInMonthType
	 *
	 * @return daysInMonthType
	 */
	@Valid
	@Schema(name = "daysInMonthType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("daysInMonthType")
	public GetLoansProductsDaysInMonthType getDaysInMonthType() {
		return daysInMonthType;
	}

	public void setDaysInMonthType(GetLoansProductsDaysInMonthType daysInMonthType) {
		this.daysInMonthType = daysInMonthType;
	}

	public GetLoanProductsResponse daysInYearType(GetLoansProductsDaysInYearType daysInYearType) {
		this.daysInYearType = daysInYearType;
		return this;
	}

	/**
	 * Get daysInYearType
	 *
	 * @return daysInYearType
	 */
	@Valid
	@Schema(name = "daysInYearType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("daysInYearType")
	public GetLoansProductsDaysInYearType getDaysInYearType() {
		return daysInYearType;
	}

	public void setDaysInYearType(GetLoansProductsDaysInYearType daysInYearType) {
		this.daysInYearType = daysInYearType;
	}

	public GetLoanProductsResponse endDate(LocalDate endDate) {
		this.endDate = endDate;
		return this;
	}

	/**
	 * Get endDate
	 *
	 * @return endDate
	 */
	@Valid
	@Schema(name = "endDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("endDate")
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public GetLoanProductsResponse fixedPrincipalPercentagePerInstallment(
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

	public GetLoanProductsResponse id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GetLoanProductsResponse includeInBorrowerCycle(Boolean includeInBorrowerCycle) {
		this.includeInBorrowerCycle = includeInBorrowerCycle;
		return this;
	}

	/**
	 * Get includeInBorrowerCycle
	 *
	 * @return includeInBorrowerCycle
	 */
	@Schema(name = "includeInBorrowerCycle", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("includeInBorrowerCycle")
	public Boolean getIncludeInBorrowerCycle() {
		return includeInBorrowerCycle;
	}

	public void setIncludeInBorrowerCycle(Boolean includeInBorrowerCycle) {
		this.includeInBorrowerCycle = includeInBorrowerCycle;
	}

	public GetLoanProductsResponse interestCalculationPeriodType(
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

	public GetLoanProductsResponse interestRateFrequencyType(
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

	public GetLoanProductsResponse interestRatePerPeriod(Double interestRatePerPeriod) {
		this.interestRatePerPeriod = interestRatePerPeriod;
		return this;
	}

	/**
	 * Get interestRatePerPeriod
	 *
	 * @return interestRatePerPeriod
	 */
	@Schema(name = "interestRatePerPeriod", example = "15.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRatePerPeriod")
	public Double getInterestRatePerPeriod() {
		return interestRatePerPeriod;
	}

	public void setInterestRatePerPeriod(Double interestRatePerPeriod) {
		this.interestRatePerPeriod = interestRatePerPeriod;
	}

	public GetLoanProductsResponse interestRateVariationsForBorrowerCycle(
			List<Integer> interestRateVariationsForBorrowerCycle) {
		this.interestRateVariationsForBorrowerCycle = interestRateVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsResponse addInterestRateVariationsForBorrowerCycleItem(
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

	public GetLoanProductsResponse interestRecalculationData(
			GetLoanProductsInterestRecalculationData interestRecalculationData) {
		this.interestRecalculationData = interestRecalculationData;
		return this;
	}

	/**
	 * Get interestRecalculationData
	 *
	 * @return interestRecalculationData
	 */
	@Valid
	@Schema(name = "interestRecalculationData", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRecalculationData")
	public GetLoanProductsInterestRecalculationData getInterestRecalculationData() {
		return interestRecalculationData;
	}

	public void setInterestRecalculationData(GetLoanProductsInterestRecalculationData interestRecalculationData) {
		this.interestRecalculationData = interestRecalculationData;
	}

	public GetLoanProductsResponse interestType(GetLoanProductsInterestType interestType) {
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
	public GetLoanProductsInterestType getInterestType() {
		return interestType;
	}

	public void setInterestType(GetLoanProductsInterestType interestType) {
		this.interestType = interestType;
	}

	public GetLoanProductsResponse isInterestRecalculationEnabled(Boolean isInterestRecalculationEnabled) {
		this.isInterestRecalculationEnabled = isInterestRecalculationEnabled;
		return this;
	}

	/**
	 * Get isInterestRecalculationEnabled
	 *
	 * @return isInterestRecalculationEnabled
	 */
	@Schema(name = "isInterestRecalculationEnabled", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("isInterestRecalculationEnabled")
	public Boolean getIsInterestRecalculationEnabled() {
		return isInterestRecalculationEnabled;
	}

	public void setIsInterestRecalculationEnabled(Boolean isInterestRecalculationEnabled) {
		this.isInterestRecalculationEnabled = isInterestRecalculationEnabled;
	}

	public GetLoanProductsResponse maxNumberOfRepayments(Integer maxNumberOfRepayments) {
		this.maxNumberOfRepayments = maxNumberOfRepayments;
		return this;
	}

	/**
	 * Get maxNumberOfRepayments
	 *
	 * @return maxNumberOfRepayments
	 */
	@Schema(name = "maxNumberOfRepayments", example = "15", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("maxNumberOfRepayments")
	public Integer getMaxNumberOfRepayments() {
		return maxNumberOfRepayments;
	}

	public void setMaxNumberOfRepayments(Integer maxNumberOfRepayments) {
		this.maxNumberOfRepayments = maxNumberOfRepayments;
	}

	public GetLoanProductsResponse maxPrincipal(Double maxPrincipal) {
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

	public GetLoanProductsResponse minNumberOfRepayments(Integer minNumberOfRepayments) {
		this.minNumberOfRepayments = minNumberOfRepayments;
		return this;
	}

	/**
	 * Get minNumberOfRepayments
	 *
	 * @return minNumberOfRepayments
	 */
	@Schema(name = "minNumberOfRepayments", example = "5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("minNumberOfRepayments")
	public Integer getMinNumberOfRepayments() {
		return minNumberOfRepayments;
	}

	public void setMinNumberOfRepayments(Integer minNumberOfRepayments) {
		this.minNumberOfRepayments = minNumberOfRepayments;
	}

	public GetLoanProductsResponse minPrincipal(Double minPrincipal) {
		this.minPrincipal = minPrincipal;
		return this;
	}

	/**
	 * Get minPrincipal
	 *
	 * @return minPrincipal
	 */
	@Schema(name = "minPrincipal", example = "5000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("minPrincipal")
	public Double getMinPrincipal() {
		return minPrincipal;
	}

	public void setMinPrincipal(Double minPrincipal) {
		this.minPrincipal = minPrincipal;
	}

	public GetLoanProductsResponse name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "personal loan product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GetLoanProductsResponse numberOfRepaymentVariationsForBorrowerCycle(
			List<Integer> numberOfRepaymentVariationsForBorrowerCycle) {
		this.numberOfRepaymentVariationsForBorrowerCycle = numberOfRepaymentVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsResponse addNumberOfRepaymentVariationsForBorrowerCycleItem(
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

	public GetLoanProductsResponse numberOfRepayments(Integer numberOfRepayments) {
		this.numberOfRepayments = numberOfRepayments;
		return this;
	}

	/**
	 * Get numberOfRepayments
	 *
	 * @return numberOfRepayments
	 */
	@Schema(name = "numberOfRepayments", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("numberOfRepayments")
	public Integer getNumberOfRepayments() {
		return numberOfRepayments;
	}

	public void setNumberOfRepayments(Integer numberOfRepayments) {
		this.numberOfRepayments = numberOfRepayments;
	}

	public GetLoanProductsResponse principal(Double principal) {
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

	public GetLoanProductsResponse principalThresholdForLastInstalment(Integer principalThresholdForLastInstalment) {
		this.principalThresholdForLastInstalment = principalThresholdForLastInstalment;
		return this;
	}

	/**
	 * Get principalThresholdForLastInstalment
	 *
	 * @return principalThresholdForLastInstalment
	 */
	@Schema(name = "principalThresholdForLastInstalment", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalThresholdForLastInstalment")
	public Integer getPrincipalThresholdForLastInstalment() {
		return principalThresholdForLastInstalment;
	}

	public void setPrincipalThresholdForLastInstalment(Integer principalThresholdForLastInstalment) {
		this.principalThresholdForLastInstalment = principalThresholdForLastInstalment;
	}

	public GetLoanProductsResponse principalVariationsForBorrowerCycle(
			List<Integer> principalVariationsForBorrowerCycle) {
		this.principalVariationsForBorrowerCycle = principalVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsResponse addPrincipalVariationsForBorrowerCycleItem(
			Integer principalVariationsForBorrowerCycleItem) {
		if (this.principalVariationsForBorrowerCycle == null) {
			this.principalVariationsForBorrowerCycle = new ArrayList<>();
		}
		this.principalVariationsForBorrowerCycle.add(principalVariationsForBorrowerCycleItem);
		return this;
	}

	/**
	 * Get principalVariationsForBorrowerCycle
	 *
	 * @return principalVariationsForBorrowerCycle
	 */
	@Schema(name = "principalVariationsForBorrowerCycle", example = "[]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalVariationsForBorrowerCycle")
	public List<Integer> getPrincipalVariationsForBorrowerCycle() {
		return principalVariationsForBorrowerCycle;
	}

	public void setPrincipalVariationsForBorrowerCycle(List<Integer> principalVariationsForBorrowerCycle) {
		this.principalVariationsForBorrowerCycle = principalVariationsForBorrowerCycle;
	}

	public GetLoanProductsResponse repaymentEvery(Integer repaymentEvery) {
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

	public GetLoanProductsResponse repaymentFrequencyType(
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

	public GetLoanProductsResponse shortName(String shortName) {
		this.shortName = shortName;
		return this;
	}

	/**
	 * Get shortName
	 *
	 * @return shortName
	 */
	@Schema(name = "shortName", example = "pe1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("shortName")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public GetLoanProductsResponse startDate(LocalDate startDate) {
		this.startDate = startDate;
		return this;
	}

	/**
	 * Get startDate
	 *
	 * @return startDate
	 */
	@Valid
	@Schema(name = "startDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("startDate")
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public GetLoanProductsResponse status(String status) {
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

	public GetLoanProductsResponse transactionProcessingStrategyId(Integer transactionProcessingStrategyId) {
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

	public GetLoanProductsResponse transactionProcessingStrategyName(String transactionProcessingStrategyName) {
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

	public GetLoanProductsResponse useBorrowerCycle(Boolean useBorrowerCycle) {
		this.useBorrowerCycle = useBorrowerCycle;
		return this;
	}

	/**
	 * Get useBorrowerCycle
	 *
	 * @return useBorrowerCycle
	 */
	@Schema(name = "useBorrowerCycle", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
		GetLoanProductsResponse getLoanProductsResponse = (GetLoanProductsResponse) o;
		return Objects.equals(this.accountingRule, getLoanProductsResponse.accountingRule)
				&& Objects.equals(this.amortizationType, getLoanProductsResponse.amortizationType)
				&& Objects.equals(this.annualInterestRate, getLoanProductsResponse.annualInterestRate)
				&& Objects.equals(this.currency, getLoanProductsResponse.currency)
				&& Objects.equals(this.daysInMonthType, getLoanProductsResponse.daysInMonthType)
				&& Objects.equals(this.daysInYearType, getLoanProductsResponse.daysInYearType)
				&& Objects.equals(this.endDate, getLoanProductsResponse.endDate)
				&& Objects.equals(this.fixedPrincipalPercentagePerInstallment,
						getLoanProductsResponse.fixedPrincipalPercentagePerInstallment)
				&& Objects.equals(this.id, getLoanProductsResponse.id)
				&& Objects.equals(this.includeInBorrowerCycle, getLoanProductsResponse.includeInBorrowerCycle)
				&& Objects.equals(this.interestCalculationPeriodType,
						getLoanProductsResponse.interestCalculationPeriodType)
				&& Objects.equals(this.interestRateFrequencyType, getLoanProductsResponse.interestRateFrequencyType)
				&& Objects.equals(this.interestRatePerPeriod, getLoanProductsResponse.interestRatePerPeriod)
				&& Objects.equals(this.interestRateVariationsForBorrowerCycle,
						getLoanProductsResponse.interestRateVariationsForBorrowerCycle)
				&& Objects.equals(this.interestRecalculationData, getLoanProductsResponse.interestRecalculationData)
				&& Objects.equals(this.interestType, getLoanProductsResponse.interestType)
				&& Objects.equals(this.isInterestRecalculationEnabled,
						getLoanProductsResponse.isInterestRecalculationEnabled)
				&& Objects.equals(this.maxNumberOfRepayments, getLoanProductsResponse.maxNumberOfRepayments)
				&& Objects.equals(this.maxPrincipal, getLoanProductsResponse.maxPrincipal)
				&& Objects.equals(this.minNumberOfRepayments, getLoanProductsResponse.minNumberOfRepayments)
				&& Objects.equals(this.minPrincipal, getLoanProductsResponse.minPrincipal)
				&& Objects.equals(this.name, getLoanProductsResponse.name)
				&& Objects.equals(this.numberOfRepaymentVariationsForBorrowerCycle,
						getLoanProductsResponse.numberOfRepaymentVariationsForBorrowerCycle)
				&& Objects.equals(this.numberOfRepayments, getLoanProductsResponse.numberOfRepayments)
				&& Objects.equals(this.principal, getLoanProductsResponse.principal)
				&& Objects.equals(this.principalThresholdForLastInstalment,
						getLoanProductsResponse.principalThresholdForLastInstalment)
				&& Objects.equals(this.principalVariationsForBorrowerCycle,
						getLoanProductsResponse.principalVariationsForBorrowerCycle)
				&& Objects.equals(this.repaymentEvery, getLoanProductsResponse.repaymentEvery)
				&& Objects.equals(this.repaymentFrequencyType, getLoanProductsResponse.repaymentFrequencyType)
				&& Objects.equals(this.shortName, getLoanProductsResponse.shortName)
				&& Objects.equals(this.startDate, getLoanProductsResponse.startDate)
				&& Objects.equals(this.status, getLoanProductsResponse.status)
				&& Objects.equals(this.transactionProcessingStrategyId,
						getLoanProductsResponse.transactionProcessingStrategyId)
				&& Objects.equals(this.transactionProcessingStrategyName,
						getLoanProductsResponse.transactionProcessingStrategyName)
				&& Objects.equals(this.useBorrowerCycle, getLoanProductsResponse.useBorrowerCycle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountingRule, amortizationType, annualInterestRate, currency, daysInMonthType,
				daysInYearType, endDate, fixedPrincipalPercentagePerInstallment, id, includeInBorrowerCycle,
				interestCalculationPeriodType, interestRateFrequencyType, interestRatePerPeriod,
				interestRateVariationsForBorrowerCycle, interestRecalculationData, interestType,
				isInterestRecalculationEnabled, maxNumberOfRepayments, maxPrincipal, minNumberOfRepayments,
				minPrincipal, name, numberOfRepaymentVariationsForBorrowerCycle, numberOfRepayments, principal,
				principalThresholdForLastInstalment, principalVariationsForBorrowerCycle, repaymentEvery,
				repaymentFrequencyType, shortName, startDate, status, transactionProcessingStrategyId,
				transactionProcessingStrategyName, useBorrowerCycle);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanProductsResponse {\n");
		sb.append("    accountingRule: ").append(toIndentedString(accountingRule)).append("\n");
		sb.append("    amortizationType: ").append(toIndentedString(amortizationType)).append("\n");
		sb.append("    annualInterestRate: ").append(toIndentedString(annualInterestRate)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    daysInMonthType: ").append(toIndentedString(daysInMonthType)).append("\n");
		sb.append("    daysInYearType: ").append(toIndentedString(daysInYearType)).append("\n");
		sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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
		sb.append("    interestRecalculationData: ").append(toIndentedString(interestRecalculationData)).append("\n");
		sb.append("    interestType: ").append(toIndentedString(interestType)).append("\n");
		sb.append("    isInterestRecalculationEnabled: ").append(toIndentedString(isInterestRecalculationEnabled))
				.append("\n");
		sb.append("    maxNumberOfRepayments: ").append(toIndentedString(maxNumberOfRepayments)).append("\n");
		sb.append("    maxPrincipal: ").append(toIndentedString(maxPrincipal)).append("\n");
		sb.append("    minNumberOfRepayments: ").append(toIndentedString(minNumberOfRepayments)).append("\n");
		sb.append("    minPrincipal: ").append(toIndentedString(minPrincipal)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    numberOfRepaymentVariationsForBorrowerCycle: ")
				.append(toIndentedString(numberOfRepaymentVariationsForBorrowerCycle)).append("\n");
		sb.append("    numberOfRepayments: ").append(toIndentedString(numberOfRepayments)).append("\n");
		sb.append("    principal: ").append(toIndentedString(principal)).append("\n");
		sb.append("    principalThresholdForLastInstalment: ")
				.append(toIndentedString(principalThresholdForLastInstalment)).append("\n");
		sb.append("    principalVariationsForBorrowerCycle: ")
				.append(toIndentedString(principalVariationsForBorrowerCycle)).append("\n");
		sb.append("    repaymentEvery: ").append(toIndentedString(repaymentEvery)).append("\n");
		sb.append("    repaymentFrequencyType: ").append(toIndentedString(repaymentFrequencyType)).append("\n");
		sb.append("    shortName: ").append(toIndentedString(shortName)).append("\n");
		sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
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
