/* (C)2025 */
package com.techservices.digitalbanking.core.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.configuration.BankConfigurationService;

import lombok.RequiredArgsConstructor;

import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentTime;

@Service
@RequiredArgsConstructor
public class NotificationUtil {
  private final BankConfigurationService bankConfigurationService;

  public static final String DEFAULT_SUBJECT = "Turbo Finance Notification";
  public static final String LOGIN_SUBJECT = "You Just Logged In";
  public static final String DEBIT_SUBJECT = "Account Debit Notification";
  public static final String CREDIT_SUBJECT = "Credit Alert: Funds Received";

  // SMS Template (Plain text)
  private final String LOGIN_NOTIFICATION_SMS = """
    Dear %s,
    you have successfully logged in to your account on %s.
    If this was not you, please contact support immediately on %s or %s as your account might have been compromised.
    """;

  private final String TRANSACTION_NOTIFICATION_SMS = """
    %s ALERT
    Amt: NGN%s
    Bal: NGN%s
    Date: %s
    Time: %s
    Desc: %s
    """;

  // Email HTML Templates
  private final String EMAIL_BASE_TEMPLATE = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>%s</title>
    </head>
    <body style="margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif; background-color: #f5f7fa;">
      <table role="presentation" style="width: 100%%; border-collapse: collapse; background-color: #f5f7fa;">
        <tr>
          <td align="center" style="padding: 40px 20px;">
            <table role="presentation" style="width: 100%%; max-width: 600px; border-collapse: collapse; background-color: #ffffff; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.07);">
              %s
            </table>
          </td>
        </tr>
      </table>
    </body>
    </html>
    """;

  private final String LOGIN_EMAIL_TEMPLATE = """
    <!-- Header -->
    <tr>
      <td style="background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); padding: 40px 30px; text-align: center;">
        <h1 style="margin: 0; color: #ffffff; font-size: 28px; font-weight: 700; letter-spacing: -0.5px;">Turbo Finance</h1>
        <p style="margin: 8px 0 0 0; color: rgba(255, 255, 255, 0.9); font-size: 14px;">Secure Banking at Your Fingertips</p>
      </td>
    </tr>
    
    <!-- Icon -->
    <tr>
      <td style="padding: 40px 30px 20px 30px; text-align: center;">
        <div style="display: inline-block; width: 80px; height: 80px; background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); border-radius: 50%%; display: flex; align-items: center; justify-content: center; box-shadow: 0 8px 16px rgba(102, 126, 234, 0.3);">
          <svg width="40" height="40" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M12 2L2 7V11C2 16.55 6.84 21.74 12 23C17.16 21.74 22 16.55 22 11V7L12 2ZM10 17L5 12L6.41 10.59L10 14.17L17.59 6.58L19 8L10 17Z" fill="white"/>
          </svg>
        </div>
      </td>
    </tr>
    
    <!-- Content -->
    <tr>
      <td style="padding: 0 40px 30px 40px;">
        <h2 style="margin: 0 0 16px 0; color: #1a202c; font-size: 24px; font-weight: 600; text-align: center;">Successful Login</h2>
        <p style="margin: 0 0 24px 0; color: #4a5568; font-size: 16px; line-height: 1.6; text-align: center;">Hi %s,</p>
        <p style="margin: 0 0 24px 0; color: #4a5568; font-size: 16px; line-height: 1.6; text-align: center;">Your account was accessed successfully on <strong style="color: #1a202c;">%s</strong>.</p>
        
        <div style="background-color: #f7fafc; border-left: 4px solid #667eea; padding: 20px; margin: 24px 0; border-radius: 8px;">
          <p style="margin: 0; color: #2d3748; font-size: 14px; line-height: 1.6;">
            <strong>Security Reminder:</strong> If this wasn't you, please contact our support team immediately to secure your account.
          </p>
        </div>
      </td>
    </tr>
    
    <!-- Support Section -->
    <tr>
      <td style="padding: 0 40px 40px 40px;">
        <div style="background: linear-gradient(135deg, #f7fafc 0%%, #edf2f7 100%%); padding: 24px; border-radius: 12px; text-align: center;">
          <p style="margin: 0 0 12px 0; color: #2d3748; font-size: 14px; font-weight: 600;">Need Help?</p>
          <p style="margin: 0 0 4px 0; color: #4a5568; font-size: 14px;">
            📧 <a href="mailto:%s" style="color: #667eea; text-decoration: none;">%s</a>
          </p>
          <p style="margin: 0; color: #4a5568; font-size: 14px;">
            📞 <a href="tel:%s" style="color: #667eea; text-decoration: none;">%s</a>
          </p>
        </div>
      </td>
    </tr>
    
