/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** PutLoansLoanIdRequest */
@Schema(name = "PutLoansLoanIdRequest", description = "PutLoansLoanIdRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PutLoansLoanIdRequest {

	private Integer amortizationType;

	private String dateFormat;

	private String expectedDisbursementDate;

	private BigDecimal fixedPrincipalPercentagePerInstallment;

	private Integer interestCalculationPeriodType;

	private Integer interestRatePerPeriod;

	private Integer interestType;

	private Integer loanTermFrequency;

	private Integer loanTermFrequencyType;

	private String locale;

	private Integer numberOfRepayments;

	private Long principal;

	private Integer productId;

	private Integer repaymentEvery;

	private Integer repaymentFrequencyType;

	private Integer transactionProcessingStrategyId;

	public PutLoansLoanIdRequest amortizationType(Integer amortizationType) {

		this.amortizationType = amortizationType;
		return this;
	}

	/**
	 * Get amortizationType
	 *
	 * @return amortizationType
	 */
	@Schema(name = "amortizationType", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amortizationType")
	public Integer getAmortizationType() {

		return amortizationType;
	}

	public void setAmortizationType(Integer amortizationType) {

		this.amortizationType = amortizationType;
	}

	public PutLoansLoanIdRequest dateFormat(String dateFormat) {

		this.dateFormat = dateFormat;
		return this;
	}

	/**
	 * Get dateFormat
	 *
	 * @return dateFormat
	 */
	@Schema(name = "dateFormat", example = "dd MMMM yyyy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("dateFormat")
	public String getDateFormat() {

		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {

		this.dateFormat = dateFormat;
	}

	public PutLoansLoanIdRequest expectedDisbursementDate(String expectedDisbursementDate) {

		this.expectedDisbursementDate = expectedDisbursementDate;
		return this;
	}

	/**
	 * Get expectedDisbursementDate
	 *
	 * @return expectedDisbursementDate
	 */
	@Schema(name = "expectedDisbursementDate", example = "04 March 2014", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("expectedDisbursementDate")
	public String getExpectedDisbursementDate() {

		return expectedDisbursementDate;
	}

	public void setExpectedDisbursementDate(String expectedDisbursementDate) {

		this.expectedDisbursementDate = expectedDisbursementDate;
	}

	public PutLoansLoanIdRequest fixedPrincipalPercentagePerInstallment(
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

	public PutLoansLoanIdRequest interestCalculationPeriodType(Integer interestCalculationPeriodType) {

		this.interestCalculationPeriodType = interestCalculationPeriodType;
		return this;
	}

	/**
	 * Get interestCalculationPeriodType
	 *
	 * @return interestCalculationPeriodType
	 */
	@Schema(name = "interestCalculationPeriodType", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestCalculationPeriodType")
	public Integer getInterestCalculationPeriodType() {

		return interestCalculationPeriodType;
	}

	public void setInterestCalculationPeriodType(Integer interestCalculationPeriodType) {

		this.interestCalculationPeriodType = interestCalculationPeriodType;
	}

	public PutLoansLoanIdRequest interestRatePerPeriod(Integer interestRatePerPeriod) {

		this.interestRatePerPeriod = interestRatePerPeriod;
		return this;
	}

	/**
	 * Get interestRatePerPeriod
	 *
	 * @return interestRatePerPeriod
	 */
	@Schema(name = "interestRatePerPeriod", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRatePerPeriod")
	public Integer getInterestRatePerPeriod() {

		return interestRatePerPeriod;
	}

	public void setInterestRatePerPeriod(Integer interestRatePerPeriod) {

		this.interestRatePerPeriod = interestRatePerPeriod;
	}

	public PutLoansLoanIdRequest interestType(Integer interestType) {

		this.interestType = interestType;
		return this;
	}

	/**
	 * Get interestType
	 *
	 * @return interestType
	 */
	@Schema(name = "interestType", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestType")
	public Integer getInterestType() {

		return interestType;
	}

	public void setInterestType(Integer interestType) {

		this.interestType = interestType;
	}

	public PutLoansLoanIdRequest loanTermFrequency(Integer loanTermFrequency) {

		this.loanTermFrequency = loanTermFrequency;
		return this;
	}

	/**
	 * Get loanTermFrequency
	 *
	 * @return loanTermFrequency
	 */
	@Schema(name = "loanTermFrequency", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("loanTermFrequency")
	public Integer getLoanTermFrequency() {

		return loanTermFrequency;
	}

	public void setLoanTermFrequency(Integer loanTermFrequency) {

		this.loanTermFrequency = loanTermFrequency;
	}

	public PutLoansLoanIdRequest loanTermFrequencyType(Integer loanTermFrequencyType) {

		this.loanTermFrequencyType = loanTermFrequencyType;
		return this;
	}

	/**
	 * Get loanTermFrequencyType
	 *
	 * @return loanTermFrequencyType
	 */
	@Schema(name = "loanTermFrequencyType", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("loanTermFrequencyType")
	public Integer getLoanTermFrequencyType() {

		return loanTermFrequencyType;
	}

	public void setLoanTermFrequencyType(Integer loanTermFrequencyType) {

		this.loanTermFrequencyType = loanTermFrequencyType;
	}

	public PutLoansLoanIdRequest locale(String locale) {

		this.locale = locale;
		return this;
	}

	/**
	 * Get locale
	 *
	 * @return locale
	 */
	@Schema(name = "locale", example = "en", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("locale")
	public String getLocale() {

		return locale;
	}

	public void setLocale(String locale) {

		this.locale = locale;
	}

	public PutLoansLoanIdRequest numberOfRepayments(Integer numberOfRepayments) {

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

	public PutLoansLoanIdRequest principal(Long principal) {

		this.principal = principal;
		return this;
	}

	/**
	 * Get principal
	 *
	 * @return principal
	 */
	@Schema(name = "principal", example = "5000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principal")
	public Long getPrincipal() {

		return principal;
	}

	public void setPrincipal(Long principal) {

		this.principal = principal;
	}

	public PutLoansLoanIdRequest productId(Integer productId) {

		this.productId = productId;
		return this;
	}

	/**
	 * Get productId
	 *
	 * @return productId
	 */
	@Schema(name = "productId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("productId")
	public Integer getProductId() {

		return productId;
	}

	public void setProductId(Integer productId) {

		this.productId = productId;
	}

	public PutLoansLoanIdRequest repaymentEvery(Integer repaymentEvery) {

		this.repaymentEvery = repaymentEvery;
		return this;
	}

	/**
	 * Get repaymentEvery
	 *
	 * @return repaymentEvery
	 */
	@Schema(name = "repaymentEvery", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("repaymentEvery")
	public Integer getRepaymentEvery() {

		return repaymentEvery;
	}

	public void setRepaymentEvery(Integer repaymentEvery) {

		this.repaymentEvery = repaymentEvery;
	}

	public PutLoansLoanIdRequest repaymentFrequencyType(Integer repaymentFrequencyType) {

		this.repaymentFrequencyType = repaymentFrequencyType;
		return this;
	}

	/**
	 * Get repaymentFrequencyType
	 *
	 * @return repaymentFrequencyType
	 */
	@Schema(name = "repaymentFrequencyType", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("repaymentFrequencyType")
	public Integer getRepaymentFrequencyType() {

		return repaymentFrequencyType;
	}

	public void setRepaymentFrequencyType(Integer repaymentFrequencyType) {

		this.repaymentFrequencyType = repaymentFrequencyType;
	}

	public PutLoansLoanIdRequest transactionProcessingStrategyId(Integer transactionProcessingStrategyId) {

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

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PutLoansLoanIdRequest putLoansLoanIdRequest = (PutLoansLoanIdRequest) o;
		return Objects.equals(this.amortizationType, putLoansLoanIdRequest.amortizationType)
				&& Objects.equals(this.dateFormat, putLoansLoanIdRequest.dateFormat)
				&& Objects.equals(this.expectedDisbursementDate, putLoansLoanIdRequest.expectedDisbursementDate)
				&& Objects.equals(this.fixedPrincipalPercentagePerInstallment,
						putLoansLoanIdRequest.fixedPrincipalPercentagePerInstallment)
				&& Objects.equals(this.interestCalculationPeriodType,
						putLoansLoanIdRequest.interestCalculationPeriodType)
				&& Objects.equals(this.interestRatePerPeriod, putLoansLoanIdRequest.interestRatePerPeriod)
				&& Objects.equals(this.interestType, putLoansLoanIdRequest.interestType)
				&& Objects.equals(this.loanTermFrequency, putLoansLoanIdRequest.loanTermFrequency)
				&& Objects.equals(this.loanTermFrequencyType, putLoansLoanIdRequest.loanTermFrequencyType)
				&& Objects.equals(this.locale, putLoansLoanIdRequest.locale)
				&& Objects.equals(this.numberOfRepayments, putLoansLoanIdRequest.numberOfRepayments)
				&& Objects.equals(this.principal, putLoansLoanIdRequest.principal)
				&& Objects.equals(this.productId, putLoansLoanIdRequest.productId)
				&& Objects.equals(this.repaymentEvery, putLoansLoanIdRequest.repaymentEvery)
				&& Objects.equals(this.repaymentFrequencyType, putLoansLoanIdRequest.repaymentFrequencyType)
				&& Objects.equals(this.transactionProcessingStrategyId,
						putLoansLoanIdRequest.transactionProcessingStrategyId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(amortizationType, dateFormat, expectedDisbursementDate,
				fixedPrincipalPercentagePerInstallment, interestCalculationPeriodType, interestRatePerPeriod,
				interestType, loanTermFrequency, loanTermFrequencyType, locale, numberOfRepayments, principal,
				productId, repaymentEvery, repaymentFrequencyType, transactionProcessingStrategyId);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PutLoansLoanIdRequest {\n");
		sb.append("    amortizationType: ").append(toIndentedString(amortizationType)).append("\n");
		sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
		sb.append("    expectedDisbursementDate: ").append(toIndentedString(expectedDisbursementDate)).append("\n");
		sb.append("    fixedPrincipalPercentagePerInstallment: ")
				.append(toIndentedString(fixedPrincipalPercentagePerInstallment)).append("\n");
		sb.append("    interestCalculationPeriodType: ").append(toIndentedString(interestCalculationPeriodType))
				.append("\n");
		sb.append("    interestRatePerPeriod: ").append(toIndentedString(interestRatePerPeriod)).append("\n");
		sb.append("    interestType: ").append(toIndentedString(interestType)).append("\n");
		sb.append("    loanTermFrequency: ").append(toIndentedString(loanTermFrequency)).append("\n");
		sb.append("    loanTermFrequencyType: ").append(toIndentedString(loanTermFrequencyType)).append("\n");
		sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
		sb.append("    numberOfRepayments: ").append(toIndentedString(numberOfRepayments)).append("\n");
		sb.append("    principal: ").append(toIndentedString(principal)).append("\n");
		sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
		sb.append("    repaymentEvery: ").append(toIndentedString(repaymentEvery)).append("\n");
		sb.append("    repaymentFrequencyType: ").append(toIndentedString(repaymentFrequencyType)).append("\n");
		sb.append("    transactionProcessingStrategyId: ").append(toIndentedString(transactionProcessingStrategyId))
				.append("\n");
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
