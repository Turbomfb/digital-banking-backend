/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PostLoanProductsResponse */
@Schema(name = "PostLoanProductsResponse", description = "PostLoanProductsResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PostLoanProductsResponse {

	private Integer resourceId;

	public PostLoanProductsResponse resourceId(Integer resourceId) {

		this.resourceId = resourceId;
		return this;
	}

	/**
	 * Get resourceId
	 *
	 * @return resourceId
	 */
	@Schema(name = "resourceId", example = "3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("resourceId")
	public Integer getResourceId() {

		return resourceId;
	}

	public void setResourceId(Integer resourceId) {

		this.resourceId = resourceId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostLoanProductsResponse postLoanProductsResponse = (PostLoanProductsResponse) o;
		return Objects.equals(this.resourceId, postLoanProductsResponse.resourceId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(resourceId);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PostLoanProductsResponse {\n");
		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
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
