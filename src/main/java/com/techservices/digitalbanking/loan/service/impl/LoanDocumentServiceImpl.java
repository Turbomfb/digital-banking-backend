package com.techservices.digitalbanking.loan.service.impl;

import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.eBanking.service.LoanService;
import com.techservices.digitalbanking.loan.domain.request.LoanDocumentUploadRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanDocumentBatchUploadResponse;
import com.techservices.digitalbanking.loan.service.LoanDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanDocumentServiceImpl implements LoanDocumentService {
    private final LoanService loanService;

    @Override
    public GenericApiResponse uploadDocument(Long loanId, LoanDocumentUploadRequest request, Long customerId) {
        loanService.retrieveLoanById(loanId, customerId);
        return uploadDocument(loanId, request);
    }

    private GenericApiResponse uploadDocument(Long loanId, LoanDocumentUploadRequest request) {
        loanService.uploadDocument(loanId, request);
        return new GenericApiResponse("Document uploaded successfully", "success", null);
    }

    @Override
    public LoanDocumentBatchUploadResponse uploadDocuments(Long loanId, List<LoanDocumentUploadRequest> requests, Long customerId) {
        LoanDocumentBatchUploadResponse batchUploadResponse = null;
        loanService.retrieveLoanById(loanId, customerId);
        batchUploadResponse = new LoanDocumentBatchUploadResponse();
        batchUploadResponse.setTotalDocuments(requests.size());
        for (LoanDocumentUploadRequest request : requests) {
            try {
                loanService.uploadDocument(loanId, request);
                batchUploadResponse.setSuccessfulUploads(batchUploadResponse.getSuccessfulUploads() + 1);
            } catch (Exception e) {
                LoanDocumentBatchUploadResponse.UploadError error = new LoanDocumentBatchUploadResponse.UploadError();
                error.setErrorMessage(e.getMessage());
                error.setDocumentName(request.getName());
                error.setFileName(request.getFile().getOriginalFilename());
                batchUploadResponse.addError(error.getDocumentName(), error.getFileName(), error.getErrorMessage(), "UPLOAD_ERROR");
                batchUploadResponse.setFailedUploads(batchUploadResponse.getFailedUploads() + 1);
            }
        }
        return batchUploadResponse;
    }
}
