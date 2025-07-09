package com.techservices.digitalbanking.core.domain.data.repository;

import com.techservices.digitalbanking.core.domain.data.model.IdentityVerificationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IdentityVerificationDataRepository extends JpaRepository<IdentityVerificationData, Long> {


    @Query("SELECT ivd FROM IdentityVerificationData ivd WHERE LOWER(ivd.identifier) = LOWER(:identifier) and LOWER(ivd.type) = LOWER(:type)")
    Optional<IdentityVerificationData> findByIdentifierAndType(String identifier, String type);
}
