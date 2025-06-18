/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetLoansTotal */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansTotal {

	private Double amount;

	private String currencyCode;

	private String defaultName;

	private Integer digitsAfterDecimal;

	private String displaySymbol;

	private String displaySymbolValue;

	private Boolean greaterThanZero;

	private Integer inMultiplesOf;

	private String nameCode;

	private Boolean zero;

	public GetLoansTotal amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 */
	@Schema(name = "amount", example = "471", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public GetLoansTotal currencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		return this;
	}

	/**
	 * Get currencyCode
	 *
	 * @return currencyCode
	 */
	@Schema(name = "currencyCode", example = "XOF", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("currencyCode")
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public GetLoansTotal defaultName(String defaultName) {
		this.defaultName = defaultName;
		return this;
	}

	/**
	 * Get defaultName
	 *
	 * @return defaultName
	 */
	@Schema(name = "defaultName", example = "CFA Franc BCEAO", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("defaultName")
	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public GetLoansTotal digitsAfterDecimal(Integer digitsAfterDecimal) {
		this.digitsAfterDecimal = digitsAfterDecimal;
		return this;
	}

	/**
	 * Get digitsAfterDecimal
	 *
	 * @return digitsAfterDecimal
	 */
	@Schema(name = "digitsAfterDecimal", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("digitsAfterDecimal")
	public Integer getDigitsAfterDecimal() {
		return digitsAfterDecimal;
	}

	public void setDigitsAfterDecimal(Integer digitsAfterDecimal) {
		this.digitsAfterDecimal = digitsAfterDecimal;
	}

	public GetLoansTotal displaySymbol(String displaySymbol) {
		this.displaySymbol = displaySymbol;
		return this;
	}

	/**
	 * Get displaySymbol
	 *
	 * @return displaySymbol
	 */
	@Schema(name = "displaySymbol", example = "CFA", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("displaySymbol")
	public String getDisplaySymbol() {
		return displaySymbol;
	}

	public void setDisplaySymbol(String displaySymbol) {
		this.displaySymbol = displaySymbol;
	}

	public GetLoansTotal displaySymbolValue(String displaySymbolValue) {
		this.displaySymbolValue = displaySymbolValue;
		return this;
	}

	/**
	 * Get displaySymbolValue
	 *
	 * @return displaySymbolValue
	 */
	@Schema(name = "displaySymbolValue", example = "471 CFA", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("displaySymbolValue")
	public String getDisplaySymbolValue() {
		return displaySymbolValue;
	}

	public void setDisplaySymbolValue(String displaySymbolValue) {
		this.displaySymbolValue = displaySymbolValue;
	}

	public GetLoansTotal greaterThanZero(Boolean greaterThanZero) {
		this.greaterThanZero = greaterThanZero;
		return this;
	}

	/**
	 * Get greaterThanZero
	 *
	 * @return greaterThanZero
	 */
	@Schema(name = "greaterThanZero", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("greaterThanZero")
	public Boolean getGreaterThanZero() {
		return greaterThanZero;
	}

	public void setGreaterThanZero(Boolean greaterThanZero) {
		this.greaterThanZero = greaterThanZero;
	}

	public GetLoansTotal inMultiplesOf(Integer inMultiplesOf) {
		this.inMultiplesOf = inMultiplesOf;
		return this;
	}

	/**
	 * Get inMultiplesOf
	 *
	 * @return inMultiplesOf
	 */
	@Schema(name = "inMultiplesOf", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("inMultiplesOf")
	public Integer getInMultiplesOf() {
		return inMultiplesOf;
	}

	public void setInMultiplesOf(Integer inMultiplesOf) {
		this.inMultiplesOf = inMultiplesOf;
	}

	public GetLoansTotal nameCode(String nameCode) {
		this.nameCode = nameCode;
		return this;
	}

	/**
	 * Get nameCode
	 *
	 * @return nameCode
	 */
	@Schema(name = "nameCode", example = "currency.XOF", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("nameCode")
	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	public GetLoansTotal zero(Boolean zero) {
		this.zero = zero;
		return this;
	}

	/**
	 * Get zero
	 *
	 * @return zero
	 */
	@Schema(name = "zero", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("zero")
	public Boolean getZero() {
		return zero;
	}

	public void setZero(Boolean zero) {
		this.zero = zero;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansTotal getLoansTotal = (GetLoansTotal) o;
		return Objects.equals(this.amount, getLoansTotal.amount)
				&& Objects.equals(this.currencyCode, getLoansTotal.currencyCode)
				&& Objects.equals(this.defaultName, getLoansTotal.defaultName)
				&& Objects.equals(this.digitsAfterDecimal, getLoansTotal.digitsAfterDecimal)
				&& Objects.equals(this.displaySymbol, getLoansTotal.displaySymbol)
				&& Objects.equals(this.displaySymbolValue, getLoansTotal.displaySymbolValue)
				&& Objects.equals(this.greaterThanZero, getLoansTotal.greaterThanZero)
				&& Objects.equals(this.inMultiplesOf, getLoansTotal.inMultiplesOf)
				&& Objects.equals(this.nameCode, getLoansTotal.nameCode)
				&& Objects.equals(this.zero, getLoansTotal.zero);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, currencyCode, defaultName, digitsAfterDecimal, displaySymbol, displaySymbolValue,
				greaterThanZero, inMultiplesOf, nameCode, zero);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansTotal {\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
		sb.append("    defaultName: ").append(toIndentedString(defaultName)).append("\n");
		sb.append("    digitsAfterDecimal: ").append(toIndentedString(digitsAfterDecimal)).append("\n");
		sb.append("    displaySymbol: ").append(toIndentedString(displaySymbol)).append("\n");
		sb.append("    displaySymbolValue: ").append(toIndentedString(displaySymbolValue)).append("\n");
		sb.append("    greaterThanZero: ").append(toIndentedString(greaterThanZero)).append("\n");
		sb.append("    inMultiplesOf: ").append(toIndentedString(inMultiplesOf)).append("\n");
		sb.append("    nameCode: ").append(toIndentedString(nameCode)).append("\n");
		sb.append("    zero: ").append(toIndentedString(zero)).append("\n");
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
