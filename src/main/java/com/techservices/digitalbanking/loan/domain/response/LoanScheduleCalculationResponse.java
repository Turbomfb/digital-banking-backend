/* (C)2024 */
package com.techservices.digitalbanking.loan.domain.response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanScheduleCalculationRequest;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanScheduleCalculationResponse {
	private BigDecimal monthlyRepayment;
	private Double interestRate;
	private BigDecimal totalRepayable;
	private List<Period> periods;

	@Setter
	@Getter
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Period {
		private Long period;
		private LocalDate dueDate;
		private BigDecimal principalDue;
		private BigDecimal interestDue;
		private BigDecimal totalDueForPeriod;
	}

	public static LoanScheduleCalculationResponse parse(PostLoansResponse loanSchedule,
			GetLoanProductsProductIdResponse product,
			@Valid LoanScheduleCalculationRequest loanScheduleCalculationRequest) {

		LoanScheduleCalculationResponse loanScheduleCalculationResponse = new LoanScheduleCalculationResponse();

		loanScheduleCalculationResponse.setTotalRepayable(loanSchedule.getTotalRepaymentExpected() == null
				? BigDecimal.ZERO
				: loanSchedule.getTotalRepaymentExpected().compareTo(BigDecimal.ZERO) > 0
						? loanSchedule.getTotalRepaymentExpected().setScale(2, RoundingMode.HALF_UP)
						: BigDecimal.ZERO);

		loanScheduleCalculationResponse.setInterestRate(product.getInterestRatePerPeriod());

		BigDecimal totalRepayable = loanScheduleCalculationResponse.getTotalRepayable();
		Long loanTermFrequency = loanScheduleCalculationRequest.getLoanTermFrequency();
		BigDecimal monthlyRepayment = BigDecimal.ZERO;

		if (totalRepayable.compareTo(BigDecimal.ZERO) > 0 && loanTermFrequency > 0) {
			monthlyRepayment = totalRepayable.divide(BigDecimal.valueOf(loanTermFrequency), RoundingMode.HALF_UP)
					.setScale(2, RoundingMode.HALF_UP);
		}

		loanScheduleCalculationResponse.setMonthlyRepayment(monthlyRepayment);
		return loanScheduleCalculationResponse;
	}
}
