package com.techservices.digitalbanking.core.domain.data.model;

import com.techservices.digitalbanking.core.domain.enums.AlertType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "alert_preferences",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"customer_id", "alert_type"})
        }
)
@Setter
@Getter
public class AlertPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_type", length = 50, nullable = false)
    private AlertType alertType;

    @Column(name = "via_sms", nullable = false)
    private boolean viaSms = false;

    @Column(name = "via_email", nullable = false)
    private boolean viaEmail = false;

    @Column(name = "via_push", nullable = false)
    private boolean viaPush = false;

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
