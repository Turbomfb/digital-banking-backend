package com.techservices.digitalbanking.core.util;

import com.techservices.digitalbanking.core.configuration.BankConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentTime;

@Service
@RequiredArgsConstructor
public class NotificationUtil {
    private final BankConfigurationService bankConfigurationService;

    private final String LOGIN_NOTIFICATION_TEMPLATE = """
            Dear %s, 
            you have successfully logged in to your account on %s. 
            If this was not you, please contact support immediately on %s or %s as your account might have been compromised.
            """;

    public String getLoginNotificationTemplate(String customerName, String loginTime) {
        return String.format(LOGIN_NOTIFICATION_TEMPLATE, customerName, loginTime, bankConfigurationService.getBankSupportEmail(), bankConfigurationService.getBankSupportPhone());
    }

    private final String TRANSACTION_NOTIFICATION_TEMPLATE = """
            %s ALERT
            Amt: NGN%s
            Bal: NGN%s
            Date: %s
            Time: %s
            Desc: %s
            """;

    public String getTransactionNotificationTemplate(String transactionType, String amount, BigDecimal balance, String description) {
        String formattedBalance = AppUtil.formatAmount(balance);
        return String.format(TRANSACTION_NOTIFICATION_TEMPLATE, transactionType, amount, formattedBalance, getCurrentDate(), getCurrentTime(), description);
    }
}
