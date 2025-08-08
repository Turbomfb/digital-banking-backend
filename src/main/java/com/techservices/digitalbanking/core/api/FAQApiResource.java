package com.techservices.digitalbanking.core.api;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.FAQDto;
import com.techservices.digitalbanking.core.domain.dto.request.CreateFAQRequest;
import com.techservices.digitalbanking.core.domain.enums.ProductType;
import com.techservices.digitalbanking.core.service.FAQService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faqs")
@RequiredArgsConstructor
public class FAQApiResource {

    private final FAQService faqService;

    @GetMapping
    public ResponseEntity<BasePageResponse<FAQDto>> getAllFAQs(
            @RequestParam(required = false) ProductType product,
            @RequestParam(required = false) Boolean webEnable,
            @RequestParam(required = false) Boolean mobileEnable
    ) {

        BasePageResponse<FAQDto> faqs;
        if (product != null || webEnable != null || mobileEnable != null) {
            faqs = faqService.searchFAQs(product, webEnable, mobileEnable);
        } else {
            faqs = faqService.getAllActiveFAQs();
        }

        return ResponseEntity.ok(faqs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FAQDto> getFAQById(@PathVariable Long id) {
        return faqService.getFAQById(id)
                .map(faq -> ResponseEntity.ok(faq))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{product}")
    public ResponseEntity<BasePageResponse<FAQDto>> getFAQsByProduct(@PathVariable ProductType product) {
        BasePageResponse<FAQDto> faqs = faqService.getFAQsByProduct(product);
        return ResponseEntity.ok(faqs);
    }

    @GetMapping("/web-enabled")
    public ResponseEntity<BasePageResponse<FAQDto>> getWebEnabledFAQs() {
        BasePageResponse<FAQDto> faqs = faqService.getWebEnabledFAQs();
        return ResponseEntity.ok(faqs);
    }

    @GetMapping("/mobile-enabled")
    public ResponseEntity<BasePageResponse<FAQDto>> getMobileEnabledFAQs() {
        BasePageResponse<FAQDto> faqs = faqService.getMobileEnabledFAQs();
        return ResponseEntity.ok(faqs);
    }

    @PostMapping
    public ResponseEntity<FAQDto> createFAQ(@Valid @RequestBody CreateFAQRequest request) {
        FAQDto createdFAQ = faqService.createFAQ(request);
        return new ResponseEntity<>(createdFAQ, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FAQDto> updateFAQ(@PathVariable Long id,
                                            @Valid @RequestBody CreateFAQRequest request) {
        try {
            FAQDto updatedFAQ = faqService.updateFAQ(id, request);
            return ResponseEntity.ok(updatedFAQ);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFAQ(@PathVariable Long id) {
        try {
            faqService.deleteFAQ(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/soft-delete")
    public ResponseEntity<Void> softDeleteFAQ(@PathVariable Long id) {
        try {
            faqService.softDeleteFAQ(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

