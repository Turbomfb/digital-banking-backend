/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** GetLoansLoanIdLinkedAccount */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansLoanIdLinkedAccount {

	private Long accountNo;

	private Integer id;

	public GetLoansLoanIdLinkedAccount accountNo(Long accountNo) {

		this.accountNo = accountNo;
		return this;
	}

	/**
	 * Get accountNo
	 *
	 * @return accountNo
	 */
	@Schema(name = "accountNo", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountNo")
	public Long getAccountNo() {

		return accountNo;
	}

	public void setAccountNo(Long accountNo) {

		this.accountNo = accountNo;
	}

	public GetLoansLoanIdLinkedAccount id(Integer id) {

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

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansLoanIdLinkedAccount getLoansLoanIdLinkedAccount = (GetLoansLoanIdLinkedAccount) o;
		return Objects.equals(this.accountNo, getLoansLoanIdLinkedAccount.accountNo)
				&& Objects.equals(this.id, getLoansLoanIdLinkedAccount.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(accountNo, id);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansLoanIdLinkedAccount {\n");
		sb.append("    accountNo: ").append(toIndentedString(accountNo)).append("\n");
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
