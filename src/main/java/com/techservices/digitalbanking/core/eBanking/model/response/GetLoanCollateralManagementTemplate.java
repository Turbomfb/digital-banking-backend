/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoanCollateralManagementTemplate */
@Schema(name = "GetLoanCollateralManagementTemplate", description = "GetLoanCollateralManagementTemplate")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanCollateralManagementTemplate {

	private BigDecimal basePrice;

	private Long collateralId;

	private String name;

	private BigDecimal pctToBase;

	private BigDecimal quantity;

	public GetLoanCollateralManagementTemplate basePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
		return this;
	}

	/**
	 * Get basePrice
	 *
	 * @return basePrice
	 */
	@Valid
	@Schema(name = "basePrice", example = "10000.0", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("basePrice")
	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public GetLoanCollateralManagementTemplate collateralId(Long collateralId) {
		this.collateralId = collateralId;
		return this;
	}

	/**
	 * Get collateralId
	 *
	 * @return collateralId
	 */
	@Schema(name = "collateralId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("collateralId")
	public Long getCollateralId() {
		return collateralId;
	}

	public void setCollateralId(Long collateralId) {
		this.collateralId = collateralId;
	}

	public GetLoanCollateralManagementTemplate name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "Vehicle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GetLoanCollateralManagementTemplate pctToBase(BigDecimal pctToBase) {
		this.pctToBase = pctToBase;
		return this;
	}

	/**
	 * Get pctToBase
	 *
	 * @return pctToBase
	 */
	@Valid
	@Schema(name = "pctToBase", example = "40", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("pctToBase")
	public BigDecimal getPctToBase() {
		return pctToBase;
	}

	public void setPctToBase(BigDecimal pctToBase) {
		this.pctToBase = pctToBase;
	}

	public GetLoanCollateralManagementTemplate quantity(BigDecimal quantity) {
		this.quantity = quantity;
		return this;
	}

	/**
	 * Get quantity
	 *
	 * @return quantity
	 */
	@Valid
	@Schema(name = "quantity", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("quantity")
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanCollateralManagementTemplate getLoanCollateralManagementTemplate = (GetLoanCollateralManagementTemplate) o;
		return Objects.equals(this.basePrice, getLoanCollateralManagementTemplate.basePrice)
				&& Objects.equals(this.collateralId, getLoanCollateralManagementTemplate.collateralId)
				&& Objects.equals(this.name, getLoanCollateralManagementTemplate.name)
				&& Objects.equals(this.pctToBase, getLoanCollateralManagementTemplate.pctToBase)
				&& Objects.equals(this.quantity, getLoanCollateralManagementTemplate.quantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(basePrice, collateralId, name, pctToBase, quantity);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanCollateralManagementTemplate {\n");
		sb.append("    basePrice: ").append(toIndentedString(basePrice)).append("\n");
		sb.append("    collateralId: ").append(toIndentedString(collateralId)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    pctToBase: ").append(toIndentedString(pctToBase)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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
