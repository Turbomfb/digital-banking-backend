/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/** LoanProductRelatedDetail */
@Getter
@Setter
public class LoanProductRelatedDetail {

  private Boolean allowPartialPeriodInterestCalcualtion;

  /** Gets or Sets amortizationMethod */
  public enum AmortizationMethodEnum {
    EQUAL_PRINCIPAL("EQUAL_PRINCIPAL"),

    EQUAL_INSTALLMENTS("EQUAL_INSTALLMENTS"),

    INVALID("INVALID");

    private String value;

    AmortizationMethodEnum(String value) {

      this.value = value;
    }

    @JsonValue
    public String getValue() {

      return value;
    }

    @Override
    public String toString() {

      return String.valueOf(value);
    }

    @JsonCreator
    public static AmortizationMethodEnum fromValue(String value) {

      for (AmortizationMethodEnum b : AmortizationMethodEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private AmortizationMethodEnum amortizationMethod;

  private BigDecimal annualNominalInterestRate;

  private BigDecimal arrearsTolerance;

  private Object currency;

  private Boolean equalAmortization;

  private Integer graceOnArrearsAgeing;

  private Integer graceOnDueDate;

  private Integer graceOnInterestPayment;

  private Integer graceOnPrincipalPayment;

  private Object inArrearsTolerance;
}
