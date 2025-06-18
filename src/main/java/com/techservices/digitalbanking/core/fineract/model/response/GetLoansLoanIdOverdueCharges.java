/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansLoanIdOverdueCharges */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdOverdueCharges {

	private Boolean active;

	private Double amount;

	private GetLoanChargeTemplateChargeAppliesTo chargeAppliesTo;

	private GetLoansLoanIdChargeCalculationType chargeCalculationType;

	private GetLoansLoanIdChargePaymentMode chargePaymentMode;

	private GetLoansLoanIdChargeTimeType chargeTimeType;

	private GetLoanCurrency currency;

	private GetLoansLoanIdFeeFrequency feeFrequency;

	private Integer feeInterval;

	private Integer id;

	private String name;

	private Boolean penalty;

	public GetLoansLoanIdOverdueCharges active(Boolean active) {
		this.active = active;
		return this;
	}

	/**
	 * Get active
	 *
	 * @return active
	 */
	@Schema(name = "active", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("active")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public GetLoansLoanIdOverdueCharges amount(Double amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 */
	@Schema(name = "amount", example = "3.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public GetLoansLoanIdOverdueCharges chargeAppliesTo(GetLoanChargeTemplateChargeAppliesTo chargeAppliesTo) {
		this.chargeAppliesTo = chargeAppliesTo;
		return this;
	}

	/**
	 * Get chargeAppliesTo
	 *
	 * @return chargeAppliesTo
	 */
	@Valid
	@Schema(name = "chargeAppliesTo", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("chargeAppliesTo")
	public GetLoanChargeTemplateChargeAppliesTo getChargeAppliesTo() {
		return chargeAppliesTo;
	}

	public void setChargeAppliesTo(GetLoanChargeTemplateChargeAppliesTo chargeAppliesTo) {
		this.chargeAppliesTo = chargeAppliesTo;
	}

	public GetLoansLoanIdOverdueCharges chargeCalculationType(
			GetLoansLoanIdChargeCalculationType chargeCalculationType) {
		this.chargeCalculationType = chargeCalculationType;
		return this;
	}

	/**
	 * Get chargeCalculationType
	 *
	 * @return chargeCalculationType
	 */
	@Valid
	@Schema(name = "chargeCalculationType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("chargeCalculationType")
	public GetLoansLoanIdChargeCalculationType getChargeCalculationType() {
		return chargeCalculationType;
	}

	public void setChargeCalculationType(GetLoansLoanIdChargeCalculationType chargeCalculationType) {
		this.chargeCalculationType = chargeCalculationType;
	}

	public GetLoansLoanIdOverdueCharges chargePaymentMode(GetLoansLoanIdChargePaymentMode chargePaymentMode) {
		this.chargePaymentMode = chargePaymentMode;
		return this;
	}

	/**
	 * Get chargePaymentMode
	 *
	 * @return chargePaymentMode
	 */
	@Valid
	@Schema(name = "chargePaymentMode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("chargePaymentMode")
	public GetLoansLoanIdChargePaymentMode getChargePaymentMode() {
		return chargePaymentMode;
	}

	public void setChargePaymentMode(GetLoansLoanIdChargePaymentMode chargePaymentMode) {
		this.chargePaymentMode = chargePaymentMode;
	}

	public GetLoansLoanIdOverdueCharges chargeTimeType(GetLoansLoanIdChargeTimeType chargeTimeType) {
		this.chargeTimeType = chargeTimeType;
		return this;
	}

	/**
	 * Get chargeTimeType
	 *
	 * @return chargeTimeType
	 */
	@Valid
	@Schema(name = "chargeTimeType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("chargeTimeType")
	public GetLoansLoanIdChargeTimeType getChargeTimeType() {
		return chargeTimeType;
	}

	public void setChargeTimeType(GetLoansLoanIdChargeTimeType chargeTimeType) {
		this.chargeTimeType = chargeTimeType;
	}

	public GetLoansLoanIdOverdueCharges currency(GetLoanCurrency currency) {
		this.currency = currency;
		return this;
	}

	/**
	 * Get currency
	 *
	 * @return currency
	 */
	@Valid
	@Schema(name = "currency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("currency")
	public GetLoanCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(GetLoanCurrency currency) {
		this.currency = currency;
	}

	public GetLoansLoanIdOverdueCharges feeFrequency(GetLoansLoanIdFeeFrequency feeFrequency) {
		this.feeFrequency = feeFrequency;
		return this;
	}

	/**
	 * Get feeFrequency
	 *
	 * @return feeFrequency
	 */
	@Valid
	@Schema(name = "feeFrequency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeFrequency")
	public GetLoansLoanIdFeeFrequency getFeeFrequency() {
		return feeFrequency;
	}

	public void setFeeFrequency(GetLoansLoanIdFeeFrequency feeFrequency) {
		this.feeFrequency = feeFrequency;
	}

	public GetLoansLoanIdOverdueCharges feeInterval(Integer feeInterval) {
		this.feeInterval = feeInterval;
		return this;
	}

	/**
	 * Get feeInterval
	 *
	 * @return feeInterval
	 */
	@Schema(name = "feeInterval", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("feeInterval")
	public Integer getFeeInterval() {
		return feeInterval;
	}

	public void setFeeInterval(Integer feeInterval) {
		this.feeInterval = feeInterval;
	}

	public GetLoansLoanIdOverdueCharges id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "20", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GetLoansLoanIdOverdueCharges name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "overdraft penality", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GetLoansLoanIdOverdueCharges penalty(Boolean penalty) {
		this.penalty = penalty;
		return this;
	}

	/**
	 * Get penalty
	 *
	 * @return penalty
	 */
	@Schema(name = "penalty", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
		GetLoansLoanIdOverdueCharges getLoansLoanIdOverdueCharges = (GetLoansLoanIdOverdueCharges) o;
		return Objects.equals(this.active, getLoansLoanIdOverdueCharges.active)
				&& Objects.equals(this.amount, getLoansLoanIdOverdueCharges.amount)
				&& Objects.equals(this.chargeAppliesTo, getLoansLoanIdOverdueCharges.chargeAppliesTo)
				&& Objects.equals(this.chargeCalculationType, getLoansLoanIdOverdueCharges.chargeCalculationType)
				&& Objects.equals(this.chargePaymentMode, getLoansLoanIdOverdueCharges.chargePaymentMode)
				&& Objects.equals(this.chargeTimeType, getLoansLoanIdOverdueCharges.chargeTimeType)
				&& Objects.equals(this.currency, getLoansLoanIdOverdueCharges.currency)
				&& Objects.equals(this.feeFrequency, getLoansLoanIdOverdueCharges.feeFrequency)
				&& Objects.equals(this.feeInterval, getLoansLoanIdOverdueCharges.feeInterval)
				&& Objects.equals(this.id, getLoansLoanIdOverdueCharges.id)
				&& Objects.equals(this.name, getLoansLoanIdOverdueCharges.name)
				&& Objects.equals(this.penalty, getLoansLoanIdOverdueCharges.penalty);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, amount, chargeAppliesTo, chargeCalculationType, chargePaymentMode, chargeTimeType,
				currency, feeFrequency, feeInterval, id, name, penalty);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdOverdueCharges {\n");
		sb.append("    active: ").append(toIndentedString(active)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    chargeAppliesTo: ").append(toIndentedString(chargeAppliesTo)).append("\n");
		sb.append("    chargeCalculationType: ").append(toIndentedString(chargeCalculationType)).append("\n");
		sb.append("    chargePaymentMode: ").append(toIndentedString(chargePaymentMode)).append("\n");
		sb.append("    chargeTimeType: ").append(toIndentedString(chargeTimeType)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    feeFrequency: ").append(toIndentedString(feeFrequency)).append("\n");
		sb.append("    feeInterval: ").append(toIndentedString(feeInterval)).append("\n");
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
