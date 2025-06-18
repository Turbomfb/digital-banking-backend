/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositAccountsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.service.RecurringDepositAccountService;
import com.techservices.digitalbanking.investment.domain.request.RecurringDepositCommandRequest;
import com.techservices.digitalbanking.investment.service.InvestmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {
	private final RecurringDepositAccountService recurringDepositAccountService;

	@Override
	public PostRecurringDepositAccountsResponse submitApplication(
			PostRecurringDepositAccountsRequest postRecurringDepositAccountsRequest, @Valid boolean activate) {
		return recurringDepositAccountService.submitApplication(postRecurringDepositAccountsRequest, activate);
	}

	@Override
	public PostRecurringDepositAccountsResponse processInvestmentCommand(Long investmentId,
			@Valid RecurringDepositCommandRequest recurringDepositCommandRequest, String command) {
		return recurringDepositAccountService.processInvestmentCommand(investmentId, recurringDepositCommandRequest,
				command);
	}

	@Override
	public List<GetRecurringDepositAccountsResponse> retrieveAllInvestments(Boolean paged, Integer offset,
			Integer limit, String orderBy, String sortOrder) {
		return recurringDepositAccountService.retrieveAllInvestments(paged, offset, limit, orderBy, sortOrder);
	}

	@Override
	public GetRecurringDepositAccountsResponse retrieveInvestmentById(Long id, Boolean staffInSelectedOfficeOnly,
			@Valid String chargeStatus) {
		return recurringDepositAccountService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus);
	}

	@Override
	public Object retrieveTemplate(Long clientId, Long productId) {
		return null;
	}
}
