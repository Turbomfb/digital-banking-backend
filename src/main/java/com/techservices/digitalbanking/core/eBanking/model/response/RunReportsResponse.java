/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/** RunReportsResponse */
public class RunReportsResponse {

	private List<ResultsetColumnHeaderData> columnHeaders = new ArrayList<>();

	private List<ResultsetRowData> data = new ArrayList<>();

	public RunReportsResponse columnHeaders(List<ResultsetColumnHeaderData> columnHeaders) {

		this.columnHeaders = columnHeaders;
		return this;
	}

	public RunReportsResponse addColumnHeadersItem(ResultsetColumnHeaderData columnHeadersItem) {

		if (this.columnHeaders == null) {
			this.columnHeaders = new ArrayList<>();
		}
		this.columnHeaders.add(columnHeadersItem);
		return this;
	}

	/**
	 * Get columnHeaders
	 *
	 * @return columnHeaders
	 */
	@JsonProperty("columnHeaders")
	public List<ResultsetColumnHeaderData> getColumnHeaders() {

		return columnHeaders;
	}

	public void setColumnHeaders(List<ResultsetColumnHeaderData> columnHeaders) {

		this.columnHeaders = columnHeaders;
	}

	public RunReportsResponse data(List<ResultsetRowData> data) {

		this.data = data;
		return this;
	}

	public RunReportsResponse addDataItem(ResultsetRowData dataItem) {

		if (this.data == null) {
			this.data = new ArrayList<>();
		}
		this.data.add(dataItem);
		return this;
	}

	/**
	 * Get data
	 *
	 * @return data
	 */
	@JsonProperty("data")
	public List<ResultsetRowData> getData() {

		return data;
	}

	public void setData(List<ResultsetRowData> data) {

		this.data = data;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RunReportsResponse runReportsResponse = (RunReportsResponse) o;
		return Objects.equals(this.columnHeaders, runReportsResponse.columnHeaders)
				&& Objects.equals(this.data, runReportsResponse.data);
	}

	@Override
	public int hashCode() {

		return Objects.hash(columnHeaders, data);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class RunReportsResponse {\n");
		sb.append("    columnHeaders: ").append(toIndentedString(columnHeaders)).append("\n");
		sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
