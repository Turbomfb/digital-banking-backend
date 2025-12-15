/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.Session;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SesSmtpEmailSender {

  private final Session smtpMailSession;

  public void sendEmail(
      String from,
      String to,
      String subject,
      String textBody,
      String htmlBody) {

    try {
      MimeMessage message = new MimeMessage(smtpMailSession);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      message.setSubject(subject, "UTF-8");

      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setText(textBody, "UTF-8");

      MimeBodyPart htmlPart = new MimeBodyPart();
      htmlPart.setContent(htmlBody, "text/html; charset=UTF-8");

      Multipart multipart = new MimeMultipart("alternative");
      multipart.addBodyPart(textPart);
      multipart.addBodyPart(htmlPart);

      message.setContent(multipart);

      Transport.send(message);
      log.info("Email successfully sent to {}", to);

    } catch (MessagingException e) {
      log.error("Failed to send email to {}", to, e);
      throw new RuntimeException("Email sending failed", e);
    }
  }
}
