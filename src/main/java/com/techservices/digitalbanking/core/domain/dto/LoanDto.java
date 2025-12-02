/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.techservices.digitalbanking.loan.domain.response.LoanScheduleCalculationResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class LoanDto {
	private Long id;
	private String loanAccountNumber;
	private String accountName;
	private String productCode;
	private String productName;
	private Long tenure;
	private BigDecimal principalAmount;
	private BigDecimal outstandingBalance;
	private BigDecimal totalRepaid;
	private BigDecimal totalExpectedRepayment;
	private BigDecimal interestAmount;
	private Double annualInterestRate;
	private Double interestRatePerPeriod;
	private String status;
	private LocalDate disbursementDate;
	private LocalDate lastPaymentDate;
	private LocalDate maturityDate;
	private LoanScheduleCalculationResponse repaymentSchedule;

	public boolean isPending() {

		List<String> pendingStatuses = List.of("Pending", "WaitingForDisbursal", "Submitted and pending approval",
				"Approved");
		return pendingStatuses.contains(this.status);
	}

	public boolean isActive() {

		List<String> pendingStatuses = List.of("Active");
		return pendingStatuses.contains(this.status);
	}

	public boolean isLiquidated() {

		List<String> pendingStatuses = List.of("Liquidated");
		return pendingStatuses.contains(this.status);
	}

	public boolean isClosed() {

		List<String> pendingStatuses = List.of("Closed");
		return pendingStatuses.contains(this.status);
	}
}
