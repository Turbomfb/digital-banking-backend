/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoanProductsPrincipalVariationsForBorrowerCycle */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanProductsPrincipalVariationsForBorrowerCycle {

	private Integer borrowerCycleNumber;

	private Double defaultValue;

	private Integer id;

	private Double maxValue;

	private Double minValue;

	private GetLoanProductsParamType paramType;

	private GetLoanProductsValueConditionType valueConditionType;

	public GetLoanProductsPrincipalVariationsForBorrowerCycle borrowerCycleNumber(Integer borrowerCycleNumber) {

		this.borrowerCycleNumber = borrowerCycleNumber;
		return this;
	}

	/**
	 * Get borrowerCycleNumber
	 *
	 * @return borrowerCycleNumber
	 */
	@Schema(name = "borrowerCycleNumber", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("borrowerCycleNumber")
	public Integer getBorrowerCycleNumber() {

		return borrowerCycleNumber;
	}

	public void setBorrowerCycleNumber(Integer borrowerCycleNumber) {

		this.borrowerCycleNumber = borrowerCycleNumber;
	}

	public GetLoanProductsPrincipalVariationsForBorrowerCycle defaultValue(Double defaultValue) {

		this.defaultValue = defaultValue;
		return this;
	}

	/**
	 * Get defaultValue
	 *
	 * @return defaultValue
	 */
	@Schema(name = "defaultValue", example = "15000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("defaultValue")
	public Double getDefaultValue() {

		return defaultValue;
	}

	public void setDefaultValue(Double defaultValue) {

		this.defaultValue = defaultValue;
	}

	public GetLoanProductsPrincipalVariationsForBorrowerCycle id(Integer id) {

		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "21", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public GetLoanProductsPrincipalVariationsForBorrowerCycle maxValue(Double maxValue) {

		this.maxValue = maxValue;
		return this;
	}

	/**
	 * Get maxValue
	 *
	 * @return maxValue
	 */
	@Schema(name = "maxValue", example = "20000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("maxValue")
	public Double getMaxValue() {

		return maxValue;
	}

	public void setMaxValue(Double maxValue) {

		this.maxValue = maxValue;
	}

	public GetLoanProductsPrincipalVariationsForBorrowerCycle minValue(Double minValue) {

		this.minValue = minValue;
		return this;
	}

	/**
	 * Get minValue
	 *
	 * @return minValue
	 */
	@Schema(name = "minValue", example = "2000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("minValue")
	public Double getMinValue() {

		return minValue;
	}

	public void setMinValue(Double minValue) {

		this.minValue = minValue;
	}

	public GetLoanProductsPrincipalVariationsForBorrowerCycle paramType(GetLoanProductsParamType paramType) {

		this.paramType = paramType;
		return this;
	}

	/**
	 * Get paramType
	 *
	 * @return paramType
	 */
	@Valid
	@Schema(name = "paramType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("paramType")
	public GetLoanProductsParamType getParamType() {

		return paramType;
	}

	public void setParamType(GetLoanProductsParamType paramType) {

		this.paramType = paramType;
	}

	public GetLoanProductsPrincipalVariationsForBorrowerCycle valueConditionType(
			GetLoanProductsValueConditionType valueConditionType) {

		this.valueConditionType = valueConditionType;
		return this;
	}

	/**
	 * Get valueConditionType
	 *
	 * @return valueConditionType
	 */
	@Valid
	@Schema(name = "valueConditionType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("valueConditionType")
	public GetLoanProductsValueConditionType getValueConditionType() {

		return valueConditionType;
	}

	public void setValueConditionType(GetLoanProductsValueConditionType valueConditionType) {

		this.valueConditionType = valueConditionType;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanProductsPrincipalVariationsForBorrowerCycle getLoanProductsPrincipalVariationsForBorrowerCycle = (GetLoanProductsPrincipalVariationsForBorrowerCycle) o;
		return Objects.equals(this.borrowerCycleNumber,
				getLoanProductsPrincipalVariationsForBorrowerCycle.borrowerCycleNumber)
				&& Objects.equals(this.defaultValue, getLoanProductsPrincipalVariationsForBorrowerCycle.defaultValue)
				&& Objects.equals(this.id, getLoanProductsPrincipalVariationsForBorrowerCycle.id)
				&& Objects.equals(this.maxValue, getLoanProductsPrincipalVariationsForBorrowerCycle.maxValue)
				&& Objects.equals(this.minValue, getLoanProductsPrincipalVariationsForBorrowerCycle.minValue)
				&& Objects.equals(this.paramType, getLoanProductsPrincipalVariationsForBorrowerCycle.paramType)
				&& Objects.equals(this.valueConditionType,
						getLoanProductsPrincipalVariationsForBorrowerCycle.valueConditionType);
	}

	@Override
	public int hashCode() {

		return Objects.hash(borrowerCycleNumber, defaultValue, id, maxValue, minValue, paramType, valueConditionType);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanProductsPrincipalVariationsForBorrowerCycle {\n");
		sb.append("    borrowerCycleNumber: ").append(toIndentedString(borrowerCycleNumber)).append("\n");
		sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    maxValue: ").append(toIndentedString(maxValue)).append("\n");
		sb.append("    minValue: ").append(toIndentedString(minValue)).append("\n");
		sb.append("    paramType: ").append(toIndentedString(paramType)).append("\n");
		sb.append("    valueConditionType: ").append(toIndentedString(valueConditionType)).append("\n");
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
