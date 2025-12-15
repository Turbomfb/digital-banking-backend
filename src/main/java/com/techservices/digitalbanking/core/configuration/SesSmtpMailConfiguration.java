/* (C)2025 */
package com.techservices.digitalbanking.core.configuration;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SesSmtpMailConfiguration {

  private final SystemProperty systemProperty;

  @Bean
  public Session smtpMailSession() {

    Properties props = new Properties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.host", systemProperty.getSmtpHost());
    props.put("mail.smtp.port", systemProperty.getSmtpPort());
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.starttls.required", "true");

    return Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
            systemProperty.getSmtpUsername(),
            systemProperty.getSmtpPassword()
        );
      }
    });
  }
}
