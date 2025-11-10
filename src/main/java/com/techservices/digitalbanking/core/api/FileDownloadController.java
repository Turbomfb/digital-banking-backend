package com.techservices.digitalbanking.core.api;

import com.techservices.digitalbanking.core.service.FileDownloadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "File Download", description = "File download endpoints")
public class FileDownloadController {

    private final FileDownloadService fileDownloadService;

    @GetMapping("/guarantor-form")
    @Operation(summary = "Download guarantor form", description = "Download the KKU guarantor form PDF")
    public ResponseEntity<Resource> downloadGuarantorForm(
            @RequestParam(defaultValue = "false") boolean inline) {
        log.info("GET /api/v1/files/guarantor-form - inline: {}", inline);

        Resource resource = fileDownloadService.getGuarantorForm();
        String filename = "KKU_Guarantor_Form.pdf";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        (inline ? "inline" : "attachment") + "; filename=\"" + filename + "\"")
                .body(resource);
    }

    @GetMapping("/download")
    @Operation(summary = "Download file by name", description = "Download any file from the files directory")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam String fileName,
            @RequestParam(defaultValue = "false") boolean inline) {
        log.info("GET /api/v1/files/download - fileName: {}, inline: {}", fileName, inline);

        Resource resource = fileDownloadService.getFile(fileName);
        MediaType mediaType = fileDownloadService.getMediaType(fileName);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        (inline ? "inline" : "attachment") + "; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
