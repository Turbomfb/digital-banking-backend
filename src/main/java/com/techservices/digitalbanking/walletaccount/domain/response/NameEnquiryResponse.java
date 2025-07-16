package com.techservices.digitalbanking.walletaccount.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
