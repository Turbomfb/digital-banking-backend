/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/** LoanProductFloatingRates */
@Getter
@Setter
public class LoanProductFloatingRates {

  private BigDecimal defaultDifferentialLendingRate;

  private Boolean floatingInterestRateCalculationAllowed;

  private Object floatingRate;

  private Long id;

  private BigDecimal interestRateDifferential;

  private LoanProduct loanProduct;

  private BigDecimal maxDifferentialLendingRate;

  private BigDecimal minDifferentialLendingRate;

  private Boolean _new;
}
