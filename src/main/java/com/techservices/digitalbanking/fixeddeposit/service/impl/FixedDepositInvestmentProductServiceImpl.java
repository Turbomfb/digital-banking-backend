/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.request.PostFixedDepositProductsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.DeleteFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutFixedDepositProductsProductIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PutFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.service.FixedDepositProductService;
import com.techservices.digitalbanking.fixeddeposit.service.FixedDepositInvestmentProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FixedDepositInvestmentProductServiceImpl implements FixedDepositInvestmentProductService {
	private final FixedDepositProductService fixedDepositProductService;

	@Override
	public PostFixedDepositProductsResponse createProduct(
			PostFixedDepositProductsRequest postFixedDepositProductsRequest) {
		return fixedDepositProductService.createProduct(postFixedDepositProductsRequest);
	}

	@Override
	public List<GetFixedDepositProductsResponse> retrieveAllProducts() {
		return fixedDepositProductService.retrieveAllProducts();
	}

	@Override
	public GetFixedDepositProductsProductIdResponse retrieveInvestmentProductById(Long productId) {
		return fixedDepositProductService.retrieveAProduct(productId);
	}

	@Override
	public Object getTemplate() {
		return fixedDepositProductService.getTemplate();
	}

	@Override
	public PutFixedDepositProductsProductIdResponse updateProduct(Long productId,
			PutFixedDepositProductsProductIdRequest putFixedDepositProductsProductIdRequest) {
		return fixedDepositProductService.updateProduct(productId, putFixedDepositProductsProductIdRequest);
	}

	@Override
	public DeleteFixedDepositProductsProductIdResponse deleteProduct(Long productId) {
		return fixedDepositProductService.deleteProduct(productId);
	}
}
