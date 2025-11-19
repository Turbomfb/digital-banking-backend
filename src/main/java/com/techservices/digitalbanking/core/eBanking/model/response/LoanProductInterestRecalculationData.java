/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

/** LoanProductInterestRecalculationData */
@Getter
@Setter
public class LoanProductInterestRecalculationData {

  private Boolean compoundingToBePostedAsTransaction;

  private EnumOptionData interestRecalculationCompoundingType;

  private Integer recalculationCompoundingFrequencyInterval;

  private EnumOptionData recalculationCompoundingFrequencyNthDay;

  private Integer recalculationCompoundingFrequencyOnDay;

  private EnumOptionData recalculationCompoundingFrequencyType;

  private EnumOptionData recalculationCompoundingFrequencyWeekday;

  private Integer recalculationRestFrequencyInterval;

  private EnumOptionData recalculationRestFrequencyNthDay;

  private Integer recalculationRestFrequencyOnDay;

  private EnumOptionData recalculationRestFrequencyType;

  private EnumOptionData recalculationRestFrequencyWeekday;

  private EnumOptionData rescheduleStrategyType;
}
