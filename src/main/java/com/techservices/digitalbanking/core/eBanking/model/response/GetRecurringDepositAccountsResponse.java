/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRecurringDepositAccountsResponse extends BaseAppResponse {
  private String accountNo;

  private Integer clientId;

  private String clientName;
  private String allocationName;

  private GetSavingsCurrency currency;

  private Double depositAmount;

  private Integer depositPeriod;

  private EnumOptionData depositPeriodFrequency;

  private Integer fieldOfficerId;

  private Integer id;

  private EnumOptionData interestCalculationDaysInYearType;

  private EnumOptionData interestCalculationType;

  private EnumOptionData interestCompoundingPeriodType;

  private Boolean interestFreePeriodApplicable;

  private EnumOptionData interestPostingPeriodType;

  private Double maturityAmount;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate maturityDate;

  private Integer maxDepositTerm;

  private EnumOptionData maxDepositTermType;

  private Integer minDepositTerm;

  private EnumOptionData minDepositTermType;

  private Boolean preClosurePenalApplicable;

  private Integer savingsProductId;

  private String savingsProductName;

  private GetSavingsStatus status;

  private GetSavingsAccountsSummary summary;

  private GetSavingsTimeline timeline;

  List<GetRecurringDepositTransactionResponse> transactions;

  public BigDecimal getAccountBalance() {

    return summary != null && summary.getAvailableBalance() != null
        ? summary.getAccountBalance()
        : BigDecimal.ZERO;
  }
}
