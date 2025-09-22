/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.api.SavingsAccountProductApiClient;
import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutSavingsAccountProductsProductIdResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;

@Service
@Slf4j
@RequiredArgsConstructor
public class SavingsProductService {

	private final SavingsAccountProductApiClient savingsAccountProductApiClient;

	public PostSavingsAccountProductsResponse createSavingsAccountProduct(
			PostSavingsAccountProductRequest postSavingsAccountProductRequest) {
		postSavingsAccountProductRequest.setLocale(DEFAULT_LOCALE);
		return savingsAccountProductApiClient.createSavingsAccountProduct(postSavingsAccountProductRequest);
	}

	public List<GetSavingsAccountProductsResponse> retrieveAllSavingsAccountProducts() {
		return savingsAccountProductApiClient.retrieveAll();
	}

	public GetSavingsAccountProductsResponse retrieveSavingsProductById(Long productId) {
		return savingsAccountProductApiClient.retrieveById(productId);
	}

	public PutSavingsAccountProductsProductIdResponse updateSavingsAccountProduct(Long productId,
			PutSavingsAccountProductRequest request) {
		request.setLocale(DEFAULT_LOCALE);
		return savingsAccountProductApiClient.updateSavingsAccountProduct(request, productId);
	}
}
