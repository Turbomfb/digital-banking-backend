/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.techservices.digitalbanking.core.eBanking.model.response.GetCodeValuesDataResponse;

public interface CodeApi {
	@GetMapping(value = "/codes/{codeId}/codevalues")
	List<GetCodeValuesDataResponse> retrieveAllCodeValues(@PathVariable("codeId") Long codeId);

	@GetMapping(value = "/codes/{codeId}/codevalues/{codeValueId}")
	GetCodeValuesDataResponse retrieveCodeValue(@PathVariable("codeValueId") Long codeValueId,
			@PathVariable("codeId") Long codeId);
}
