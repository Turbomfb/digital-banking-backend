package com.techservices.digitalbanking.walletaccount.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletPaymentOrderResponse {
    private DataPayload data;
    private String status;
    private String statusCode;
    private String message;

    public boolean isSuccessful() {
        return StringUtils.equalsIgnoreCase(this.status, "SUCCESS") ||
               StringUtils.equalsIgnoreCase(this.statusCode, "01");
    }

    @Setter
    @Getter
    public static class DataPayload {
        private Order order;
        private Customer customer;
        private SubsidiaryOrderSummary subsidiaryOrderSummary;

        @Setter
        @Getter
        public static class Order {
            private String reference;
            private String processorReference;
            private BigDecimal amount;
            private String status;
            private String currency;
            private String narration;
        }

        @Setter
        @Getter
        public static class Customer {
            private String email;
            private String firstName;
            private String lastName;
            private String mobile;
            private String country;
        }

        @Setter
        @Getter
        public static class SubsidiaryOrderSummary {
            private String orderName;
            private double totalAmount;
            private String reference;
            private String currency;
            private List<OrderItem> orderItems;

            @Setter
            @Getter
            public static class OrderItem {
                private String name;
                private double amount;
            }

        }


    }
}
