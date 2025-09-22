/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostFixedDepositTransactionCommandResponse;
import com.techservices.digitalbanking.core.eBanking.service.FixedDepositTransactionService;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositTransactionCommandRequest;
import com.techservices.digitalbanking.fixeddeposit.service.FixedDepositInvestmentTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FixedDepositInvestmentTransactionServiceImpl implements FixedDepositInvestmentTransactionService {
	private final FixedDepositTransactionService fixedDepositTransactionService;

	@Override
	public GetFixedDepositTransactionResponse retrieveInvestmentTransactionById(Long investmentId, Long transactionId) {
		return fixedDepositTransactionService.retrieveInvestmentTransactionById(investmentId, transactionId);
	}

	@Override
	public List<GetFixedDepositTransactionResponse> retrieveAllInvestmentTransactions(Long investmentId) {
		return fixedDepositTransactionService.retrieveAllInvestmentTransactions(investmentId);
	}

	@Override
	public PostFixedDepositTransactionCommandResponse processInvestmentTransactionCommand(Long investmentId,
			FixedDepositTransactionCommandRequest request, String command) {
		return fixedDepositTransactionService.processInvestmentTransactionCommand(investmentId, request, command);
	}
}
