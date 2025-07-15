package com.techservices.digitalbanking.loan.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanOfferResponse {
    private Long offerId;
    private BigDecimal loanAmount;
    private Long loanTenor;
    private BigDecimal monthlyRepayment;
}
