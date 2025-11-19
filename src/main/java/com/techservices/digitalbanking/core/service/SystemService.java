/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.domain.data.repository.IndustrySectorRepository;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetCodeValuesDataResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemService {
  private final IndustrySectorRepository industrySectorRepository;

  public BasePageResponse<GetCodeValuesDataResponse> retrieveCodeValuesByCodeId(Long codeId) {

    List<GetCodeValuesDataResponse> codeValues = List.of();
    if (codeId == 25) {
      codeValues =
          industrySectorRepository.findAll().stream()
              .map(GetCodeValuesDataResponse::parse)
              .toList();
    }
    return BasePageResponse.instance(codeValues);
  }
}
