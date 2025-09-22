/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/** List of PostLoansDisbursementData */
@Getter
@Setter
public class PostLoansDisbursementData {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate expectedDisbursementDate;

	private Double principal;
}
