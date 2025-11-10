package com.techservices.digitalbanking.loan.service;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanDocumentUploadRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanDocumentBatchUploadResponse;

import java.util.List;

public interface LoanDocumentService {
    GenericApiResponse uploadDocument(Long loanId, LoanDocumentUploadRequest request, Long customerId);

    LoanDocumentBatchUploadResponse uploadDocuments(Long loanId, List<LoanDocumentUploadRequest> requests, Long customerId);
}
