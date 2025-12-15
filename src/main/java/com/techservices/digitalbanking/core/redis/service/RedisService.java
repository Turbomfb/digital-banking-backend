/* (C)2025 */
package com.techservices.digitalbanking.core.redis.service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.techservices.digitalbanking.core.configuration.BankConfigurationService;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.enums.NotificationChannel;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.redis.configuration.RedisProperty;
import com.techservices.digitalbanking.core.service.NotificationService;
import com.techservices.digitalbanking.core.service.SesSmtpEmailSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService {

  private final RedisProperty redisProperty;
  private final NotificationService notificationService;
  private final SystemProperty systemProperty;
  private final RedisTemplate<String, Object> redisTemplate;
  private final SesSmtpEmailSender emailSender;
  private final BankConfigurationService bankConfigurationService;

  public void save(String key, Object value) {

    redisTemplate.opsForValue().set(key, value, Long.parseLong(redisProperty.getTtlMinutes()), TimeUnit.MINUTES);
  }

  public void saveWithCustomExpiration(String key, Object value, Integer expirationInMinutes) {

    redisTemplate.opsForValue().set(key, value, expirationInMinutes.longValue(), TimeUnit.MINUTES);
  }

  public Object get(String key) {

    return redisTemplate.opsForValue().get(key);
  }

  public <T> T getAndRefresh(String key, Class<T> clazz) {

    Object rawValue = redisTemplate.opsForValue().get(key);
    if (rawValue != null) {
      redisTemplate.expire(key, Duration.ofMinutes(Long.parseLong(redisProperty.getTtlMinutes())));
      return clazz.cast(rawValue);
    }
    return null;
  }

  public void delete(String key) {

    redisTemplate.delete(key);
  }

  public void increment(String key) {

    redisTemplate.opsForValue().increment(key);
  }

  public void expire(String key) {

    redisTemplate.expire(key, Long.parseLong(redisProperty.getTtlMinutes()), TimeUnit.MINUTES);
  }

  public boolean exists(String key) {

    return Boolean.TRUE.equals(redisTemplate.hasKey(key));
  }

  public boolean isOtpValid(OtpDto otpDto, OtpType otpType, String otp) {

    return (otpDto.getOtp().equals(otp) || isDevEnvAndIsDefaultCode(otp)) && otpDto.getOtpType().equals(otpType);
  }

  private boolean isDevEnvAndIsDefaultCode(String otp) {
    return "123456".equals(otp) && StringUtils.equalsIgnoreCase(systemProperty.getActiveProfile(), "dev");
  }

  public void save(Object request, OtpType otpType, String uniqueId) {

    OtpDto otpDto = new OtpDto();
    otpDto.setUniqueId(uniqueId);
    otpDto.setOtpType(otpType);
    int otp = new SecureRandom().nextInt(900000) + 100000;
    otpDto.setOtp(String.valueOf(otp));
    otpDto.setData(request);
    this.save(otpDto.getUniqueId(), otpDto);
  }

  public OtpDto generateOtpRequest(Object request, OtpType otpType, NotificationRequestDto notificationRequestDto,
      BigDecimal amount) {

    OtpDto otpDto = new OtpDto();
    String uniqueId = UUID.randomUUID().toString();
    otpDto.setUniqueId(uniqueId);
    otpDto.setOtpType(otpType);
    int otp = new SecureRandom().nextInt(900000) + 100000;
    otpDto.setOtp(String.valueOf(otp));
    otpDto.setData(request);
    this.save(otpDto.getUniqueId(), otpDto);
    log.info("Sending OTP notification for uniqueId: {}", notificationRequestDto);

    if (notificationRequestDto != null) {
      // Build messages based on OTP type
      String smsMessage = buildSmsMessage(otpType, otpDto.getOtp(), amount);
      String emailSubject = buildEmailSubject(otpType);
      String emailHtml = buildOtpEmailHtml(otpType, otpDto.getOtp(), amount);

      if (notificationRequestDto.getChannel() != null) {
        log.info("Sending OTP notification for channel: {}", notificationRequestDto.getChannel());

        if (notificationRequestDto.getChannel().equals(NotificationChannel.SMS)) {
          notificationService.sendSms(notificationRequestDto.getPhoneNumber(), smsMessage);

        } else if (notificationRequestDto.getChannel().equals(NotificationChannel.EMAIL)) {
          sendOtpEmail(notificationRequestDto.getEmailAddress(), emailSubject, smsMessage, emailHtml);

        } else if (notificationRequestDto.getChannel().equals(NotificationChannel.SMS_AND_EMAIL)) {
          notificationService.sendSms(notificationRequestDto.getPhoneNumber(), smsMessage);
          sendOtpEmail(notificationRequestDto.getEmailAddress(), emailSubject, smsMessage, emailHtml);
        }
      }
    }
    return otpDto;
  }

  private String buildSmsMessage(OtpType otpType, String otp, BigDecimal amount) {
    return switch (otpType) {
      case ONBOARDING -> "Welcome to our platform! Your OTP for onboarding is: " + otp;
      case KYC_UPGRADE -> "Your OTP for upgrading your KYC is: " + otp;
      case FORGOT_PASSWORD -> "You requested a password reset. Your OTP is: " + otp;
      case TRANSFER -> String.format("You have initiated a transfer of %s. Your one-time password (OTP) is: %s",
          amount, otp);
      case ADD_BENEFICIARY -> String.format(
          "You are adding a new beneficiary. Your OTP to complete this action is: %s", otp);
      case DELETE_BENEFICIARY -> String.format(
          "You are about to delete a beneficiary. Your OTP to confirm this action is: %s", otp);
      case UPDATE_BENEFICIARY -> String.format(
          "You are about to update a beneficiary. Your OTP to confirm this action is: %s", otp);
    };
  }

  private String buildEmailSubject(OtpType otpType) {
    return switch (otpType) {
      case ONBOARDING -> "Welcome to Turbo Finance - Verify Your Account";
      case KYC_UPGRADE -> "KYC Upgrade Verification Code";
      case FORGOT_PASSWORD -> "Password Reset Verification Code";
      case TRANSFER -> "Transfer Verification Code";
      case ADD_BENEFICIARY -> "Add Beneficiary Verification Code";
      case DELETE_BENEFICIARY -> "Delete Beneficiary Verification Code";
      case UPDATE_BENEFICIARY -> "Update Beneficiary Verification Code";
    };
  }

  private void sendOtpEmail(String emailAddress, String subject, String plainText, String htmlContent) {
    try {
      emailSender.sendEmail(
          systemProperty.getSmtpSenderEmail(),
          emailAddress,
          subject,
          plainText,
          htmlContent
      );
      log.info("OTP email sent successfully to {}", emailAddress);
    } catch (Exception e) {
      log.error("Failed to send OTP email to {}", emailAddress, e);
    }
  }

  private String buildOtpEmailHtml(OtpType otpType, String otp, BigDecimal amount) {
    String title = getOtpTitle(otpType);
    String description = getOtpDescription(otpType, amount);
    String gradientColors = getOtpGradientColors(otpType);

    return String.format("""
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
			          
			          <!-- Header -->
			          <tr>
			            <td style="background: linear-gradient(135deg, %s); padding: 40px 30px; text-align: center;">
			              <h1 style="margin: 0; color: #ffffff; font-size: 28px; font-weight: 700; letter-spacing: -0.5px;">Turbo Finance</h1>
			              <p style="margin: 8px 0 0 0; color: rgba(255, 255, 255, 0.9); font-size: 14px;">Secure Banking at Your Fingertips</p>
			            </td>
			          </tr>
			          
			          <!-- Content -->
			          <tr>
			            <td style="padding: 40px 40px 30px 40px;">
			              <h2 style="margin: 0 0 16px 0; color: #1a202c; font-size: 24px; font-weight: 600; text-align: center;">%s</h2>
			              <p style="margin: 0 0 24px 0; color: #4a5568; font-size: 16px; line-height: 1.6; text-align: center;">%s</p>
			              
			              <!-- OTP Box -->
			              <div style="background: linear-gradient(135deg, #f7fafc 0%%, #edf2f7 100%%); border-radius: 12px; padding: 30px; margin: 24px 0; text-align: center;">
			                <p style="margin: 0 0 12px 0; color: #718096; font-size: 14px; text-transform: uppercase; letter-spacing: 1px; font-weight: 600;">Your Verification Code</p>
			                <div style="background-color: #ffffff; border: 2px dashed #cbd5e0; border-radius: 8px; padding: 20px; margin: 16px auto; display: inline-block;">
			                  <p style="margin: 0; color: #1a202c; font-size: 36px; font-weight: 700; letter-spacing: 8px; font-family: 'Courier New', monospace;">%s</p>
			                </div>
			                <p style="margin: 12px 0 0 0; color: #718096; font-size: 13px;">This code expires in %s minutes</p>
			              </div>
			              
			              <!-- Security Notice -->
			              <div style="background-color: #fffaf0; border-left: 4px solid #ed8936; padding: 16px; margin: 24px 0; border-radius: 8px;">
			                <p style="margin: 0; color: #744210; font-size: 14px; line-height: 1.6;">
			                  <strong>⚠️ Security Notice:</strong> Never share this code with anyone. Turbo Finance will never ask for your verification code via phone or email.
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
        title,
        gradientColors,
        title,
        description,
        otp,
        redisProperty.getTtlMinutes(),
        bankConfigurationService.getBankSupportEmail(),
        bankConfigurationService.getBankSupportEmail(),
        bankConfigurationService.getBankSupportPhone(),
        bankConfigurationService.getBankSupportPhone()
    );
  }

  private String getOtpTitle(OtpType otpType) {
    return switch (otpType) {
      case ONBOARDING -> "Welcome to Turbo Finance";
      case KYC_UPGRADE -> "KYC Upgrade Verification";
      case FORGOT_PASSWORD -> "Password Reset Request";
      case TRANSFER -> "Transfer Authorization";
      case ADD_BENEFICIARY -> "Add Beneficiary";
      case DELETE_BENEFICIARY -> "Delete Beneficiary";
      case UPDATE_BENEFICIARY -> "Update Beneficiary";
    };
  }

  private String getOtpDescription(OtpType otpType, BigDecimal amount) {
    return switch (otpType) {
      case ONBOARDING -> "Thank you for joining us! Please use the verification code below to complete your account setup.";
      case KYC_UPGRADE -> "You're upgrading your KYC level. Please verify your identity using the code below.";
      case FORGOT_PASSWORD -> "We received a request to reset your password. Use the code below to proceed.";
      case TRANSFER -> String.format("You're about to transfer ₦%s. Please verify this transaction with the code below.",
          amount != null ? amount.toString() : "0.00");
      case ADD_BENEFICIARY -> "You're adding a new beneficiary to your account. Please confirm this action with the code below.";
      case DELETE_BENEFICIARY -> "You're about to remove a beneficiary. Please confirm this action with the code below.";
      case UPDATE_BENEFICIARY -> "You're updating beneficiary information. Please confirm this action with the code below.";
    };
  }

  private String getOtpGradientColors(OtpType otpType) {
    return switch (otpType) {
      case ONBOARDING -> "#667eea 0%, #764ba2 100%";
      case KYC_UPGRADE -> "#4299e1 0%, #3182ce 100%";
      case FORGOT_PASSWORD -> "#ed8936 0%, #dd6b20 100%";
      case TRANSFER -> "#667eea 0%, #764ba2 100%";
      case ADD_BENEFICIARY, UPDATE_BENEFICIARY -> "#48bb78 0%, #38a169 100%";
      case DELETE_BENEFICIARY -> "#f56565 0%, #c53030 100%";
    };
  }

  public OtpDto validateOtp(String uniqueId, String otp, OtpType otpType) {

    OtpDto otpDto = retrieveOtpDto(uniqueId);
    if (otpDto == null) {
      throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
    }
    if (!this.isOtpValid(otpDto, otpType, otp)) {
      throw new ValidationException("invalid.otp", "Invalid OTP provided.");
    }
    this.delete(otpDto.getUniqueId());
    return otpDto;
  }

  public OtpDto validateOtpWithoutDeletingRecord(String uniqueId, String otp, OtpType otpType) {

    OtpDto otpDto = retrieveOtpDto(uniqueId);
    if (otpDto == null) {
      throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
    }
    if (!this.isOtpValid(otpDto, otpType, otp)) {
      throw new ValidationException("invalid.otp", "Invalid OTP provided.");
    }
    otpDto.setValidated(true);
    log.info("OTP validated successfully for uniqueId: {}", uniqueId);
    this.save(uniqueId, otpDto);
    return otpDto;
  }

  public OtpDto validateOtpWithoutOtp(String uniqueId) {

    OtpDto otpDto = retrieveOtpDto(uniqueId);
    if (otpDto == null) {
      throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
    }
    if (!otpDto.isValidated()) {
      throw new ValidationException("otp.not.validated", "OTP has not been validated yet.");
    }
    this.delete(otpDto.getUniqueId());
    return otpDto;
  }

  @SuppressWarnings("unchecked")
  public <T> T retrieveData(String uniqueId, Class<T> clazz) {

    OtpDto otpDto = retrieveOtpDto(uniqueId);
    if (otpDto == null) {
      throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
    }
    Object data = otpDto.getData();
    return clazz.cast(data);
  }

  public OtpDto retrieveOtpDto(String uniqueId) {

    if (uniqueId == null) {
      throw new ValidationException("uniqueId.required", "Unique ID is required for OTP verification.");
    }
    return this.getAndRefresh(uniqueId, OtpDto.class);
  }
}