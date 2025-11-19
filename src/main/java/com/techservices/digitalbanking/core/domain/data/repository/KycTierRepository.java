/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techservices.digitalbanking.core.domain.data.model.KycTier;

public interface KycTierRepository extends JpaRepository<KycTier, Long> {
}
