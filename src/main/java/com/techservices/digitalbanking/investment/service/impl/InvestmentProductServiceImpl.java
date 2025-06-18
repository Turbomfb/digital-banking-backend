/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.request.PostRecurringDepositProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.DeleteRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetRecurringDepositProductProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.fineract.service.RecurringDepositProductService;
import com.techservices.digitalbanking.investment.service.InvestmentProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentProductServiceImpl implements InvestmentProductService {
	private final RecurringDepositProductService recurringDepositProductService;

	@Override
	public PostRecurringDepositProductResponse createProduct(
			PostRecurringDepositProductsRequest postRecurringDepositProductsRequest) {
		return recurringDepositProductService.createProduct(postRecurringDepositProductsRequest);
	}

	@Override
	public List<GetRecurringDepositProductProductIdResponse> retrieveAllProducts() {
		return recurringDepositProductService.retrieveAllProducts();
	}

	@Override
	public GetRecurringDepositProductProductIdResponse retrieveInvestmentProductById(Long productId) {
		return recurringDepositProductService.retrieveAProduct(productId);
	}

	@Override
	public PutRecurringDepositProductResponse updateProduct(Long productId,
			PutRecurringDepositProductRequest putRecurringDepositProductRequest) {
		return recurringDepositProductService.updateProduct(productId, putRecurringDepositProductRequest);
	}

	@Override
	public DeleteRecurringDepositProductResponse deleteProduct(Long productId) {
		return recurringDepositProductService.deleteProduct(productId);
	}

	@Override
	public Object retrieveProductTemplate() {
		return recurringDepositProductService.retrieveProductTemplate();
	}
}
