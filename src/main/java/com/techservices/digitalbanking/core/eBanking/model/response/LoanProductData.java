/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/** LoanProductData */
@Getter
@Setter
public class LoanProductData {

  private Boolean allowPartialPeriodInterestCalcualtion;

  private EnumOptionData amortizationType;

  @Valid private List<@Valid EnumOptionData> amortizationTypeOptions = new ArrayList<>();

  private BigDecimal annualInterestRate;

  @Valid private List<@Valid Object> chargeOptions = new ArrayList<>();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate closeDate;

  private Boolean compoundingToBePostedAsTransaction;

  private Object currency;

  private EnumOptionData daysInMonthType;

  private EnumOptionData daysInYearType;

  private BigDecimal defaultDifferentialLendingRate;

  private String description;

  private Boolean equalAmortization;

  private BigDecimal fixedPrincipalPercentagePerInstallment;

  private Boolean floatingInterestRateCalculationAllowed;

  private Long fundId;

  private String fundName;

  @Valid private List<@Valid Object> fundOptions = new ArrayList<>();

  private LoanProductConfigurableAttributes getloanProductConfigurableAttributes;

  private Integer graceOnArrearsAgeing;

  private Integer graceOnInterestCharged;

  private Integer graceOnInterestPayment;

  private Integer graceOnPrincipalPayment;

  private Long id;

  private BigDecimal inArrearsTolerance;

  private EnumOptionData interestCalculationPeriodType;

  @Valid
  private List<@Valid EnumOptionData> interestCalculationPeriodTypeOptions = new ArrayList<>();

  private BigDecimal interestRateDifferential;

  private EnumOptionData interestRateFrequencyType;

  @Valid private List<@Valid EnumOptionData> interestRateFrequencyTypeOptions = new ArrayList<>();

  private BigDecimal interestRatePerPeriod;

  @Valid
  private List<@Valid LoanProductBorrowerCycleVariationData>
      interestRateVariationsForBorrowerCycle = new ArrayList<>();

  private LoanProductInterestRecalculationData interestRecalculationData;

  private Boolean interestRecalculationEnabled;

  private EnumOptionData interestType;

  @Valid private List<@Valid EnumOptionData> interestTypeOptions = new ArrayList<>();

  private Boolean linkedToFloatingInterestRates;

  private BigDecimal maxDifferentialLendingRate;

  private BigDecimal maxInterestRatePerPeriod;

  private Integer maxNumberOfRepayments;

  private BigDecimal maxPrincipal;

  private Integer maximumGapBetweenInstallments;

  private BigDecimal minDifferentialLendingRate;

  private BigDecimal minInterestRatePerPeriod;

  private Integer minNumberOfRepayments;

  private BigDecimal minPrincipal;

  private Integer minimumGapBetweenInstallments;

  private Boolean multiDisburseLoan;

  private String name;

  @Valid
  private List<@Valid LoanProductBorrowerCycleVariationData>
      numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<>();

  private Integer numberOfRepayments;

  private BigDecimal outstandingLoanBalance;

  private BigDecimal principal;

  @Valid
  private List<@Valid LoanProductBorrowerCycleVariationData> principalVariationsForBorrowerCycle =
      new ArrayList<>();

  private Integer recurringMoratoriumOnPrincipalPeriods;

  private Integer repaymentEvery;

  private EnumOptionData repaymentFrequencyType;

  @Valid private List<@Valid EnumOptionData> repaymentFrequencyTypeOptions = new ArrayList<>();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate startDate;

  private Long transactionProcessingStrategyId;

  private String transactionProcessingStrategyName;

  private Boolean variableInstallmentsAllowed;
}
