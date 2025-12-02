/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto;

import java.time.LocalDate;

import com.techservices.digitalbanking.core.domain.data.model.FAQ;
import com.techservices.digitalbanking.core.domain.enums.ProductType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FAQDto {
	private Long id;
	private String question;
	private String answer;
	private ProductType product;
	private Boolean webEnable;
	private Boolean mobileEnable;
	private LocalDate launchDate;

	// Constructors
	public FAQDto() {
	}

	public FAQDto(FAQ faq) {

		this.id = faq.getId();
		this.question = faq.getQuestion();
		this.answer = faq.getAnswer();
		this.product = faq.getProduct();
		this.webEnable = faq.getWebEnable();
		this.mobileEnable = faq.getMobileEnable();
		this.launchDate = faq.getLaunchDate();
	}
}
