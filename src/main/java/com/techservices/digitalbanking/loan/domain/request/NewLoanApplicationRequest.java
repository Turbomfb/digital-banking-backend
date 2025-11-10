/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewLoanApplicationRequest {
	private String productId;
	private BigDecimal amount;
	private Long duration;

	private String employerCategory;
	private String employerSector;
	private String employerEmail;
	private String employerName;
}
