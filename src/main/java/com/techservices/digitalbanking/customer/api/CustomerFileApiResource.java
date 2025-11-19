/* (C)2024 */
package com.techservices.digitalbanking.customer.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostClientClientIdImagesResponse;
import com.techservices.digitalbanking.customer.service.CustomerFileService;

import lombok.RequiredArgsConstructor;

@RequestMapping("api/v1/customers/me/files")
@RestController
@RequiredArgsConstructor
public class CustomerFileApiResource {

	private final CustomerFileService customerFileService;
	private final SpringSecurityAuditorAware springSecurityAuditorAware;

	@PostMapping(value = "images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PostClientClientIdImagesResponse> uploadImageDataUri(
			@RequestBody(required = false) String dataUri, @RequestBody(required = false) MultipartFile file,
			@PathVariable String customerId, @RequestParam(value = "imageType") String imageType) {

		return ResponseEntity.ok(customerFileService.uploadImage(imageType, dataUri, file, customerId));
	}

	@PostMapping(value = "/upload-document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<GenericApiResponse> uploadDocument(@RequestParam("name") String name,
			@RequestParam("description") String description, @RequestBody MultipartFile file) {

		Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
		GenericApiResponse response = customerFileService.uploadDocument(customerId, name, description, file);
		return ResponseEntity.ok(response);
	}
}
