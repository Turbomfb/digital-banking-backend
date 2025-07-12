package com.techservices.digitalbanking.core.domain.dto.request;

import com.techservices.digitalbanking.core.domain.enums.NotificationChannel;
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
}
