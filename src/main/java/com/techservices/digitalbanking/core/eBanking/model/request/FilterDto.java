package com.techservices.digitalbanking.core.eBanking.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FilterDto {
    private String accountNumber;
    private String customerId;
    private String fromDate;
    private String toDate;
    private Long size;

    public FilterDto(String savingsAccountId, String startDate, String endDate, Long limit) {
        this.accountNumber = savingsAccountId;
        this.fromDate = startDate;
        this.toDate = endDate;
        this.size = limit;
    }

    public FilterDto accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public FilterDto customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public FilterDto size(Long limit) {
        this.size = limit;
        return this;
    }
}
