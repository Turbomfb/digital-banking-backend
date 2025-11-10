package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.domain.data.model.BusinessCategory;
import com.techservices.digitalbanking.core.domain.data.model.BusinessCategoryItem;
import com.techservices.digitalbanking.core.domain.data.repository.BusinessCategoryItemRepository;
import com.techservices.digitalbanking.core.domain.data.repository.BusinessCategoryRepository;
import com.techservices.digitalbanking.core.domain.dto.response.BusinessCategoryItemResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BusinessCategoryResponse;
import com.techservices.digitalbanking.core.domain.enums.BusinessCategoryType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessCategoryService {

    private final BusinessCategoryRepository businessCategoryRepository;
    private final BusinessCategoryItemRepository businessCategoryItemRepository;

    @Transactional(readOnly = true)
    public List<BusinessCategoryResponse> getAllCategories(boolean includeItems) {
        log.info("Fetching all business categories, includeItems: {}", includeItems);
        List<BusinessCategory> categories = businessCategoryRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
        return categories.stream()
                .map(category -> BusinessCategoryResponse.from(category, includeItems))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BusinessCategoryResponse getCategoriesByType(BusinessCategoryType categoryType, boolean includeItems) {
        log.info("Fetching business categories by type: {}, includeItems: {}", categoryType, includeItems);
        return businessCategoryRepository.findByCategoryTypeAndIsActiveTrueOrderByDisplayOrderAsc(categoryType)
                .map(category -> BusinessCategoryResponse.from(category, includeItems))
                .orElseThrow(
                        () -> new ValidationException("category.type.not.found", "Business category type not found")
                );
    }

    @Transactional(readOnly = true)
    public BusinessCategoryResponse getCategoryById(Long id, boolean includeItems) {
        log.info("Fetching business category by id: {}, includeItems: {}", id, includeItems);
        BusinessCategory category = includeItems
                ? businessCategoryRepository.findByIdWithItems(id)
                .orElseThrow(() -> new ValidationException("category.not.found", "Business category not found"))
                : businessCategoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("category.not.found", "Business category not found"));

        return BusinessCategoryResponse.from(category, includeItems);
    }

    @Transactional(readOnly = true)
    public BusinessCategoryResponse getCategoryByCode(String code, boolean includeItems) {
        log.info("Fetching business category by code: {}, includeItems: {}", code, includeItems);
        BusinessCategory category = includeItems
                ? businessCategoryRepository.findByCodeWithItems(code)
                .orElseThrow(() -> new ValidationException("category.not.found", "Business category not found"))
                : businessCategoryRepository.findByCode(code)
                .orElseThrow(() -> new ValidationException("category.not.found", "Business category not found"));

        return BusinessCategoryResponse.from(category, includeItems);
    }

    @Transactional(readOnly = true)
    public List<BusinessCategoryItemResponse> getCategoryItems(Long categoryId) {
        log.info("Fetching items for category id: {}", categoryId);

        if (!businessCategoryRepository.existsById(categoryId)) {
            throw new ValidationException("category.not.found", "Business category not found");
        }

        List<BusinessCategoryItem> items = businessCategoryItemRepository.findByCategoryIdAndIsActiveTrueOrderByDisplayOrderAsc(categoryId);
        return items.stream()
                .map(BusinessCategoryItemResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BusinessCategoryItemResponse getCategoryItemByCode(String categoryCode, String itemCode) {
        log.info("Fetching category item by categoryCode: {}, itemCode: {}", categoryCode, itemCode);
        BusinessCategoryItem item = businessCategoryItemRepository.findByCategoryCodeAndItemCode(categoryCode, itemCode)
                .orElseThrow(() -> new ValidationException("category.item.not.found", "Business category item not found"));

        return BusinessCategoryItemResponse.from(item);
    }

    @Transactional(readOnly = true)
    public BusinessCategoryResponse getPrivateSectorCategories(boolean includeItems) {
        return getCategoriesByType(BusinessCategoryType.PRIVATE_SECTOR, includeItems);
    }

    @Transactional(readOnly = true)
    public BusinessCategoryResponse getPublicSectorCategories(boolean includeItems) {
        return getCategoriesByType(BusinessCategoryType.PUBLIC_SECTOR, includeItems);
    }
}
