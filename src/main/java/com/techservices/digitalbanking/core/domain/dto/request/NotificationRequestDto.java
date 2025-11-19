/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import com.techservices.digitalbanking.core.domain.dto.response.IdentityVerificationResponse;
import com.techservices.digitalbanking.core.domain.enums.NotificationChannel;
import com.techservices.digitalbanking.core.util.AppUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
@ToString
public class NotificationRequestDto {
  private String phoneNumber;
  private String emailAddress;
  private NotificationChannel channel;

  public NotificationRequestDto(String phoneNumber, String emailAddress) {

    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    if (StringUtils.isNotBlank(emailAddress) && StringUtils.isBlank(phoneNumber)) {
      this.channel = NotificationChannel.EMAIL;
    } else if (StringUtils.isNotBlank(phoneNumber) && StringUtils.isBlank(emailAddress)) {
      this.channel = NotificationChannel.SMS;
    } else if (StringUtils.isNotBlank(emailAddress) && StringUtils.isNotBlank(phoneNumber)) {
      this.channel = NotificationChannel.SMS_AND_EMAIL;
    }
  }

  public NotificationRequestDto(IdentityVerificationResponse verificationResponse) {

    if (verificationResponse != null && verificationResponse.getData() != null) {
      this.phoneNumber = verificationResponse.getData().getMobile();
      this.emailAddress = verificationResponse.getData().getEmail();
      if (StringUtils.isNotBlank(emailAddress) && StringUtils.isBlank(phoneNumber)) {
        this.channel = NotificationChannel.EMAIL;
      } else if (StringUtils.isNotBlank(phoneNumber) && StringUtils.isBlank(emailAddress)) {
        this.channel = NotificationChannel.SMS;
      } else if (StringUtils.isNotBlank(emailAddress) && StringUtils.isNotBlank(phoneNumber)) {
        this.channel = NotificationChannel.SMS_AND_EMAIL;
      }
    }
  }

  public String getOtpResponseMessage() {

    String message = "We sent an OTP to ";
    if (StringUtils.isNotBlank(phoneNumber) && StringUtils.isBlank(emailAddress))
      message += AppUtil.maskPhoneNumber(phoneNumber);
    else if (StringUtils.isNotBlank(emailAddress) && StringUtils.isBlank(phoneNumber))
      message += AppUtil.maskEmailAddress(emailAddress);
    else if (StringUtils.isNotBlank(emailAddress) && StringUtils.isNotBlank(phoneNumber))
      message +=
          AppUtil.maskPhoneNumber(phoneNumber) + " and " + AppUtil.maskEmailAddress(emailAddress);
    else message += "your registered contact details";
    return message;
  }
}
