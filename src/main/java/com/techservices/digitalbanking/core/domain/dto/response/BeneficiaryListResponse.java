/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiaryListResponse {

  private List<BeneficiaryResponse> beneficiaries;
  private long totalCount;
  private long activeCount;

  public BeneficiaryListResponse(
      List<BeneficiaryResponse> beneficiaries, long totalCount, long activeCount) {

    this.beneficiaries = beneficiaries;
    this.totalCount = totalCount;
    this.activeCount = activeCount;
  }
}
