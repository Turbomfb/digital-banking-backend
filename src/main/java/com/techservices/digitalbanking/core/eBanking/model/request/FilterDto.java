/* (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FilterDto {
  private String accountNumber;
  private String customerId;
  private Long loanId;
  private String fromDate;
  private UserType customerType;
  private String bvn;
  private String emailAddress;
  private String toDate;
  private Long size;
  private TransactionType transactionType;

  public FilterDto(String savingsAccountId, String startDate, String endDate, Long limit) {

    this.accountNumber = savingsAccountId;
    this.fromDate = startDate;
    this.toDate = endDate;
    this.size = limit;
  }

  public FilterDto(
      String savingsAccountId,
      String startDate,
      String endDate,
      Long limit,
      TransactionType transactionType) {

    this.accountNumber = savingsAccountId;
    this.fromDate = startDate;
    this.toDate = endDate;
    this.size = limit;
    this.transactionType = transactionType;
  }

  public FilterDto accountNumber(String accountNumber) {

    this.accountNumber = accountNumber;
    return this;
  }

  public FilterDto customerId(String customerId) {

    this.customerId = customerId;
    return this;
  }

  public FilterDto customerType(UserType customerType) {

    this.customerType = customerType;
    return this;
  }

  public FilterDto bvn(String bvn) {

    this.bvn = bvn;
    return this;
  }

  public FilterDto emailAddress(String emailAddress) {

    this.emailAddress = emailAddress;
    return this;
  }

  public FilterDto size(Long limit) {

    this.size = limit;
    return this;
  }

  public FilterDto loanId(Long loanId) {

    this.loanId = loanId;
    return this;
  }
}
