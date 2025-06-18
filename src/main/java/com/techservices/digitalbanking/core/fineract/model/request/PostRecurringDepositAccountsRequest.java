/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_DATE_FORMAT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostRecurringDepositAccountsRequest {

	private Long clientId;

	private String dateFormat = DEFAULT_DATE_FORMAT;

	private BigDecimal depositAmount;
	private BigDecimal mandatoryRecommendedDepositAmount;

	private Long depositPeriod;
	private Long recurringFrequencyType;
	private Long recurringFrequency;
	private Boolean isCalendarInherited;

	private Long depositPeriodFrequencyId;

	private String locale = DEFAULT_LOCALE;

	private Long productId;

	private String accountNo;
	private String externalId;

	private String submittedOnDate = getCurrentDate();

	private Long linkAccountId;

	private Boolean allowWithdrawal;
	private Boolean adjustAdvanceTowardsFuturePayments;
	private Boolean isMandatoryDeposit;
	private Long withdrawalFrequencyType;
	private Long withdrawalFrequencyValue;
}
