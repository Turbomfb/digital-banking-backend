/* (C)2025 */
package com.techservices.digitalbanking.core.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BusinessCategoryItemResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BusinessCategoryResponse;
import com.techservices.digitalbanking.core.domain.enums.BusinessCategoryType;
import com.techservices.digitalbanking.core.service.BusinessCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/business-categories")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Business Categories", description = "Business category and sector management endpoints")
public class BusinessCategoryApiResource {

	private final BusinessCategoryService businessCategoryService;

	@GetMapping
	@Operation(summary = "Get all business categories", description = "Retrieve all active business categories")
	public ResponseEntity<BasePageResponse<BusinessCategoryResponse>> getAllCategories(
			@Parameter(description = "Include category items in response") @RequestParam(defaultValue = "false") boolean includeItems) {

		log.info("GET /api/v1/business-categories - includeItems: {}", includeItems);
		List<BusinessCategoryResponse> categories = businessCategoryService.getAllCategories(includeItems);
		return ResponseEntity.ok(BasePageResponse.instance(categories));
	}

	@GetMapping("/type/{categoryType}")
	@Operation(summary = "Get categories by type", description = "Retrieve business categories by type (PRIVATE_SECTOR or PUBLIC_SECTOR)")
	public ResponseEntity<BusinessCategoryResponse> getCategoriesByType(@PathVariable BusinessCategoryType categoryType,
			@Parameter(description = "Include category items in response") @RequestParam(defaultValue = "false") boolean includeItems) {

		log.info("GET /api/v1/business-categories/type/{} - includeItems: {}", categoryType, includeItems);
		BusinessCategoryResponse categories = businessCategoryService.getCategoriesByType(categoryType, includeItems);
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/private-sector")
	@Operation(summary = "Get private sector categories", description = "Retrieve all private sector categories")
	public ResponseEntity<BusinessCategoryResponse> getPrivateSectorCategories(
			@Parameter(description = "Include category items in response") @RequestParam(defaultValue = "false") boolean includeItems) {

		log.info("GET /api/v1/business-categories/private-sector - includeItems: {}", includeItems);
		BusinessCategoryResponse categories = businessCategoryService.getPrivateSectorCategories(includeItems);
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/public-sector")
	@Operation(summary = "Get public sector categories", description = "Retrieve all public sector categories")
	public ResponseEntity<BusinessCategoryResponse> getPublicSectorCategories(
			@Parameter(description = "Include category items in response") @RequestParam(defaultValue = "false") boolean includeItems) {

		log.info("GET /api/v1/business-categories/public-sector - includeItems: {}", includeItems);
		BusinessCategoryResponse categories = businessCategoryService.getPublicSectorCategories(includeItems);
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get category by ID", description = "Retrieve a business category by its ID")
	public ResponseEntity<BusinessCategoryResponse> getCategoryById(@PathVariable Long id,
			@Parameter(description = "Include category items in response") @RequestParam(defaultValue = "false") boolean includeItems) {

		log.info("GET /api/v1/business-categories/{} - includeItems: {}", id, includeItems);
		BusinessCategoryResponse category = businessCategoryService.getCategoryById(id, includeItems);
		return ResponseEntity.ok(category);
	}

	@GetMapping("/code/{code}")
	@Operation(summary = "Get category by code", description = "Retrieve a business category by its code")
	public ResponseEntity<BusinessCategoryResponse> getCategoryByCode(@PathVariable String code,
			@Parameter(description = "Include category items in response") @RequestParam(defaultValue = "false") boolean includeItems) {

		log.info("GET /api/v1/business-categories/code/{} - includeItems: {}", code, includeItems);
		BusinessCategoryResponse category = businessCategoryService.getCategoryByCode(code, includeItems);
		return ResponseEntity.ok(category);
	}

	@GetMapping("/{categoryId}/items")
	@Operation(summary = "Get category items", description = "Retrieve all items for a specific category")
	public ResponseEntity<BasePageResponse<BusinessCategoryItemResponse>> getCategoryItems(
			@PathVariable Long categoryId) {

		log.info("GET /api/v1/business-categories/{}/items", categoryId);
		List<BusinessCategoryItemResponse> items = businessCategoryService.getCategoryItems(categoryId);
		return ResponseEntity.ok(BasePageResponse.instance(items));
	}

	@GetMapping("/{categoryCode}/items/{itemCode}")
	@Operation(summary = "Get category item by code", description = "Retrieve a specific item by category code and item code")
	public ResponseEntity<BusinessCategoryItemResponse> getCategoryItemByCode(@PathVariable String categoryCode,
			@PathVariable String itemCode) {

		log.info("GET /api/v1/business-categories/{}/items/{}", categoryCode, itemCode);
		BusinessCategoryItemResponse item = businessCategoryService.getCategoryItemByCode(categoryCode, itemCode);
		return ResponseEntity.ok(item);
	}
}
