/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/** ResultsetColumnHeaderData */
public class ResultsetColumnHeaderData {

	private Boolean booleanDisplayType;

	private Boolean codeLookupDisplayType;

	private Boolean codeValueDisplayType;

	private String columnCode;

	private String columnDisplayType;

	private Long columnLength;

	private String columnName;

	private String columnType;

	private List<Object> columnValues = new ArrayList<>();

	private Boolean dateDisplayType;

	private Boolean dateTimeDisplayType;

	private Boolean decimalDisplayType;

	private Boolean integerDisplayType;

	private Boolean isColumnNullable;

	private Boolean isColumnPrimaryKey;

	private Boolean mandatory;

	private Boolean optional;

	private Boolean string;

	public ResultsetColumnHeaderData booleanDisplayType(Boolean booleanDisplayType) {
		this.booleanDisplayType = booleanDisplayType;
		return this;
	}

	/**
	 * Get booleanDisplayType
	 *
	 * @return booleanDisplayType
	 */
	@JsonProperty("booleanDisplayType")
	public Boolean getBooleanDisplayType() {
		return booleanDisplayType;
	}

	public void setBooleanDisplayType(Boolean booleanDisplayType) {
		this.booleanDisplayType = booleanDisplayType;
	}

	public ResultsetColumnHeaderData codeLookupDisplayType(Boolean codeLookupDisplayType) {
		this.codeLookupDisplayType = codeLookupDisplayType;
		return this;
	}

	/**
	 * Get codeLookupDisplayType
	 *
	 * @return codeLookupDisplayType
	 */
	@JsonProperty("codeLookupDisplayType")
	public Boolean getCodeLookupDisplayType() {
		return codeLookupDisplayType;
	}

	public void setCodeLookupDisplayType(Boolean codeLookupDisplayType) {
		this.codeLookupDisplayType = codeLookupDisplayType;
	}

	public ResultsetColumnHeaderData codeValueDisplayType(Boolean codeValueDisplayType) {
		this.codeValueDisplayType = codeValueDisplayType;
		return this;
	}

	/**
	 * Get codeValueDisplayType
	 *
	 * @return codeValueDisplayType
	 */
	@JsonProperty("codeValueDisplayType")
	public Boolean getCodeValueDisplayType() {
		return codeValueDisplayType;
	}

	public void setCodeValueDisplayType(Boolean codeValueDisplayType) {
		this.codeValueDisplayType = codeValueDisplayType;
	}

	public ResultsetColumnHeaderData columnCode(String columnCode) {
		this.columnCode = columnCode;
		return this;
	}

	/**
	 * Get columnCode
	 *
	 * @return columnCode
	 */
	@JsonProperty("columnCode")
	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public ResultsetColumnHeaderData columnDisplayType(String columnDisplayType) {
		this.columnDisplayType = columnDisplayType;
		return this;
	}

	/**
	 * Get columnDisplayType
	 *
	 * @return columnDisplayType
	 */
	@JsonProperty("columnDisplayType")
	public String getColumnDisplayType() {
		return columnDisplayType;
	}

	public void setColumnDisplayType(String columnDisplayType) {
		this.columnDisplayType = columnDisplayType;
	}

	public ResultsetColumnHeaderData columnLength(Long columnLength) {
		this.columnLength = columnLength;
		return this;
	}

	/**
	 * Get columnLength
	 *
	 * @return columnLength
	 */
	@JsonProperty("columnLength")
	public Long getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(Long columnLength) {
		this.columnLength = columnLength;
	}

	public ResultsetColumnHeaderData columnName(String columnName) {
		this.columnName = columnName;
		return this;
	}

	/**
	 * Get columnName
	 *
	 * @return columnName
	 */
	@JsonProperty("columnName")
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public ResultsetColumnHeaderData columnType(String columnType) {
		this.columnType = columnType;
		return this;
	}

	/**
	 * Get columnType
	 *
	 * @return columnType
	 */
	@JsonProperty("columnType")
	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public ResultsetColumnHeaderData columnValues(List<Object> columnValues) {
		this.columnValues = columnValues;
		return this;
	}

	public ResultsetColumnHeaderData addColumnValuesItem(Object columnValuesItem) {
		if (this.columnValues == null) {
			this.columnValues = new ArrayList<>();
		}
		this.columnValues.add(columnValuesItem);
		return this;
	}

	/**
	 * Get columnValues
	 *
	 * @return columnValues
	 */
	@JsonProperty("columnValues")
	public List<Object> getColumnValues() {
		return columnValues;
	}

	public void setColumnValues(List<Object> columnValues) {
		this.columnValues = columnValues;
	}

	public ResultsetColumnHeaderData dateDisplayType(Boolean dateDisplayType) {
		this.dateDisplayType = dateDisplayType;
		return this;
	}

	/**
	 * Get dateDisplayType
	 *
	 * @return dateDisplayType
	 */
	@JsonProperty("dateDisplayType")
	public Boolean getDateDisplayType() {
		return dateDisplayType;
	}

	public void setDateDisplayType(Boolean dateDisplayType) {
		this.dateDisplayType = dateDisplayType;
	}

	public ResultsetColumnHeaderData dateTimeDisplayType(Boolean dateTimeDisplayType) {
		this.dateTimeDisplayType = dateTimeDisplayType;
		return this;
	}

	/**
	 * Get dateTimeDisplayType
	 *
	 * @return dateTimeDisplayType
	 */
	@JsonProperty("dateTimeDisplayType")
	public Boolean getDateTimeDisplayType() {
		return dateTimeDisplayType;
	}

