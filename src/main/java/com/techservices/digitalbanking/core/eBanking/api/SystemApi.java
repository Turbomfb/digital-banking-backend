/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import org.springframework.web.bind.annotation.GetMapping;

public interface SystemApi {
	@GetMapping(value = "/actuator/health")
	Object healthCheck();

}
