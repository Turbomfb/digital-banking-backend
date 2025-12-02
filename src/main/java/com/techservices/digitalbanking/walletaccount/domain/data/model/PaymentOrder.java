/* (C)2025 */
package com.techservices.digitalbanking.walletaccount.domain.data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.techservices.digitalbanking.common.domain.model.BaseEntity;
import com.techservices.digitalbanking.walletaccount.domain.data.PaymentOrderStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payment_order")
@Getter
@Setter
@ToString
public class PaymentOrder extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_order_seq_gen")
	@SequenceGenerator(name = "payment_order_seq_gen", sequenceName = "payment_order_seq", allocationSize = 1)
	private Long id;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "reference")
	private String reference;

	@Column(name = "currency")
	private String currency;

	@Column(name = "customer_id")
	private Long customerId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PaymentOrderStatus status;

	@PrePersist
	public void prePersist() {

		LocalDateTime now = LocalDateTime.now();
		if (getCreatedAt() == null) {
			this.setCreatedAt(now);
		}
		this.setUpdatedAt(now);
	}

	@PreUpdate
	public void preUpdate() {

		this.setUpdatedAt(LocalDateTime.now());
	}
}
