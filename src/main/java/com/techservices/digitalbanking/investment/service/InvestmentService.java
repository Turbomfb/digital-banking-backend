/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service;

import java.util.List;

import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositCommandRequest;

import jakarta.validation.Valid;

public interface InvestmentService {
	PostRecurringDepositAccountsResponse submitApplication(
			PostRecurringDepositAccountsRequest postRecurringDepositAccountsRequest, @Valid boolean activate);

	PostRecurringDepositAccountsResponse processInvestmentCommand(Long investmentId,
			@Valid RecurringDepositCommandRequest recurringDepositCommandRequest, String command);

	List<GetRecurringDepositAccountsResponse> retrieveAllInvestments(Boolean paged, Integer offset, Integer limit,
			String orderBy, String sortOrder);

	GetRecurringDepositAccountsResponse retrieveInvestmentById(Long id, Boolean staffInSelectedOfficeOnly,
			@Valid String chargeStatus);

	Object retrieveTemplate(Long clientId, Long productId);
}
