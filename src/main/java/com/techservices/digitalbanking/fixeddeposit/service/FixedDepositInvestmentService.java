/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.service;

import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositAccountsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostFixedDepositAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountsResponse;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositCommandRequest;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;

import jakarta.validation.Valid;

public interface FixedDepositInvestmentService {
	PostSavingsAccountsResponse submitApplication(FixedDepositApplicationRequest fixedDepositApplicationRequest,
			@Valid boolean activate);

	PostFixedDepositAccountsAccountIdResponse processInvestmentCommand(Long investmentId,
			FixedDepositCommandRequest fixedDepositCommandRequest, String command);

	GetFixedDepositAccountsResponse retrieveAllInvestments(Boolean paged, Integer offset, Integer limit, String orderBy,
			String sortOrder);

	GetFixedDepositAccountsAccountIdResponse retrieveInvestmentById(Long id, Boolean staffInSelectedOfficeOnly,
			@Valid String chargeStatus);

	Object retrieveTemplate(Long clientId, Long productId);
}
