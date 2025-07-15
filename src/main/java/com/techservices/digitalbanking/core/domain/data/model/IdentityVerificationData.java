package com.techservices.digitalbanking.core.domain.data.model;

import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "identity_verification_data", uniqueConstraints = @UniqueConstraint(columnNames = {"identifier", "type"}))
@Setter
@Getter
@ToString
public class IdentityVerificationData {

    @Id
    private String id;

    @Column(length = 50, nullable = false)
    private String identifier;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(length = 20)
    private String mobile;

    @Column(length = 100)
    private String email;

    @Column(length = 1)
    private String gender;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "address_line", length = 255)
    private String addressLine;

    @Column(length = 100)
    private String town;

    @Column(length = 100)
    private String lga;

    @Column(length = 100)
    private String state;

    @Column(name = "birth_state", length = 100)
    private String birthState;

    @Column(name = "birth_lga", length = 100)
    private String birthLga;

    @Column(name = "birth_country", length = 100)
    private String birthCountry;

    @Column(length = 50)
    private String religion;

    @Column(length = 20)
    private String type;

    @Column(length = 50)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column(columnDefinition = "TEXT")
    private String signature;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    public static IdentityVerificationData parse(IdentityVerificationResponse verificationResponse) {
        IdentityVerificationData verificationData = new IdentityVerificationData();
        IdentityVerificationResponse.IdentityVerificationResponseData data = verificationResponse.getData();
        if (data != null) {
            verificationData.setId(data.getId());
            verificationData.setFirstName(data.getFirstName());
            verificationData.setMiddleName(data.getMiddleName());
            verificationData.setLastName(data.getLastName());
            verificationData.setMobile(data.getMobile());
            verificationData.setEmail(data.getEmail());
            verificationData.setDateOfBirth(data.getDateOfBirth());
            verificationData.setGender(data.getGender());
            verificationData.setReligion(data.getReligion());
            verificationData.setImage(data.getImage());
            verificationData.setSignature(data.getSignature());
        }
        return verificationData;
    }
}
