/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoanProductsAssetAccountOptions */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanProductsAssetAccountOptions {

	private Boolean disabled;

	private Integer glCode;

	private Integer id;

	private Boolean manualEntriesAllowed;

	private String name;

	private String nameDecorated;

	private Integer organizationRunningBalance;

	private GetLoanProductsLiabilityTagId tagId;

	private GetLoanProductsLiabilityType type;

	private GetLoanProductsLiabilityUsage usage;

	public GetLoanProductsAssetAccountOptions disabled(Boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	/**
	 * Get disabled
	 *
	 * @return disabled
	 */
	@Schema(name = "disabled", example = "false", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("disabled")
	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public GetLoanProductsAssetAccountOptions glCode(Integer glCode) {
		this.glCode = glCode;
		return this;
	}

	/**
	 * Get glCode
	 *
	 * @return glCode
	 */
	@Schema(name = "glCode", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("glCode")
	public Integer getGlCode() {
		return glCode;
	}

	public void setGlCode(Integer glCode) {
		this.glCode = glCode;
	}

	public GetLoanProductsAssetAccountOptions id(Integer id) {
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

	public GetLoanProductsAssetAccountOptions manualEntriesAllowed(Boolean manualEntriesAllowed) {
		this.manualEntriesAllowed = manualEntriesAllowed;
		return this;
	}

	/**
	 * Get manualEntriesAllowed
	 *
	 * @return manualEntriesAllowed
	 */
	@Schema(name = "manualEntriesAllowed", example = "true", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("manualEntriesAllowed")
	public Boolean getManualEntriesAllowed() {
		return manualEntriesAllowed;
	}

	public void setManualEntriesAllowed(Boolean manualEntriesAllowed) {
		this.manualEntriesAllowed = manualEntriesAllowed;
	}

	public GetLoanProductsAssetAccountOptions name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get name
	 *
	 * @return name
	 */
	@Schema(name = "name", example = "Loan portfolio", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GetLoanProductsAssetAccountOptions nameDecorated(String nameDecorated) {
		this.nameDecorated = nameDecorated;
		return this;
	}

	/**
	 * Get nameDecorated
	 *
	 * @return nameDecorated
	 */
	@Schema(name = "nameDecorated", example = "Loan portfolio", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("nameDecorated")
	public String getNameDecorated() {
		return nameDecorated;
	}

	public void setNameDecorated(String nameDecorated) {
		this.nameDecorated = nameDecorated;
	}

	public GetLoanProductsAssetAccountOptions organizationRunningBalance(Integer organizationRunningBalance) {
		this.organizationRunningBalance = organizationRunningBalance;
		return this;
	}

	/**
	 * Get organizationRunningBalance
	 *
	 * @return organizationRunningBalance
	 */
	@Schema(name = "organizationRunningBalance", example = "60000", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("organizationRunningBalance")
	public Integer getOrganizationRunningBalance() {
		return organizationRunningBalance;
	}

	public void setOrganizationRunningBalance(Integer organizationRunningBalance) {
		this.organizationRunningBalance = organizationRunningBalance;
	}

	public GetLoanProductsAssetAccountOptions tagId(GetLoanProductsLiabilityTagId tagId) {
		this.tagId = tagId;
		return this;
	}

	/**
	 * Get tagId
	 *
	 * @return tagId
	 */
	@Valid
	@Schema(name = "tagId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("tagId")
	public GetLoanProductsLiabilityTagId getTagId() {
		return tagId;
	}

	public void setTagId(GetLoanProductsLiabilityTagId tagId) {
		this.tagId = tagId;
	}

	public GetLoanProductsAssetAccountOptions type(GetLoanProductsLiabilityType type) {
		this.type = type;
		return this;
	}

	/**
	 * Get type
	 *
	 * @return type
	 */
	@Valid
	@Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("type")
	public GetLoanProductsLiabilityType getType() {
		return type;
	}

	public void setType(GetLoanProductsLiabilityType type) {
		this.type = type;
	}

	public GetLoanProductsAssetAccountOptions usage(GetLoanProductsLiabilityUsage usage) {
		this.usage = usage;
		return this;
	}

	/**
	 * Get usage
	 *
	 * @return usage
	 */
	@Valid
	@Schema(name = "usage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("usage")
	public GetLoanProductsLiabilityUsage getUsage() {
		return usage;
	}

	public void setUsage(GetLoanProductsLiabilityUsage usage) {
		this.usage = usage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanProductsAssetAccountOptions getLoanProductsAssetAccountOptions = (GetLoanProductsAssetAccountOptions) o;
		return Objects.equals(this.disabled, getLoanProductsAssetAccountOptions.disabled)
				&& Objects.equals(this.glCode, getLoanProductsAssetAccountOptions.glCode)
				&& Objects.equals(this.id, getLoanProductsAssetAccountOptions.id)
				&& Objects.equals(this.manualEntriesAllowed, getLoanProductsAssetAccountOptions.manualEntriesAllowed)
				&& Objects.equals(this.name, getLoanProductsAssetAccountOptions.name)
				&& Objects.equals(this.nameDecorated, getLoanProductsAssetAccountOptions.nameDecorated)
				&& Objects.equals(this.organizationRunningBalance,
						getLoanProductsAssetAccountOptions.organizationRunningBalance)
				&& Objects.equals(this.tagId, getLoanProductsAssetAccountOptions.tagId)
				&& Objects.equals(this.type, getLoanProductsAssetAccountOptions.type)
				&& Objects.equals(this.usage, getLoanProductsAssetAccountOptions.usage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(disabled, glCode, id, manualEntriesAllowed, name, nameDecorated, organizationRunningBalance,
				tagId, type, usage);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanProductsAssetAccountOptions {\n");
		sb.append("    disabled: ").append(toIndentedString(disabled)).append("\n");
		sb.append("    glCode: ").append(toIndentedString(glCode)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    manualEntriesAllowed: ").append(toIndentedString(manualEntriesAllowed)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    nameDecorated: ").append(toIndentedString(nameDecorated)).append("\n");
		sb.append("    organizationRunningBalance: ").append(toIndentedString(organizationRunningBalance)).append("\n");
		sb.append("    tagId: ").append(toIndentedString(tagId)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
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
