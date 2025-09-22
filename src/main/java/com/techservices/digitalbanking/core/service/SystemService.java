package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.domain.data.repository.IndustrySectorRepository;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.eBanking.api.CodeApiClient;
import com.techservices.digitalbanking.core.eBanking.api.SystemApiClient;
import com.techservices.digitalbanking.core.eBanking.model.response.GetCodeValuesDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final CodeApiClient codeApi;
    private final SystemApiClient systemApiClient;
    private final IndustrySectorRepository industrySectorRepository;


    public BasePageResponse<GetCodeValuesDataResponse> retrieveCodeValuesByCodeId(Long codeId) {
        List<GetCodeValuesDataResponse> codeValues = List.of();
        if (codeId == 25) {
            codeValues = industrySectorRepository.findAll().stream().map(GetCodeValuesDataResponse::parse).toList();
        }
        return BasePageResponse.instance(codeValues);
    }

    public GetCodeValuesDataResponse retrieveCodeValueDetails(Long codeId, Long codeValueId) {
        return codeApi.retrieveCodeValue(codeValueId, codeId);
    }

    public Object healthCheck() {
        return systemApiClient.healthCheck();
    }

}
