/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** PutLoanChanges */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class PutLoanChanges {

	private String locale;

	private Double principal;

	public PutLoanChanges locale(String locale) {

		this.locale = locale;
		return this;
	}

	/**
	 * Get locale
	 *
	 * @return locale
	 */
	@Schema(name = "locale", example = "en_GB", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("locale")
	public String getLocale() {

		return locale;
	}

	public void setLocale(String locale) {

		this.locale = locale;
	}

	public PutLoanChanges principal(Double principal) {

		this.principal = principal;
		return this;
	}

	/**
	 * Get principal
	 *
	 * @return principal
	 */
	@Schema(name = "principal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principal")
	public Double getPrincipal() {

		return principal;
	}

	public void setPrincipal(Double principal) {

		this.principal = principal;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PutLoanChanges putLoanChanges = (PutLoanChanges) o;
		return Objects.equals(this.locale, putLoanChanges.locale)
				&& Objects.equals(this.principal, putLoanChanges.principal);
	}

	@Override
	public int hashCode() {

		return Objects.hash(locale, principal);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class PutLoanChanges {\n");
		sb.append("    locale: ").append(toIndentedString(locale)).append("\n");
		sb.append("    principal: ").append(toIndentedString(principal)).append("\n");
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
