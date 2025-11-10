package com.techservices.digitalbanking.core;

import com.techservices.digitalbanking.core.service.SystemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cba")
@AllArgsConstructor
public class HealthApi {
    private final SystemService systemService;

}
