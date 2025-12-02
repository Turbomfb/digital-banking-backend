/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetPocketLoanAccounts */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetPocketLoanAccounts {

	private Integer accountId;

	private Integer accountNumber;

	private Integer accountType;

	private Integer id;

	private Integer pocketId;

	public GetPocketLoanAccounts accountId(Integer accountId) {

		this.accountId = accountId;
		return this;
	}

	/**
	 * Get accountId
	 *
	 * @return accountId
	 */
	@Schema(name = "accountId", example = "11", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountId")
	public Integer getAccountId() {

		return accountId;
	}

	public void setAccountId(Integer accountId) {

		this.accountId = accountId;
	}

	public GetPocketLoanAccounts accountNumber(Integer accountNumber) {

		this.accountNumber = accountNumber;
		return this;
	}

	/**
	 * Get accountNumber
	 *
	 * @return accountNumber
	 */
	@Schema(name = "accountNumber", example = "11", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountNumber")
	public Integer getAccountNumber() {

		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {

		this.accountNumber = accountNumber;
	}

	public GetPocketLoanAccounts accountType(Integer accountType) {

		this.accountType = accountType;
		return this;
	}

	/**
	 * Get accountType
	 *
	 * @return accountType
	 */
	@Schema(name = "accountType", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountType")
	public Integer getAccountType() {

		return accountType;
	}

	public void setAccountType(Integer accountType) {

		this.accountType = accountType;
	}

	public GetPocketLoanAccounts id(Integer id) {

		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "10", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public GetPocketLoanAccounts pocketId(Integer pocketId) {

		this.pocketId = pocketId;
		return this;
	}

	/**
	 * Get pocketId
	 *
	 * @return pocketId
	 */
	@Schema(name = "pocketId", example = "6", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("pocketId")
	public Integer getPocketId() {

		return pocketId;
	}

	public void setPocketId(Integer pocketId) {

		this.pocketId = pocketId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetPocketLoanAccounts getPocketLoanAccounts = (GetPocketLoanAccounts) o;
		return Objects.equals(this.accountId, getPocketLoanAccounts.accountId)
				&& Objects.equals(this.accountNumber, getPocketLoanAccounts.accountNumber)
				&& Objects.equals(this.accountType, getPocketLoanAccounts.accountType)
				&& Objects.equals(this.id, getPocketLoanAccounts.id)
				&& Objects.equals(this.pocketId, getPocketLoanAccounts.pocketId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(accountId, accountNumber, accountType, id, pocketId);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetPocketLoanAccounts {\n");
		sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
		sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
		sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    pocketId: ").append(toIndentedString(pocketId)).append("\n");
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
