/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.model.request.PostLoanProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetLoanProductsTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostLoanProductsResponse;

public interface LoanProductService {

	BasePageResponse<GetLoanProductsProductIdResponse> getLoanProducts(Long fields);

	GetLoanProductsProductIdResponse getLoanProductById(Long productId, Long fields, Long template);

	PostLoanProductsResponse createALoanProduct(PostLoanProductsRequest postLoanProductRequest);

	GetLoanProductsTemplateResponse retrieveLoanProductTemplate();
}
