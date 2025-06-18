/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientClientIdImagesResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientImageService;
import com.techservices.digitalbanking.customer.service.CustomerImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerImageServiceImpl implements CustomerImageService {

	private final ClientImageService clientImageService;

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
}
