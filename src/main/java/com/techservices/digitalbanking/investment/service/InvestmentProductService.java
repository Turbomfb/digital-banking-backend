/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service;

import java.util.List;

import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.DeleteRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositProductProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutRecurringDepositProductResponse;

public interface InvestmentProductService {
	PostRecurringDepositProductResponse createProduct(
			PostRecurringDepositProductsRequest postRecurringDepositProductsRequest);

	List<GetRecurringDepositProductProductIdResponse> retrieveAllProducts();

	GetRecurringDepositProductProductIdResponse retrieveInvestmentProductById(Long productId);

	PutRecurringDepositProductResponse updateProduct(Long productId,
			PutRecurringDepositProductRequest putRecurringDepositProductRequest);

	DeleteRecurringDepositProductResponse deleteProduct(Long productId);

	Object retrieveProductTemplate();
}
