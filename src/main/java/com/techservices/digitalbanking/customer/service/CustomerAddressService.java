/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service;

import java.util.List;

import com.techservices.digitalbanking.core.eBanking.model.request.PutClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetClientAddressTemplateResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutClientClientIdAddressesResponse;

public interface CustomerAddressService {

	GetClientAddressTemplateResponse getCustomerAddressTemplate();

	List<GetClientClientIdAddressesResponse> getCustomerAddresses(Long customerId);

	PutClientClientIdAddressesResponse updateCustomerAddress(Long customerId,
			PutClientClientIdAddressesRequest putClientClientIdAddressesRequest);
}
