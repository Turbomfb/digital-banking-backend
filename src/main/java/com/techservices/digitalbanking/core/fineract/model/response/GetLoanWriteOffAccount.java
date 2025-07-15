/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetLoanWriteOffAccount */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanWriteOffAccount {

	private Integer glCode;

	private Integer id;

	private String name;

	public GetLoanWriteOffAccount glCode(Integer glCode) {
		this.glCode = glCode;
		return this;
	}

	/**
	 * Get glCode
	 *
	 * @return glCode
	 */
	@Schema(name = "glCode", example = "12", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("glCode")
	public Integer getGlCode() {
		return glCode;
	}

	public void setGlCode(Integer glCode) {
		this.glCode = glCode;
	}

	public GetLoanWriteOffAccount id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GetLoanWriteOffAccount name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "loans written off 2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanWriteOffAccount getLoanWriteOffAccount = (GetLoanWriteOffAccount) o;
		return Objects.equals(this.glCode, getLoanWriteOffAccount.glCode)
				&& Objects.equals(this.id, getLoanWriteOffAccount.id)
				&& Objects.equals(this.name, getLoanWriteOffAccount.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(glCode, id, name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanWriteOffAccount {\n");
		sb.append("    glCode: ").append(toIndentedString(glCode)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
