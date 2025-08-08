package com.techservices.digitalbanking.core.domain.data.repository;

import com.techservices.digitalbanking.core.domain.data.model.CustomerStatusAudit;
import com.techservices.digitalbanking.core.domain.enums.CustomerStatusAuditType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerStatusAuditRepository extends JpaRepository<CustomerStatusAudit, Long> {

    Optional<CustomerStatusAudit> findByCustomerIdAndTypeAndIsActive(Long customerId, CustomerStatusAuditType type, boolean active);
}
