package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiaryListResponse {

  private List<BeneficiaryResponse> beneficiaries;
  private Integer totalCount;
  private Integer activeCount;

  public BeneficiaryListResponse(List<BeneficiaryResponse> beneficiaries, Integer totalCount, Integer activeCount) {
    this.beneficiaries = beneficiaries;
    this.totalCount = totalCount;
    this.activeCount = activeCount;
  }
}