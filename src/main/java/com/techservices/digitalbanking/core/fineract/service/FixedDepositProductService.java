/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.api.FixeddepositproductsApi;
import com.techservices.digitalbanking.core.fineract.model.request.PostFixedDepositProductsRequest;
import com.techservices.digitalbanking.core.fineract.model.response.DeleteFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutFixedDepositProductsProductIdRequest;
import com.techservices.digitalbanking.core.fineract.model.response.PutFixedDepositProductsProductIdResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixedDepositProductService {
	private final FixeddepositproductsApi fixeddepositproductsApi;

	public PostFixedDepositProductsResponse createProduct(
			PostFixedDepositProductsRequest postFixedDepositProductsRequest) {
		return fixeddepositproductsApi.createProduct(postFixedDepositProductsRequest);
	}

	public List<GetFixedDepositProductsResponse> retrieveAllProducts() {
		return fixeddepositproductsApi.retrieveAll39();
	}

	public GetFixedDepositProductsProductIdResponse retrieveAProduct(Long productId) {
		return fixeddepositproductsApi.retrieveOne(productId);
	}

	public Object getTemplate() {
		return fixeddepositproductsApi.retrieveTemplate();
	}

	public PutFixedDepositProductsProductIdResponse updateProduct(Long productId,
			PutFixedDepositProductsProductIdRequest putFixedDepositProductsProductIdRequest) {
		return fixeddepositproductsApi.updateProduct(productId, putFixedDepositProductsProductIdRequest);
	}

	public DeleteFixedDepositProductsProductIdResponse deleteProduct(Long productId) {
		return fixeddepositproductsApi.delete(productId);
	}
}
