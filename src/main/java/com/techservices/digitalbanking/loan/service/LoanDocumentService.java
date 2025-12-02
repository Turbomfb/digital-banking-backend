/* (C)2025 */
package com.techservices.digitalbanking.loan.service;

import java.util.List;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanDocumentUploadRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanDocumentBatchUploadResponse;

public interface LoanDocumentService {
	GenericApiResponse uploadDocument(Long loanId, LoanDocumentUploadRequest request, Long customerId);

	LoanDocumentBatchUploadResponse uploadDocuments(Long loanId, List<LoanDocumentUploadRequest> requests,
			Long customerId);
}
