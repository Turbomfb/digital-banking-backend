/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutFixedDepositAccountsAccountIdResponse {
  private PutFixedDepositAccountsChanges changes;

  private Integer clientId;

  private Integer officeId;

  private Integer resourceId;

  private Integer savingsId;
}
