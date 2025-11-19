/* (C)2025 */
package com.techservices.digitalbanking.authentication.domain.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techservices.digitalbanking.authentication.domain.data.model.UserLoginActivity;

public interface UserLoginActivityRepository extends JpaRepository<UserLoginActivity, Long> {

	Optional<UserLoginActivity> findByDeviceName(String deviceName);

	Optional<UserLoginActivity> findByCustomerIdAndDeviceNameAndSource(Long customerId, String deviceName,
			String source);

	List<UserLoginActivity> findAllByCustomerId(Long customerId);
}
