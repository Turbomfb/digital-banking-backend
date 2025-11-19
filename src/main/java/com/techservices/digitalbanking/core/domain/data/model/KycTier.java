/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

/**
 * Represents a KYC Tier configuration in the system. Defines limits on account balances, deposits,
 * and withdrawals according to regulatory tiers.
 */
@Entity
@Table(name = "kyc_tier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class KycTier {

  @Id private Long id;

  @Column(name = "tier_name", nullable = false, length = 50)
  private String tierName;

  @Column(name = "maximum_balance", precision = 18, scale = 6, nullable = false)
  private BigDecimal maximumBalance;

  @Column(name = "single_deposit_limit", precision = 18, scale = 6, nullable = false)
  private BigDecimal singleDepositLimit;

  @Column(name = "single_withdrawal_limit", precision = 18, scale = 6, nullable = false)
  private BigDecimal singleWithdrawalLimit;

  @Column(name = "daily_withdrawal_limit", precision = 18, scale = 6, nullable = false)
  private BigDecimal dailyWithdrawalLimit;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
