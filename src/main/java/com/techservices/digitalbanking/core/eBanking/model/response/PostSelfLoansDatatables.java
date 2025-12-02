/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** PostSelfLoansDatatables */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostSelfLoansDatatables {

	private PostSelfLoansData data;

	private String registeredTableName;

	public PostSelfLoansDatatables data(PostSelfLoansData data) {

		this.data = data;
		return this;
	}

	/**
	 * Get data
	 *
	 * @return data
	 */
	@Valid
	@Schema(name = "data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("data")
	public PostSelfLoansData getData() {

		return data;
	}

	public void setData(PostSelfLoansData data) {

		this.data = data;
	}

	public PostSelfLoansDatatables registeredTableName(String registeredTableName) {

		this.registeredTableName = registeredTableName;
		return this;
	}

	/**
	 * Get registeredTableName
	 *
	 * @return registeredTableName
	 */
	@Schema(name = "registeredTableName", example = "Date Loan Field", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("registeredTableName")
	public String getRegisteredTableName() {

		return registeredTableName;
	}

	public void setRegisteredTableName(String registeredTableName) {

		this.registeredTableName = registeredTableName;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostSelfLoansDatatables postSelfLoansDatatables = (PostSelfLoansDatatables) o;
		return Objects.equals(this.data, postSelfLoansDatatables.data)
				&& Objects.equals(this.registeredTableName, postSelfLoansDatatables.registeredTableName);
	}

	@Override
	public int hashCode() {

		return Objects.hash(data, registeredTableName);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PostSelfLoansDatatables {\n");
		sb.append("    data: ").append(toIndentedString(data)).append("\n");
		sb.append("    registeredTableName: ").append(toIndentedString(registeredTableName)).append("\n");
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
