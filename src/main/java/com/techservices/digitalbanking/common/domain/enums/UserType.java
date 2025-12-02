/* (C)2025 */
package com.techservices.digitalbanking.common.domain.enums;

import lombok.Getter;

@Getter
public enum UserType {
	RETAIL(1L), CORPORATE(2L);

	private final Long id;

	UserType(Long id) {

		this.id = id;
	}

	public boolean isCorporate() {

		return this == CORPORATE;
	}
}
