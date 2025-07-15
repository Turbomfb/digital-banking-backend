package com.techservices.digitalbanking.customer.domian.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerTierData {
    private Long id;
    private String tierName;
    private BigDecimal maximumBalance;
    private BigDecimal singleDepositLimit;
    private BigDecimal singleWithdrawalLimit;
    private BigDecimal dailyWithdrawalLimit;
}
