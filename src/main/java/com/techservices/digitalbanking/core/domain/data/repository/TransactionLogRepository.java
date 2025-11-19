/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techservices.digitalbanking.core.domain.data.model.TransactionLog;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, String> {
}
