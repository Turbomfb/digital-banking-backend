/* (C)2025 */
package com.techservices.digitalbanking.loan.domain.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanDocumentBatchUploadResponse {
	private int totalDocuments;
	private int successfulUploads;
	private int failedUploads;
	private List<UploadError> errors;

	@Setter
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class UploadError {
		private String documentName;
		private String fileName;
		private String errorMessage;
		private String errorCode;
	}

	public void addError(String documentName, String fileName, String errorMessage, String errorCode) {

		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(new UploadError(documentName, fileName, errorMessage, errorCode));
	}
}
