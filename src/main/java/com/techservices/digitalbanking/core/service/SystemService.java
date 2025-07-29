package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.fineract.api.CodeApiClient;
import com.techservices.digitalbanking.core.fineract.api.SystemApiClient;
import com.techservices.digitalbanking.core.fineract.model.response.GetCodeValuesDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final CodeApiClient codeApi;
    private final SystemApiClient systemApiClient;


    public BasePageResponse<GetCodeValuesDataResponse> retrieveCodeValuesByCodeId(Long codeId) {
        return BasePageResponse.instance(codeApi.retrieveAllCodeValues(codeId));
    }

    public GetCodeValuesDataResponse retrieveCodeValueDetails(Long codeId, Long codeValueId) {
        return codeApi.retrieveCodeValue(codeValueId, codeId);
    }

    public Object healthCheck() {
        return systemApiClient.healthCheck();
    }

}
