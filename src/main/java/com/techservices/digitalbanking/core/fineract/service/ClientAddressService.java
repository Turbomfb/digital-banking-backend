/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.api.ClientApiClient;
import com.techservices.digitalbanking.core.fineract.model.request.PutClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientAddressTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutClientClientIdAddressesResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientAddressService {

	private final ClientApiClient clientApiClient;

	public GetClientAddressTemplateResponse getCustomerAddressTemplate() {
		return clientApiClient.getAddressesTemplate();
	}

	public List<GetClientClientIdAddressesResponse> getCustomerAddresses(Long customerId) {
		return clientApiClient.getClientAddresses(customerId, null, null);
	}

	public PutClientClientIdAddressesResponse updateCustomerAddress(Long customerId,
			PutClientClientIdAddressesRequest putClientClientIdAddressesRequest) {
		return clientApiClient.updateClientAddress(customerId, null, putClientClientIdAddressesRequest);
	}
}
