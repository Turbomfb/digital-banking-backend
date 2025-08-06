/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.customer.domian.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CustomerTransactionPinRequest {
	private String pin;
	private String newPin;
	private Long customerId;

}
