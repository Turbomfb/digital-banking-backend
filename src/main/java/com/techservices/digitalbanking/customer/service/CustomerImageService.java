/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service;

import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.fineract.model.response.PostClientClientIdImagesResponse;

public interface CustomerImageService {

	PostClientClientIdImagesResponse uploadImage(String imageType, String dataUri, MultipartFile file,
			String customerId);
}
