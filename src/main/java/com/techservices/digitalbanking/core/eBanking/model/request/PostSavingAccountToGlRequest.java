/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.math.BigDecimal;

import jakarta.annotation.Generated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Generated("jsonschema2pojo")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class PostSavingAccountToGlRequest {

	private Long officeId;
	private String currencyCode;
	private BigDecimal amount;
	private String referenceNumber;
	private Long paymentTypeId;
	private String transactionDate;
	private String locale;
	private String dateFormat;
	private GLAccount credits;
}
