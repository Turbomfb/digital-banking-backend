/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** LoanProductProvisioningEntryData */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class LoanProductProvisioningEntryData {

	private BigDecimal amountreserved;

	private BigDecimal balance;

	private Long categoryId;

	private String categoryName;

	private Long criteriaId;

	private String currencyCode;

	private Long expenseAccount;

	private String expenseAccountCode;

	private String expenseAccountName;

	private Long historyId;

	private String liabilityAccountCode;

	private String liabilityAccountName;

	private Long liablityAccount;

	private Long officeId;

	private String officeName;

	private Long overdueInDays;

	private BigDecimal percentage;

	private Long productId;

	private String productName;

	public LoanProductProvisioningEntryData amountreserved(BigDecimal amountreserved) {
		this.amountreserved = amountreserved;
		return this;
	}

	/**
	 * Get amountreserved
	 *
	 * @return amountreserved
	 */
	@Valid
	@Schema(name = "amountreserved", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amountreserved")
	public BigDecimal getAmountreserved() {
		return amountreserved;
	}

	public void setAmountreserved(BigDecimal amountreserved) {
		this.amountreserved = amountreserved;
	}

	public LoanProductProvisioningEntryData balance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}

	/**
	 * Get balance
	 *
	 * @return balance
	 */
	@Valid
	@Schema(name = "balance", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("balance")
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public LoanProductProvisioningEntryData categoryId(Long categoryId) {
		this.categoryId = categoryId;
		return this;
	}

	/**
	 * Get categoryId
	 *
	 * @return categoryId
	 */
	@Schema(name = "categoryId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("categoryId")
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public LoanProductProvisioningEntryData categoryName(String categoryName) {
		this.categoryName = categoryName;
		return this;
	}

	/**
	 * Get categoryName
	 *
	 * @return categoryName
	 */
	@Schema(name = "categoryName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("categoryName")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public LoanProductProvisioningEntryData criteriaId(Long criteriaId) {
		this.criteriaId = criteriaId;
		return this;
	}

	/**
	 * Get criteriaId
	 *
	 * @return criteriaId
	 */
	@Schema(name = "criteriaId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("criteriaId")
	public Long getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(Long criteriaId) {
		this.criteriaId = criteriaId;
	}

	public LoanProductProvisioningEntryData currencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		return this;
	}

	/**
	 * Get currencyCode
	 *
	 * @return currencyCode
	 */
	@Schema(name = "currencyCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("currencyCode")
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public LoanProductProvisioningEntryData expenseAccount(Long expenseAccount) {
		this.expenseAccount = expenseAccount;
		return this;
	}

	/**
	 * Get expenseAccount
	 *
	 * @return expenseAccount
	 */
	@Schema(name = "expenseAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("expenseAccount")
	public Long getExpenseAccount() {
		return expenseAccount;
	}

	public void setExpenseAccount(Long expenseAccount) {
		this.expenseAccount = expenseAccount;
	}

	public LoanProductProvisioningEntryData expenseAccountCode(String expenseAccountCode) {
		this.expenseAccountCode = expenseAccountCode;
		return this;
	}

	/**
	 * Get expenseAccountCode
	 *
	 * @return expenseAccountCode
	 */
	@Schema(name = "expenseAccountCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("expenseAccountCode")
	public String getExpenseAccountCode() {
		return expenseAccountCode;
	}

	public void setExpenseAccountCode(String expenseAccountCode) {
		this.expenseAccountCode = expenseAccountCode;
	}

	public LoanProductProvisioningEntryData expenseAccountName(String expenseAccountName) {
		this.expenseAccountName = expenseAccountName;
		return this;
	}

	/**
	 * Get expenseAccountName
	 *
	 * @return expenseAccountName
	 */
	@Schema(name = "expenseAccountName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("expenseAccountName")
	public String getExpenseAccountName() {
		return expenseAccountName;
	}

	public void setExpenseAccountName(String expenseAccountName) {
		this.expenseAccountName = expenseAccountName;
	}

	public LoanProductProvisioningEntryData historyId(Long historyId) {
		this.historyId = historyId;
		return this;
	}

	/**
	 * Get historyId
	 *
	 * @return historyId
	 */
	@Schema(name = "historyId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("historyId")
	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public LoanProductProvisioningEntryData liabilityAccountCode(String liabilityAccountCode) {
		this.liabilityAccountCode = liabilityAccountCode;
		return this;
	}

	/**
	 * Get liabilityAccountCode
	 *
	 * @return liabilityAccountCode
	 */
	@Schema(name = "liabilityAccountCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("liabilityAccountCode")
	public String getLiabilityAccountCode() {
		return liabilityAccountCode;
	}

	public void setLiabilityAccountCode(String liabilityAccountCode) {
		this.liabilityAccountCode = liabilityAccountCode;
	}

	public LoanProductProvisioningEntryData liabilityAccountName(String liabilityAccountName) {
		this.liabilityAccountName = liabilityAccountName;
		return this;
	}

	/**
	 * Get liabilityAccountName
	 *
	 * @return liabilityAccountName
	 */
	@Schema(name = "liabilityAccountName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("liabilityAccountName")
	public String getLiabilityAccountName() {
		return liabilityAccountName;
	}

	public void setLiabilityAccountName(String liabilityAccountName) {
		this.liabilityAccountName = liabilityAccountName;
	}

	public LoanProductProvisioningEntryData liablityAccount(Long liablityAccount) {
		this.liablityAccount = liablityAccount;
		return this;
	}

	/**
	 * Get liablityAccount
	 *
	 * @return liablityAccount
	 */
	@Schema(name = "liablityAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("liablityAccount")
	public Long getLiablityAccount() {
		return liablityAccount;
	}

	public void setLiablityAccount(Long liablityAccount) {
		this.liablityAccount = liablityAccount;
	}

	public LoanProductProvisioningEntryData officeId(Long officeId) {
		this.officeId = officeId;
		return this;
	}

	/**
	 * Get officeId
	 *
	 * @return officeId
	 */
	@Schema(name = "officeId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("officeId")
	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public LoanProductProvisioningEntryData officeName(String officeName) {
		this.officeName = officeName;
		return this;
	}

	/**
	 * Get officeName
	 *
	 * @return officeName
	 */
	@Schema(name = "officeName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("officeName")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public LoanProductProvisioningEntryData overdueInDays(Long overdueInDays) {
		this.overdueInDays = overdueInDays;
		return this;
	}

	/**
	 * Get overdueInDays
	 *
	 * @return overdueInDays
	 */
	@Schema(name = "overdueInDays", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("overdueInDays")
	public Long getOverdueInDays() {
		return overdueInDays;
	}

	public void setOverdueInDays(Long overdueInDays) {
		this.overdueInDays = overdueInDays;
	}

	public LoanProductProvisioningEntryData percentage(BigDecimal percentage) {
		this.percentage = percentage;
		return this;
	}

	/**
	 * Get percentage
	 *
	 * @return percentage
	 */
	@Valid
	@Schema(name = "percentage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("percentage")
	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public LoanProductProvisioningEntryData productId(Long productId) {
		this.productId = productId;
		return this;
	}

	/**
	 * Get productId
	 *
	 * @return productId
	 */
	@Schema(name = "productId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("productId")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public LoanProductProvisioningEntryData productName(String productName) {
		this.productName = productName;
		return this;
	}

	/**
	 * Get productName
	 *
	 * @return productName
	 */
	@Schema(name = "productName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("productName")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoanProductProvisioningEntryData loanProductProvisioningEntryData = (LoanProductProvisioningEntryData) o;
		return Objects.equals(this.amountreserved, loanProductProvisioningEntryData.amountreserved)
				&& Objects.equals(this.balance, loanProductProvisioningEntryData.balance)
				&& Objects.equals(this.categoryId, loanProductProvisioningEntryData.categoryId)
				&& Objects.equals(this.categoryName, loanProductProvisioningEntryData.categoryName)
				&& Objects.equals(this.criteriaId, loanProductProvisioningEntryData.criteriaId)
				&& Objects.equals(this.currencyCode, loanProductProvisioningEntryData.currencyCode)
				&& Objects.equals(this.expenseAccount, loanProductProvisioningEntryData.expenseAccount)
				&& Objects.equals(this.expenseAccountCode, loanProductProvisioningEntryData.expenseAccountCode)
				&& Objects.equals(this.expenseAccountName, loanProductProvisioningEntryData.expenseAccountName)
				&& Objects.equals(this.historyId, loanProductProvisioningEntryData.historyId)
				&& Objects.equals(this.liabilityAccountCode, loanProductProvisioningEntryData.liabilityAccountCode)
				&& Objects.equals(this.liabilityAccountName, loanProductProvisioningEntryData.liabilityAccountName)
				&& Objects.equals(this.liablityAccount, loanProductProvisioningEntryData.liablityAccount)
				&& Objects.equals(this.officeId, loanProductProvisioningEntryData.officeId)
				&& Objects.equals(this.officeName, loanProductProvisioningEntryData.officeName)
				&& Objects.equals(this.overdueInDays, loanProductProvisioningEntryData.overdueInDays)
				&& Objects.equals(this.percentage, loanProductProvisioningEntryData.percentage)
				&& Objects.equals(this.productId, loanProductProvisioningEntryData.productId)
				&& Objects.equals(this.productName, loanProductProvisioningEntryData.productName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amountreserved, balance, categoryId, categoryName, criteriaId, currencyCode, expenseAccount,
				expenseAccountCode, expenseAccountName, historyId, liabilityAccountCode, liabilityAccountName,
				liablityAccount, officeId, officeName, overdueInDays, percentage, productId, productName);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LoanProductProvisioningEntryData {\n");
		sb.append("    amountreserved: ").append(toIndentedString(amountreserved)).append("\n");
		sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
		sb.append("    categoryId: ").append(toIndentedString(categoryId)).append("\n");
		sb.append("    categoryName: ").append(toIndentedString(categoryName)).append("\n");
		sb.append("    criteriaId: ").append(toIndentedString(criteriaId)).append("\n");
		sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
		sb.append("    expenseAccount: ").append(toIndentedString(expenseAccount)).append("\n");
		sb.append("    expenseAccountCode: ").append(toIndentedString(expenseAccountCode)).append("\n");
		sb.append("    expenseAccountName: ").append(toIndentedString(expenseAccountName)).append("\n");
		sb.append("    historyId: ").append(toIndentedString(historyId)).append("\n");
		sb.append("    liabilityAccountCode: ").append(toIndentedString(liabilityAccountCode)).append("\n");
		sb.append("    liabilityAccountName: ").append(toIndentedString(liabilityAccountName)).append("\n");
		sb.append("    liablityAccount: ").append(toIndentedString(liablityAccount)).append("\n");
		sb.append("    officeId: ").append(toIndentedString(officeId)).append("\n");
		sb.append("    officeName: ").append(toIndentedString(officeName)).append("\n");
		sb.append("    overdueInDays: ").append(toIndentedString(overdueInDays)).append("\n");
		sb.append("    percentage: ").append(toIndentedString(percentage)).append("\n");
		sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
		sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
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
