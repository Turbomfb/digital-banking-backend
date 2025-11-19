/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** PostLoanChanges */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoanChanges {

	@Valid
	private List<Integer> removedEntityIds = new ArrayList<>();

	public PostLoanChanges removedEntityIds(List<Integer> removedEntityIds) {

		this.removedEntityIds = removedEntityIds;
		return this;
	}

	public PostLoanChanges addRemovedEntityIdsItem(Integer removedEntityIdsItem) {

		if (this.removedEntityIds == null) {
			this.removedEntityIds = new ArrayList<>();
		}
		this.removedEntityIds.add(removedEntityIdsItem);
		return this;
	}

	/**
	 * Get removedEntityIds
	 *
	 * @return removedEntityIds
	 */
	@Schema(name = "removedEntityIds", example = "[21,22]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("removedEntityIds")
	public List<Integer> getRemovedEntityIds() {

		return removedEntityIds;
	}

	public void setRemovedEntityIds(List<Integer> removedEntityIds) {

		this.removedEntityIds = removedEntityIds;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostLoanChanges postLoanChanges = (PostLoanChanges) o;
		return Objects.equals(this.removedEntityIds, postLoanChanges.removedEntityIds);
	}

	@Override
	public int hashCode() {

		return Objects.hash(removedEntityIds);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoanChanges {\n");
		sb.append("    removedEntityIds: ").append(toIndentedString(removedEntityIds)).append("\n");
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
