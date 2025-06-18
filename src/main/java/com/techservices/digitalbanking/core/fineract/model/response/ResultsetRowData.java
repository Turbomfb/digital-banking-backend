/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;

/** ResultsetRowData */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-28T23:27:31.910763+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class ResultsetRowData {

	private List<String> row = new ArrayList<>();

	public ResultsetRowData row(List<String> row) {
		this.row = row;
		return this;
	}

	public ResultsetRowData addRowItem(String rowItem) {
		if (this.row == null) {
			this.row = new ArrayList<>();
		}
		this.row.add(rowItem);
		return this;
	}

	/**
	 * Get row
	 *
	 * @return row
	 */
	@JsonProperty("row")
	public List<String> getRow() {
		return row;
	}

	public void setRow(List<String> row) {
		this.row = row;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ResultsetRowData resultsetRowData = (ResultsetRowData) o;
		return Objects.equals(this.row, resultsetRowData.row);
	}

	@Override
	public int hashCode() {
		return Objects.hash(row);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ResultsetRowData {\n");
		sb.append("    row: ").append(toIndentedString(row)).append("\n");
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
