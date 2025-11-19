/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import com.techservices.digitalbanking.core.domain.data.model.NameEnquiryCache;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameEnquiryCacheRepository extends JpaRepository<NameEnquiryCache, String> {

  /** Find cached name enquiry by account number and bank code */
  Optional<NameEnquiryCache> findByAccountNumberAndBankCode(String accountNumber, String bankCode);
}
