/* (C)2025 */
package com.techservices.digitalbanking.core.domain.enums;

import lombok.Getter;

@Getter
public enum AccountType {
  SAVINGS(100L),
  FIXED_DEPOSIT(200L),
  RECURRING_DEPOSIT(300L);

  private final Long code;

  AccountType(Long code) {

    this.code = code;
  }
}
