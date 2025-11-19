/* (C)2025 */
package com.techservices.digitalbanking.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.digitalbanking.core.service.SystemService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/cba")
@AllArgsConstructor
public class HealthApi {
	private final SystemService systemService;
}
