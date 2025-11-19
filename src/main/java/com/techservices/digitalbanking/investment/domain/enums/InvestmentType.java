/* (C)2025 */
package com.techservices.digitalbanking.investment.domain.enums;

public enum InvestmentType {
  FLEX,
  LOCK,
  WALLET;

  public static boolean isFlex(InvestmentType investmentType) {

    return investmentType == FLEX;
  }

  public static boolean isLock(InvestmentType investmentType) {

    return investmentType == LOCK;
  }
}
