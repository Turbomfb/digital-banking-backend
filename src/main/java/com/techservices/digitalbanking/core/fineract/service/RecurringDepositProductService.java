/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.api.RecurringDepositProductsApiClient;
import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.DeleteRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositProductProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutRecurringDepositProductResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecurringDepositProductService {
	private final RecurringDepositProductsApiClient recurringDepositProductsApiClient;

	public PostRecurringDepositProductResponse createProduct(
			PostRecurringDepositProductsRequest postFixedDepositProductsRequest) {
		return recurringDepositProductsApiClient.create(postFixedDepositProductsRequest);
	}

	public List<GetRecurringDepositProductProductIdResponse> retrieveAllProducts() {
		return recurringDepositProductsApiClient.retrieveAll();
	}

	public GetRecurringDepositProductProductIdResponse retrieveAProduct(Long productId) {
		return recurringDepositProductsApiClient.retrieveOne(productId);
	}

	public PutRecurringDepositProductResponse updateProduct(Long productId,
			PutRecurringDepositProductRequest putRecurringDepositProductRequest) {
		return recurringDepositProductsApiClient.update(productId, putRecurringDepositProductRequest);
	}

	public DeleteRecurringDepositProductResponse deleteProduct(Long productId) {
		return recurringDepositProductsApiClient.delete(productId);
	}

	public Object retrieveTemplate(Long clientId, Long productId) {
		return recurringDepositProductsApiClient.retrieveAccountTemplate(clientId, null, productId, null);
	}

	public Object retrieveProductTemplate() {
		return recurringDepositProductsApiClient.retrieveProductTemplate();
	}
}
