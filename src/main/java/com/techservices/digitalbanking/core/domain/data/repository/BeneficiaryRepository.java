package com.techservices.digitalbanking.core.domain.data.repository;

import com.techservices.digitalbanking.core.domain.data.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

  List<Beneficiary> findByCustomerIdAndIsActiveTrueOrderByLastUsedAtDesc(Long customerId);

  List<Beneficiary> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

  Optional<Beneficiary> findByIdAndCustomerId(Long id, Long customerId);

  Optional<Beneficiary> findByCustomerIdAndAccountNumberAndBankCode(Long customerId, String accountNumber, String bankCode);

  boolean existsByCustomerIdAndAccountNumberAndBankCode(Long customerId, String accountNumber, String bankCode);

  boolean existsByCustomerIdAndNickname(Long customerId, String nickname);

  Optional<Beneficiary> findByCustomerIdAndNickname(Long customerId, String nickname);

  @Query("SELECT b FROM Beneficiary b WHERE b.customerId = :customerId AND b.isActive = true ORDER BY b.usageCount DESC, b.lastUsedAt DESC")
  List<Beneficiary> findFrequentBeneficiaries(@Param("customerId") Long customerId);

  @Query("SELECT COUNT(b) FROM Beneficiary b WHERE b.customerId = :customerId AND b.isActive = true")
  long countActiveByCustomerId(@Param("customerId") Long customerId);

  @Query("SELECT b FROM Beneficiary b WHERE b.customerId = :customerId AND b.isActive = true " +
      "AND (LOWER(b.accountName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
      "OR LOWER(b.nickname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
      "OR b.accountNumber LIKE CONCAT('%', :searchTerm, '%')) " +
      "ORDER BY b.lastUsedAt DESC")
  List<Beneficiary> searchBeneficiaries(@Param("customerId") Long customerId, @Param("searchTerm") String searchTerm);

  @Query("SELECT b FROM Beneficiary b WHERE b.customerId = :customerId AND b.isActive = true " +
      "AND b.createdAt BETWEEN :startDate AND :endDate " +
      "ORDER BY b.createdAt DESC")
  List<Beneficiary> findByCustomerIdAndDateRange(@Param("customerId") Long customerId,
      @Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate);

}