package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.domain.data.model.FAQ;
import com.techservices.digitalbanking.core.domain.data.repository.FAQRepository;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.FAQDto;
import com.techservices.digitalbanking.core.domain.dto.request.CreateFAQRequest;
import com.techservices.digitalbanking.core.domain.enums.ProductType;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

        @Transactional(readOnly = true)
    public BasePageResponse<FAQDto> getAllActiveFAQs() {
        return BasePageResponse.instance(
                faqRepository.findByDelFlgFalse()
                .stream()
                .map(FAQDto::new)
                .collect(Collectors.toList())
        );
    }

        @Transactional(readOnly = true)
    public BasePageResponse<FAQDto> getFAQsByProduct(ProductType product) {
        return BasePageResponse.instance(
                faqRepository.findByProductAndDelFlgFalse(product)
                .stream()
                .map(FAQDto::new)
                .collect(Collectors.toList())
        );
    }

        @Transactional(readOnly = true)
    public BasePageResponse<FAQDto> getWebEnabledFAQs() {
        return BasePageResponse.instance(
                faqRepository.findByWebEnableTrueAndDelFlgFalse()
                .stream()
                .map(FAQDto::new)
                .collect(Collectors.toList())
        );
    }

        @Transactional(readOnly = true)
    public BasePageResponse<FAQDto> getMobileEnabledFAQs() {
        return BasePageResponse.instance(
                faqRepository.findByMobileEnableTrueAndDelFlgFalse()
                .stream()
                .map(FAQDto::new)
                .collect(Collectors.toList())
        );
    }

        @Transactional(readOnly = true)
    public BasePageResponse<FAQDto> searchFAQs(ProductType product, Boolean webEnable, Boolean mobileEnable) {
        return BasePageResponse.instance(
                faqRepository.findByFilters(product, webEnable, mobileEnable)
                .stream()
                .map(FAQDto::new)
                .collect(Collectors.toList())
        );
    }

        @Transactional(readOnly = true)
    public Optional<FAQDto> getFAQById(Long id) {
        return faqRepository.findById(id)
                .filter(faq -> !faq.getDelFlg())
                .map(FAQDto::new);
    }

        public FAQDto createFAQ(CreateFAQRequest request) {
        FAQ faq = new FAQ();
        faq.setQuestion(request.getQuestion());
        faq.setAnswer(request.getAnswer());
        faq.setProduct(request.getProduct());
        faq.setWebEnable(request.getWebEnable());
        faq.setMobileEnable(request.getMobileEnable());
        faq.setLaunchDate(request.getLaunchDate());
        faq.setDelFlg(false);

        FAQ savedFAQ = faqRepository.save(faq);
        return new FAQDto(savedFAQ);
    }

        public FAQDto updateFAQ(Long id, CreateFAQRequest request) {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found with id: " + id));

        if (faq.getDelFlg()) {
            throw new RuntimeException("Cannot update deleted FAQ");
        }

        faq.setQuestion(request.getQuestion());
        faq.setAnswer(request.getAnswer());
        faq.setProduct(request.getProduct());
        faq.setWebEnable(request.getWebEnable());
        faq.setMobileEnable(request.getMobileEnable());
        faq.setLaunchDate(request.getLaunchDate());

        FAQ updatedFAQ = faqRepository.save(faq);
        return new FAQDto(updatedFAQ);
    }

        public void deleteFAQ(Long id) {
        faqRepository.deleteById(id);
    }

        public void softDeleteFAQ(Long id) {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found with id: " + id));
        faq.setDelFlg(true);
        faqRepository.save(faq);
    }
}
