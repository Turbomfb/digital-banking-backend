/*
 * Copyright (c) 2025 TechService Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of TechService Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * TechService Engineering Team
 * Email: engineering@techservicehub.io
 */
package com.techservices.digitalbanking.core.domain.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a KYC Tier configuration in the system.
 * Defines limits on account balances, deposits, and withdrawals
 * according to regulatory tiers.
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

    @Id
    private Long id;

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
