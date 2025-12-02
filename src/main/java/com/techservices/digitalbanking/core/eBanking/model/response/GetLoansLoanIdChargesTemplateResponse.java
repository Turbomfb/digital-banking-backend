/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdChargesTemplateResponse */
@Schema(name = "GetLoansLoanIdChargesTemplateResponse", description = "GetLoansLoanIdChargesTemplateResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdChargesTemplateResponse {

	private Double amountPaid;

	private Double amountWaived;

	private Double amountWrittenOff;

	@Valid
	private Set<@Valid GetLoanChargeTemplateChargeOptions> chargeOptions = new LinkedHashSet<>();

	private Boolean penalty;

	public GetLoansLoanIdChargesTemplateResponse amountPaid(Double amountPaid) {

		this.amountPaid = amountPaid;
		return this;
	}

	/**
	 * Get amountPaid
	 *
	 * @return amountPaid
	 */
	@Schema(name = "amountPaid", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amountPaid")
	public Double getAmountPaid() {

		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {

		this.amountPaid = amountPaid;
	}

	public GetLoansLoanIdChargesTemplateResponse amountWaived(Double amountWaived) {

		this.amountWaived = amountWaived;
		return this;
	}

	/**
	 * Get amountWaived
	 *
	 * @return amountWaived
	 */
	@Schema(name = "amountWaived", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amountWaived")
	public Double getAmountWaived() {

		return amountWaived;
	}

	public void setAmountWaived(Double amountWaived) {

		this.amountWaived = amountWaived;
	}

	public GetLoansLoanIdChargesTemplateResponse amountWrittenOff(Double amountWrittenOff) {

		this.amountWrittenOff = amountWrittenOff;
		return this;
	}

	/**
	 * Get amountWrittenOff
	 *
	 * @return amountWrittenOff
	 */
	@Schema(name = "amountWrittenOff", example = "0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amountWrittenOff")
	public Double getAmountWrittenOff() {

		return amountWrittenOff;
	}

	public void setAmountWrittenOff(Double amountWrittenOff) {

		this.amountWrittenOff = amountWrittenOff;
	}

	public GetLoansLoanIdChargesTemplateResponse chargeOptions(
			Set<@Valid GetLoanChargeTemplateChargeOptions> chargeOptions) {

		this.chargeOptions = chargeOptions;
		return this;
	}

	public GetLoansLoanIdChargesTemplateResponse addChargeOptionsItem(
			GetLoanChargeTemplateChargeOptions chargeOptionsItem) {

		if (this.chargeOptions == null) {
			this.chargeOptions = new LinkedHashSet<>();
		}
		this.chargeOptions.add(chargeOptionsItem);
		return this;
	}

	/**
	 * Get chargeOptions
	 *
	 * @return chargeOptions
	 */
	@Valid
	@Schema(name = "chargeOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("chargeOptions")
	public Set<@Valid GetLoanChargeTemplateChargeOptions> getChargeOptions() {

		return chargeOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setChargeOptions(Set<@Valid GetLoanChargeTemplateChargeOptions> chargeOptions) {

		this.chargeOptions = chargeOptions;
	}

	public GetLoansLoanIdChargesTemplateResponse penalty(Boolean penalty) {

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
		GetLoansLoanIdChargesTemplateResponse getLoansLoanIdChargesTemplateResponse = (GetLoansLoanIdChargesTemplateResponse) o;
		return Objects.equals(this.amountPaid, getLoansLoanIdChargesTemplateResponse.amountPaid)
				&& Objects.equals(this.amountWaived, getLoansLoanIdChargesTemplateResponse.amountWaived)
				&& Objects.equals(this.amountWrittenOff, getLoansLoanIdChargesTemplateResponse.amountWrittenOff)
				&& Objects.equals(this.chargeOptions, getLoansLoanIdChargesTemplateResponse.chargeOptions)
				&& Objects.equals(this.penalty, getLoansLoanIdChargesTemplateResponse.penalty);
	}

	@Override
	public int hashCode() {

		return Objects.hash(amountPaid, amountWaived, amountWrittenOff, chargeOptions, penalty);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdChargesTemplateResponse {\n");
		sb.append("    amountPaid: ").append(toIndentedString(amountPaid)).append("\n");
		sb.append("    amountWaived: ").append(toIndentedString(amountWaived)).append("\n");
		sb.append("    amountWrittenOff: ").append(toIndentedString(amountWrittenOff)).append("\n");
		sb.append("    chargeOptions: ").append(toIndentedString(chargeOptions)).append("\n");
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
