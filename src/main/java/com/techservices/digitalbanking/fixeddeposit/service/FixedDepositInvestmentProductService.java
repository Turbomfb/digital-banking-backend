/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.fixeddeposit.service;

import java.util.List;

import com.techservices.digitalbanking.core.eBanking.model.request.PostFixedDepositProductsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.DeleteFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutFixedDepositProductsProductIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PutFixedDepositProductsProductIdResponse;

public interface FixedDepositInvestmentProductService {
	PostFixedDepositProductsResponse createProduct(PostFixedDepositProductsRequest postFixedDepositProductsRequest);

	List<GetFixedDepositProductsResponse> retrieveAllProducts();

	GetFixedDepositProductsProductIdResponse retrieveInvestmentProductById(Long productId);

	Object getTemplate();

	PutFixedDepositProductsProductIdResponse updateProduct(Long productId,
			PutFixedDepositProductsProductIdRequest putFixedDepositProductsProductIdRequest);

	DeleteFixedDepositProductsProductIdResponse deleteProduct(Long productId);
}
