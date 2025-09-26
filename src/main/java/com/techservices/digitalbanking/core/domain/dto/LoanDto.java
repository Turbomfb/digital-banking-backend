package com.techservices.digitalbanking.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoanDto {
    private Long id;
    private String loanAccountNumber;
    private String accountName;
    private String productCode;
    private String productName;
    private BigDecimal principalAmount;
    private BigDecimal outstandingBalance;
    private BigDecimal totalExpectedRepayment;
    private BigDecimal totalPaid;
    private BigDecimal interestAmount;
    private Double interestRate;
    private String status;
    private LocalDate disbursementDate;
    private LocalDate lastPaymentDate;
    private LocalDate maturityDate;

}
