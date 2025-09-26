package com.techservices.digitalbanking.core.eBanking.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TransactionHistoryFilter {
    private String accountNumber;
    private String fromDate;
    private String toDate;
    private Long size;

    public TransactionHistoryFilter accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }
}
