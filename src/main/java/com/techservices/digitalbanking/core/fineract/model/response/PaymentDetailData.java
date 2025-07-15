/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDetailData {

	private String accountNumber;

	private String bankNumber;

	private String checkNumber;

	private Long id;

	private PaymentTypData paymentType;

	private String receiptNumber;

	private String routingCode;
}
