/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  private static final String SECURITY_SCHEME_NAME = "bearerAuth";

  @Bean
  public OpenAPI customOpenAPI() {

    return new OpenAPI()
        .info(
            new Info()
                .title("Digital Banking Core API")
                .version("v1.0.0")
                .description(
                    "Core API for Digital Banking application, providing essential services such as customer management, transactions, and loan processing.")
                .contact(
                    new Contact()
                        .name("Techservice Engineering Team")
                        .email("engineering@techservice.com")
                        .url("https://techservice.com"))
                .license(new License().name("Proprietary").url("https://techservice.com/license")))
        .servers(
            List.of(
                new Server().url("http://localhost:8080").description("Local Dev"),
                new Server().url("https://api-bank-one.dev.turbomfb.com").description("Staging")))
        // Remove global security requirement - let individual endpoints specify their
        // own
        .components(
            new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes(
                    SECURITY_SCHEME_NAME,
                    new SecurityScheme()
                        .name(SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description(
                            "JWT Authorization header using the Bearer scheme. Example: \"Authorization: Bearer {token}\"")
                        .in(SecurityScheme.In.HEADER)));
  }
}