    <!-- Footer -->
    <tr>
      <td style="background-color: #f7fafc; padding: 24px 40px; text-align: center; border-top: 1px solid #e2e8f0;">
        <p style="margin: 0 0 8px 0; color: #718096; font-size: 12px;">© 2025 Turbo Finance. All rights reserved.</p>
        <p style="margin: 0; color: #a0aec0; font-size: 11px;">This is an automated message. Please do not reply to this email.</p>
      </td>
    </tr>
    """;

  private final String DEBIT_EMAIL_TEMPLATE = """
    <!-- Header -->
    <tr>
      <td style="background: linear-gradient(135deg, #f56565 0%%, #c53030 100%%); padding: 40px 30px; text-align: center;">
        <h1 style="margin: 0; color: #ffffff; font-size: 28px; font-weight: 700; letter-spacing: -0.5px;">Turbo Finance</h1>
        <p style="margin: 8px 0 0 0; color: rgba(255, 255, 255, 0.9); font-size: 14px;">Transaction Alert</p>
      </td>
    </tr>
    
    <!-- Icon -->
    <tr>
      <td style="padding: 40px 30px 20px 30px; text-align: center;">
        <div style="display: inline-block; width: 80px; height: 80px; background: linear-gradient(135deg, #f56565 0%%, #c53030 100%%); border-radius: 50%%; box-shadow: 0 8px 16px rgba(245, 101, 101, 0.3);">
          <svg width="40" height="40" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" style="margin-top: 20px;">
            <path d="M19 13H5V11H19V13Z" fill="white"/>
          </svg>
        </div>
      </td>
    </tr>
    
    <!-- Content -->
    <tr>
      <td style="padding: 0 40px 30px 40px;">
        <h2 style="margin: 0 0 24px 0; color: #1a202c; font-size: 24px; font-weight: 600; text-align: center;">Debit Transaction</h2>
        
        <div style="background: linear-gradient(135deg, #fff5f5 0%%, #fed7d7 100%%); border-radius: 12px; padding: 30px; margin-bottom: 24px;">
          <div style="text-align: center; margin-bottom: 20px;">
            <p style="margin: 0 0 8px 0; color: #718096; font-size: 14px; text-transform: uppercase; letter-spacing: 1px;">Amount Debited</p>
            <p style="margin: 0; color: #c53030; font-size: 36px; font-weight: 700;">₦%s</p>
          </div>
          
          <div style="border-top: 2px dashed #feb2b2; padding-top: 20px;">
            <table role="presentation" style="width: 100%%; border-collapse: collapse;">
              <tr>
                <td style="padding: 8px 0; color: #718096; font-size: 14px;">Available Balance</td>
                <td style="padding: 8px 0; color: #1a202c; font-size: 14px; font-weight: 600; text-align: right;">₦%s</td>
              </tr>
              <tr>
                <td style="padding: 8px 0; color: #718096; font-size: 14px;">Date</td>
                <td style="padding: 8px 0; color: #1a202c; font-size: 14px; font-weight: 600; text-align: right;">%s</td>
              </tr>
              <tr>
                <td style="padding: 8px 0; color: #718096; font-size: 14px;">Time</td>
                <td style="padding: 8px 0; color: #1a202c; font-size: 14px; font-weight: 600; text-align: right;">%s</td>
              </tr>
              <tr>
                <td style="padding: 8px 0; color: #718096; font-size: 14px; vertical-align: top;">Description</td>
                <td style="padding: 8px 0; color: #1a202c; font-size: 14px; font-weight: 600; text-align: right; word-break: break-word;">%s</td>
              </tr>
            </table>
          </div>
        </div>
      </td>
    </tr>
    
    <!-- Footer -->
    <tr>
      <td style="background-color: #f7fafc; padding: 24px 40px; text-align: center; border-top: 1px solid #e2e8f0;">
        <p style="margin: 0 0 8px 0; color: #718096; font-size: 12px;">© 2025 Turbo Finance. All rights reserved.</p>
        <p style="margin: 0; color: #a0aec0; font-size: 11px;">This is an automated message. Please do not reply to this email.</p>
      </td>
    </tr>
    """;

  private final String CREDIT_EMAIL_TEMPLATE = """
    <!-- Header -->
    <tr>
      <td style="background: linear-gradient(135deg, #48bb78 0%%, #2f855a 100%%); padding: 40px 30px; text-align: center;">
        <h1 style="margin: 0; color: #ffffff; font-size: 28px; font-weight: 700; letter-spacing: -0.5px;">Turbo Finance</h1>
        <p style="margin: 8px 0 0 0; color: rgba(255, 255, 255, 0.9); font-size: 14px;">Transaction Alert</p>
      </td>
    </tr>
    
    <!-- Icon -->
    <tr>
      <td style="padding: 40px 30px 20px 30px; text-align: center;">
        <div style="display: inline-block; width: 80px; height: 80px; background: linear-gradient(135deg, #48bb78 0%%, #2f855a 100%%); border-radius: 50%%; box-shadow: 0 8px 16px rgba(72, 187, 120, 0.3);">
          <svg width="40" height="40" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" style="margin-top: 20px;">
            <path d="M12 5V19M5 12H19" stroke="white" stroke-width="3" stroke-linecap="round"/>
          </svg>
        </div>
      </td>
    </tr>
    
