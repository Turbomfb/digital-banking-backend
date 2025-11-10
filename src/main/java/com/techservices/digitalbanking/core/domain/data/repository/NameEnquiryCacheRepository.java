package com.techservices.digitalbanking.core.domain.data.repository;

import com.techservices.digitalbanking.core.domain.data.model.NameEnquiryCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NameEnquiryCacheRepository extends JpaRepository<NameEnquiryCache, String> {

    /**
     * Find cached name enquiry by account number and bank code
     */
    Optional<NameEnquiryCache> findByAccountNumberAndBankCode(String accountNumber, String bankCode);
}
