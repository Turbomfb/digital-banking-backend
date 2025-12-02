/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

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

/** GetLoanProductsTemplateResponse */
@Schema(name = "GetLoanProductsTemplateResponse", description = "GetLoanProductsTemplateResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanProductsTemplateResponse {

	private GetLoanProductsAccountingMappingOptions accountingMappingOptions;

	private GetLoanProductsAccountingRule accountingRule;

	@Valid
	private Set<@Valid GetLoanProductsAccountingRule> accountingRuleOptions = new LinkedHashSet<>();

	private GetLoanProductsAmortizationType amortizationType;

	@Valid
	private Set<@Valid GetLoanProductsAmortizationType> amortizationTypeOptions = new LinkedHashSet<>();

	@Valid
	private Set<@Valid GetLoanProductsChargeOptions> chargeOptions = new LinkedHashSet<>();

	private GetLoanProductsTemplateCurrency currency;

	@Valid
	private Set<@Valid GetLoanProductsCurrencyOptions> currencyOptions = new LinkedHashSet<>();

	private GetLoansProductsDaysInMonthTemplateType daysInMonthType;

	@Valid
	private Set<@Valid GetLoansProductsDaysInMonthTemplateType> daysInMonthTypeOptions = new LinkedHashSet<>();

	private GetLoanProductsDaysInYearTemplateType daysInYearType;

	@Valid
	private Set<@Valid GetLoanProductsInterestTemplateType> daysInYearTypeOptions = new LinkedHashSet<>();

	private Boolean includeInBorrowerCycle;

	private GetLoansProductsInterestCalculationPeriodType interestCalculationPeriodType;

	@Valid
	private Set<@Valid GetLoansProductsInterestCalculationPeriodType> interestCalculationPeriodTypeOptions = new LinkedHashSet<>();

	private GetLoanProductsInterestRateTemplateFrequencyType interestRateFrequencyType;

	@Valid
	private Set<@Valid GetLoanProductsInterestRateTemplateFrequencyType> interestRateFrequencyTypeOptions = new LinkedHashSet<>();

	@Valid
	private List<Integer> interestRateVariationsForBorrowerCycle = new ArrayList<>();

	@Valid
	private Set<@Valid GetLoanProductsInterestRecalculationCompoundingType> interestRecalculationCompoundingTypeOptions = new LinkedHashSet<>();

	private GetLoanProductsInterestRecalculationTemplateData interestRecalculationData;

	@Valid
	private Set<@Valid GetLoanProductsInterestRecalculationCompoundingFrequencyType> interestRecalculationFrequencyTypeOptions = new LinkedHashSet<>();

	private GetLoanProductsInterestTemplateType interestType;

	@Valid
	private Set<@Valid GetLoanProductsInterestTemplateType> interestTypeOptions = new LinkedHashSet<>();

	private Boolean isInterestRecalculationEnabled;

	@Valid
	private List<Integer> numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<>();

	@Valid
	private Set<@Valid GetLoanProductsPaymentTypeOptions> paymentTypeOptions = new LinkedHashSet<>();

	@Valid
	private Set<@Valid GetLoanProductsPreClosureInterestCalculationStrategy> preClosureInterestCalculationStrategyOptions = new LinkedHashSet<>();

	@Valid
	private List<Integer> principalVariationsForBorrowerCycle = new ArrayList<>();

	private GetLoanProductsRepaymentTemplateFrequencyType repaymentFrequencyType;

	@Valid
	private Set<@Valid GetLoanProductsRepaymentTemplateFrequencyType> repaymentFrequencyTypeOptions = new LinkedHashSet<>();

	@Valid
	private Set<@Valid GetLoanProductsRescheduleStrategyType> rescheduleStrategyTypeOptions = new LinkedHashSet<>();

	@Valid
	private Set<@Valid GetLoanProductsTransactionProcessingStrategyOptions> transactionProcessingStrategyOptions = new LinkedHashSet<>();

	private Boolean useBorrowerCycle;

	@Valid
	private Set<@Valid GetLoanProductsValueConditionTypeOptions> valueConditionTypeOptions = new LinkedHashSet<>();

	public GetLoanProductsTemplateResponse accountingMappingOptions(
			GetLoanProductsAccountingMappingOptions accountingMappingOptions) {

		this.accountingMappingOptions = accountingMappingOptions;
		return this;
	}

	/**
	 * Get accountingMappingOptions
	 *
	 * @return accountingMappingOptions
	 */
	@Valid
	@Schema(name = "accountingMappingOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountingMappingOptions")
	public GetLoanProductsAccountingMappingOptions getAccountingMappingOptions() {

		return accountingMappingOptions;
	}

	public void setAccountingMappingOptions(GetLoanProductsAccountingMappingOptions accountingMappingOptions) {

		this.accountingMappingOptions = accountingMappingOptions;
	}

	public GetLoanProductsTemplateResponse accountingRule(GetLoanProductsAccountingRule accountingRule) {

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

	public GetLoanProductsTemplateResponse accountingRuleOptions(
			Set<@Valid GetLoanProductsAccountingRule> accountingRuleOptions) {

		this.accountingRuleOptions = accountingRuleOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addAccountingRuleOptionsItem(
			GetLoanProductsAccountingRule accountingRuleOptionsItem) {

		if (this.accountingRuleOptions == null) {
			this.accountingRuleOptions = new LinkedHashSet<>();
		}
		this.accountingRuleOptions.add(accountingRuleOptionsItem);
		return this;
	}

	/**
	 * Get accountingRuleOptions
	 *
	 * @return accountingRuleOptions
	 */
	@Valid
	@Schema(name = "accountingRuleOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountingRuleOptions")
	public Set<@Valid GetLoanProductsAccountingRule> getAccountingRuleOptions() {

		return accountingRuleOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setAccountingRuleOptions(Set<@Valid GetLoanProductsAccountingRule> accountingRuleOptions) {

		this.accountingRuleOptions = accountingRuleOptions;
	}

	public GetLoanProductsTemplateResponse amortizationType(GetLoanProductsAmortizationType amortizationType) {

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

	public GetLoanProductsTemplateResponse amortizationTypeOptions(
			Set<@Valid GetLoanProductsAmortizationType> amortizationTypeOptions) {

		this.amortizationTypeOptions = amortizationTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addAmortizationTypeOptionsItem(
			GetLoanProductsAmortizationType amortizationTypeOptionsItem) {

		if (this.amortizationTypeOptions == null) {
			this.amortizationTypeOptions = new LinkedHashSet<>();
		}
		this.amortizationTypeOptions.add(amortizationTypeOptionsItem);
		return this;
	}

	/**
	 * Get amortizationTypeOptions
	 *
	 * @return amortizationTypeOptions
	 */
	@Valid
	@Schema(name = "amortizationTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amortizationTypeOptions")
	public Set<@Valid GetLoanProductsAmortizationType> getAmortizationTypeOptions() {

		return amortizationTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setAmortizationTypeOptions(Set<@Valid GetLoanProductsAmortizationType> amortizationTypeOptions) {

		this.amortizationTypeOptions = amortizationTypeOptions;
	}

	public GetLoanProductsTemplateResponse chargeOptions(Set<@Valid GetLoanProductsChargeOptions> chargeOptions) {

		this.chargeOptions = chargeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addChargeOptionsItem(GetLoanProductsChargeOptions chargeOptionsItem) {

		if (this.chargeOptions == null) {
			this.chargeOptions = new LinkedHashSet<>();
		}
		this.chargeOptions.add(chargeOptionsItem);
		return this;
	}

	/**
	 * Get chargeOptions
	 *
	 * @return chargeOptions
	 */
	@Valid
	@Schema(name = "chargeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("chargeOptions")
	public Set<@Valid GetLoanProductsChargeOptions> getChargeOptions() {

		return chargeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setChargeOptions(Set<@Valid GetLoanProductsChargeOptions> chargeOptions) {

		this.chargeOptions = chargeOptions;
	}

	public GetLoanProductsTemplateResponse currency(GetLoanProductsTemplateCurrency currency) {

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
	public GetLoanProductsTemplateCurrency getCurrency() {

		return currency;
	}

	public void setCurrency(GetLoanProductsTemplateCurrency currency) {

		this.currency = currency;
	}

	public GetLoanProductsTemplateResponse currencyOptions(Set<@Valid GetLoanProductsCurrencyOptions> currencyOptions) {

		this.currencyOptions = currencyOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addCurrencyOptionsItem(GetLoanProductsCurrencyOptions currencyOptionsItem) {

		if (this.currencyOptions == null) {
			this.currencyOptions = new LinkedHashSet<>();
		}
		this.currencyOptions.add(currencyOptionsItem);
		return this;
	}

	/**
	 * Get currencyOptions
	 *
	 * @return currencyOptions
	 */
	@Valid
	@Schema(name = "currencyOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("currencyOptions")
	public Set<@Valid GetLoanProductsCurrencyOptions> getCurrencyOptions() {

		return currencyOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setCurrencyOptions(Set<@Valid GetLoanProductsCurrencyOptions> currencyOptions) {

		this.currencyOptions = currencyOptions;
	}

	public GetLoanProductsTemplateResponse daysInMonthType(GetLoansProductsDaysInMonthTemplateType daysInMonthType) {

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
	public GetLoansProductsDaysInMonthTemplateType getDaysInMonthType() {

		return daysInMonthType;
	}

	public void setDaysInMonthType(GetLoansProductsDaysInMonthTemplateType daysInMonthType) {

		this.daysInMonthType = daysInMonthType;
	}

	public GetLoanProductsTemplateResponse daysInMonthTypeOptions(
			Set<@Valid GetLoansProductsDaysInMonthTemplateType> daysInMonthTypeOptions) {

		this.daysInMonthTypeOptions = daysInMonthTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addDaysInMonthTypeOptionsItem(
			GetLoansProductsDaysInMonthTemplateType daysInMonthTypeOptionsItem) {

		if (this.daysInMonthTypeOptions == null) {
			this.daysInMonthTypeOptions = new LinkedHashSet<>();
		}
		this.daysInMonthTypeOptions.add(daysInMonthTypeOptionsItem);
		return this;
	}

	/**
	 * Get daysInMonthTypeOptions
	 *
	 * @return daysInMonthTypeOptions
	 */
	@Valid
	@Schema(name = "daysInMonthTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("daysInMonthTypeOptions")
	public Set<@Valid GetLoansProductsDaysInMonthTemplateType> getDaysInMonthTypeOptions() {

		return daysInMonthTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setDaysInMonthTypeOptions(Set<@Valid GetLoansProductsDaysInMonthTemplateType> daysInMonthTypeOptions) {

		this.daysInMonthTypeOptions = daysInMonthTypeOptions;
	}

	public GetLoanProductsTemplateResponse daysInYearType(GetLoanProductsDaysInYearTemplateType daysInYearType) {

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
	public GetLoanProductsDaysInYearTemplateType getDaysInYearType() {

		return daysInYearType;
	}

	public void setDaysInYearType(GetLoanProductsDaysInYearTemplateType daysInYearType) {

		this.daysInYearType = daysInYearType;
	}

	public GetLoanProductsTemplateResponse daysInYearTypeOptions(
			Set<@Valid GetLoanProductsInterestTemplateType> daysInYearTypeOptions) {

		this.daysInYearTypeOptions = daysInYearTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addDaysInYearTypeOptionsItem(
			GetLoanProductsInterestTemplateType daysInYearTypeOptionsItem) {

		if (this.daysInYearTypeOptions == null) {
			this.daysInYearTypeOptions = new LinkedHashSet<>();
		}
		this.daysInYearTypeOptions.add(daysInYearTypeOptionsItem);
		return this;
	}

	/**
	 * Get daysInYearTypeOptions
	 *
	 * @return daysInYearTypeOptions
	 */
	@Valid
	@Schema(name = "daysInYearTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("daysInYearTypeOptions")
	public Set<@Valid GetLoanProductsInterestTemplateType> getDaysInYearTypeOptions() {

		return daysInYearTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setDaysInYearTypeOptions(Set<@Valid GetLoanProductsInterestTemplateType> daysInYearTypeOptions) {

		this.daysInYearTypeOptions = daysInYearTypeOptions;
	}

	public GetLoanProductsTemplateResponse includeInBorrowerCycle(Boolean includeInBorrowerCycle) {

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

	public GetLoanProductsTemplateResponse interestCalculationPeriodType(
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

	public GetLoanProductsTemplateResponse interestCalculationPeriodTypeOptions(
			Set<@Valid GetLoansProductsInterestCalculationPeriodType> interestCalculationPeriodTypeOptions) {

		this.interestCalculationPeriodTypeOptions = interestCalculationPeriodTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addInterestCalculationPeriodTypeOptionsItem(
			GetLoansProductsInterestCalculationPeriodType interestCalculationPeriodTypeOptionsItem) {

		if (this.interestCalculationPeriodTypeOptions == null) {
			this.interestCalculationPeriodTypeOptions = new LinkedHashSet<>();
		}
		this.interestCalculationPeriodTypeOptions.add(interestCalculationPeriodTypeOptionsItem);
		return this;
	}

	/**
	 * Get interestCalculationPeriodTypeOptions
	 *
	 * @return interestCalculationPeriodTypeOptions
	 */
	@Valid
	@Schema(name = "interestCalculationPeriodTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestCalculationPeriodTypeOptions")
	public Set<@Valid GetLoansProductsInterestCalculationPeriodType> getInterestCalculationPeriodTypeOptions() {

		return interestCalculationPeriodTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setInterestCalculationPeriodTypeOptions(
			Set<@Valid GetLoansProductsInterestCalculationPeriodType> interestCalculationPeriodTypeOptions) {

		this.interestCalculationPeriodTypeOptions = interestCalculationPeriodTypeOptions;
	}

	public GetLoanProductsTemplateResponse interestRateFrequencyType(
			GetLoanProductsInterestRateTemplateFrequencyType interestRateFrequencyType) {

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
	public GetLoanProductsInterestRateTemplateFrequencyType getInterestRateFrequencyType() {

		return interestRateFrequencyType;
	}

	public void setInterestRateFrequencyType(
			GetLoanProductsInterestRateTemplateFrequencyType interestRateFrequencyType) {

		this.interestRateFrequencyType = interestRateFrequencyType;
	}

	public GetLoanProductsTemplateResponse interestRateFrequencyTypeOptions(
			Set<@Valid GetLoanProductsInterestRateTemplateFrequencyType> interestRateFrequencyTypeOptions) {

		this.interestRateFrequencyTypeOptions = interestRateFrequencyTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addInterestRateFrequencyTypeOptionsItem(
			GetLoanProductsInterestRateTemplateFrequencyType interestRateFrequencyTypeOptionsItem) {

		if (this.interestRateFrequencyTypeOptions == null) {
			this.interestRateFrequencyTypeOptions = new LinkedHashSet<>();
		}
		this.interestRateFrequencyTypeOptions.add(interestRateFrequencyTypeOptionsItem);
		return this;
	}

	/**
	 * Get interestRateFrequencyTypeOptions
	 *
	 * @return interestRateFrequencyTypeOptions
	 */
	@Valid
	@Schema(name = "interestRateFrequencyTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRateFrequencyTypeOptions")
	public Set<@Valid GetLoanProductsInterestRateTemplateFrequencyType> getInterestRateFrequencyTypeOptions() {

		return interestRateFrequencyTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setInterestRateFrequencyTypeOptions(
			Set<@Valid GetLoanProductsInterestRateTemplateFrequencyType> interestRateFrequencyTypeOptions) {

		this.interestRateFrequencyTypeOptions = interestRateFrequencyTypeOptions;
	}

	public GetLoanProductsTemplateResponse interestRateVariationsForBorrowerCycle(
			List<Integer> interestRateVariationsForBorrowerCycle) {

		this.interestRateVariationsForBorrowerCycle = interestRateVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsTemplateResponse addInterestRateVariationsForBorrowerCycleItem(
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

	public GetLoanProductsTemplateResponse interestRecalculationCompoundingTypeOptions(
			Set<@Valid GetLoanProductsInterestRecalculationCompoundingType> interestRecalculationCompoundingTypeOptions) {

		this.interestRecalculationCompoundingTypeOptions = interestRecalculationCompoundingTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addInterestRecalculationCompoundingTypeOptionsItem(
			GetLoanProductsInterestRecalculationCompoundingType interestRecalculationCompoundingTypeOptionsItem) {

		if (this.interestRecalculationCompoundingTypeOptions == null) {
			this.interestRecalculationCompoundingTypeOptions = new LinkedHashSet<>();
		}
		this.interestRecalculationCompoundingTypeOptions.add(interestRecalculationCompoundingTypeOptionsItem);
		return this;
	}

	/**
	 * Get interestRecalculationCompoundingTypeOptions
	 *
	 * @return interestRecalculationCompoundingTypeOptions
	 */
	@Valid
	@Schema(name = "interestRecalculationCompoundingTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRecalculationCompoundingTypeOptions")
	public Set<@Valid GetLoanProductsInterestRecalculationCompoundingType> getInterestRecalculationCompoundingTypeOptions() {

		return interestRecalculationCompoundingTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setInterestRecalculationCompoundingTypeOptions(
			Set<@Valid GetLoanProductsInterestRecalculationCompoundingType> interestRecalculationCompoundingTypeOptions) {

		this.interestRecalculationCompoundingTypeOptions = interestRecalculationCompoundingTypeOptions;
	}

	public GetLoanProductsTemplateResponse interestRecalculationData(
			GetLoanProductsInterestRecalculationTemplateData interestRecalculationData) {

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
	public GetLoanProductsInterestRecalculationTemplateData getInterestRecalculationData() {

		return interestRecalculationData;
	}

	public void setInterestRecalculationData(
			GetLoanProductsInterestRecalculationTemplateData interestRecalculationData) {

		this.interestRecalculationData = interestRecalculationData;
	}

	public GetLoanProductsTemplateResponse interestRecalculationFrequencyTypeOptions(
			Set<@Valid GetLoanProductsInterestRecalculationCompoundingFrequencyType> interestRecalculationFrequencyTypeOptions) {

		this.interestRecalculationFrequencyTypeOptions = interestRecalculationFrequencyTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addInterestRecalculationFrequencyTypeOptionsItem(
			GetLoanProductsInterestRecalculationCompoundingFrequencyType interestRecalculationFrequencyTypeOptionsItem) {

		if (this.interestRecalculationFrequencyTypeOptions == null) {
			this.interestRecalculationFrequencyTypeOptions = new LinkedHashSet<>();
		}
		this.interestRecalculationFrequencyTypeOptions.add(interestRecalculationFrequencyTypeOptionsItem);
		return this;
	}

	/**
	 * Get interestRecalculationFrequencyTypeOptions
	 *
	 * @return interestRecalculationFrequencyTypeOptions
	 */
	@Valid
	@Schema(name = "interestRecalculationFrequencyTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRecalculationFrequencyTypeOptions")
	public Set<@Valid GetLoanProductsInterestRecalculationCompoundingFrequencyType> getInterestRecalculationFrequencyTypeOptions() {

		return interestRecalculationFrequencyTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setInterestRecalculationFrequencyTypeOptions(
			Set<@Valid GetLoanProductsInterestRecalculationCompoundingFrequencyType> interestRecalculationFrequencyTypeOptions) {

		this.interestRecalculationFrequencyTypeOptions = interestRecalculationFrequencyTypeOptions;
	}

	public GetLoanProductsTemplateResponse interestType(GetLoanProductsInterestTemplateType interestType) {

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

	public GetLoanProductsTemplateResponse interestTypeOptions(
			Set<@Valid GetLoanProductsInterestTemplateType> interestTypeOptions) {

		this.interestTypeOptions = interestTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addInterestTypeOptionsItem(
			GetLoanProductsInterestTemplateType interestTypeOptionsItem) {

		if (this.interestTypeOptions == null) {
			this.interestTypeOptions = new LinkedHashSet<>();
		}
		this.interestTypeOptions.add(interestTypeOptionsItem);
		return this;
	}

	/**
	 * Get interestTypeOptions
	 *
	 * @return interestTypeOptions
	 */
	@Valid
	@Schema(name = "interestTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestTypeOptions")
	public Set<@Valid GetLoanProductsInterestTemplateType> getInterestTypeOptions() {

		return interestTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setInterestTypeOptions(Set<@Valid GetLoanProductsInterestTemplateType> interestTypeOptions) {

		this.interestTypeOptions = interestTypeOptions;
	}

	public GetLoanProductsTemplateResponse isInterestRecalculationEnabled(Boolean isInterestRecalculationEnabled) {

		this.isInterestRecalculationEnabled = isInterestRecalculationEnabled;
		return this;
	}

	/**
	 * Get isInterestRecalculationEnabled
	 *
	 * @return isInterestRecalculationEnabled
	 */
	@Schema(name = "isInterestRecalculationEnabled", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("isInterestRecalculationEnabled")
	public Boolean getIsInterestRecalculationEnabled() {

		return isInterestRecalculationEnabled;
	}

	public void setIsInterestRecalculationEnabled(Boolean isInterestRecalculationEnabled) {

		this.isInterestRecalculationEnabled = isInterestRecalculationEnabled;
	}

	public GetLoanProductsTemplateResponse numberOfRepaymentVariationsForBorrowerCycle(
			List<Integer> numberOfRepaymentVariationsForBorrowerCycle) {

		this.numberOfRepaymentVariationsForBorrowerCycle = numberOfRepaymentVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsTemplateResponse addNumberOfRepaymentVariationsForBorrowerCycleItem(
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

	public GetLoanProductsTemplateResponse paymentTypeOptions(
			Set<@Valid GetLoanProductsPaymentTypeOptions> paymentTypeOptions) {

		this.paymentTypeOptions = paymentTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addPaymentTypeOptionsItem(
			GetLoanProductsPaymentTypeOptions paymentTypeOptionsItem) {

		if (this.paymentTypeOptions == null) {
			this.paymentTypeOptions = new LinkedHashSet<>();
		}
		this.paymentTypeOptions.add(paymentTypeOptionsItem);
		return this;
	}

	/**
	 * Get paymentTypeOptions
	 *
	 * @return paymentTypeOptions
	 */
	@Valid
	@Schema(name = "paymentTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("paymentTypeOptions")
	public Set<@Valid GetLoanProductsPaymentTypeOptions> getPaymentTypeOptions() {

		return paymentTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setPaymentTypeOptions(Set<@Valid GetLoanProductsPaymentTypeOptions> paymentTypeOptions) {

		this.paymentTypeOptions = paymentTypeOptions;
	}

	public GetLoanProductsTemplateResponse preClosureInterestCalculationStrategyOptions(
			Set<@Valid GetLoanProductsPreClosureInterestCalculationStrategy> preClosureInterestCalculationStrategyOptions) {

		this.preClosureInterestCalculationStrategyOptions = preClosureInterestCalculationStrategyOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addPreClosureInterestCalculationStrategyOptionsItem(
			GetLoanProductsPreClosureInterestCalculationStrategy preClosureInterestCalculationStrategyOptionsItem) {

		if (this.preClosureInterestCalculationStrategyOptions == null) {
			this.preClosureInterestCalculationStrategyOptions = new LinkedHashSet<>();
		}
		this.preClosureInterestCalculationStrategyOptions.add(preClosureInterestCalculationStrategyOptionsItem);
		return this;
	}

	/**
	 * Get preClosureInterestCalculationStrategyOptions
	 *
	 * @return preClosureInterestCalculationStrategyOptions
	 */
	@Valid
	@Schema(name = "preClosureInterestCalculationStrategyOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("preClosureInterestCalculationStrategyOptions")
	public Set<@Valid GetLoanProductsPreClosureInterestCalculationStrategy> getPreClosureInterestCalculationStrategyOptions() {

		return preClosureInterestCalculationStrategyOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setPreClosureInterestCalculationStrategyOptions(
			Set<@Valid GetLoanProductsPreClosureInterestCalculationStrategy> preClosureInterestCalculationStrategyOptions) {

		this.preClosureInterestCalculationStrategyOptions = preClosureInterestCalculationStrategyOptions;
	}

	public GetLoanProductsTemplateResponse principalVariationsForBorrowerCycle(
			List<Integer> principalVariationsForBorrowerCycle) {

		this.principalVariationsForBorrowerCycle = principalVariationsForBorrowerCycle;
		return this;
	}

	public GetLoanProductsTemplateResponse addPrincipalVariationsForBorrowerCycleItem(
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

	public GetLoanProductsTemplateResponse repaymentFrequencyType(
			GetLoanProductsRepaymentTemplateFrequencyType repaymentFrequencyType) {

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
	public GetLoanProductsRepaymentTemplateFrequencyType getRepaymentFrequencyType() {

		return repaymentFrequencyType;
	}

	public void setRepaymentFrequencyType(GetLoanProductsRepaymentTemplateFrequencyType repaymentFrequencyType) {

		this.repaymentFrequencyType = repaymentFrequencyType;
	}

	public GetLoanProductsTemplateResponse repaymentFrequencyTypeOptions(
			Set<@Valid GetLoanProductsRepaymentTemplateFrequencyType> repaymentFrequencyTypeOptions) {

		this.repaymentFrequencyTypeOptions = repaymentFrequencyTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addRepaymentFrequencyTypeOptionsItem(
			GetLoanProductsRepaymentTemplateFrequencyType repaymentFrequencyTypeOptionsItem) {

		if (this.repaymentFrequencyTypeOptions == null) {
			this.repaymentFrequencyTypeOptions = new LinkedHashSet<>();
		}
		this.repaymentFrequencyTypeOptions.add(repaymentFrequencyTypeOptionsItem);
		return this;
	}

	/**
	 * Get repaymentFrequencyTypeOptions
	 *
	 * @return repaymentFrequencyTypeOptions
	 */
	@Valid
	@Schema(name = "repaymentFrequencyTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("repaymentFrequencyTypeOptions")
	public Set<@Valid GetLoanProductsRepaymentTemplateFrequencyType> getRepaymentFrequencyTypeOptions() {

		return repaymentFrequencyTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setRepaymentFrequencyTypeOptions(
			Set<@Valid GetLoanProductsRepaymentTemplateFrequencyType> repaymentFrequencyTypeOptions) {

		this.repaymentFrequencyTypeOptions = repaymentFrequencyTypeOptions;
	}

	public GetLoanProductsTemplateResponse rescheduleStrategyTypeOptions(
			Set<@Valid GetLoanProductsRescheduleStrategyType> rescheduleStrategyTypeOptions) {

		this.rescheduleStrategyTypeOptions = rescheduleStrategyTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addRescheduleStrategyTypeOptionsItem(
			GetLoanProductsRescheduleStrategyType rescheduleStrategyTypeOptionsItem) {

		if (this.rescheduleStrategyTypeOptions == null) {
			this.rescheduleStrategyTypeOptions = new LinkedHashSet<>();
		}
		this.rescheduleStrategyTypeOptions.add(rescheduleStrategyTypeOptionsItem);
		return this;
	}

	/**
	 * Get rescheduleStrategyTypeOptions
	 *
	 * @return rescheduleStrategyTypeOptions
	 */
	@Valid
	@Schema(name = "rescheduleStrategyTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("rescheduleStrategyTypeOptions")
	public Set<@Valid GetLoanProductsRescheduleStrategyType> getRescheduleStrategyTypeOptions() {

		return rescheduleStrategyTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setRescheduleStrategyTypeOptions(
			Set<@Valid GetLoanProductsRescheduleStrategyType> rescheduleStrategyTypeOptions) {

		this.rescheduleStrategyTypeOptions = rescheduleStrategyTypeOptions;
	}

	public GetLoanProductsTemplateResponse transactionProcessingStrategyOptions(
			Set<@Valid GetLoanProductsTransactionProcessingStrategyOptions> transactionProcessingStrategyOptions) {

		this.transactionProcessingStrategyOptions = transactionProcessingStrategyOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addTransactionProcessingStrategyOptionsItem(
			GetLoanProductsTransactionProcessingStrategyOptions transactionProcessingStrategyOptionsItem) {

		if (this.transactionProcessingStrategyOptions == null) {
			this.transactionProcessingStrategyOptions = new LinkedHashSet<>();
		}
		this.transactionProcessingStrategyOptions.add(transactionProcessingStrategyOptionsItem);
		return this;
	}

	/**
	 * Get transactionProcessingStrategyOptions
	 *
	 * @return transactionProcessingStrategyOptions
	 */
	@Valid
	@Schema(name = "transactionProcessingStrategyOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("transactionProcessingStrategyOptions")
	public Set<@Valid GetLoanProductsTransactionProcessingStrategyOptions> getTransactionProcessingStrategyOptions() {

		return transactionProcessingStrategyOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setTransactionProcessingStrategyOptions(
			Set<@Valid GetLoanProductsTransactionProcessingStrategyOptions> transactionProcessingStrategyOptions) {

		this.transactionProcessingStrategyOptions = transactionProcessingStrategyOptions;
	}

	public GetLoanProductsTemplateResponse useBorrowerCycle(Boolean useBorrowerCycle) {

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

	public GetLoanProductsTemplateResponse valueConditionTypeOptions(
			Set<@Valid GetLoanProductsValueConditionTypeOptions> valueConditionTypeOptions) {

		this.valueConditionTypeOptions = valueConditionTypeOptions;
		return this;
	}

	public GetLoanProductsTemplateResponse addValueConditionTypeOptionsItem(
			GetLoanProductsValueConditionTypeOptions valueConditionTypeOptionsItem) {

		if (this.valueConditionTypeOptions == null) {
			this.valueConditionTypeOptions = new LinkedHashSet<>();
		}
		this.valueConditionTypeOptions.add(valueConditionTypeOptionsItem);
		return this;
	}

	/**
	 * Get valueConditionTypeOptions
	 *
	 * @return valueConditionTypeOptions
	 */
	@Valid
	@Schema(name = "valueConditionTypeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("valueConditionTypeOptions")
	public Set<@Valid GetLoanProductsValueConditionTypeOptions> getValueConditionTypeOptions() {

		return valueConditionTypeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setValueConditionTypeOptions(
			Set<@Valid GetLoanProductsValueConditionTypeOptions> valueConditionTypeOptions) {

		this.valueConditionTypeOptions = valueConditionTypeOptions;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanProductsTemplateResponse getLoanProductsTemplateResponse = (GetLoanProductsTemplateResponse) o;
		return Objects.equals(this.accountingMappingOptions, getLoanProductsTemplateResponse.accountingMappingOptions)
				&& Objects.equals(this.accountingRule, getLoanProductsTemplateResponse.accountingRule)
				&& Objects.equals(this.accountingRuleOptions, getLoanProductsTemplateResponse.accountingRuleOptions)
				&& Objects.equals(this.amortizationType, getLoanProductsTemplateResponse.amortizationType)
				&& Objects.equals(this.amortizationTypeOptions, getLoanProductsTemplateResponse.amortizationTypeOptions)
				&& Objects.equals(this.chargeOptions, getLoanProductsTemplateResponse.chargeOptions)
				&& Objects.equals(this.currency, getLoanProductsTemplateResponse.currency)
				&& Objects.equals(this.currencyOptions, getLoanProductsTemplateResponse.currencyOptions)
				&& Objects.equals(this.daysInMonthType, getLoanProductsTemplateResponse.daysInMonthType)
				&& Objects.equals(this.daysInMonthTypeOptions, getLoanProductsTemplateResponse.daysInMonthTypeOptions)
				&& Objects.equals(this.daysInYearType, getLoanProductsTemplateResponse.daysInYearType)
				&& Objects.equals(this.daysInYearTypeOptions, getLoanProductsTemplateResponse.daysInYearTypeOptions)
				&& Objects.equals(this.includeInBorrowerCycle, getLoanProductsTemplateResponse.includeInBorrowerCycle)
				&& Objects.equals(this.interestCalculationPeriodType,
						getLoanProductsTemplateResponse.interestCalculationPeriodType)
				&& Objects.equals(this.interestCalculationPeriodTypeOptions,
						getLoanProductsTemplateResponse.interestCalculationPeriodTypeOptions)
				&& Objects.equals(this.interestRateFrequencyType,
						getLoanProductsTemplateResponse.interestRateFrequencyType)
				&& Objects.equals(this.interestRateFrequencyTypeOptions,
						getLoanProductsTemplateResponse.interestRateFrequencyTypeOptions)
				&& Objects.equals(this.interestRateVariationsForBorrowerCycle,
						getLoanProductsTemplateResponse.interestRateVariationsForBorrowerCycle)
				&& Objects.equals(this.interestRecalculationCompoundingTypeOptions,
						getLoanProductsTemplateResponse.interestRecalculationCompoundingTypeOptions)
				&& Objects.equals(this.interestRecalculationData,
						getLoanProductsTemplateResponse.interestRecalculationData)
				&& Objects.equals(this.interestRecalculationFrequencyTypeOptions,
						getLoanProductsTemplateResponse.interestRecalculationFrequencyTypeOptions)
				&& Objects.equals(this.interestType, getLoanProductsTemplateResponse.interestType)
				&& Objects.equals(this.interestTypeOptions, getLoanProductsTemplateResponse.interestTypeOptions)
				&& Objects.equals(this.isInterestRecalculationEnabled,
						getLoanProductsTemplateResponse.isInterestRecalculationEnabled)
				&& Objects.equals(this.numberOfRepaymentVariationsForBorrowerCycle,
						getLoanProductsTemplateResponse.numberOfRepaymentVariationsForBorrowerCycle)
				&& Objects.equals(this.paymentTypeOptions, getLoanProductsTemplateResponse.paymentTypeOptions)
				&& Objects.equals(this.preClosureInterestCalculationStrategyOptions,
						getLoanProductsTemplateResponse.preClosureInterestCalculationStrategyOptions)
				&& Objects.equals(this.principalVariationsForBorrowerCycle,
						getLoanProductsTemplateResponse.principalVariationsForBorrowerCycle)
				&& Objects.equals(this.repaymentFrequencyType, getLoanProductsTemplateResponse.repaymentFrequencyType)
				&& Objects.equals(this.repaymentFrequencyTypeOptions,
						getLoanProductsTemplateResponse.repaymentFrequencyTypeOptions)
				&& Objects.equals(this.rescheduleStrategyTypeOptions,
						getLoanProductsTemplateResponse.rescheduleStrategyTypeOptions)
				&& Objects.equals(this.transactionProcessingStrategyOptions,
						getLoanProductsTemplateResponse.transactionProcessingStrategyOptions)
				&& Objects.equals(this.useBorrowerCycle, getLoanProductsTemplateResponse.useBorrowerCycle)
				&& Objects.equals(this.valueConditionTypeOptions,
						getLoanProductsTemplateResponse.valueConditionTypeOptions);
	}

	@Override
	public int hashCode() {

		return Objects.hash(accountingMappingOptions, accountingRule, accountingRuleOptions, amortizationType,
				amortizationTypeOptions, chargeOptions, currency, currencyOptions, daysInMonthType,
				daysInMonthTypeOptions, daysInYearType, daysInYearTypeOptions, includeInBorrowerCycle,
				interestCalculationPeriodType, interestCalculationPeriodTypeOptions, interestRateFrequencyType,
				interestRateFrequencyTypeOptions, interestRateVariationsForBorrowerCycle,
				interestRecalculationCompoundingTypeOptions, interestRecalculationData,
				interestRecalculationFrequencyTypeOptions, interestType, interestTypeOptions,
				isInterestRecalculationEnabled, numberOfRepaymentVariationsForBorrowerCycle, paymentTypeOptions,
				preClosureInterestCalculationStrategyOptions, principalVariationsForBorrowerCycle,
				repaymentFrequencyType, repaymentFrequencyTypeOptions, rescheduleStrategyTypeOptions,
				transactionProcessingStrategyOptions, useBorrowerCycle, valueConditionTypeOptions);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanProductsTemplateResponse {\n");
		sb.append("    accountingMappingOptions: ").append(toIndentedString(accountingMappingOptions)).append("\n");
		sb.append("    accountingRule: ").append(toIndentedString(accountingRule)).append("\n");
		sb.append("    accountingRuleOptions: ").append(toIndentedString(accountingRuleOptions)).append("\n");
		sb.append("    amortizationType: ").append(toIndentedString(amortizationType)).append("\n");
		sb.append("    amortizationTypeOptions: ").append(toIndentedString(amortizationTypeOptions)).append("\n");
		sb.append("    chargeOptions: ").append(toIndentedString(chargeOptions)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    currencyOptions: ").append(toIndentedString(currencyOptions)).append("\n");
		sb.append("    daysInMonthType: ").append(toIndentedString(daysInMonthType)).append("\n");
		sb.append("    daysInMonthTypeOptions: ").append(toIndentedString(daysInMonthTypeOptions)).append("\n");
		sb.append("    daysInYearType: ").append(toIndentedString(daysInYearType)).append("\n");
		sb.append("    daysInYearTypeOptions: ").append(toIndentedString(daysInYearTypeOptions)).append("\n");
		sb.append("    includeInBorrowerCycle: ").append(toIndentedString(includeInBorrowerCycle)).append("\n");
		sb.append("    interestCalculationPeriodType: ").append(toIndentedString(interestCalculationPeriodType))
				.append("\n");
		sb.append("    interestCalculationPeriodTypeOptions: ")
				.append(toIndentedString(interestCalculationPeriodTypeOptions)).append("\n");
		sb.append("    interestRateFrequencyType: ").append(toIndentedString(interestRateFrequencyType)).append("\n");
		sb.append("    interestRateFrequencyTypeOptions: ").append(toIndentedString(interestRateFrequencyTypeOptions))
				.append("\n");
		sb.append("    interestRateVariationsForBorrowerCycle: ")
				.append(toIndentedString(interestRateVariationsForBorrowerCycle)).append("\n");
		sb.append("    interestRecalculationCompoundingTypeOptions: ")
				.append(toIndentedString(interestRecalculationCompoundingTypeOptions)).append("\n");
		sb.append("    interestRecalculationData: ").append(toIndentedString(interestRecalculationData)).append("\n");
		sb.append("    interestRecalculationFrequencyTypeOptions: ")
				.append(toIndentedString(interestRecalculationFrequencyTypeOptions)).append("\n");
		sb.append("    interestType: ").append(toIndentedString(interestType)).append("\n");
		sb.append("    interestTypeOptions: ").append(toIndentedString(interestTypeOptions)).append("\n");
		sb.append("    isInterestRecalculationEnabled: ").append(toIndentedString(isInterestRecalculationEnabled))
				.append("\n");
		sb.append("    numberOfRepaymentVariationsForBorrowerCycle: ")
				.append(toIndentedString(numberOfRepaymentVariationsForBorrowerCycle)).append("\n");
		sb.append("    paymentTypeOptions: ").append(toIndentedString(paymentTypeOptions)).append("\n");
		sb.append("    preClosureInterestCalculationStrategyOptions: ")
				.append(toIndentedString(preClosureInterestCalculationStrategyOptions)).append("\n");
		sb.append("    principalVariationsForBorrowerCycle: ")
				.append(toIndentedString(principalVariationsForBorrowerCycle)).append("\n");
		sb.append("    repaymentFrequencyType: ").append(toIndentedString(repaymentFrequencyType)).append("\n");
		sb.append("    repaymentFrequencyTypeOptions: ").append(toIndentedString(repaymentFrequencyTypeOptions))
				.append("\n");
		sb.append("    rescheduleStrategyTypeOptions: ").append(toIndentedString(rescheduleStrategyTypeOptions))
				.append("\n");
		sb.append("    transactionProcessingStrategyOptions: ")
				.append(toIndentedString(transactionProcessingStrategyOptions)).append("\n");
		sb.append("    useBorrowerCycle: ").append(toIndentedString(useBorrowerCycle)).append("\n");
		sb.append("    valueConditionTypeOptions: ").append(toIndentedString(valueConditionTypeOptions)).append("\n");
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
