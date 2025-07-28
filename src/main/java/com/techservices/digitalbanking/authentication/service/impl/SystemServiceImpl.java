package com.techservices.digitalbanking.authentication.service.impl;

import com.techservices.digitalbanking.authentication.service.SystemService;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.api.CodeApiClient;
import com.techservices.digitalbanking.core.fineract.model.response.GetCodeValuesDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {
    private final CodeApiClient codeApi;


    @Override
    public BasePageResponse<GetCodeValuesDataResponse> retrieveCodeValuesByCodeId(Long codeId) {
        return BasePageResponse.instance(codeApi.retrieveAllCodeValues(codeId));
    }

    @Override
    public GetCodeValuesDataResponse retrieveCodeValueDetails(Long codeId, Long codeValueId) {
        return codeApi.retrieveCodeValue(codeValueId, codeId);
    }
}
