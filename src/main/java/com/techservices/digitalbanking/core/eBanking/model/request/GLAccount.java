/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class GLAccount implements Serializable {

	private Long glAccountId;
	private String glCode;
	private String glName;
	private BigDecimal amount;
}
