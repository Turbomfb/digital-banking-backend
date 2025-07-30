/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositProductProductIdResponse;
import com.techservices.digitalbanking.investment.domain.request.InvestmentApplicationRequest;
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

	private String clientId;

	private String dateFormat = DEFAULT_DATE_FORMAT;
	private String allocationName;

	private BigDecimal depositAmount;
	private BigDecimal mandatoryRecommendedDepositAmount;

	private Long depositPeriod;
	private Long recurringFrequencyType;
	private Long recurringFrequency;

	@JsonProperty("isCalendarInherited")
	private boolean isCalendarInherited;

	private Long depositPeriodFrequencyId;

	private String locale = DEFAULT_LOCALE;

	private Long productId;

	private String accountNo;
	private String externalId;

	private String submittedOnDate = getCurrentDate();

	private String linkAccountId;

	private Boolean allowWithdrawal;
	private Boolean adjustAdvanceTowardsFuturePayments;
	private Boolean isMandatoryDeposit;
	private Long withdrawalFrequencyType;
	private Long withdrawalFrequencyValue;

	public static PostRecurringDepositAccountsRequest parse(InvestmentApplicationRequest request, GetRecurringDepositProductProductIdResponse product) {
		PostRecurringDepositAccountsRequest recurringDepositAccountsRequest = new PostRecurringDepositAccountsRequest();
		recurringDepositAccountsRequest.setDepositAmount(request.getAmount());
		recurringDepositAccountsRequest.setDepositPeriod(product.getMaxDepositTerm());
		recurringDepositAccountsRequest.setDepositPeriodFrequencyId(product.getMaxDepositTermType().getId());
		recurringDepositAccountsRequest.setProductId(product.getId());
		recurringDepositAccountsRequest.setMandatoryRecommendedDepositAmount(request.getAmount());
		recurringDepositAccountsRequest.setRecurringFrequencyType(product.getInMultiplesOfDepositTermType().getId());
		recurringDepositAccountsRequest.setRecurringFrequency(product.getInMultiplesOfDepositTerm());
		recurringDepositAccountsRequest.setAllocationName(request.getAllocationName());
		return recurringDepositAccountsRequest;
	}
}
