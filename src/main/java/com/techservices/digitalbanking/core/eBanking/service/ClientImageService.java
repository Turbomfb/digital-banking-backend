/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.eBanking.api.CustomerApiClient;
import com.techservices.digitalbanking.core.eBanking.model.request.PutClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostClientClientIdImagesResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutClientClientIdAddressesResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientImageService {

	private final CustomerApiClient customerApiClient;

	public List<GetClientClientIdAddressesResponse> getCustomerAddresses(Long customerId) {

		return customerApiClient.getClientAddresses(customerId, null, null);
	}

	public PutClientClientIdAddressesResponse updateCustomerAddress(Long customerId,
			PutClientClientIdAddressesRequest putClientClientIdAddressesRequest) {

		return customerApiClient.updateClientAddress(customerId, null, putClientClientIdAddressesRequest);
	}

	public PostClientClientIdImagesResponse uploadImageDataUri(String customerId, String dataUri) {

		return new PostClientClientIdImagesResponse();
	}

	public PostClientClientIdImagesResponse uploadImageFile(String customerId, MultipartFile file) {

		return new PostClientClientIdImagesResponse();
	}
}
