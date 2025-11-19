/* (C)2025 */
package com.techservices.digitalbanking.customer.domian.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAccountActivationRequest {
  private String emailAddress;
  private String phoneNumber;
  private Long customerId;
}
