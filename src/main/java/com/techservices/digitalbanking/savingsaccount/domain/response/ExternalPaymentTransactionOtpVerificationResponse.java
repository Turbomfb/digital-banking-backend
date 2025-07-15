/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.domain.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalPaymentTransactionOtpVerificationResponse extends BaseAppResponse {
    private Long data;
    private String status;
    private String statusCode;
    private String message;

	public boolean isSuccessful() {
		return "success".equalsIgnoreCase(status);
	}


    @Setter
    @Getter
    public static class Data {
        private BigDecimal amount;
        private String transactionReference;

        @JsonCreator
        public Data(
                @JsonProperty("amount") BigDecimal amount,
                @JsonProperty("transactionReference") String transactionReference
        ) {
            this.amount = amount;
            this.transactionReference = transactionReference;
        }
    }
}
