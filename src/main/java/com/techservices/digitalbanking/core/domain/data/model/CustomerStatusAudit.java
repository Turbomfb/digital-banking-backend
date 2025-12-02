/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import java.time.LocalDateTime;

import com.techservices.digitalbanking.core.domain.enums.CustomerStatusAuditType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_status_audit", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"customer_id", "type", "is_active"})})
@Setter
@Getter
public class CustomerStatusAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "customer_id")
	private Long customerId;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", length = 50, nullable = false)
	private CustomerStatusAuditType type;

	@Column(name = "reason", length = 50, nullable = false)
	private String reason;

	@Column(name = "is_active", nullable = false)
	private boolean isActive = false;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at")
	private LocalDateTime updatedAt = LocalDateTime.now();

	@PreUpdate
	public void preUpdate() {

		this.updatedAt = LocalDateTime.now();
	}

	@PrePersist
	public void prePersist() {

		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
}
