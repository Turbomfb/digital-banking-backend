/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/** LoanProduct */
@Getter
@Setter
public class LoanProduct {

  private Boolean accountingDisabled;

  private Integer accountingType;

  private Boolean accrualBasedAccountingEnabled;

  private Boolean allowApprovedDisbursedAmountsOverApplied;

  private Boolean arrearsBasedOnOriginalSchedule;

  private Boolean cashBasedAccountingEnabled;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate closeDate;

  private Object currency;

  private Boolean disallowExpectedDisbursements;

  private Boolean equalAmortization;

  private String externalId;

  private BigDecimal fixedPrincipalPercentagePerInstallment;

  private LoanProductFloatingRates floatingRates;

  private Boolean holdGuaranteeFundsEnabled;

  private Long id;

  private Boolean includeInBorrowerCycle;

  private Integer installmentAmountInMultiplesOf;

  /** Gets or Sets interestPeriodFrequencyType */
  public enum InterestPeriodFrequencyTypeEnum {
    DAYS("DAYS"),

    WEEKS("WEEKS"),

    MONTHS("MONTHS"),

    YEARS("YEARS"),

    WHOLE_TERM("WHOLE_TERM"),

    INVALID("INVALID");

    private String value;

    InterestPeriodFrequencyTypeEnum(String value) {

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
    public static InterestPeriodFrequencyTypeEnum fromValue(String value) {

      for (InterestPeriodFrequencyTypeEnum b : InterestPeriodFrequencyTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private InterestPeriodFrequencyTypeEnum interestPeriodFrequencyType;

  private Boolean interestRecalculationEnabled;

  private Boolean linkedToFloatingInterestRate;

  private LoanProductTrancheDetails loanProducTrancheDetails;

  @Valid private List<@Valid Object> loanProductCharges = new ArrayList<>();

  private LoanProductConfigurableAttributes loanProductConfigurableAttributes;

  private LoanProductGuaranteeDetails loanProductGuaranteeDetails;

  private LoanProductRelatedDetail loanProductRelatedDetail;

  private BigDecimal maxNominalInterestRatePerPeriod;

  private Integer maxNumberOfRepayments;

  private Object maxPrincipalAmount;

  private BigDecimal minNominalInterestRatePerPeriod;

  private Integer minNumberOfRepayments;

  private Object minPrincipalAmount;

  private Integer minimumDaysBetweenDisbursalAndFirstRepayment;

  private Boolean multiDisburseLoan;

  private Boolean _new;

  private BigDecimal nominalInterestRatePerPeriod;

  private Integer numberOfRepayments;

  private String overAppliedCalculationType;

  private Integer overAppliedNumber;

  private Boolean periodicAccrualAccountingEnabled;

  private Object principalAmount;

  private BigDecimal principalThresholdForLastInstallment;

  private LoanProductInterestRecalculationDetails productInterestRecalculationDetails;

  @Valid private List<@Valid Object> rates = new ArrayList<>();

  private LoanTransactionProcessingStrategy repaymentStrategy;

  private String shortName;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startDate;

  private Boolean syncExpectedWithDisbursementDate;

  private Boolean upfrontAccrualAccountingEnabled;
}
