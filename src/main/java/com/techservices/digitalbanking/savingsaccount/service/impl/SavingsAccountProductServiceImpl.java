/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.request.PostSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutSavingsAccountProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.service.SavingsProductService;
import com.techservices.digitalbanking.savingsaccount.service.SavingsAccountProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SavingsAccountProductServiceImpl implements SavingsAccountProductService {
	private final SavingsProductService savingsProductService;

	@Override
	public PostSavingsAccountProductsResponse createSavingsAccountProduct(
			PostSavingsAccountProductRequest postSavingsAccountProductRequest) {
		return savingsProductService.createSavingsAccountProduct(postSavingsAccountProductRequest);
	}

	@Override
	public List<GetSavingsAccountProductsResponse> retrieveAllSavingsAccountProducts() {
		return savingsProductService.retrieveAllSavingsAccountProducts();
	}

	@Override
	public GetSavingsAccountProductsResponse retrieveSavingsProductById(Long productId) {
		return savingsProductService.retrieveSavingsProductById(productId);
	}

	@Override
	public PutSavingsAccountProductsProductIdResponse updateSavingsAccountProduct(Long productId,
			PutSavingsAccountProductRequest request) {
		return savingsProductService.updateSavingsAccountProduct(productId, request);
	}
}
