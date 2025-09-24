package com.techservices.digitalbanking.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.enums.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class FlexAccountDto {

    private String accountNumber;
    private String customerId;
    private String id;
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private String currencyCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String productCode;
    private String productName;
}
