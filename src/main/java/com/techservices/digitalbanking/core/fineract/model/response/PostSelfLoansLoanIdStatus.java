/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PostSelfLoansLoanIdStatus */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostSelfLoansLoanIdStatus {

	private Boolean active;

	private Boolean closed;

	private Boolean closedObligationsMet;

	private Boolean closedRescheduled;

	private Boolean closedWrittenOff;

	private String code;

	private String description;

	private Integer id;

	private Boolean overpaid;

	private Boolean pendingApproval;

	private Boolean waitingForDisbursal;

	public PostSelfLoansLoanIdStatus active(Boolean active) {
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

	public PostSelfLoansLoanIdStatus closed(Boolean closed) {
		this.closed = closed;
		return this;
	}

	/**
	 * Get closed
	 *
	 * @return closed
	 */
	@Schema(name = "closed", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("closed")
	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public PostSelfLoansLoanIdStatus closedObligationsMet(Boolean closedObligationsMet) {
		this.closedObligationsMet = closedObligationsMet;
		return this;
	}

	/**
	 * Get closedObligationsMet
	 *
	 * @return closedObligationsMet
	 */
	@Schema(name = "closedObligationsMet", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("closedObligationsMet")
	public Boolean getClosedObligationsMet() {
		return closedObligationsMet;
	}

	public void setClosedObligationsMet(Boolean closedObligationsMet) {
		this.closedObligationsMet = closedObligationsMet;
	}

	public PostSelfLoansLoanIdStatus closedRescheduled(Boolean closedRescheduled) {
		this.closedRescheduled = closedRescheduled;
		return this;
	}

	/**
	 * Get closedRescheduled
	 *
	 * @return closedRescheduled
	 */
	@Schema(name = "closedRescheduled", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("closedRescheduled")
	public Boolean getClosedRescheduled() {
		return closedRescheduled;
	}

	public void setClosedRescheduled(Boolean closedRescheduled) {
		this.closedRescheduled = closedRescheduled;
	}

	public PostSelfLoansLoanIdStatus closedWrittenOff(Boolean closedWrittenOff) {
		this.closedWrittenOff = closedWrittenOff;
		return this;
	}

	/**
	 * Get closedWrittenOff
	 *
	 * @return closedWrittenOff
	 */
	@Schema(name = "closedWrittenOff", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("closedWrittenOff")
	public Boolean getClosedWrittenOff() {
		return closedWrittenOff;
	}

	public void setClosedWrittenOff(Boolean closedWrittenOff) {
		this.closedWrittenOff = closedWrittenOff;
	}

	public PostSelfLoansLoanIdStatus code(String code) {
		this.code = code;
		return this;
	}

	/**
	 * Get code
	 *
	 * @return code
	 */
	@Schema(name = "code", example = "loanStatusType.withdrawn.by.client", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public PostSelfLoansLoanIdStatus description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 *
	 * @return description
	 */
	@Schema(name = "description", example = "Withdrawn by applicant", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PostSelfLoansLoanIdStatus id(Integer id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "400", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PostSelfLoansLoanIdStatus overpaid(Boolean overpaid) {
		this.overpaid = overpaid;
		return this;
	}

	/**
	 * Get overpaid
	 *
	 * @return overpaid
	 */
	@Schema(name = "overpaid", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("overpaid")
	public Boolean getOverpaid() {
		return overpaid;
	}

	public void setOverpaid(Boolean overpaid) {
		this.overpaid = overpaid;
	}

	public PostSelfLoansLoanIdStatus pendingApproval(Boolean pendingApproval) {
		this.pendingApproval = pendingApproval;
		return this;
	}

	/**
	 * Get pendingApproval
	 *
	 * @return pendingApproval
	 */
	@Schema(name = "pendingApproval", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("pendingApproval")
	public Boolean getPendingApproval() {
		return pendingApproval;
	}

	public void setPendingApproval(Boolean pendingApproval) {
		this.pendingApproval = pendingApproval;
	}

	public PostSelfLoansLoanIdStatus waitingForDisbursal(Boolean waitingForDisbursal) {
		this.waitingForDisbursal = waitingForDisbursal;
		return this;
	}

	/**
	 * Get waitingForDisbursal
	 *
	 * @return waitingForDisbursal
	 */
	@Schema(name = "waitingForDisbursal", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("waitingForDisbursal")
	public Boolean getWaitingForDisbursal() {
		return waitingForDisbursal;
	}

	public void setWaitingForDisbursal(Boolean waitingForDisbursal) {
		this.waitingForDisbursal = waitingForDisbursal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostSelfLoansLoanIdStatus postSelfLoansLoanIdStatus = (PostSelfLoansLoanIdStatus) o;
		return Objects.equals(this.active, postSelfLoansLoanIdStatus.active)
				&& Objects.equals(this.closed, postSelfLoansLoanIdStatus.closed)
				&& Objects.equals(this.closedObligationsMet, postSelfLoansLoanIdStatus.closedObligationsMet)
				&& Objects.equals(this.closedRescheduled, postSelfLoansLoanIdStatus.closedRescheduled)
				&& Objects.equals(this.closedWrittenOff, postSelfLoansLoanIdStatus.closedWrittenOff)
				&& Objects.equals(this.code, postSelfLoansLoanIdStatus.code)
				&& Objects.equals(this.description, postSelfLoansLoanIdStatus.description)
				&& Objects.equals(this.id, postSelfLoansLoanIdStatus.id)
				&& Objects.equals(this.overpaid, postSelfLoansLoanIdStatus.overpaid)
				&& Objects.equals(this.pendingApproval, postSelfLoansLoanIdStatus.pendingApproval)
				&& Objects.equals(this.waitingForDisbursal, postSelfLoansLoanIdStatus.waitingForDisbursal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, closed, closedObligationsMet, closedRescheduled, closedWrittenOff, code,
				description, id, overpaid, pendingApproval, waitingForDisbursal);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PostSelfLoansLoanIdStatus {\n");
		sb.append("    active: ").append(toIndentedString(active)).append("\n");
		sb.append("    closed: ").append(toIndentedString(closed)).append("\n");
		sb.append("    closedObligationsMet: ").append(toIndentedString(closedObligationsMet)).append("\n");
		sb.append("    closedRescheduled: ").append(toIndentedString(closedRescheduled)).append("\n");
		sb.append("    closedWrittenOff: ").append(toIndentedString(closedWrittenOff)).append("\n");
		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    overpaid: ").append(toIndentedString(overpaid)).append("\n");
		sb.append("    pendingApproval: ").append(toIndentedString(pendingApproval)).append("\n");
		sb.append("    waitingForDisbursal: ").append(toIndentedString(waitingForDisbursal)).append("\n");
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
