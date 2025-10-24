package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanProductListResponse {
    private long id;
    private String productCode;
    private String productName;
    private Double annualInterestRate;
    private BigDecimal principal;
    private BigDecimal minPrincipal;
    private BigDecimal maxPrincipal;
    private long numberOfRepayments;
    private long minimumTenure;
    private long maximumTenure;
    private long repaymentEvery;
    private String repaymentFrequencyType;
}
