/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoansTemplateResponse */
@Schema(name = "GetLoansTemplateResponse", description = "GetLoansTemplateResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoansTemplateResponse {

	private Long clientId;

	private String clientName;

	private Integer clientOfficeId;

	@Valid
	private Set<@Valid GetLoansTemplateProductOptions> productOptions = new LinkedHashSet<>();

	private GetLoansTemplateTimeline timeline;

	public GetLoansTemplateResponse clientId(Long clientId) {
		this.clientId = clientId;
		return this;
	}

	/**
	 * Get clientId
	 *
	 * @return clientId
	 */
	@Schema(name = "clientId", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("clientId")
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public GetLoansTemplateResponse clientName(String clientName) {
		this.clientName = clientName;
		return this;
	}

	/**
	 * Get clientName
	 *
	 * @return clientName
	 */
	@Schema(name = "clientName", example = "Kampala first Client", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("clientName")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public GetLoansTemplateResponse clientOfficeId(Integer clientOfficeId) {
		this.clientOfficeId = clientOfficeId;
		return this;
	}

	/**
	 * Get clientOfficeId
	 *
	 * @return clientOfficeId
	 */
	@Schema(name = "clientOfficeId", example = "2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("clientOfficeId")
	public Integer getClientOfficeId() {
		return clientOfficeId;
	}

	public void setClientOfficeId(Integer clientOfficeId) {
		this.clientOfficeId = clientOfficeId;
	}

	public GetLoansTemplateResponse productOptions(Set<@Valid GetLoansTemplateProductOptions> productOptions) {
		this.productOptions = productOptions;
		return this;
	}

	public GetLoansTemplateResponse addProductOptionsItem(GetLoansTemplateProductOptions productOptionsItem) {
		if (this.productOptions == null) {
			this.productOptions = new LinkedHashSet<>();
		}
		this.productOptions.add(productOptionsItem);
		return this;
	}

	/**
	 * Get productOptions
	 *
	 * @return productOptions
	 */
	@Valid
	@Schema(name = "productOptions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("productOptions")
	public Set<@Valid GetLoansTemplateProductOptions> getProductOptions() {
		return productOptions;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setProductOptions(Set<@Valid GetLoansTemplateProductOptions> productOptions) {
		this.productOptions = productOptions;
	}

	public GetLoansTemplateResponse timeline(GetLoansTemplateTimeline timeline) {
		this.timeline = timeline;
		return this;
	}

	/**
	 * Get timeline
	 *
	 * @return timeline
	 */
	@Valid
	@Schema(name = "timeline", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("timeline")
	public GetLoansTemplateTimeline getTimeline() {
		return timeline;
	}

	public void setTimeline(GetLoansTemplateTimeline timeline) {
		this.timeline = timeline;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoansTemplateResponse getLoansTemplateResponse = (GetLoansTemplateResponse) o;
		return Objects.equals(this.clientId, getLoansTemplateResponse.clientId)
				&& Objects.equals(this.clientName, getLoansTemplateResponse.clientName)
				&& Objects.equals(this.clientOfficeId, getLoansTemplateResponse.clientOfficeId)
				&& Objects.equals(this.productOptions, getLoansTemplateResponse.productOptions)
				&& Objects.equals(this.timeline, getLoansTemplateResponse.timeline);
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientId, clientName, clientOfficeId, productOptions, timeline);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoansTemplateResponse {\n");
		sb.append("    clientId: ").append(toIndentedString(clientId)).append("\n");
		sb.append("    clientName: ").append(toIndentedString(clientName)).append("\n");
		sb.append("    clientOfficeId: ").append(toIndentedString(clientOfficeId)).append("\n");
		sb.append("    productOptions: ").append(toIndentedString(productOptions)).append("\n");
		sb.append("    timeline: ").append(toIndentedString(timeline)).append("\n");
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
