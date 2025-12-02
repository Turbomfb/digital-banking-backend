/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.data.model.BusinessCategory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessCategoryResponse {
	private Long id;
	private String name;
	private String code;
	private String description;
	private String categoryType;
	private Integer displayOrder;
	private Boolean isActive;
	private List<BusinessCategoryItemResponse> items;

	public static BusinessCategoryResponse from(BusinessCategory category, boolean includeItems) {

		BusinessCategoryResponse response = new BusinessCategoryResponse();
		response.setId(category.getId());
		response.setName(category.getName());
		response.setCode(category.getCode());
		response.setDescription(category.getDescription());
		response.setCategoryType(category.getCategoryType().name());
		response.setDisplayOrder(category.getDisplayOrder());
		response.setIsActive(category.getIsActive());

		if (includeItems && category.getItems() != null) {
			response.setItems(
					category.getItems().stream().map(BusinessCategoryItemResponse::from).collect(Collectors.toList()));
		}

		return response;
	}
}