	public void setDateTimeDisplayType(Boolean dateTimeDisplayType) {
		this.dateTimeDisplayType = dateTimeDisplayType;
	}

	public ResultsetColumnHeaderData decimalDisplayType(Boolean decimalDisplayType) {
		this.decimalDisplayType = decimalDisplayType;
		return this;
	}

	/**
	 * Get decimalDisplayType
	 *
	 * @return decimalDisplayType
	 */
	@JsonProperty("decimalDisplayType")
	public Boolean getDecimalDisplayType() {
		return decimalDisplayType;
	}

	public void setDecimalDisplayType(Boolean decimalDisplayType) {
		this.decimalDisplayType = decimalDisplayType;
	}

	public ResultsetColumnHeaderData integerDisplayType(Boolean integerDisplayType) {
		this.integerDisplayType = integerDisplayType;
		return this;
	}

	/**
	 * Get integerDisplayType
	 *
	 * @return integerDisplayType
	 */
	@JsonProperty("integerDisplayType")
	public Boolean getIntegerDisplayType() {
		return integerDisplayType;
	}

	public void setIntegerDisplayType(Boolean integerDisplayType) {
		this.integerDisplayType = integerDisplayType;
	}

	public ResultsetColumnHeaderData isColumnNullable(Boolean isColumnNullable) {
		this.isColumnNullable = isColumnNullable;
		return this;
	}

	/**
	 * Get isColumnNullable
	 *
	 * @return isColumnNullable
	 */
	@JsonProperty("isColumnNullable")
	public Boolean getIsColumnNullable() {
		return isColumnNullable;
	}

	public void setIsColumnNullable(Boolean isColumnNullable) {
		this.isColumnNullable = isColumnNullable;
	}

	public ResultsetColumnHeaderData isColumnPrimaryKey(Boolean isColumnPrimaryKey) {
		this.isColumnPrimaryKey = isColumnPrimaryKey;
		return this;
	}

	/**
	 * Get isColumnPrimaryKey
	 *
	 * @return isColumnPrimaryKey
	 */
	@JsonProperty("isColumnPrimaryKey")
	public Boolean getIsColumnPrimaryKey() {
		return isColumnPrimaryKey;
	}

	public void setIsColumnPrimaryKey(Boolean isColumnPrimaryKey) {
		this.isColumnPrimaryKey = isColumnPrimaryKey;
	}

	public ResultsetColumnHeaderData mandatory(Boolean mandatory) {
		this.mandatory = mandatory;
		return this;
	}

	/**
	 * Get mandatory
	 *
	 * @return mandatory
	 */
	@JsonProperty("mandatory")
	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public ResultsetColumnHeaderData optional(Boolean optional) {
		this.optional = optional;
		return this;
	}

	/**
	 * Get optional
	 *
	 * @return optional
	 */
	@JsonProperty("optional")
	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional = optional;
	}

	public ResultsetColumnHeaderData string(Boolean string) {
		this.string = string;
		return this;
	}

	/**
	 * Get string
	 *
	 * @return string
	 */
	@JsonProperty("string")
	public Boolean getString() {
		return string;
	}

	public void setString(Boolean string) {
		this.string = string;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResultsetColumnHeaderData resultsetColumnHeaderData = (ResultsetColumnHeaderData) o;
		return Objects.equals(this.booleanDisplayType, resultsetColumnHeaderData.booleanDisplayType)
				&& Objects.equals(this.codeLookupDisplayType, resultsetColumnHeaderData.codeLookupDisplayType)
				&& Objects.equals(this.codeValueDisplayType, resultsetColumnHeaderData.codeValueDisplayType)
				&& Objects.equals(this.columnCode, resultsetColumnHeaderData.columnCode)
				&& Objects.equals(this.columnDisplayType, resultsetColumnHeaderData.columnDisplayType)
				&& Objects.equals(this.columnLength, resultsetColumnHeaderData.columnLength)
				&& Objects.equals(this.columnName, resultsetColumnHeaderData.columnName)
				&& Objects.equals(this.columnType, resultsetColumnHeaderData.columnType)
				&& Objects.equals(this.columnValues, resultsetColumnHeaderData.columnValues)
				&& Objects.equals(this.dateDisplayType, resultsetColumnHeaderData.dateDisplayType)
				&& Objects.equals(this.dateTimeDisplayType, resultsetColumnHeaderData.dateTimeDisplayType)
				&& Objects.equals(this.decimalDisplayType, resultsetColumnHeaderData.decimalDisplayType)
				&& Objects.equals(this.integerDisplayType, resultsetColumnHeaderData.integerDisplayType)
				&& Objects.equals(this.isColumnNullable, resultsetColumnHeaderData.isColumnNullable)
				&& Objects.equals(this.isColumnPrimaryKey, resultsetColumnHeaderData.isColumnPrimaryKey)
				&& Objects.equals(this.mandatory, resultsetColumnHeaderData.mandatory)
				&& Objects.equals(this.optional, resultsetColumnHeaderData.optional)
				&& Objects.equals(this.string, resultsetColumnHeaderData.string);
	}

	@Override
	public int hashCode() {
		return Objects.hash(booleanDisplayType, codeLookupDisplayType, codeValueDisplayType, columnCode,
				columnDisplayType, columnLength, columnName, columnType, columnValues, dateDisplayType,
				dateTimeDisplayType, decimalDisplayType, integerDisplayType, isColumnNullable, isColumnPrimaryKey,
				mandatory, optional, string);
	}
}
