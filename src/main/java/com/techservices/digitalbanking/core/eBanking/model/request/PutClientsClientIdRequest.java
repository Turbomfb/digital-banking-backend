/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PutClientsClientIdRequest {

  @JsonProperty("phoneNumber")
  private String mobileNo;

  @JsonProperty("firstName")
  private String firstname;

  @JsonProperty("lastName")
  private String lastname;

  @JsonProperty("middlename")
  private String middleName;

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("emailAddress")
  private String emailAddress;

  @JsonProperty("dateOfBirth")
  private String dateOfBirth;

  @JsonProperty("dateFormat")
  private String dateFormat;

  @JsonProperty("gender")
  private String gender;

  @JsonProperty("externalId")
  private String externalId;

  @JsonProperty("activationDate")
  private String activationDate;

  @JsonProperty("active")
  private Boolean active;

  @JsonProperty("legalFormId")
  private Integer legalFormId;

  @JsonProperty("locale")
  private String locale;

  @JsonProperty("submittedOnDate")
  private String submittedOnDate;

  @JsonProperty("pepStatus")
  private String pepStatus;

  @JsonProperty("nin")
  private String nin;

  private String tin;
  private String rcNumber;

  @JsonProperty("bvn")
  private String bvn;

  @JsonProperty("sourceSystem")
  private String sourceSystem;

  private Long kycTier;

  private PostClientNonPersonDetails clientNonPersonDetails;
  private Long clientTypeId;
  private Long clientClassificationId;

  private Boolean proofOfAddress;

  @JsonProperty("account_number")
  private String accountNumber;

  @JsonProperty("account_name")
  private String accountName;

  @JsonProperty("bank_code")
  private String bankCode;

  @JsonProperty("bank_name")
  private String bankName;

  @Valid private List<@Valid PostClientsDatatable> datatables = Collections.emptyList();

  // you cannot directly update this , but this is to ensure we keep all the data
  // in one request
  // body
  @Valid private List<@Valid PostClientsAddressRequest> address = Collections.emptyList();

  // check if all fields aside from address are null
  @JsonIgnore
  public boolean isAllFieldsNull() {

    return (mobileNo == null
        && firstname == null
        && lastname == null
        && middleName == null
        && nin == null
        && kycTier == null
        && bvn == null
        && fullName == null
        && emailAddress == null
        && proofOfAddress == null
        && dateOfBirth == null
        && clientClassificationId == null
        && clientTypeId == null
        && clientNonPersonDetails == null);
  }
}
