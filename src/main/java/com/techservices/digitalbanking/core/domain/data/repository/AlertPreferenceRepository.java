/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techservices.digitalbanking.core.domain.data.model.AlertPreference;
import com.techservices.digitalbanking.core.domain.enums.AlertType;

public interface AlertPreferenceRepository extends JpaRepository<AlertPreference, Long> {
	List<AlertPreference> findByCustomerId(Long customerId);

	Optional<AlertPreference> findByCustomerIdAndAlertType(Long customerId, AlertType alertType);

	boolean existsByCustomerIdAndAlertType(Long customerId, AlertType alertType);
}
