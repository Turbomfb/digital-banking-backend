/* (C)2025 */
package com.techservices.digitalbanking.core.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/privacy-policy")
@RequiredArgsConstructor
public class PrivacyPolicyApiResource {

  @GetMapping
  public ResponseEntity<String> getPrivacyPolicy() {

    try {
      ClassPathResource resource = new ClassPathResource("static/privacy-policy.html");
      String htmlContent =
          new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

      return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(htmlContent);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error loading privacy policy");
    }
  }
}
