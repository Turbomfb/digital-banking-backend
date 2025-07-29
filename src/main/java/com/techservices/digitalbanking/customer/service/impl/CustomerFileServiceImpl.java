/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.fineract.api.ClientApiClient;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientClientIdImagesResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientImageService;
import com.techservices.digitalbanking.customer.service.CustomerFileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerFileServiceImpl implements CustomerFileService {

	private final ClientImageService clientImageService;
	private final CustomerService customerService;
	private final ClientApiClient clientApiClient;

	@Override
	public PostClientClientIdImagesResponse uploadImage(String imageType, String dataUri, MultipartFile file,
			String customerId) {
		if ("dataUri".equalsIgnoreCase(imageType)) {
			if (dataUri != null && !dataUri.isBlank()) {
				return clientImageService.uploadImageDataUri(customerId, dataUri);
			} else {
				throw new ValidationException("Data URI is required");
			}
		} else if ("file".equalsIgnoreCase(imageType)) {
			if (file != null && !file.isEmpty()) {
				return clientImageService.uploadImageFile(customerId, file);
			} else {
				throw new ValidationException("Image file is required");
			}
		} else {
			throw new ValidationException("Invalid image type");
		}
	}

	@Override
	public GenericApiResponse uploadDocument(Long customerId, String name, String description, MultipartFile file) {
		Customer customer = customerService.getCustomerById(customerId);
		System.err.println("Requests data: " + customer.getExternalId() + ", " + name + ", " + description);
		clientApiClient.uploadDocument(customer.getExternalId(), name, description, file);
		return GenericApiResponse.builder().message("Upload successful").build();
	}
}
