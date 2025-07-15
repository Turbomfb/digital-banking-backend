/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** LoanTransactionProcessingStrategy */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class LoanTransactionProcessingStrategy {

	private Boolean creocoreStrategy;

	private Boolean earlyPaymentStrategy;

	private Boolean heavensfamilyStrategy;

	private Long id;

	private Boolean indianRBIStrategy;

	private Boolean interestPrincipalPenaltiesFeesOrderStrategy;

	private Boolean _new;

	private Boolean principalInterestPenaltiesFeesOrderStrategy;

	private Boolean standardStrategy;

	public LoanTransactionProcessingStrategy creocoreStrategy(Boolean creocoreStrategy) {
		this.creocoreStrategy = creocoreStrategy;
		return this;
	}

	/**
	 * Get creocoreStrategy
	 *
	 * @return creocoreStrategy
	 */
	@Schema(name = "creocoreStrategy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("creocoreStrategy")
	public Boolean getCreocoreStrategy() {
		return creocoreStrategy;
	}

	public void setCreocoreStrategy(Boolean creocoreStrategy) {
		this.creocoreStrategy = creocoreStrategy;
	}

	public LoanTransactionProcessingStrategy earlyPaymentStrategy(Boolean earlyPaymentStrategy) {
		this.earlyPaymentStrategy = earlyPaymentStrategy;
		return this;
	}

	/**
	 * Get earlyPaymentStrategy
	 *
	 * @return earlyPaymentStrategy
	 */
	@Schema(name = "earlyPaymentStrategy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("earlyPaymentStrategy")
	public Boolean getEarlyPaymentStrategy() {
		return earlyPaymentStrategy;
	}

	public void setEarlyPaymentStrategy(Boolean earlyPaymentStrategy) {
		this.earlyPaymentStrategy = earlyPaymentStrategy;
	}

	public LoanTransactionProcessingStrategy heavensfamilyStrategy(Boolean heavensfamilyStrategy) {
		this.heavensfamilyStrategy = heavensfamilyStrategy;
		return this;
	}

	/**
	 * Get heavensfamilyStrategy
	 *
	 * @return heavensfamilyStrategy
	 */
	@Schema(name = "heavensfamilyStrategy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("heavensfamilyStrategy")
	public Boolean getHeavensfamilyStrategy() {
		return heavensfamilyStrategy;
	}

	public void setHeavensfamilyStrategy(Boolean heavensfamilyStrategy) {
		this.heavensfamilyStrategy = heavensfamilyStrategy;
	}

	public LoanTransactionProcessingStrategy id(Long id) {
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

	public LoanTransactionProcessingStrategy indianRBIStrategy(Boolean indianRBIStrategy) {
		this.indianRBIStrategy = indianRBIStrategy;
		return this;
	}

	/**
	 * Get indianRBIStrategy
	 *
	 * @return indianRBIStrategy
	 */
	@Schema(name = "indianRBIStrategy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("indianRBIStrategy")
	public Boolean getIndianRBIStrategy() {
		return indianRBIStrategy;
	}

	public void setIndianRBIStrategy(Boolean indianRBIStrategy) {
		this.indianRBIStrategy = indianRBIStrategy;
	}

	public LoanTransactionProcessingStrategy interestPrincipalPenaltiesFeesOrderStrategy(
			Boolean interestPrincipalPenaltiesFeesOrderStrategy) {
		this.interestPrincipalPenaltiesFeesOrderStrategy = interestPrincipalPenaltiesFeesOrderStrategy;
		return this;
	}

	/**
	 * Get interestPrincipalPenaltiesFeesOrderStrategy
	 *
	 * @return interestPrincipalPenaltiesFeesOrderStrategy
	 */
	@Schema(name = "interestPrincipalPenaltiesFeesOrderStrategy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestPrincipalPenaltiesFeesOrderStrategy")
	public Boolean getInterestPrincipalPenaltiesFeesOrderStrategy() {
		return interestPrincipalPenaltiesFeesOrderStrategy;
	}

	public void setInterestPrincipalPenaltiesFeesOrderStrategy(Boolean interestPrincipalPenaltiesFeesOrderStrategy) {
		this.interestPrincipalPenaltiesFeesOrderStrategy = interestPrincipalPenaltiesFeesOrderStrategy;
	}

	public LoanTransactionProcessingStrategy _new(Boolean _new) {
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

	public LoanTransactionProcessingStrategy principalInterestPenaltiesFeesOrderStrategy(
			Boolean principalInterestPenaltiesFeesOrderStrategy) {
		this.principalInterestPenaltiesFeesOrderStrategy = principalInterestPenaltiesFeesOrderStrategy;
		return this;
	}

	/**
	 * Get principalInterestPenaltiesFeesOrderStrategy
	 *
	 * @return principalInterestPenaltiesFeesOrderStrategy
	 */
	@Schema(name = "principalInterestPenaltiesFeesOrderStrategy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("principalInterestPenaltiesFeesOrderStrategy")
	public Boolean getPrincipalInterestPenaltiesFeesOrderStrategy() {
		return principalInterestPenaltiesFeesOrderStrategy;
	}

	public void setPrincipalInterestPenaltiesFeesOrderStrategy(Boolean principalInterestPenaltiesFeesOrderStrategy) {
		this.principalInterestPenaltiesFeesOrderStrategy = principalInterestPenaltiesFeesOrderStrategy;
	}

	public LoanTransactionProcessingStrategy standardStrategy(Boolean standardStrategy) {
		this.standardStrategy = standardStrategy;
		return this;
	}

	/**
	 * Get standardStrategy
	 *
	 * @return standardStrategy
	 */
	@Schema(name = "standardStrategy", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("standardStrategy")
	public Boolean getStandardStrategy() {
		return standardStrategy;
	}

	public void setStandardStrategy(Boolean standardStrategy) {
		this.standardStrategy = standardStrategy;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoanTransactionProcessingStrategy loanTransactionProcessingStrategy = (LoanTransactionProcessingStrategy) o;
		return Objects.equals(this.creocoreStrategy, loanTransactionProcessingStrategy.creocoreStrategy)
				&& Objects.equals(this.earlyPaymentStrategy, loanTransactionProcessingStrategy.earlyPaymentStrategy)
				&& Objects.equals(this.heavensfamilyStrategy, loanTransactionProcessingStrategy.heavensfamilyStrategy)
				&& Objects.equals(this.id, loanTransactionProcessingStrategy.id)
				&& Objects.equals(this.indianRBIStrategy, loanTransactionProcessingStrategy.indianRBIStrategy)
				&& Objects.equals(this.interestPrincipalPenaltiesFeesOrderStrategy,
						loanTransactionProcessingStrategy.interestPrincipalPenaltiesFeesOrderStrategy)
				&& Objects.equals(this._new, loanTransactionProcessingStrategy._new)
				&& Objects.equals(this.principalInterestPenaltiesFeesOrderStrategy,
						loanTransactionProcessingStrategy.principalInterestPenaltiesFeesOrderStrategy)
				&& Objects.equals(this.standardStrategy, loanTransactionProcessingStrategy.standardStrategy);
	}

	@Override
	public int hashCode() {
		return Objects.hash(creocoreStrategy, earlyPaymentStrategy, heavensfamilyStrategy, id, indianRBIStrategy,
				interestPrincipalPenaltiesFeesOrderStrategy, _new, principalInterestPenaltiesFeesOrderStrategy,
				standardStrategy);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LoanTransactionProcessingStrategy {\n");
		sb.append("    creocoreStrategy: ").append(toIndentedString(creocoreStrategy)).append("\n");
		sb.append("    earlyPaymentStrategy: ").append(toIndentedString(earlyPaymentStrategy)).append("\n");
		sb.append("    heavensfamilyStrategy: ").append(toIndentedString(heavensfamilyStrategy)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    indianRBIStrategy: ").append(toIndentedString(indianRBIStrategy)).append("\n");
		sb.append("    interestPrincipalPenaltiesFeesOrderStrategy: ")
				.append(toIndentedString(interestPrincipalPenaltiesFeesOrderStrategy)).append("\n");
		sb.append("    _new: ").append(toIndentedString(_new)).append("\n");
		sb.append("    principalInterestPenaltiesFeesOrderStrategy: ")
				.append(toIndentedString(principalInterestPenaltiesFeesOrderStrategy)).append("\n");
		sb.append("    standardStrategy: ").append(toIndentedString(standardStrategy)).append("\n");
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
