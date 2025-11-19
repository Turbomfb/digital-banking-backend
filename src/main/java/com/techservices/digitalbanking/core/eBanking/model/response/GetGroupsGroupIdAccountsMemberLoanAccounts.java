/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetGroupsGroupIdAccountsMemberLoanAccounts */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetGroupsGroupIdAccountsMemberLoanAccounts {

	private Long accountNo;

	private Integer id;

	private GetGroupsGroupIdAccountsMemberLoanType loanType;

	private Integer productId;

	private String productName;

	private GetGroupsGroupIdAccountsMemberLoanStatus status;

	public GetGroupsGroupIdAccountsMemberLoanAccounts accountNo(Long accountNo) {

		this.accountNo = accountNo;
		return this;
	}

	/**
	 * Get accountNo
	 *
	 * @return accountNo
	 */
	@Schema(name = "accountNo", example = "4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("accountNo")
	public Long getAccountNo() {

		return accountNo;
	}

	public void setAccountNo(Long accountNo) {

		this.accountNo = accountNo;
	}

	public GetGroupsGroupIdAccountsMemberLoanAccounts id(Integer id) {

		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", example = "4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public GetGroupsGroupIdAccountsMemberLoanAccounts loanType(GetGroupsGroupIdAccountsMemberLoanType loanType) {

		this.loanType = loanType;
		return this;
	}

	/**
	 * Get loanType
	 *
	 * @return loanType
	 */
	@Valid
	@Schema(name = "loanType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("loanType")
	public GetGroupsGroupIdAccountsMemberLoanType getLoanType() {

		return loanType;
	}

	public void setLoanType(GetGroupsGroupIdAccountsMemberLoanType loanType) {

		this.loanType = loanType;
	}

	public GetGroupsGroupIdAccountsMemberLoanAccounts productId(Integer productId) {

		this.productId = productId;
		return this;
	}

	/**
	 * Get productId
	 *
	 * @return productId
	 */
	@Schema(name = "productId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("productId")
	public Integer getProductId() {

		return productId;
	}

	public void setProductId(Integer productId) {

		this.productId = productId;
	}

	public GetGroupsGroupIdAccountsMemberLoanAccounts productName(String productName) {

		this.productName = productName;
		return this;
	}

	/**
	 * Get productName
	 *
	 * @return productName
	 */
	@Schema(name = "productName", example = "testLoan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("productName")
	public String getProductName() {

		return productName;
	}

	public void setProductName(String productName) {

		this.productName = productName;
	}

	public GetGroupsGroupIdAccountsMemberLoanAccounts status(GetGroupsGroupIdAccountsMemberLoanStatus status) {

		this.status = status;
		return this;
	}

	/**
	 * Get status
	 *
	 * @return status
	 */
	@Valid
	@Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("status")
	public GetGroupsGroupIdAccountsMemberLoanStatus getStatus() {

		return status;
	}

	public void setStatus(GetGroupsGroupIdAccountsMemberLoanStatus status) {

		this.status = status;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetGroupsGroupIdAccountsMemberLoanAccounts getGroupsGroupIdAccountsMemberLoanAccounts = (GetGroupsGroupIdAccountsMemberLoanAccounts) o;
		return Objects.equals(this.accountNo, getGroupsGroupIdAccountsMemberLoanAccounts.accountNo)
				&& Objects.equals(this.id, getGroupsGroupIdAccountsMemberLoanAccounts.id)
				&& Objects.equals(this.loanType, getGroupsGroupIdAccountsMemberLoanAccounts.loanType)
				&& Objects.equals(this.productId, getGroupsGroupIdAccountsMemberLoanAccounts.productId)
				&& Objects.equals(this.productName, getGroupsGroupIdAccountsMemberLoanAccounts.productName)
				&& Objects.equals(this.status, getGroupsGroupIdAccountsMemberLoanAccounts.status);
	}

	@Override
	public int hashCode() {

		return Objects.hash(accountNo, id, loanType, productId, productName, status);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class GetGroupsGroupIdAccountsMemberLoanAccounts {\n");
		sb.append("    accountNo: ").append(toIndentedString(accountNo)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    loanType: ").append(toIndentedString(loanType)).append("\n");
		sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
		sb.append("    productName: ").append(toIndentedString(productName)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
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
