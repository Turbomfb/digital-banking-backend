package com.techservices.digitalbanking.customer.domian.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.BaseAppResponse;
import com.techservices.digitalbanking.core.fineract.service.ClientService;
import com.techservices.digitalbanking.customer.domian.CustomerKycTier;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.dto.CustomerTierData;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class CustomerDashboardResponse {
    private Account walletAccount;
    private Account flexAccount;
    private Account lockAccount;


    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @RequiredArgsConstructor
    public static class Account {
        private BigDecimal walletBalance;
        private BigDecimal totalInterestEarned;
        private BigDecimal totalDeposit;
        private BigDecimal totalWithdrawal;
        private Long totalActivePlan;

        public Account (BigDecimal walletBalance, BigDecimal totalInterestEarned, BigDecimal totalDeposit, BigDecimal totalWithdrawal, Long totalActivePlan) {
            this.walletBalance = walletBalance;
            this.totalInterestEarned = totalInterestEarned;
            this.totalDeposit = totalDeposit;
            this.totalWithdrawal = totalWithdrawal;
            this.totalActivePlan = totalActivePlan;
        }
    }
}
