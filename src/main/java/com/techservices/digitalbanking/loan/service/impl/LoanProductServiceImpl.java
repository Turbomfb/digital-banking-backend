/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.request.PostLoanProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanProductsTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoanProductsResponse;
import com.techservices.digitalbanking.core.fineract.service.LoanService;
import com.techservices.digitalbanking.loan.service.LoanProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanProductServiceImpl implements LoanProductService {

	private final LoanService loanProductService;

	@Override
	public List<GetLoanProductsProductIdResponse> getLoanProducts(Long fields) {
		return loanProductService.getLoanProducts(fields);
	}

	@Override
	public GetLoanProductsProductIdResponse getLoanProductById(Long productId, Long fields, Long template) {
		return loanProductService.getLoanProductById(productId, fields, template);
	}

	@Override
	public PostLoanProductsResponse createALoanProduct(PostLoanProductsRequest postLoanProductRequest) {
		return loanProductService.createALoanProduct(postLoanProductRequest);
	}

	@Override
	public GetLoanProductsTemplateResponse retrieveLoanProductTemplate() {
		return loanProductService.retrieveLoanProductTemplate();
	}
}
