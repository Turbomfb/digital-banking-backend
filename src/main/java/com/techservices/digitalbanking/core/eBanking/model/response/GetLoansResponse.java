/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/** GetLoansResponse */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLoansResponse {

	@Valid
	private Set<@Valid GetLoansLoanIdResponse> pageItems = new LinkedHashSet<>();

	private Integer totalFilteredRecords;

	public BigDecimal getActiveLoanBalance() {

		return this.getPageItems().stream().filter(loan -> loan.getStatus().isActive())
				.map(loan -> Optional.ofNullable(loan.getSummary().getTotalOutstanding()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getTotalExpectedRepayment() {

		return this.getPageItems().stream().filter(loan -> loan.getStatus().isActive())
				.map(loan -> Optional.ofNullable(loan.getSummary().getTotalExpectedRepayment()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getTotalRepayment() {

		return this.getPageItems().stream().filter(loan -> loan.getStatus().isActive())
				.map(loan -> Optional.ofNullable(loan.getSummary().getTotalRepayment()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Long getActiveLoanCount() {

		return this.getPageItems().stream().filter(loan -> loan.getStatus().isActive()).count();
	}

	public Long getPendingLoanCount() {

		return this.getPageItems().stream()
				.filter(loan -> loan.getStatus().isWaitingForDisbursal() || loan.getStatus().isPendingApproval())
				.count();
	}

	public Long getLiquidatedLoanCount() {

		return this.getPageItems().stream().filter(loan -> loan.getStatus().isClosed()).count();
	}
}
