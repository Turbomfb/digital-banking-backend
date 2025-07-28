package com.techservices.digitalbanking.authentication.service;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetCodeValuesDataResponse;

public interface SystemService {
    BasePageResponse<GetCodeValuesDataResponse> retrieveCodeValuesByCodeId(Long codeId);
    GetCodeValuesDataResponse retrieveCodeValueDetails(Long codeId, Long codeValueId);
}
