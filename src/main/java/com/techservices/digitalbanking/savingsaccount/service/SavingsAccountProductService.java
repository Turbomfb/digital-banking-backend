/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service;

import java.util.List;

import com.techservices.digitalbanking.core.fineract.model.request.PostSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutSavingsAccountProductsProductIdResponse;

public interface SavingsAccountProductService {

	PostSavingsAccountProductsResponse createSavingsAccountProduct(
			PostSavingsAccountProductRequest postSavingsAccountProductRequest);

	List<GetSavingsAccountProductsResponse> retrieveAllSavingsAccountProducts();

	GetSavingsAccountProductsResponse retrieveSavingsProductById(Long productId);

	PutSavingsAccountProductsProductIdResponse updateSavingsAccountProduct(Long productId,
			PutSavingsAccountProductRequest request);
}
