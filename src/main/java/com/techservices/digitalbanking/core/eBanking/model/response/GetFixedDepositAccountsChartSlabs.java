/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetFixedDepositAccountsChartSlabs {

  private Double annualInterestRate;

  private GetFixedDepositAccountsAccountChartCurrency currency;

  private Integer fromPeriod;

  private Integer id;

  private EnumOptionData periodType;

  private Integer toPeriod;
}
