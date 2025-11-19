/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDataTablesResponse {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("account_number")
  private String accountNumber;

  @JsonProperty("account_name")
  private String accountName;

  @JsonProperty("bank_code")
  private String bankCode;

  @JsonProperty("bank_name")
  private String bankName;

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
