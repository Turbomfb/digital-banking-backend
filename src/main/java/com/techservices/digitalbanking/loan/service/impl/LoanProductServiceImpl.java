/* (C)2024 */
package com.techservices.digitalbanking.loan.service.impl;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.eBanking.model.request.PostLoanProductsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanProductsTemplateResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanProductListResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoanProductsResponse;
import com.techservices.digitalbanking.core.eBanking.service.LoanService;
import com.techservices.digitalbanking.loan.service.LoanProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoanProductServiceImpl implements LoanProductService {

	private final LoanService loanProductService;

	@Override
	public BasePageResponse<LoanProductListResponse> getLoanProducts(Long fields) {

		return BasePageResponse.instance(loanProductService.getLoanProducts(fields));
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
