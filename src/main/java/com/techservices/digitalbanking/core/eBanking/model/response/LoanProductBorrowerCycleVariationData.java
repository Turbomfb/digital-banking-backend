/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** LoanProductBorrowerCycleVariationData */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class LoanProductBorrowerCycleVariationData {

	private Integer borrowerCycleNumber;

	private BigDecimal defaultValue;

	/** Gets or Sets paramType */
	public enum ParamTypeEnum {
		INVALID("INVALID"),

		PRINCIPAL("PRINCIPAL"),

		INTERESTRATE("INTERESTRATE"),

		REPAYMENT("REPAYMENT");

		private String value;

		ParamTypeEnum(String value) {

			this.value = value;
		}

		@JsonValue
		public String getValue() {

			return value;
		}

		@Override
		public String toString() {

			return String.valueOf(value);
		}

		@JsonCreator
		public static ParamTypeEnum fromValue(String value) {

			for (ParamTypeEnum b : ParamTypeEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	private ParamTypeEnum paramType;

	/** Gets or Sets valueConditionType */
	public enum ValueConditionTypeEnum {
		INVALID("INVALID"),

		EQUAL("EQUAL"),

		GREATERTHAN("GREATERTHAN");

		private String value;

		ValueConditionTypeEnum(String value) {

			this.value = value;
		}

		@JsonValue
		public String getValue() {

			return value;
		}

		@Override
		public String toString() {

			return String.valueOf(value);
		}

		@JsonCreator
		public static ValueConditionTypeEnum fromValue(String value) {

			for (ValueConditionTypeEnum b : ValueConditionTypeEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	private ValueConditionTypeEnum valueConditionType;

	public LoanProductBorrowerCycleVariationData borrowerCycleNumber(Integer borrowerCycleNumber) {

		this.borrowerCycleNumber = borrowerCycleNumber;
		return this;
	}

	/**
	 * Get borrowerCycleNumber
	 *
	 * @return borrowerCycleNumber
	 */
	@Schema(name = "borrowerCycleNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("borrowerCycleNumber")
	public Integer getBorrowerCycleNumber() {

		return borrowerCycleNumber;
	}

	public void setBorrowerCycleNumber(Integer borrowerCycleNumber) {

		this.borrowerCycleNumber = borrowerCycleNumber;
	}

	public LoanProductBorrowerCycleVariationData defaultValue(BigDecimal defaultValue) {

		this.defaultValue = defaultValue;
		return this;
	}

	/**
	 * Get defaultValue
	 *
	 * @return defaultValue
	 */
	@Valid
	@Schema(name = "defaultValue", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("defaultValue")
	public BigDecimal getDefaultValue() {

		return defaultValue;
	}

	public void setDefaultValue(BigDecimal defaultValue) {

		this.defaultValue = defaultValue;
	}

	public LoanProductBorrowerCycleVariationData paramType(ParamTypeEnum paramType) {

		this.paramType = paramType;
		return this;
	}

	/**
	 * Get paramType
	 *
	 * @return paramType
	 */
	@Schema(name = "paramType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("paramType")
	public ParamTypeEnum getParamType() {

		return paramType;
	}

	public void setParamType(ParamTypeEnum paramType) {

		this.paramType = paramType;
	}

	public LoanProductBorrowerCycleVariationData valueConditionType(ValueConditionTypeEnum valueConditionType) {

		this.valueConditionType = valueConditionType;
		return this;
	}

	/**
	 * Get valueConditionType
	 *
	 * @return valueConditionType
	 */
	@Schema(name = "valueConditionType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("valueConditionType")
	public ValueConditionTypeEnum getValueConditionType() {

		return valueConditionType;
	}

	public void setValueConditionType(ValueConditionTypeEnum valueConditionType) {

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
		LoanProductBorrowerCycleVariationData loanProductBorrowerCycleVariationData = (LoanProductBorrowerCycleVariationData) o;
		return Objects.equals(this.borrowerCycleNumber, loanProductBorrowerCycleVariationData.borrowerCycleNumber)
				&& Objects.equals(this.defaultValue, loanProductBorrowerCycleVariationData.defaultValue)
				&& Objects.equals(this.paramType, loanProductBorrowerCycleVariationData.paramType)
				&& Objects.equals(this.valueConditionType, loanProductBorrowerCycleVariationData.valueConditionType);
	}

	@Override
	public int hashCode() {

		return Objects.hash(borrowerCycleNumber, defaultValue, paramType, valueConditionType);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class LoanProductBorrowerCycleVariationData {\n");
		sb.append("    borrowerCycleNumber: ").append(toIndentedString(borrowerCycleNumber)).append("\n");
		sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
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
