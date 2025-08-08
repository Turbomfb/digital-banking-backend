package com.techservices.digitalbanking.core.alert.service;

import com.techservices.digitalbanking.core.alert.domain.data.enums.AlertType;
import com.techservices.digitalbanking.core.alert.domain.data.model.AlertPreference;
import com.techservices.digitalbanking.core.alert.domain.data.repository.AlertPreferenceRepository;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertPreferenceService {

    private final AlertPreferenceRepository alertPreferenceRepository;
    private final CustomerRepository customerRepository;


    @Transactional(readOnly = true)
    public List<AlertPreference> getPreferencesForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return alertPreferenceRepository.findByCustomer(customer);
    }


    @Transactional
    public AlertPreference updatePreference(Long customerId, AlertType alertType,
                                            boolean viaSms, boolean viaEmail, boolean viaPush) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        AlertPreference preference = alertPreferenceRepository
                .findByCustomerAndAlertType(customer, alertType)
                .orElseGet(() -> {
                    AlertPreference newPref = new AlertPreference();
                    newPref.setCustomerId(customer.getId());
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
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        AlertPreference preference = alertPreferenceRepository
                .findByCustomerAndAlertType(customer, alertType)
                .orElseThrow(() -> new RuntimeException("Alert preference not found"));

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
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        AlertPreference preference = alertPreferenceRepository
                .findByCustomerAndAlertType(customer, alertType)
                .orElseThrow(() -> new RuntimeException("Alert preference not found"));

        switch (channel.toUpperCase()) {
            case "SMS" -> preference.setViaSms(false);
            case "EMAIL" -> preference.setViaEmail(false);
            case "PUSH" -> preference.setViaPush(false);
            default -> throw new IllegalArgumentException("Invalid channel: " + channel);
        }

        return alertPreferenceRepository.save(preference);
    }
}
