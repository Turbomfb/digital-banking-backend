/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetLoanChargeCurrency */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanChargeCurrency {

	private String code;

	private Integer decimalPlaces;

	private String displayLabel;

	private String displaySymbol;

	private String name;

	private String nameCode;

	public GetLoanChargeCurrency code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * Get code
	 *
	 * @return code
	 */
	@Schema(name = "code", example = "USD", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public GetLoanChargeCurrency decimalPlaces(Integer decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
		return this;
	}

	/**
	 * Get decimalPlaces
	 *
	 * @return decimalPlaces
	 */
	@Schema(name = "decimalPlaces", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("decimalPlaces")
	public Integer getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(Integer decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public GetLoanChargeCurrency displayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
		return this;
	}

	/**
	 * Get displayLabel
	 *
	 * @return displayLabel
	 */
	@Schema(name = "displayLabel", example = "US Dollar ($)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("displayLabel")
	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public GetLoanChargeCurrency displaySymbol(String displaySymbol) {
		this.displaySymbol = displaySymbol;
		return this;
	}

	/**
	 * Get displaySymbol
	 *
	 * @return displaySymbol
	 */
	@Schema(name = "displaySymbol", example = "$", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("displaySymbol")
	public String getDisplaySymbol() {
		return displaySymbol;
	}

	public void setDisplaySymbol(String displaySymbol) {
		this.displaySymbol = displaySymbol;
	}

	public GetLoanChargeCurrency name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "US Dollar", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GetLoanChargeCurrency nameCode(String nameCode) {
		this.nameCode = nameCode;
		return this;
	}

	/**
	 * Get nameCode
	 *
	 * @return nameCode
	 */
	@Schema(name = "nameCode", example = "currency.USD", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("nameCode")
	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanChargeCurrency getLoanChargeCurrency = (GetLoanChargeCurrency) o;
		return Objects.equals(this.code, getLoanChargeCurrency.code)
				&& Objects.equals(this.decimalPlaces, getLoanChargeCurrency.decimalPlaces)
				&& Objects.equals(this.displayLabel, getLoanChargeCurrency.displayLabel)
				&& Objects.equals(this.displaySymbol, getLoanChargeCurrency.displaySymbol)
				&& Objects.equals(this.name, getLoanChargeCurrency.name)
				&& Objects.equals(this.nameCode, getLoanChargeCurrency.nameCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, decimalPlaces, displayLabel, displaySymbol, name, nameCode);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanChargeCurrency {\n");
		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    decimalPlaces: ").append(toIndentedString(decimalPlaces)).append("\n");
		sb.append("    displayLabel: ").append(toIndentedString(displayLabel)).append("\n");
		sb.append("    displaySymbol: ").append(toIndentedString(displaySymbol)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    nameCode: ").append(toIndentedString(nameCode)).append("\n");
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
