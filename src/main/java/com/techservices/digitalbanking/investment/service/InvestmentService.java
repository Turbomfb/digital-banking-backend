/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service;

import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;

import com.techservices.digitalbanking.investment.domain.request.InvestmentApplicationRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentCalculatorRequest;
import com.techservices.digitalbanking.investment.domain.request.InvestmentUpdateRequest;
import com.techservices.digitalbanking.investment.domain.response.InvestmentApplicationResponse;
import com.techservices.digitalbanking.investment.domain.response.InvestmentCalculatorResponse;

public interface InvestmentService {

	AccountDto retrieveInvestmentById(Long id, InvestmentType investmentType, Long customerId);


	BasePageResponse<AccountDto> retrieveAllCustomerInvestments(Long customerId, String investmentType);

	Object retrieveInvestmentTransactionsById(String id, String investmentType, Long customerId);

	InvestmentApplicationResponse submitApplication(Long customerId, InvestmentType investmentType, InvestmentApplicationRequest request);

	BaseAppResponse updateAnInvestment(Long customerId, InvestmentType investmentType, InvestmentUpdateRequest request, String investmentId);

	BaseAppResponse fundInvestment(Long customerId, InvestmentType investmentType, InvestmentUpdateRequest request, String investmentId);

	InvestmentCalculatorResponse calculateInvestment(Long customerId, InvestmentCalculatorRequest request);

    BaseAppResponse withdrawFlexInvestment(Long customerId, InvestmentUpdateRequest request, String investmentId);
}
