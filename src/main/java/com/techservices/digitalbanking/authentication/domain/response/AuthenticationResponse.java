/* (C)2025 */
package com.techservices.digitalbanking.authentication.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponse {
  private String accessToken;
  private String username;

  private Long id;
  private String firstname;
  private String lastname;
  private String emailAddress;
  private String externalId;
  private String phoneNumber;
  private String referralCode;
  private UserType userType;
}
