/* (C)2025 */
package com.techservices.digitalbanking.core.api;

import com.techservices.digitalbanking.core.domain.data.model.BusinessCategory;
import com.techservices.digitalbanking.core.domain.data.model.BusinessCategoryItem;
import com.techservices.digitalbanking.core.domain.data.repository.BusinessCategoryItemRepository;
import com.techservices.digitalbanking.core.domain.data.repository.BusinessCategoryRepository;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/business-categories")
@RequiredArgsConstructor
@Slf4j
@Tag(
    name = "Business Categories Admin",
    description = "Admin endpoints for managing business categories")
public class BusinessCategoryAdminApiResource {

  private final BusinessCategoryRepository businessCategoryRepository;
  private final BusinessCategoryItemRepository businessCategoryItemRepository;

  @PutMapping("/{categoryId}/activate")
  @Operation(summary = "Activate a category", description = "Activate a business category")
  public ResponseEntity<GenericApiResponse> activateCategory(@PathVariable Long categoryId) {

    log.info("PUT /api/v1/admin/business-categories/{}/activate", categoryId);
    BusinessCategory category =
        businessCategoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));
    category.setIsActive(true);
    businessCategoryRepository.save(category);
    return ResponseEntity.ok(
        new GenericApiResponse("Category activated successfully", "success", null));
  }

  @PutMapping("/{categoryId}/deactivate")
  @Operation(summary = "Deactivate a category", description = "Deactivate a business category")
  public ResponseEntity<GenericApiResponse> deactivateCategory(@PathVariable Long categoryId) {

    log.info("PUT /api/v1/admin/business-categories/{}/deactivate", categoryId);
    BusinessCategory category =
        businessCategoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found"));
    category.setIsActive(false);
    businessCategoryRepository.save(category);
    return ResponseEntity.ok(
        new GenericApiResponse("Category deactivated successfully", "success", null));
  }

  @PutMapping("/items/{itemId}/activate")
  @Operation(
      summary = "Activate a category item",
      description = "Activate a business category item")
  public ResponseEntity<GenericApiResponse> activateCategoryItem(@PathVariable Long itemId) {

    log.info("PUT /api/v1/admin/business-categories/items/{}/activate", itemId);
    BusinessCategoryItem item =
        businessCategoryItemRepository
            .findById(itemId)
            .orElseThrow(() -> new RuntimeException("Category item not found"));
    item.setIsActive(true);
    businessCategoryItemRepository.save(item);
    return ResponseEntity.ok(
        new GenericApiResponse("Category item activated successfully", "success", null));
  }

  @PutMapping("/items/{itemId}/deactivate")
  @Operation(
      summary = "Deactivate a category item",
      description = "Deactivate a business category item")
  public ResponseEntity<GenericApiResponse> deactivateCategoryItem(@PathVariable Long itemId) {
    log.info("PUT /api/v1/admin/business-categories/items/{}/deactivate", itemId);
    BusinessCategoryItem item =
        businessCategoryItemRepository
            .findById(itemId)
            .orElseThrow(() -> new RuntimeException("Category item not found"));
    item.setIsActive(false);
    businessCategoryItemRepository.save(item);
    return ResponseEntity.ok(
        new GenericApiResponse("Category item deactivated successfully", "success", null));
  }
}
