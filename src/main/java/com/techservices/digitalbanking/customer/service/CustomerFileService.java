/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.service;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.eBanking.model.response.PostClientClientIdImagesResponse;

public interface CustomerFileService {

	PostClientClientIdImagesResponse uploadImage(String imageType, String dataUri, MultipartFile file,
			String customerId);

	GenericApiResponse uploadDocument(Long customerId, String name, String description, MultipartFile file);
}
