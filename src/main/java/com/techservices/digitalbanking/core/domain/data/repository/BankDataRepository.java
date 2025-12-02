/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techservices.digitalbanking.core.domain.data.model.BankData;

public interface BankDataRepository extends JpaRepository<BankData, Long> {

	@Query("SELECT bd FROM BankData bd WHERE LOWER(bd.code) = LOWER(:code)")
	Optional<BankData> findByCode(String code);
}
