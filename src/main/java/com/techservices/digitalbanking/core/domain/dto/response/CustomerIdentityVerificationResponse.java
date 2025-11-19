/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CustomerIdentityVerificationResponse {
  private boolean isValid;
  private List<String> mismatchedFields;
}
