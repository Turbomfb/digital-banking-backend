/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetSelfLoansProductOptions */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetSelfLoansProductOptions {

	private Integer id;

	private String name;

	public GetSelfLoansProductOptions id(Integer id) {

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

	public GetSelfLoansProductOptions name(String name) {

		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "Kampala Product (with cash accounting)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
		GetSelfLoansProductOptions getSelfLoansProductOptions = (GetSelfLoansProductOptions) o;
		return Objects.equals(this.id, getSelfLoansProductOptions.id)
				&& Objects.equals(this.name, getSelfLoansProductOptions.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetSelfLoansProductOptions {\n");
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
