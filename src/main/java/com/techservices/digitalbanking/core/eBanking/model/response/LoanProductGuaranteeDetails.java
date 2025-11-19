/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** LoanProductGuaranteeDetails */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class LoanProductGuaranteeDetails {

	private Long id;

	private BigDecimal mandatoryGuarantee;

	private BigDecimal minimumGuaranteeFromGuarantor;

	private BigDecimal minimumGuaranteeFromOwnFunds;

	private Boolean _new;

	public LoanProductGuaranteeDetails id(Long id) {

		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public LoanProductGuaranteeDetails mandatoryGuarantee(BigDecimal mandatoryGuarantee) {

		this.mandatoryGuarantee = mandatoryGuarantee;
		return this;
	}

	/**
	 * Get mandatoryGuarantee
	 *
	 * @return mandatoryGuarantee
	 */
	@Valid
	@Schema(name = "mandatoryGuarantee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("mandatoryGuarantee")
	public BigDecimal getMandatoryGuarantee() {

		return mandatoryGuarantee;
	}

	public void setMandatoryGuarantee(BigDecimal mandatoryGuarantee) {

		this.mandatoryGuarantee = mandatoryGuarantee;
	}

	public LoanProductGuaranteeDetails minimumGuaranteeFromGuarantor(BigDecimal minimumGuaranteeFromGuarantor) {

		this.minimumGuaranteeFromGuarantor = minimumGuaranteeFromGuarantor;
		return this;
	}

	/**
	 * Get minimumGuaranteeFromGuarantor
	 *
	 * @return minimumGuaranteeFromGuarantor
	 */
	@Valid
	@Schema(name = "minimumGuaranteeFromGuarantor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("minimumGuaranteeFromGuarantor")
	public BigDecimal getMinimumGuaranteeFromGuarantor() {

		return minimumGuaranteeFromGuarantor;
	}

	public void setMinimumGuaranteeFromGuarantor(BigDecimal minimumGuaranteeFromGuarantor) {

		this.minimumGuaranteeFromGuarantor = minimumGuaranteeFromGuarantor;
	}

	public LoanProductGuaranteeDetails minimumGuaranteeFromOwnFunds(BigDecimal minimumGuaranteeFromOwnFunds) {

		this.minimumGuaranteeFromOwnFunds = minimumGuaranteeFromOwnFunds;
		return this;
	}

	/**
	 * Get minimumGuaranteeFromOwnFunds
	 *
	 * @return minimumGuaranteeFromOwnFunds
	 */
	@Valid
	@Schema(name = "minimumGuaranteeFromOwnFunds", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("minimumGuaranteeFromOwnFunds")
	public BigDecimal getMinimumGuaranteeFromOwnFunds() {

		return minimumGuaranteeFromOwnFunds;
	}

	public void setMinimumGuaranteeFromOwnFunds(BigDecimal minimumGuaranteeFromOwnFunds) {

		this.minimumGuaranteeFromOwnFunds = minimumGuaranteeFromOwnFunds;
	}

	public LoanProductGuaranteeDetails _new(Boolean _new) {

		this._new = _new;
		return this;
	}

	/**
	 * Get _new
	 *
	 * @return _new
	 */
	@Schema(name = "new", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("new")
	public Boolean getNew() {

		return _new;
	}

	public void setNew(Boolean _new) {

		this._new = _new;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoanProductGuaranteeDetails loanProductGuaranteeDetails = (LoanProductGuaranteeDetails) o;
		return Objects.equals(this.id, loanProductGuaranteeDetails.id)
				&& Objects.equals(this.mandatoryGuarantee, loanProductGuaranteeDetails.mandatoryGuarantee)
				&& Objects.equals(this.minimumGuaranteeFromGuarantor,
						loanProductGuaranteeDetails.minimumGuaranteeFromGuarantor)
				&& Objects.equals(this.minimumGuaranteeFromOwnFunds,
						loanProductGuaranteeDetails.minimumGuaranteeFromOwnFunds)
				&& Objects.equals(this._new, loanProductGuaranteeDetails._new);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, mandatoryGuarantee, minimumGuaranteeFromGuarantor, minimumGuaranteeFromOwnFunds, _new);
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("class LoanProductGuaranteeDetails {\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    mandatoryGuarantee: ").append(toIndentedString(mandatoryGuarantee)).append("\n");
		sb.append("    minimumGuaranteeFromGuarantor: ").append(toIndentedString(minimumGuaranteeFromGuarantor))
				.append("\n");
		sb.append("    minimumGuaranteeFromOwnFunds: ").append(toIndentedString(minimumGuaranteeFromOwnFunds))
				.append("\n");
		sb.append("    _new: ").append(toIndentedString(_new)).append("\n");
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
