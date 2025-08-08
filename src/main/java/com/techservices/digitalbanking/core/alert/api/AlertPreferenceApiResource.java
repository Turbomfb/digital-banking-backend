package com.techservices.digitalbanking.core.alert.api;

import com.techservices.digitalbanking.core.alert.domain.data.enums.AlertType;
import com.techservices.digitalbanking.core.alert.domain.data.model.AlertPreference;
import com.techservices.digitalbanking.core.alert.service.AlertPreferenceService;
import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/me/alerts")
@RequiredArgsConstructor
public class AlertPreferenceApiResource {

    private final AlertPreferenceService alertPreferenceService;
    private final SpringSecurityAuditorAware springSecurityAuditorAware;

    @Operation(summary = "Get all alert preferences for a customer")
    @GetMapping
    public ResponseEntity<List<AlertPreference>> getAllPreferences() {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        List<AlertPreference> preferences = alertPreferenceService.getPreferencesForCustomer(customerId);
        return ResponseEntity.ok(preferences);
    }

    @Operation(summary = "Create or update an alert preference for a specific alert type")
    @PutMapping("/{alertType}")
    public ResponseEntity<AlertPreference> updatePreference(
            @PathVariable AlertType alertType,
            @RequestParam boolean viaSms,
            @RequestParam boolean viaEmail,
            @RequestParam boolean viaPush
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        AlertPreference updated = alertPreferenceService.updatePreference(customerId, alertType, viaSms, viaEmail, viaPush);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Enable a specific channel for an alert type")
    @PatchMapping("/{alertType}/enable/{channel}")
    public ResponseEntity<AlertPreference> enableChannel(
            @PathVariable AlertType alertType,
            @PathVariable String channel
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        AlertPreference updated = alertPreferenceService.enableChannel(customerId, alertType, channel);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Disable a specific channel for an alert type")
    @PatchMapping("/{alertType}/disable/{channel}")
    public ResponseEntity<AlertPreference> disableChannel(
            @PathVariable AlertType alertType,
            @PathVariable String channel
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        AlertPreference updated = alertPreferenceService.disableChannel(customerId, alertType, channel);
        return ResponseEntity.ok(updated);
    }
}
