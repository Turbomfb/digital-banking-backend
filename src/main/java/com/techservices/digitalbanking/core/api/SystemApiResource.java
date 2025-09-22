package com.techservices.digitalbanking.core.api;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetCodeValuesDataResponse;
import com.techservices.digitalbanking.core.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/system")
@RequiredArgsConstructor
public class SystemApiResource {
    private final SystemService systemService;

    @GetMapping("/codes/{codeId}")
    public ResponseEntity<BasePageResponse<GetCodeValuesDataResponse>> retrieveCodeValuesByCodeId(@PathVariable("codeId") Long codeId) {
        BasePageResponse<GetCodeValuesDataResponse> response = systemService.retrieveCodeValuesByCodeId(codeId);
        return ResponseEntity.ok(response);
    }
}
