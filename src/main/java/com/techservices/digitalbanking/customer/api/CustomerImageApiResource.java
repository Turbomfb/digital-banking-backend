/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.fineract.model.response.PostClientClientIdImagesResponse;
import com.techservices.digitalbanking.customer.service.CustomerImageService;

import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/customers/{customerId}")
@RestController
@RequiredArgsConstructor
public class CustomerImageApiResource {

	private final CustomerImageService customerImageService;

	@PostMapping(value = "images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PostClientClientIdImagesResponse> uploadImageDataUri(
			@RequestBody(required = false) String dataUri, @RequestBody(required = false) MultipartFile file,
			@PathVariable String customerId, @RequestParam(value = "imageType") String imageType) {
		return ResponseEntity.ok(customerImageService.uploadImage(imageType, dataUri, file, customerId));
	}
}
