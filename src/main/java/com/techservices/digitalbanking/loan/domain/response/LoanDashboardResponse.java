package com.techservices.digitalbanking.loan.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoansLoanIdResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class LoanDashboardResponse {
    private BigDecimal activeLoanBalance;
    private BigDecimal totalExpectedRepayment;
    private BigDecimal totalRepaid;
    private Long activeLoanCount;
    private Long pendingLoanCount;
    private Long liquidatedLoanCount;
    private List<GetLoansLoanIdResponse> loans;
}
