/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
    name = "beneficiary",
    uniqueConstraints =
        @UniqueConstraint(columnNames = {"customer_id", "account_number", "bank_code"}))
@Setter
@Getter
@ToString
public class Beneficiary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "customer_id", nullable = false)
  private Long customerId;

  @Column(name = "account_name", length = 255, nullable = false)
  private String accountName;

  @Column(name = "account_number", length = 20, nullable = false)
  private String accountNumber;

  @Column(name = "bank_name", length = 255, nullable = false)
  private String bankName;

  @Column(name = "bank_code", length = 20, nullable = false)
  private String bankCode;

  @Column(length = 100)
  private String nickname;

  @Column(name = "is_verified")
  private Boolean isVerified = false;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "last_modified_at")
  private LocalDateTime lastModifiedAt;

  @Column(name = "last_used_at")
  private LocalDateTime lastUsedAt;

  @Column(name = "usage_count")
  private Integer usageCount = 0;

  @PrePersist
  protected void onCreate() {

    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {

    lastModifiedAt = LocalDateTime.now();
  }

  public void incrementUsageCount() {

    if (this.usageCount == null) {
      this.usageCount = 0;
    }
    this.usageCount++;
    this.lastUsedAt = LocalDateTime.now();
    this.lastModifiedAt = LocalDateTime.now();
  }
}
