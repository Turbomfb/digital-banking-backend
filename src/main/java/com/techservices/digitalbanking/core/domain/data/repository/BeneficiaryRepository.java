package com.techservices.digitalbanking.core.domain.data.repository;

import com.techservices.digitalbanking.core.domain.data.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

  List<Beneficiary> findByCustomerIdAndIsActiveTrueOrderByLastUsedAtDesc(Long customerId);

  List<Beneficiary> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

  Optional<Beneficiary> findByIdAndCustomerId(Long id, Long customerId);

  Optional<Beneficiary> findByCustomerIdAndAccountNumberAndBankCode(Long customerId, String accountNumber, String bankCode);

  boolean existsByCustomerIdAndAccountNumberAndBankCode(Long customerId, String accountNumber, String bankCode);

  @Query("SELECT b FROM Beneficiary b WHERE b.customerId = :customerId AND b.isActive = true ORDER BY b.usageCount DESC, b.lastUsedAt DESC")
  List<Beneficiary> findFrequentBeneficiaries(@Param("customerId") Long customerId);

  @Query("SELECT COUNT(b) FROM Beneficiary b WHERE b.customerId = :customerId AND b.isActive = true")
  long countActiveByCustomerId(@Param("customerId") Long customerId);
}