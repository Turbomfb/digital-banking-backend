/* (C)2025 */
package com.techservices.digitalbanking.walletaccount.domain.response;

import static com.techservices.digitalbanking.core.util.AppUtil.DEFAULT_CURRENCY;
import static com.techservices.digitalbanking.core.util.AppUtil.SUCCESS;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.configuration.BankConfigurationService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryResponse {
  private boolean success;
  private String status;
  private NameEnquiryResponseData data;

  public static NameEnquiryResponse from(
      Customer customer, BankConfigurationService bankConfigurationService) {

    NameEnquiryResponse response = new NameEnquiryResponse();
    response.setSuccess(true);
    response.setStatus(SUCCESS);

    NameEnquiryResponse.NameEnquiryResponseData data = response.new NameEnquiryResponseData();
    NameEnquiryResponse.NameEnquiryResponseData.BankDetail bankDetail = data.new BankDetail();

    bankDetail.setAccountName(customer.getFullName());
    bankDetail.setAccountNumber(customer.getNuban());
    bankDetail.setBankName(bankConfigurationService.getBankName());
    bankDetail.setAccountCurrency(DEFAULT_CURRENCY);

    data.setBankDetails(bankDetail);
    response.setData(data);

    return response;
  }

  @Setter
  @Getter
  public class NameEnquiryResponseData {
    private BankDetail bankDetails;

    @Setter
    @Getter
    public class BankDetail {
      private String accountName;
      private String accountNumber;
      private String bankName;
      private String accountCurrency;
      private String accountType;
    }
  }
}
