/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.investment.service.impl;

import java.util.List;

import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.eBanking.configuration.FineractProperty;
import com.techservices.digitalbanking.core.eBanking.service.FixedDepositProductService;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.request.PostRecurringDepositProductsRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutRecurringDepositProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.DeleteRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetRecurringDepositProductProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutRecurringDepositProductResponse;
import com.techservices.digitalbanking.core.eBanking.service.RecurringDepositProductService;
import com.techservices.digitalbanking.investment.service.InvestmentProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentProductServiceImpl implements InvestmentProductService {
	private final RecurringDepositProductService recurringDepositProductService;
	private final FineractProperty fineractProperty;
	private final FixedDepositProductService fixedDepositProductService;

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
	public Object retrieveCurrentInvestmentProduct(String investmentType) {
		if (InvestmentType.FLEX.name().equals(investmentType)) {
			return recurringDepositProductService.retrieveAProduct(fineractProperty.getDefaultRecurringDepositProductId());
		} else if (InvestmentType.LOCK.name().equals(investmentType)) {
			return fixedDepositProductService.retrieveAProduct(fineractProperty.getDefaultFixedDepositProductId());
		} else {
			throw new ValidationException("invalid.investment.type", "The investment type provided is invalid");
		}
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
