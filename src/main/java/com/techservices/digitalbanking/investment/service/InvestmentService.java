/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.*;
import com.techservices.digitalbanking.fixeddeposit.domain.request.FixedDepositCommandRequest;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;

import jakarta.validation.Valid;

public interface InvestmentService {
	PostSavingsAccountsResponse submitApplication(
			FixedDepositApplicationRequest fixedDepositApplicationRequest, @Valid boolean activate);

	PostFixedDepositAccountsAccountIdResponse processInvestmentCommand(Long investmentId,
																	   @Valid FixedDepositCommandRequest fixedDepositCommandRequest, String command);

	GetFixedDepositAccountsResponse retrieveAllInvestments(Boolean paged, Integer offset, Integer limit,
														   String orderBy, String sortOrder);

	BaseAppResponse retrieveInvestmentById(Long id, Boolean staffInSelectedOfficeOnly,
										   @Valid String chargeStatus, String investmentType, Long customerId);

	Object retrieveTemplate(Long clientId, Long productId);

	BasePageResponse<GetClientsSavingsAccounts> retrieveAllCustomerInvestments(Long customerId, String investmentType);

	Object retrieveInvestmentTransactionsById(Long id, Boolean staffInSelectedOfficeOnly, @Valid String chargeStatus, String investmentType, Long customerId);

}
