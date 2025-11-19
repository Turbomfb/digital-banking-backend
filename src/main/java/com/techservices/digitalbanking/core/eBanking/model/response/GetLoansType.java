/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetLoansType */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansType {

	private String code;

	private Boolean contra;

	private String description;

	private Boolean disbursement;

	private Integer id;

	private Boolean recoveryRepayment;

	private Boolean repayment;

	private Boolean repaymentAtDisbursement;

	private Boolean waiveCharges;

	private Boolean waiveInterest;

	private Boolean writeOff;

	public GetLoansType code(String code) {

		this.code = code;
		return this;
	}

	/**
	 * Get code
	 *
	 * @return code
	 */
	@Schema(name = "code", example = "loanTransactionType.repayment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("code")
	public String getCode() {

		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public GetLoansType contra(Boolean contra) {

		this.contra = contra;
		return this;
	}

	/**
	 * Get contra
	 *
	 * @return contra
	 */
	@Schema(name = "contra", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("contra")
	public Boolean getContra() {

		return contra;
	}

	public void setContra(Boolean contra) {

		this.contra = contra;
	}

	public GetLoansType description(String description) {

		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 */
	@Schema(name = "description", example = "Repayment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("description")
	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public GetLoansType disbursement(Boolean disbursement) {

		this.disbursement = disbursement;
		return this;
	}

	/**
	 * Get disbursement
	 *
	 * @return disbursement
	 */
	@Schema(name = "disbursement", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("disbursement")
	public Boolean getDisbursement() {

		return disbursement;
	}

	public void setDisbursement(Boolean disbursement) {

		this.disbursement = disbursement;
	}

	public GetLoansType id(Integer id) {

		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public GetLoansType recoveryRepayment(Boolean recoveryRepayment) {

		this.recoveryRepayment = recoveryRepayment;
		return this;
	}

	/**
	 * Get recoveryRepayment
	 *
	 * @return recoveryRepayment
	 */
	@Schema(name = "recoveryRepayment", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("recoveryRepayment")
	public Boolean getRecoveryRepayment() {

		return recoveryRepayment;
	}

	public void setRecoveryRepayment(Boolean recoveryRepayment) {

		this.recoveryRepayment = recoveryRepayment;
	}

	public GetLoansType repayment(Boolean repayment) {

		this.repayment = repayment;
		return this;
	}

	/**
	 * Get repayment
	 *
	 * @return repayment
	 */
	@Schema(name = "repayment", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("repayment")
	public Boolean getRepayment() {

		return repayment;
	}

	public void setRepayment(Boolean repayment) {

		this.repayment = repayment;
	}

	public GetLoansType repaymentAtDisbursement(Boolean repaymentAtDisbursement) {

		this.repaymentAtDisbursement = repaymentAtDisbursement;
		return this;
	}

	/**
	 * Get repaymentAtDisbursement
	 *
	 * @return repaymentAtDisbursement
	 */
	@Schema(name = "repaymentAtDisbursement", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("repaymentAtDisbursement")
	public Boolean getRepaymentAtDisbursement() {

		return repaymentAtDisbursement;
	}

	public void setRepaymentAtDisbursement(Boolean repaymentAtDisbursement) {

		this.repaymentAtDisbursement = repaymentAtDisbursement;
	}

	public GetLoansType waiveCharges(Boolean waiveCharges) {

		this.waiveCharges = waiveCharges;
		return this;
	}

	/**
	 * Get waiveCharges
	 *
	 * @return waiveCharges
	 */
	@Schema(name = "waiveCharges", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("waiveCharges")
	public Boolean getWaiveCharges() {

		return waiveCharges;
	}

	public void setWaiveCharges(Boolean waiveCharges) {

		this.waiveCharges = waiveCharges;
	}

	public GetLoansType waiveInterest(Boolean waiveInterest) {

		this.waiveInterest = waiveInterest;
		return this;
	}

	/**
	 * Get waiveInterest
	 *
	 * @return waiveInterest
	 */
	@Schema(name = "waiveInterest", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("waiveInterest")
	public Boolean getWaiveInterest() {

		return waiveInterest;
	}

	public void setWaiveInterest(Boolean waiveInterest) {

		this.waiveInterest = waiveInterest;
	}

	public GetLoansType writeOff(Boolean writeOff) {

		this.writeOff = writeOff;
		return this;
	}

	/**
	 * Get writeOff
	 *
	 * @return writeOff
	 */
	@Schema(name = "writeOff", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("writeOff")
	public Boolean getWriteOff() {

		return writeOff;
	}

	public void setWriteOff(Boolean writeOff) {

		this.writeOff = writeOff;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansType getLoansType = (GetLoansType) o;
		return Objects.equals(this.code, getLoansType.code) && Objects.equals(this.contra, getLoansType.contra)
				&& Objects.equals(this.description, getLoansType.description)
				&& Objects.equals(this.disbursement, getLoansType.disbursement)
				&& Objects.equals(this.id, getLoansType.id)
				&& Objects.equals(this.recoveryRepayment, getLoansType.recoveryRepayment)
				&& Objects.equals(this.repayment, getLoansType.repayment)
				&& Objects.equals(this.repaymentAtDisbursement, getLoansType.repaymentAtDisbursement)
				&& Objects.equals(this.waiveCharges, getLoansType.waiveCharges)
				&& Objects.equals(this.waiveInterest, getLoansType.waiveInterest)
				&& Objects.equals(this.writeOff, getLoansType.writeOff);
	}

	@Override
	public int hashCode() {

		return Objects.hash(code, contra, description, disbursement, id, recoveryRepayment, repayment,
				repaymentAtDisbursement, waiveCharges, waiveInterest, writeOff);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansType {\n");
		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    contra: ").append(toIndentedString(contra)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    disbursement: ").append(toIndentedString(disbursement)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    recoveryRepayment: ").append(toIndentedString(recoveryRepayment)).append("\n");
		sb.append("    repayment: ").append(toIndentedString(repayment)).append("\n");
		sb.append("    repaymentAtDisbursement: ").append(toIndentedString(repaymentAtDisbursement)).append("\n");
		sb.append("    waiveCharges: ").append(toIndentedString(waiveCharges)).append("\n");
		sb.append("    waiveInterest: ").append(toIndentedString(waiveInterest)).append("\n");
		sb.append("    writeOff: ").append(toIndentedString(writeOff)).append("\n");
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
