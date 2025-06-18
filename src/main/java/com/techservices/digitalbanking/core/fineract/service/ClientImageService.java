/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.fineract.api.ClientApiClient;
import com.techservices.digitalbanking.core.fineract.model.request.PutClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientAddressTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientClientIdImagesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutClientClientIdAddressesResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientImageService {

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

	public PostClientClientIdImagesResponse uploadImageDataUri(String customerId, String dataUri) {
		return clientApiClient.uploadImageDataUri(customerId, dataUri);
	}

	public PostClientClientIdImagesResponse uploadImageFile(String customerId, MultipartFile file) {
		return clientApiClient.uploadImageFile(customerId, file);
	}
}
