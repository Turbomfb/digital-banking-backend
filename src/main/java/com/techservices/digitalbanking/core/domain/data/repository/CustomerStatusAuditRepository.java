/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import com.techservices.digitalbanking.core.domain.data.model.CustomerStatusAudit;
import com.techservices.digitalbanking.core.domain.enums.CustomerStatusAuditType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerStatusAuditRepository extends JpaRepository<CustomerStatusAudit, Long> {

  Optional<CustomerStatusAudit> findByCustomerIdAndTypeAndIsActive(
      Long customerId, CustomerStatusAuditType type, boolean active);
}
