/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationResponse {
  private String code;
  private String message;
  private String status;
}
