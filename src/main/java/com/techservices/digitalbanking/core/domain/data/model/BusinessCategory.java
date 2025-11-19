/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import com.techservices.digitalbanking.core.domain.enums.BusinessCategoryType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "business_category")
@Setter
@Getter
@ToString(exclude = "items")
public class BusinessCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false, unique = true)
  private String name;

  @Column(length = 50, nullable = false, unique = true)
  private String code;

  @Column(length = 255)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(name = "category_type", length = 50, nullable = false)
  private BusinessCategoryType categoryType;

  @Column(name = "display_order")
  private Integer displayOrder;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<BusinessCategoryItem> items = new ArrayList<>();

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

  public void addItem(BusinessCategoryItem item) {

    items.add(item);
    item.setCategory(this);
  }
}
