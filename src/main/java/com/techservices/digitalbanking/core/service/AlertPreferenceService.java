/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.domain.data.model.AlertPreference;
import com.techservices.digitalbanking.core.domain.data.repository.AlertPreferenceRepository;
import com.techservices.digitalbanking.core.domain.enums.AlertType;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlertPreferenceService {

  private final AlertPreferenceRepository alertPreferenceRepository;
  private final CustomerRepository customerRepository;

  @Transactional
  public List<AlertPreference> getPreferencesForCustomer(Long customerId) {

    List<AlertPreference> alertPreferences = alertPreferenceRepository.findByCustomerId(customerId);
    if (alertPreferences.isEmpty()) {
      for (AlertType alertType : AlertType.values()) {
        AlertPreference preference =
            this.updatePreference(customerId, alertType, false, false, false);
        alertPreferences.add(preference);
      }
    }
    return alertPreferences;
  }

  @Transactional
  public Optional<AlertPreference> getPreferencesForCustomerByAlertType(
      Long customerId, AlertType alertType) {

    return alertPreferenceRepository.findByCustomerIdAndAlertType(customerId, alertType);
  }

  @Transactional
  public AlertPreference updatePreference(
      Long customerId, AlertType alertType, boolean viaSms, boolean viaEmail, boolean viaPush) {

    AlertPreference preference =
        alertPreferenceRepository
            .findByCustomerIdAndAlertType(customerId, alertType)
            .orElseGet(
                () -> {
                  AlertPreference newPref = new AlertPreference();
                  newPref.setCustomerId(customerId);
                  newPref.setAlertType(alertType);
                  return newPref;
                });

    preference.setViaSms(viaSms);
    preference.setViaEmail(viaEmail);
    preference.setViaPush(viaPush);

    return alertPreferenceRepository.save(preference);
  }

  @Transactional
  public AlertPreference enableChannel(Long customerId, AlertType alertType, String channel) {

    Optional<AlertPreference> preferenceOptional =
        alertPreferenceRepository.findByCustomerIdAndAlertType(customerId, alertType);

    AlertPreference preference =
        preferenceOptional.orElseGet(
            () -> {
              AlertPreference newPref = new AlertPreference();
              newPref.setCustomerId(customerId);
              newPref.setAlertType(alertType);
              return newPref;
            });
    switch (channel.toUpperCase()) {
      case "SMS" -> preference.setViaSms(true);
      case "EMAIL" -> preference.setViaEmail(true);
      case "PUSH" -> preference.setViaPush(true);
      default -> throw new IllegalArgumentException("Invalid channel: " + channel);
    }

    return alertPreferenceRepository.save(preference);
  }

  @Transactional
  public AlertPreference disableChannel(Long customerId, AlertType alertType, String channel) {

    Optional<AlertPreference> preferenceOptional =
        alertPreferenceRepository.findByCustomerIdAndAlertType(customerId, alertType);

    AlertPreference preference =
        preferenceOptional.orElseGet(
            () -> {
              AlertPreference newPref = new AlertPreference();
              newPref.setCustomerId(customerId);
              newPref.setAlertType(alertType);
              return newPref;
            });
    switch (channel.toUpperCase()) {
      case "SMS" -> preference.setViaSms(false);
      case "EMAIL" -> preference.setViaEmail(false);
      case "PUSH" -> preference.setViaPush(false);
      default -> throw new IllegalArgumentException("Invalid channel: " + channel);
    }

    return alertPreferenceRepository.save(preference);
  }
}
