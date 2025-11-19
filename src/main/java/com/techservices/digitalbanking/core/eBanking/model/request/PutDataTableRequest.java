/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class PutDataTableRequest {

  @JsonProperty("account_number")
  private String accountNumber;

  @JsonProperty("account_name")
  private String accountName;

  @JsonProperty("bank_code")
  private String bankCode;

  @JsonProperty("bank_name")
  private String bankName;

  @JsonProperty("locale")
  private String locale;

  // Directors datatable
  @JsonProperty("nin")
  private String nin;

  @JsonProperty("bvn")
  private String bvn;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("middleName")
  private String middleName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("emailAddress")
  private String emailAddress;

  @JsonProperty("mobileNumber")
  private String mobileNumber;
}
