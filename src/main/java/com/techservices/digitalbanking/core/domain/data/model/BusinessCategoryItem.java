/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "business_category_item")
@Setter
@Getter
@ToString(exclude = "category")
public class BusinessCategoryItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private BusinessCategory category;

  @Column(length = 255, nullable = false)
  private String name;

  @Column(length = 100, nullable = false)
  private String code;

  @Column(length = 500)
  private String description;

  @Column(name = "display_order")
  private Integer displayOrder;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "last_modified_at")
  private LocalDateTime lastModifiedAt;

  @PrePersist
  protected void onCreate() {

    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {

    lastModifiedAt = LocalDateTime.now();
  }
}
