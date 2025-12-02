/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import lombok.Setter;

/** GetLoansLoanIdInterestCalculationPeriodType */
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdInterestCalculationPeriodType {

	private String code;

	private String description;

	private Integer id;

	public GetLoansLoanIdInterestCalculationPeriodType code(String code) {

		this.code = code;
		return this;
	}

	/**
	 * Get code
	 *
	 * @return code
	 */
	@Schema(name = "code", example = "interestCalculationPeriodType.same.as.repayment.period", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("code")
	public String getCode() {

		return code;
	}

	public GetLoansLoanIdInterestCalculationPeriodType description(String description) {

		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 */
	@Schema(name = "description", example = "Same as repayment period", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("description")
	public String getDescription() {

		return description;
	}

	public GetLoansLoanIdInterestCalculationPeriodType id(Integer id) {

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

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansLoanIdInterestCalculationPeriodType getLoansLoanIdInterestCalculationPeriodType = (GetLoansLoanIdInterestCalculationPeriodType) o;
		return Objects.equals(this.code, getLoansLoanIdInterestCalculationPeriodType.code)
				&& Objects.equals(this.description, getLoansLoanIdInterestCalculationPeriodType.description)
				&& Objects.equals(this.id, getLoansLoanIdInterestCalculationPeriodType.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(code, description, id);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdInterestCalculationPeriodType {\n");
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
