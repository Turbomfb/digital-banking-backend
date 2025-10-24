package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanProductListResponse {
    private Long id;
    private String productCode;
    private String productName;
    private Double annualInterestRate;
    private BigDecimal principal;
    private BigDecimal minPrincipal;
    private BigDecimal maxPrincipal;
    private Long numberOfRepayments;
    private Long minimumTenure;
    private Long maximumTenure;
    private Long repaymentEvery;
    private String repaymentFrequencyType;
}
