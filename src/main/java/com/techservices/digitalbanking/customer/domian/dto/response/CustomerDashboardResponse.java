package com.techservices.digitalbanking.customer.domian.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CustomerDashboardResponse {
    private Account walletAccount;
    private Account flexAccount;
    private Account lockAccount;
    private Account investmentMetrics;


    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @RequiredArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Account {
        private BigDecimal balance = BigDecimal.ZERO;
        private BigDecimal totalInterestEarned = BigDecimal.ZERO;
        private BigDecimal totalDeposit = BigDecimal.ZERO;
        private BigDecimal totalWithdrawal = BigDecimal.ZERO;
        private Long totalActivePlan;

        public Account (BigDecimal balance, BigDecimal totalInterestEarned, BigDecimal totalDeposit, BigDecimal totalWithdrawal, Long totalActivePlan) {
            this.balance = balance;
            this.totalInterestEarned = totalInterestEarned;
            this.totalDeposit = totalDeposit;
            this.totalWithdrawal = totalWithdrawal;
            this.totalActivePlan = totalActivePlan;
        }
    }
}
