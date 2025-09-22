/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoanProductsChargeOptions */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanProductsChargeOptions {

	private Boolean active;

	private Long amount;

	private GetLoanProductsChargeAppliesTo chargeAppliesTo;

	private GetLoanChargeCalculationType chargeCalculationType;

	private GetLoansChargePaymentMode chargePaymentMode;

	private GetLoanChargeTimeType chargeTimeType;

	private GetLoanProductsCurrencyOptions currency;

	private Integer id;

	private String name;

	private Boolean penalty;

	public GetLoanProductsChargeOptions active(Boolean active) {
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

	public GetLoanProductsChargeOptions amount(Long amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Get amount
	 *
	 * @return amount
	 */
	@Schema(name = "amount", example = "100", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("amount")
	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public GetLoanProductsChargeOptions chargeAppliesTo(GetLoanProductsChargeAppliesTo chargeAppliesTo) {
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
	public GetLoanProductsChargeAppliesTo getChargeAppliesTo() {
		return chargeAppliesTo;
	}

	public void setChargeAppliesTo(GetLoanProductsChargeAppliesTo chargeAppliesTo) {
		this.chargeAppliesTo = chargeAppliesTo;
	}

	public GetLoanProductsChargeOptions chargeCalculationType(GetLoanChargeCalculationType chargeCalculationType) {
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
	public GetLoanChargeCalculationType getChargeCalculationType() {
		return chargeCalculationType;
	}

	public void setChargeCalculationType(GetLoanChargeCalculationType chargeCalculationType) {
		this.chargeCalculationType = chargeCalculationType;
	}

	public GetLoanProductsChargeOptions chargePaymentMode(GetLoansChargePaymentMode chargePaymentMode) {
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
	public GetLoansChargePaymentMode getChargePaymentMode() {
		return chargePaymentMode;
	}

	public void setChargePaymentMode(GetLoansChargePaymentMode chargePaymentMode) {
		this.chargePaymentMode = chargePaymentMode;
	}

	public GetLoanProductsChargeOptions chargeTimeType(GetLoanChargeTimeType chargeTimeType) {
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
	public GetLoanChargeTimeType getChargeTimeType() {
		return chargeTimeType;
	}

	public void setChargeTimeType(GetLoanChargeTimeType chargeTimeType) {
		this.chargeTimeType = chargeTimeType;
	}

	public GetLoanProductsChargeOptions currency(GetLoanProductsCurrencyOptions currency) {
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
	public GetLoanProductsCurrencyOptions getCurrency() {
		return currency;
	}

	public void setCurrency(GetLoanProductsCurrencyOptions currency) {
		this.currency = currency;
	}

	public GetLoanProductsChargeOptions id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "5", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GetLoanProductsChargeOptions name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "des charges", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GetLoanProductsChargeOptions penalty(Boolean penalty) {
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
		GetLoanProductsChargeOptions getLoanProductsChargeOptions = (GetLoanProductsChargeOptions) o;
		return Objects.equals(this.active, getLoanProductsChargeOptions.active)
				&& Objects.equals(this.amount, getLoanProductsChargeOptions.amount)
				&& Objects.equals(this.chargeAppliesTo, getLoanProductsChargeOptions.chargeAppliesTo)
				&& Objects.equals(this.chargeCalculationType, getLoanProductsChargeOptions.chargeCalculationType)
				&& Objects.equals(this.chargePaymentMode, getLoanProductsChargeOptions.chargePaymentMode)
				&& Objects.equals(this.chargeTimeType, getLoanProductsChargeOptions.chargeTimeType)
				&& Objects.equals(this.currency, getLoanProductsChargeOptions.currency)
				&& Objects.equals(this.id, getLoanProductsChargeOptions.id)
				&& Objects.equals(this.name, getLoanProductsChargeOptions.name)
				&& Objects.equals(this.penalty, getLoanProductsChargeOptions.penalty);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, amount, chargeAppliesTo, chargeCalculationType, chargePaymentMode, chargeTimeType,
				currency, id, name, penalty);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanProductsChargeOptions {\n");
		sb.append("    active: ").append(toIndentedString(active)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
		sb.append("    chargeAppliesTo: ").append(toIndentedString(chargeAppliesTo)).append("\n");
		sb.append("    chargeCalculationType: ").append(toIndentedString(chargeCalculationType)).append("\n");
		sb.append("    chargePaymentMode: ").append(toIndentedString(chargePaymentMode)).append("\n");
		sb.append("    chargeTimeType: ").append(toIndentedString(chargeTimeType)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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
