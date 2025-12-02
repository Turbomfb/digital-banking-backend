/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private Long id;
	private String productCode;
	private String productName;
	private String type;
	private Double interestRate;
	private Integer tenure;
}
