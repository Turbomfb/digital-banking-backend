/* (C)2025 */
package com.techservices.digitalbanking.walletaccount.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryRequest {
  private String accountNumber;
  private String bankCode;

  @JsonProperty("isSubjectConsent")
  private boolean isSubjectConsent;

  @Override
  public String toString() {

    return new ToStringBuilder(this)
        .append("accountNumber", accountNumber)
        .append("bankCode", bankCode)
        .append("isSubjectConsent", isSubjectConsent)
        .toString();
  }
}
