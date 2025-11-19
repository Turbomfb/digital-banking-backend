/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techservices.digitalbanking.core.domain.data.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByCustomerId(Long customerId);

	Optional<Address> findByCustomerIdAndType(Long customerId, String type);

	boolean existsByCustomerIdAndType(Long customerId, String type);
}
