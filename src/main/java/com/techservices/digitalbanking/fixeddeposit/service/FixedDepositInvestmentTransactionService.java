/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.service;

import java.util.List;

import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostFixedDepositTransactionCommandResponse;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositTransactionCommandRequest;

public interface FixedDepositInvestmentTransactionService {

	GetFixedDepositTransactionResponse retrieveInvestmentTransactionById(Long investmentId, Long transactionId);

	List<GetFixedDepositTransactionResponse> retrieveAllInvestmentTransactions(Long investmentId);

	PostFixedDepositTransactionCommandResponse processInvestmentTransactionCommand(Long investmentId,
			FixedDepositTransactionCommandRequest request, String command);
}
