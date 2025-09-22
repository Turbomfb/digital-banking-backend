/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutFixedDepositProductsProductIdResponse {
	private Map<String, String> changes;

	private Integer resourceId;
}
