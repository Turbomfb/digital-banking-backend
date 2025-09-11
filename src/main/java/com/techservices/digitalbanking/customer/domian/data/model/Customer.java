package com.techservices.digitalbanking.customer.domian.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.common.domain.model.BaseEntity;
import com.techservices.digitalbanking.customer.domian.CustomerKycTier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "recurring_deposit_account_id")
    private String recurringDepositAccountId;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(unique = true, name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "is_authenticated")
    private boolean isAuthenticated;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "nin")
    private String nin;

    @Column(name = "bvn")
    private String bvn;

    @Column(name = "rc_number")
    private String rcNumber;

    @Column(name = "tin")
    private String tin;

    @Column(name = "industry_id")
    private String industryId;

    @Column(name = "business_name")
    private String businessName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(name = "Kyc_tier")
    private CustomerKycTier KycTier;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "is_transaction_pin_set", nullable = false)
    private boolean isTransactionPinSet;

    @Column(name = "transaction_pin")
    private String transactionPin;


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

    public boolean isCorporateUser() {
        return this.userType == UserType.CORPORATE;
    }
}
