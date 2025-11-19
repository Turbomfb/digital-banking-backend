/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.data.model.IndustrySector;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCodeValuesDataResponse {

  private Long id;
  private String description;
  private String name;
  private Long position;
  private boolean active;
  private boolean mandatory;

  public static GetCodeValuesDataResponse parse(IndustrySector industry) {

    GetCodeValuesDataResponse getCodeValuesDataResponse = new GetCodeValuesDataResponse();
    getCodeValuesDataResponse.setDescription(industry.getDescription());
    getCodeValuesDataResponse.setId(industry.getId());
    getCodeValuesDataResponse.setName(industry.getName());
    getCodeValuesDataResponse.setPosition(industry.getPosition());
    getCodeValuesDataResponse.setActive(industry.isActive());
    getCodeValuesDataResponse.setMandatory(industry.isMandatory());
    return getCodeValuesDataResponse;
  }
}
