package com.techservices.digitalbanking.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import com.techservices.digitalbanking.core.eBanking.model.response.EnumOptionData;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
public class AccountDto {

    private String id;
    private String accountNumber;
    private String customerId;
    private AccountType accountType;
    private String currency;
    private String currencyCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String productCode;
    private String productName;
    private String allocationName;
    private String nuban;
    private BigDecimal accountBalance = BigDecimal.ZERO;
    private Double annualInterestRate;
    private BigDecimal expectedInterest = BigDecimal.ZERO;
    private BigDecimal interestEarned = BigDecimal.ZERO;
    private Long duration;
    private String maturityDate;
    private EnumOptionData depositType;
    private GetSavingsStatus status;
}
