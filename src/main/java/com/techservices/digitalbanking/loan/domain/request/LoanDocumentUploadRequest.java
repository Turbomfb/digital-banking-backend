/* (C)2025 */
package com.techservices.digitalbanking.loan.domain.request;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.exception.ValidationException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = "file")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDocumentUploadRequest {
	private MultipartFile file;
	private String name;
	private String description;

	public void validate() {

		if (file == null || file.isEmpty()) {
			throw new ValidationException("file.cannot.be.empty", "File cannot be empty");
		}

		if (StringUtils.isBlank(name)) {
			throw new ValidationException("document.name.cannot.be.empty", "Document name cannot be empty");
		}

		// Validate file size (e.g., max 10MB)
		long maxFileSize = 10 * 1024 * 1024; // 10MB
		if (file.getSize() > maxFileSize) {
			throw new ValidationException("file.size.exceeds.limit", "File size exceeds maximum allowed size of 10MB");
		}

		// Validate file type
		String contentType = file.getContentType();
		if (contentType == null || !isAllowedContentType(contentType)) {
			throw new ValidationException("invalid.file.type",
					"Invalid file type. Allowed types: PDF, PNG, JPG, JPEG, DOC, DOCX");
		}
	}

	private boolean isAllowedContentType(String contentType) {

		return contentType.equals("application/pdf") || contentType.equals("image/png")
				|| contentType.equals("image/jpeg") || contentType.equals("image/jpg")
				|| contentType.equals("application/msword")
				|| contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	}

	public String getOriginalFilename() {

		return file != null ? file.getOriginalFilename() : null;
	}

	public String getContentType() {

		return file != null ? file.getContentType() : null;
	}

	public long getFileSize() {

		return file != null ? file.getSize() : 0;
	}
}
