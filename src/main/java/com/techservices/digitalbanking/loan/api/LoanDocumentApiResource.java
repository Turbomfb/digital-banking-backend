package com.techservices.digitalbanking.loan.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.loan.domain.request.LoanDocumentUploadRequest;
import com.techservices.digitalbanking.loan.domain.response.LoanDocumentBatchUploadResponse;
import com.techservices.digitalbanking.loan.service.LoanDocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/me/loans/{loanId}/documents")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Loan Documents", description = "Loan document management endpoints")
public class LoanDocumentApiResource {

    private final LoanDocumentService loanDocumentService;
    private final SpringSecurityAuditorAware springSecurityAuditorAware;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Upload single loan document",
            description = "Upload a single document for a loan application"
    )
    public ResponseEntity<GenericApiResponse> uploadDocument(
            @Parameter(description = "Loan ID", required = true)
            @PathVariable Long loanId,
            @Parameter(description = "Document file", required = true)
            @RequestPart("file") MultipartFile file,
            @Parameter(description = "Document name", required = true)
            @RequestPart("name") String name,
            @Parameter(description = "Document description")
            @RequestPart(value = "description", required = false) String description
    ) {

        log.info("POST /api/v1/loans/{}/documents - Uploading single document: {}", loanId, name);

        LoanDocumentUploadRequest request = new LoanDocumentUploadRequest();
        request.setFile(file);
        request.setName(name);
        request.setDescription(description);
        request.validate();
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();

        GenericApiResponse response = loanDocumentService.uploadDocument(loanId, request, customerId);

        return ResponseEntity.ok(new GenericApiResponse(
                "Document uploaded successfully",
                "success",
                response
        ));
    }

    @PostMapping(value = "/batch", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Upload multiple loan documents",
            description = "Upload multiple documents for a loan application in a single request"
    )
    public ResponseEntity<GenericApiResponse> uploadDocuments(
            @Parameter(description = "Loan ID", required = true)
            @PathVariable Long loanId,
            @Parameter(description = "Array of document files", required = true)
            @RequestParam("files") List<MultipartFile> files,
            @Parameter(description = "Array of document names (must match files array length)", required = true)
            @RequestParam("names") List<String> names,
            @Parameter(description = "Array of document descriptions")
            @RequestParam(value = "descriptions", required = false) List<String> descriptions) {

        log.info("POST /api/v1/loans/{}/documents/batch - Uploading {} documents", loanId, files.size());

        if (files.size() != names.size()) {
            return ResponseEntity.badRequest().body(new GenericApiResponse(
                    "Files and names arrays must have the same length",
                    "error",
                    null
            ));
        }

        List<LoanDocumentUploadRequest> requests = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            LoanDocumentUploadRequest request = new LoanDocumentUploadRequest();
            request.setFile(files.get(i));
            request.setName(names.get(i));
            request.setDescription(descriptions != null && i < descriptions.size() ? descriptions.get(i) : null);
            requests.add(request);
        }

        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        LoanDocumentBatchUploadResponse response = loanDocumentService.uploadDocuments(loanId, requests, customerId);

        String message = String.format("Batch upload completed. %d successful, %d failed",
                response.getSuccessfulUploads(), response.getFailedUploads());

        return ResponseEntity.ok(new GenericApiResponse(
                message,
                response.getFailedUploads() > 0 ? "partial_success" : "success",
                response
        ));
    }
}
