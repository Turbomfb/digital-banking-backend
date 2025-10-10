package com.techservices.digitalbanking.core.domain.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_log")
@Setter
@Getter
@ToString
public class TransactionLog {

    @Id
    private String id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "beneficiary_account_number", length = 20, nullable = false)
    private String beneficiaryAccountNumber;

    @Column(name = "beneficiary_account_name", length = 255)
    private String beneficiaryAccountName;

    @Column(name = "beneficiary_bank_name", length = 255)
    private String beneficiaryBankName;

    @Column(name = "beneficiary_bank_code", length = 20, nullable = false)
    private String beneficiaryBankCode;

    @Column(nullable = false, precision = 24, scale = 6)
    private BigDecimal amount;

    @Column(length = 500)
    private String narration;

    @Column(name = "transaction_reference", length = 100, nullable = false, unique = true)
    private String transactionReference;

    @Column(name = "external_reference", length = 100)
    private String externalReference;

    @Column(length = 50, nullable = false)
    private String status;

    @Column(name = "response_code", length = 20)
    private String responseCode;

    @Column(name = "response_message", columnDefinition = "TEXT")
    private String responseMessage;

    @Column(name = "transaction_type", length = 50, nullable = false)
    private String transactionType;

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
