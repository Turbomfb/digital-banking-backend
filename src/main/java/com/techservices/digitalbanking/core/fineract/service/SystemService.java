/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import com.techservices.digitalbanking.core.fineract.api.SavingsAccountProductApiClient;
import com.techservices.digitalbanking.core.fineract.api.SystemApiClient;
import com.techservices.digitalbanking.core.fineract.model.request.PostSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutSavingsAccountProductsProductIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;

@Service
@Slf4j
@RequiredArgsConstructor
public class SystemService {

	private final SystemApiClient systemApiClient;

	public Object healthCheck() {
		return systemApiClient.healthCheck();
	}

}
