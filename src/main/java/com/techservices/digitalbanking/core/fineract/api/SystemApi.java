/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import com.techservices.digitalbanking.core.fineract.model.response.GetCodeValuesDataResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SystemApi {
	@GetMapping(value = "/actuator/health")
	Object healthCheck();

}
