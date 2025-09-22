/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/** List of PostLoansLoanIdDisbursementData */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostLoansLoanIdDisbursementData {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate expectedDisbursementDate;

	private BigDecimal principal;
}
