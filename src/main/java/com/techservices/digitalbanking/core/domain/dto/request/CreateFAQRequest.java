/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import java.time.LocalDate;

import com.techservices.digitalbanking.core.domain.enums.ProductType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateFAQRequest {
	@NotBlank(message = "Question is required")
	private String question;

	@NotBlank(message = "Answer is required")
	private String answer;

	@NotNull(message = "Product type is required")
	private ProductType product;

	private Boolean webEnable = true;
	private Boolean mobileEnable = true;
	private LocalDate launchDate;
	private String channel;
}
