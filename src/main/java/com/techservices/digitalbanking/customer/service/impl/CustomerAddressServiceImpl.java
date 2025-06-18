/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.fineract.model.request.PutClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientAddressTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientAddressService;
import com.techservices.digitalbanking.customer.service.CustomerAddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerAddressServiceImpl implements CustomerAddressService {

	private final ClientAddressService clientAddressService;

	@Override
	public GetClientAddressTemplateResponse getCustomerAddressTemplate() {
		return clientAddressService.getCustomerAddressTemplate();
	}

	@Override
	public List<GetClientClientIdAddressesResponse> getCustomerAddresses(Long customerId) {
		return clientAddressService.getCustomerAddresses(customerId);
	}

	@Override
	public PutClientClientIdAddressesResponse updateCustomerAddress(Long customerId,
			PutClientClientIdAddressesRequest putClientClientIdAddressesRequest) {
		return clientAddressService.updateCustomerAddress(customerId, putClientClientIdAddressesRequest);
	}
}
