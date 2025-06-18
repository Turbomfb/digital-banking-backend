/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.service.impl;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostFixedDepositAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.core.fineract.service.FixedDepositService;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositCommandRequest;
import com.techservices.digitalbanking.fixeddeposit.service.FixedDepositInvestmentService;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FixedDepositInvestmentServiceImpl implements FixedDepositInvestmentService {
	private final FixedDepositService fixedDepositService;

	@Override
	public PostSavingsAccountsResponse submitApplication(FixedDepositApplicationRequest fixedDepositApplicationRequest,
			@Valid boolean activate) {
		return fixedDepositService.submitApplication(fixedDepositApplicationRequest, activate);
	}

	@Override
	public PostFixedDepositAccountsAccountIdResponse processInvestmentCommand(Long investmentId,
			FixedDepositCommandRequest fixedDepositCommandRequest, String command) {
		return fixedDepositService.processInvestmentCommand(investmentId, fixedDepositCommandRequest, command);
	}

	@Override
	public GetFixedDepositAccountsResponse retrieveAllInvestments(Boolean paged, Integer offset, Integer limit,
			String orderBy, String sortOrder) {
		return fixedDepositService.retrieveAllInvestments(paged, offset, limit, orderBy, sortOrder);
	}

	@Override
	public GetFixedDepositAccountsAccountIdResponse retrieveInvestmentById(Long id, Boolean staffInSelectedOfficeOnly,
			@Valid String chargeStatus) {
		return fixedDepositService.retrieveInvestmentById(id, staffInSelectedOfficeOnly, chargeStatus);
	}

	@Override
	public Object retrieveTemplate(Long clientId, Long productId) {
		return fixedDepositService.retrieveTemplate(clientId, productId);
	}
}
