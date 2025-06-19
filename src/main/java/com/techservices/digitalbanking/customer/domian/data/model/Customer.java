package com.techservices.digitalbanking.customer.domian.data.model;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.common.domain.model.BaseEntity;
import com.techservices.digitalbanking.customer.domian.CustomerKycTier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    private String accountId;
    private String password;
    private String firstname;
    private String lastname;

    @Column(unique = true, name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "nin")
    private String nin;

    @Column(name = "bvn")
    private String bvn;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(name = "Kyc_tier")
    private CustomerKycTier KycTier;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;


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


    public void setExternalId(Long id) {
        if (id == null) {
            this.externalId = null;
        } else {
            this.externalId = String.valueOf(id);
        }
    }

    public void setExternalId(String id) {
        this.externalId = id;
    }
}
