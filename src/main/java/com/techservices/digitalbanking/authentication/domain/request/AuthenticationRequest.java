/* (C)2025 */
package com.techservices.digitalbanking.authentication.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationRequest {
  private String phoneNumber;
  private String password;
  private String emailAddress;
}
