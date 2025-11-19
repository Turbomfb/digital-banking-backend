/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.data.model.BusinessCategoryItem;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessCategoryItemResponse {
  private Long id;
  private String name;
  private String code;
  private String description;
  private Integer displayOrder;
  private Boolean isActive;
  private Long categoryId;
  private String categoryName;

  public static BusinessCategoryItemResponse from(BusinessCategoryItem item) {

    BusinessCategoryItemResponse response = new BusinessCategoryItemResponse();
    response.setId(item.getId());
    response.setName(item.getName());
    response.setCode(item.getCode());
    response.setDescription(item.getDescription());
    response.setDisplayOrder(item.getDisplayOrder());
    response.setIsActive(item.getIsActive());

    if (item.getCategory() != null) {
      response.setCategoryId(item.getCategory().getId());
      response.setCategoryName(item.getCategory().getName());
    }

    return response;
  }
}
