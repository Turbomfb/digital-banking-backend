/* Developed by MKAN Engineering (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class LoanApplicationResponse {
	private String loanId;
	private String customerId;
	private String message;
}
