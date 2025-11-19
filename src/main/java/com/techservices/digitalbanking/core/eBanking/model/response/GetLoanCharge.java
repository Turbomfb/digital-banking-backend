/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetLoanCharge */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanCharge {

	private Boolean active;

	private Integer id;

	private String name;

	private Boolean penalty;

	public GetLoanCharge active(Boolean active) {

		this.active = active;
		return this;
	}

	/**
	 * Get active
	 *
	 * @return active
	 */
	@Schema(name = "active", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("active")
	public Boolean getActive() {

		return active;
	}

	public void setActive(Boolean active) {

		this.active = active;
	}

	public GetLoanCharge id(Integer id) {

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

	public GetLoanCharge name(String name) {

		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "flat install", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public GetLoanCharge penalty(Boolean penalty) {

		this.penalty = penalty;
		return this;
	}

	/**
	 * Get penalty
	 *
	 * @return penalty
	 */
	@Schema(name = "penalty", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("penalty")
	public Boolean getPenalty() {

		return penalty;
	}

	public void setPenalty(Boolean penalty) {

		this.penalty = penalty;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanCharge getLoanCharge = (GetLoanCharge) o;
		return Objects.equals(this.active, getLoanCharge.active) && Objects.equals(this.id, getLoanCharge.id)
				&& Objects.equals(this.name, getLoanCharge.name) && Objects.equals(this.penalty, getLoanCharge.penalty);
	}

	@Override
	public int hashCode() {

		return Objects.hash(active, id, name, penalty);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanCharge {\n");
		sb.append("    active: ").append(toIndentedString(active)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    penalty: ").append(toIndentedString(penalty)).append("\n");
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
