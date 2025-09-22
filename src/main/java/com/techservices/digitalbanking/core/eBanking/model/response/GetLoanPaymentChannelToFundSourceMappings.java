/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoanPaymentChannelToFundSourceMappings */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanPaymentChannelToFundSourceMappings {

	private GetLoanFundSourceAccount fundSourceAccount;

	private GetLoanPaymentType paymentType;

	public GetLoanPaymentChannelToFundSourceMappings fundSourceAccount(GetLoanFundSourceAccount fundSourceAccount) {
		this.fundSourceAccount = fundSourceAccount;
		return this;
	}

	/**
	 * Get fundSourceAccount
	 *
	 * @return fundSourceAccount
	 */
	@Valid
	@Schema(name = "fundSourceAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("fundSourceAccount")
	public GetLoanFundSourceAccount getFundSourceAccount() {
		return fundSourceAccount;
	}

	public void setFundSourceAccount(GetLoanFundSourceAccount fundSourceAccount) {
		this.fundSourceAccount = fundSourceAccount;
	}

	public GetLoanPaymentChannelToFundSourceMappings paymentType(GetLoanPaymentType paymentType) {
		this.paymentType = paymentType;
		return this;
	}

	/**
	 * Get paymentType
	 *
	 * @return paymentType
	 */
	@Valid
	@Schema(name = "paymentType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("paymentType")
	public GetLoanPaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(GetLoanPaymentType paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanPaymentChannelToFundSourceMappings getLoanPaymentChannelToFundSourceMappings = (GetLoanPaymentChannelToFundSourceMappings) o;
		return Objects.equals(this.fundSourceAccount, getLoanPaymentChannelToFundSourceMappings.fundSourceAccount)
				&& Objects.equals(this.paymentType, getLoanPaymentChannelToFundSourceMappings.paymentType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fundSourceAccount, paymentType);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanPaymentChannelToFundSourceMappings {\n");
		sb.append("    fundSourceAccount: ").append(toIndentedString(fundSourceAccount)).append("\n");
		sb.append("    paymentType: ").append(toIndentedString(paymentType)).append("\n");
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
