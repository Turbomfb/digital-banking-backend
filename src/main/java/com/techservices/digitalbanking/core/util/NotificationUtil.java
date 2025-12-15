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
        Dear %s, you have successfully logged in to your account on %s. 
        If this was not you, please contact support immediately on %s or %s 
        as your account might have been compromised.
        """;

  private final String TRANSACTION_NOTIFICATION_SMS = """
        %s ALERT
        Amt: NGN%s
        Bal: NGN%s
        Date: %s
        Time: %s
        Desc: %s
        """;

  // Public methods for SMS (plain text)
  public String getLoginNotificationTemplate(String customerName, String loginTime) {
    return String.format(LOGIN_NOTIFICATION_SMS,
        customerName,
        loginTime,
        bankConfigurationService.getBankSupportEmail(),
        bankConfigurationService.getBankSupportPhone());
  }

  public String getTransactionNotificationTemplate(String transactionType, String amount,
      BigDecimal balance, String description) {
    String formattedBalance = AppUtil.formatAmount(balance);
    return String.format(TRANSACTION_NOTIFICATION_SMS,
        transactionType,
        amount,
        formattedBalance,
        getCurrentDate(),
        getCurrentTime(),
        description);
  }

  // Public methods for HTML Email
  public String getLoginEmailTemplate(String customerName, String loginTime) {
    return buildLoginEmail(
        customerName,
        loginTime,
        bankConfigurationService.getBankSupportEmail(),
        bankConfigurationService.getBankSupportPhone()
    );
  }

  public String getDebitEmailTemplate(String amount, BigDecimal balance, String description) {
    String formattedBalance = AppUtil.formatAmount(balance);
    return buildDebitEmail(
        amount,
        formattedBalance,
        getCurrentDate(),
        getCurrentTime(),
        description
    );
  }

  public String getCreditEmailTemplate(String amount, BigDecimal balance, String description) {
    String formattedBalance = AppUtil.formatAmount(balance);
    return buildCreditEmail(
        amount,
        formattedBalance,
        getCurrentDate(),
        getCurrentTime(),
        description
    );
  }

  // Private email builders
  private String buildLoginEmail(String customerName, String loginTime, String supportEmail, String supportPhone) {
    return String.format("""
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Login Alert</title>
        </head>
        <body style="margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif; background-color: #f5f7fa;">
          <table role="presentation" style="width: 100%%; border-collapse: collapse; background-color: #f5f7fa;">
            <tr>
              <td align="center" style="padding: 40px 20px;">
                <table role="presentation" style="width: 100%%; max-width: 600px; border-collapse: collapse; background-color: #ffffff; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.07);">
                  
                  <!-- Header -->
                  <tr>
                    <td style="background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%); padding: 40px 30px; text-align: center;">
                      <h1 style="margin: 0; color: #ffffff; font-size: 28px; font-weight: 700; letter-spacing: -0.5px;">Turbo Finance</h1>
                      <p style="margin: 8px 0 0 0; color: rgba(255, 255, 255, 0.9); font-size: 14px;">Secure Banking at Your Fingertips</p>
                    </td>
                  </tr>
                  
                  <!-- Content -->
                  <tr>
                    <td style="padding: 40px 40px 30px 40px;">
                      <div style="background-color: #e8f5e9; border-left: 4px solid #4caf50; padding: 16px; margin-bottom: 24px; border-radius: 8px;">
                        <h2 style="margin: 0; color: #2e7d32; font-size: 20px; font-weight: 600;">✓ Successful Login</h2>
                      </div>
                      
                      <p style="margin: 0 0 16px 0; color: #1a202c; font-size: 16px; line-height: 1.6;">
                        Hi <strong>%s</strong>,
                      </p>
                      
                      <p style="margin: 0 0 24px 0; color: #4a5568; font-size: 15px; line-height: 1.6;">
                        Your account was accessed successfully on <strong>%s</strong>.
                      </p>
                      
                      <!-- Security Notice -->
                      <div style="background-color: #fffaf0; border-left: 4px solid #ed8936; padding: 16px; margin: 24px 0; border-radius: 8px;">
                        <p style="margin: 0; color: #744210; font-size: 14px; line-height: 1.6;">
                          <strong>⚠️ Security Notice:</strong> If this wasn't you, please contact our support team immediately to secure your account.
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
                  
                </table>
              </td>
            </tr>
          </table>
        </body>
        </html>
        """,
        customerName,
        loginTime,
        supportEmail,
        supportEmail,
        supportPhone,
        supportPhone
    );
  }

  private String buildDebitEmail(String amount, String balance, String date, String time, String description) {
    return String.format("""
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Debit Alert</title>
        </head>
        <body style="margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif; background-color: #f5f7fa;">
          <table role="presentation" style="width: 100%%; border-collapse: collapse; background-color: #f5f7fa;">
            <tr>
              <td align="center" style="padding: 40px 20px;">
                <table role="presentation" style="width: 100%%; max-width: 600px; border-collapse: collapse; background-color: #ffffff; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.07);">
                  
                  <!-- Header -->
                  <tr>
                    <td style="background: linear-gradient(135deg, #f56565 0%%, #c53030 100%%); padding: 40px 30px; text-align: center;">
                      <h1 style="margin: 0; color: #ffffff; font-size: 28px; font-weight: 700; letter-spacing: -0.5px;">Turbo Finance</h1>
                      <p style="margin: 8px 0 0 0; color: rgba(255, 255, 255, 0.9); font-size: 14px;">Transaction Alert</p>
                    </td>
                  </tr>
                  
                  <!-- Content -->
                  <tr>
                    <td style="padding: 40px 40px 30px 40px;">
                      <div style="background-color: #ffebee; border-left: 4px solid #f44336; padding: 16px; margin-bottom: 24px; border-radius: 8px;">
                        <h2 style="margin: 0; color: #c62828; font-size: 20px; font-weight: 600;">Debit Transaction</h2>
                      </div>
                      
                      <!-- Amount Box -->
                      <div style="background: linear-gradient(135deg, #fff5f5 0%%, #fed7d7 100%%); border-radius: 12px; padding: 24px; margin: 24px 0; text-align: center;">
                        <p style="margin: 0 0 8px 0; color: #718096; font-size: 14px; text-transform: uppercase; letter-spacing: 1px; font-weight: 600;">Amount Debited</p>
                        <p style="margin: 0; color: #c53030; font-size: 36px; font-weight: 700; letter-spacing: -1px;">₦%s</p>
                      </div>
                      
                      <!-- Transaction Details -->
                      <table role="presentation" style="width: 100%%; border-collapse: collapse; margin: 24px 0;">
                        <tr>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0;">
                            <p style="margin: 0; color: #718096; font-size: 14px;">Available Balance</p>
                          </td>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0; text-align: right;">
                            <p style="margin: 0; color: #1a202c; font-size: 14px; font-weight: 600;">₦%s</p>
                          </td>
                        </tr>
                        <tr>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0;">
                            <p style="margin: 0; color: #718096; font-size: 14px;">Date</p>
                          </td>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0; text-align: right;">
                            <p style="margin: 0; color: #1a202c; font-size: 14px;">%s</p>
                          </td>
                        </tr>
                        <tr>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0;">
                            <p style="margin: 0; color: #718096; font-size: 14px;">Time</p>
                          </td>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0; text-align: right;">
                            <p style="margin: 0; color: #1a202c; font-size: 14px;">%s</p>
                          </td>
                        </tr>
                        <tr>
                          <td style="padding: 12px 0;">
                            <p style="margin: 0; color: #718096; font-size: 14px;">Description</p>
                          </td>
                          <td style="padding: 12px 0; text-align: right;">
                            <p style="margin: 0; color: #1a202c; font-size: 14px;">%s</p>
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                  
                  <!-- Footer -->
                  <tr>
                    <td style="background-color: #f7fafc; padding: 24px 40px; text-align: center; border-top: 1px solid #e2e8f0;">
                      <p style="margin: 0 0 8px 0; color: #718096; font-size: 12px;">© 2025 Turbo Finance. All rights reserved.</p>
                      <p style="margin: 0; color: #a0aec0; font-size: 11px;">This is an automated message. Please do not reply to this email.</p>
                    </td>
                  </tr>
                  
                </table>
              </td>
            </tr>
          </table>
        </body>
        </html>
        """,
        amount,
        balance,
        date,
        time,
        description
    );
  }

  private String buildCreditEmail(String amount, String balance, String date, String time, String description) {
    return String.format("""
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Credit Alert</title>
        </head>
        <body style="margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif; background-color: #f5f7fa;">
          <table role="presentation" style="width: 100%%; border-collapse: collapse; background-color: #f5f7fa;">
            <tr>
              <td align="center" style="padding: 40px 20px;">
                <table role="presentation" style="width: 100%%; max-width: 600px; border-collapse: collapse; background-color: #ffffff; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.07);">
                  
                  <!-- Header -->
                  <tr>
                    <td style="background: linear-gradient(135deg, #48bb78 0%%, #38a169 100%%); padding: 40px 30px; text-align: center;">
                      <h1 style="margin: 0; color: #ffffff; font-size: 28px; font-weight: 700; letter-spacing: -0.5px;">Turbo Finance</h1>
                      <p style="margin: 8px 0 0 0; color: rgba(255, 255, 255, 0.9); font-size: 14px;">Transaction Alert</p>
                    </td>
                  </tr>
                  
                  <!-- Content -->
                  <tr>
                    <td style="padding: 40px 40px 30px 40px;">
                      <div style="background-color: #e8f5e9; border-left: 4px solid #4caf50; padding: 16px; margin-bottom: 24px; border-radius: 8px;">
                        <h2 style="margin: 0; color: #2e7d32; font-size: 20px; font-weight: 600;">Credit Transaction</h2>
                      </div>
                      
                      <!-- Amount Box -->
                      <div style="background: linear-gradient(135deg, #f0fff4 0%%, #c6f6d5 100%%); border-radius: 12px; padding: 24px; margin: 24px 0; text-align: center;">
                        <p style="margin: 0 0 8px 0; color: #718096; font-size: 14px; text-transform: uppercase; letter-spacing: 1px; font-weight: 600;">Amount Received</p>
                        <p style="margin: 0; color: #38a169; font-size: 36px; font-weight: 700; letter-spacing: -1px;">₦%s</p>
                      </div>
                      
                      <!-- Transaction Details -->
                      <table role="presentation" style="width: 100%%; border-collapse: collapse; margin: 24px 0;">
                        <tr>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0;">
                            <p style="margin: 0; color: #718096; font-size: 14px;">Available Balance</p>
                          </td>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0; text-align: right;">
                            <p style="margin: 0; color: #1a202c; font-size: 14px; font-weight: 600;">₦%s</p>
                          </td>
                        </tr>
                        <tr>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0;">
                            <p style="margin: 0; color: #718096; font-size: 14px;">Date</p>
                          </td>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0; text-align: right;">
                            <p style="margin: 0; color: #1a202c; font-size: 14px;">%s</p>
                          </td>
                        </tr>
                        <tr>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0;">
                            <p style="margin: 0; color: #718096; font-size: 14px;">Time</p>
                          </td>
                          <td style="padding: 12px 0; border-bottom: 1px solid #e2e8f0; text-align: right;">
                            <p style="margin: 0; color: #1a202c; font-size: 14px;">%s</p>
                          </td>
                        </tr>
                        <tr>
                          <td style="padding: 12px 0;">
                            <p style="margin: 0; color: #718096; font-size: 14px;">Description</p>
                          </td>
                          <td style="padding: 12px 0; text-align: right;">
                            <p style="margin: 0; color: #1a202c; font-size: 14px;">%s</p>
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                  
                  <!-- Footer -->
                  <tr>
                    <td style="background-color: #f7fafc; padding: 24px 40px; text-align: center; border-top: 1px solid #e2e8f0;">
                      <p style="margin: 0 0 8px 0; color: #718096; font-size: 12px;">© 2025 Turbo Finance. All rights reserved.</p>
                      <p style="margin: 0; color: #a0aec0; font-size: 11px;">This is an automated message. Please do not reply to this email.</p>
                    </td>
                  </tr>
                  
                </table>
              </td>
            </tr>
          </table>
        </body>
        </html>
        """,
        amount,
        balance,
        date,
        time,
        description
    );
  }
}