    <!-- Content -->
    <tr>
      <td style="padding: 0 40px 30px 40px;">
        <h2 style="margin: 0 0 24px 0; color: #1a202c; font-size: 24px; font-weight: 600; text-align: center;">Credit Transaction</h2>
        
        <div style="background: linear-gradient(135deg, #f0fff4 0%%, #c6f6d5 100%%); border-radius: 12px; padding: 30px; margin-bottom: 24px;">
          <div style="text-align: center; margin-bottom: 20px;">
            <p style="margin: 0 0 8px 0; color: #718096; font-size: 14px; text-transform: uppercase; letter-spacing: 1px;">Amount Received</p>
            <p style="margin: 0; color: #2f855a; font-size: 36px; font-weight: 700;">₦%s</p>
          </div>
          
          <div style="border-top: 2px dashed #9ae6b4; padding-top: 20px;">
            <table role="presentation" style="width: 100%%; border-collapse: collapse;">
              <tr>
                <td style="padding: 8px 0; color: #718096; font-size: 14px;">Available Balance</td>
                <td style="padding: 8px 0; color: #1a202c; font-size: 14px; font-weight: 600; text-align: right;">₦%s</td>
              </tr>
              <tr>
                <td style="padding: 8px 0; color: #718096; font-size: 14px;">Date</td>
                <td style="padding: 8px 0; color: #1a202c; font-size: 14px; font-weight: 600; text-align: right;">%s</td>
              </tr>
              <tr>
                <td style="padding: 8px 0; color: #718096; font-size: 14px;">Time</td>
                <td style="padding: 8px 0; color: #1a202c; font-size: 14px; font-weight: 600; text-align: right;">%s</td>
              </tr>
              <tr>
                <td style="padding: 8px 0; color: #718096; font-size: 14px; vertical-align: top;">Description</td>
                <td style="padding: 8px 0; color: #1a202c; font-size: 14px; font-weight: 600; text-align: right; word-break: break-word;">%s</td>
              </tr>
            </table>
          </div>
        </div>
      </td>
    </tr>
    
    <!-- Footer -->
    <tr>
      <td style="background-color: #f7fafc; padding: 24px 40px; text-align: center; border-top: 1px solid #e2e8f0;">
        <p style="margin: 0 0 8px 0; color: #718096; font-size: 12px;">© 2025 Turbo Finance. All rights reserved.</p>
        <p style="margin: 0; color: #a0aec0; font-size: 11px;">This is an automated message. Please do not reply to this email.</p>
      </td>
    </tr>
    """;

  // Public methods for SMS (plain text)
  public String getLoginNotificationTemplate(String customerName, String loginTime) {
    return String.format(LOGIN_NOTIFICATION_SMS, customerName, loginTime,
        bankConfigurationService.getBankSupportEmail(), bankConfigurationService.getBankSupportPhone());
  }

  public String getTransactionNotificationTemplate(String transactionType, String amount, BigDecimal balance,
      String description) {
    String formattedBalance = AppUtil.formatAmount(balance);
    return String.format(TRANSACTION_NOTIFICATION_SMS, transactionType, amount, formattedBalance,
        getCurrentDate(), getCurrentTime(), description);
  }

  // Public methods for HTML Email
  public String getLoginEmailTemplate(String customerName, String loginTime) {
    String emailContent = String.format(LOGIN_EMAIL_TEMPLATE,
        customerName,
        loginTime,
        bankConfigurationService.getBankSupportEmail(),
        bankConfigurationService.getBankSupportEmail(),
        bankConfigurationService.getBankSupportPhone(),
        bankConfigurationService.getBankSupportPhone()
    );
    return String.format(EMAIL_BASE_TEMPLATE, "Login Alert - Turbo Finance", emailContent);
  }

  public String getDebitEmailTemplate(String amount, BigDecimal balance, String description) {
    String formattedBalance = AppUtil.formatAmount(balance);
    String emailContent = String.format(DEBIT_EMAIL_TEMPLATE,
        amount,
        formattedBalance,
        getCurrentDate(),
        getCurrentTime(),
        description
    );
    return String.format(EMAIL_BASE_TEMPLATE, "Debit Alert - Turbo Finance", emailContent);
  }

  public String getCreditEmailTemplate(String amount, BigDecimal balance, String description) {
    String formattedBalance = AppUtil.formatAmount(balance);
    String emailContent = String.format(CREDIT_EMAIL_TEMPLATE,
        amount,
        formattedBalance,
        getCurrentDate(),
        getCurrentTime(),
        description
    );
    return String.format(EMAIL_BASE_TEMPLATE, "Credit Alert - Turbo Finance", emailContent);
  }
}