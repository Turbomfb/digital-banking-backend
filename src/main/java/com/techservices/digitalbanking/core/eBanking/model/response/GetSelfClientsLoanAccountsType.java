/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetSelfClientsLoanAccountsType */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetSelfClientsLoanAccountsType {

	private String code;

	private String description;

	private Integer id;

	public GetSelfClientsLoanAccountsType code(String code) {

		this.code = code;
		return this;
	}

	/**
	 * Get code
	 *
	 * @return code
	 */
	@Schema(name = "code", example = "loanType.individual", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("code")
	public String getCode() {

		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public GetSelfClientsLoanAccountsType description(String description) {

		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 */
	@Schema(name = "description", example = "Individual", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("description")
	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public GetSelfClientsLoanAccountsType id(Integer id) {

		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetSelfClientsLoanAccountsType getSelfClientsLoanAccountsType = (GetSelfClientsLoanAccountsType) o;
		return Objects.equals(this.code, getSelfClientsLoanAccountsType.code)
				&& Objects.equals(this.description, getSelfClientsLoanAccountsType.description)
				&& Objects.equals(this.id, getSelfClientsLoanAccountsType.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(code, description, id);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetSelfClientsLoanAccountsType {\n");
		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
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
