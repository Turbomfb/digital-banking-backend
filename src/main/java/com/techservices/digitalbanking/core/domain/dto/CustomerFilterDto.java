/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Filter criteria for searching customers. All fields are optional and can be
 * combined for flexible customer search functionality.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilterDto {

	private String customerId;
	private String bvn;
	private String email;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String customerType;
	private String status;
	private LocalDate dateOfBirthFrom;
	private LocalDate dateOfBirthTo;
	private String city;
	private String state;
	private String country;

	public CustomerFilterDto bvn(String bvn) {

		this.bvn = bvn;
		return this;
	}
}
