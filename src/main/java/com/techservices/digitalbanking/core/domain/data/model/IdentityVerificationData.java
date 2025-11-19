/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import com.techservices.digitalbanking.core.domain.dto.response.BusinessDataResponse;
import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
    name = "identity_verification_data",
    uniqueConstraints = @UniqueConstraint(columnNames = {"identifier", "type"}))
@Setter
@Getter
@ToString
public class IdentityVerificationData {

  @Id private String id;

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

  @Column(length = 50)
  private String type;

  @Column(length = 50)
  private String status;

  @Column(columnDefinition = "TEXT")
  private String image;

  @Column(columnDefinition = "TEXT")
  private String signature;

  // fields for business data
  @Column(name = "business_name", length = 255)
  private String businessName;

  @Column(name = "registration_number", length = 100)
  private String registrationNumber;

  @Column(name = "registration_date", length = 100)
  private String registrationDate;

  @Column(length = 50)
  private String tin;

  @Column(name = "vat_number", length = 50)
  private String vatNumber;

  @Column(name = "company_status", length = 100)
  private String companyStatus;

  @Column(name = "business_type", length = 100)
  private String businessType;

  @Column(length = 255)
  private String activity;

  @Column(name = "business_email", length = 100)
  private String businessEmail;

  @Column(name = "business_phone", length = 20)
  private String businessPhone;

  @Column(name = "head_office_address", length = 255)
  private String headOfficeAddress;

  @Column(name = "branch_address", length = 255)
  private String branchAddress;

  @Column(columnDefinition = "TEXT")
  private String objectives;

  @Column(name = "share_capital", length = 100)
  private String shareCapital;

  @Column(name = "country_code", length = 10)
  private String countryCode;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "last_modified_at")
  private LocalDateTime lastModifiedAt;

  public static IdentityVerificationData parse(IdentityVerificationResponse verificationResponse) {

    IdentityVerificationData verificationData = new IdentityVerificationData();
    IdentityVerificationResponse.IdentityVerificationResponseData data =
        verificationResponse.getData();
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
      if (data.getAddress() != null) {
        verificationData.setTown(data.getAddress().getTown());
        verificationData.setLga(data.getAddress().getLga());
        verificationData.setState(data.getAddress().getState());
        verificationData.setAddressLine(data.getAddress().getAddressLine());
      }
    }
    return verificationData;
  }

  /** Parse business data response (RC Number/TIN) */
  public static IdentityVerificationData parse(BusinessDataResponse businessDataResponse) {

    IdentityVerificationData verificationData = new IdentityVerificationData();
    BusinessDataResponse.BusinessData data = businessDataResponse.getData();
    if (data != null) {
      verificationData.setId(data.getId());

      verificationData.setBusinessName(data.getName());
      verificationData.setRegistrationNumber(data.getRegistrationNumber());
      verificationData.setRegistrationDate(data.getRegistrationDate());
      verificationData.setTin(data.getTin());
      verificationData.setVatNumber(data.getVatNumber());
      verificationData.setCompanyStatus(data.getCompanyStatus());
      verificationData.setBusinessType(data.getTypeOfEntity());
      verificationData.setActivity(data.getActivity());
      verificationData.setBusinessEmail(data.getEmail());
      verificationData.setBusinessPhone(data.getPhone());
      verificationData.setHeadOfficeAddress(data.getHeadOfficeAddress());
      verificationData.setBranchAddress(data.getBranchAddress());
      verificationData.setObjectives(data.getObjectives());
      verificationData.setShareCapital(data.getPaidShareCapital());
      verificationData.setCountryCode(data.getCountryCode());
      verificationData.setStatus(data.getStatus());

      verificationData.setState(data.getState());
      verificationData.setLga(data.getLga());
      verificationData.setAddressLine(data.getAddress());
      verificationData.setTown(data.getCity());
    }
    return verificationData;
  }
}
