package com.techservices.digitalbanking.common.domain.enums;

import lombok.Getter;

@Getter
public enum UserType {
    CUSTOMER(0L),
    RETAIL(1L),
    CORPORATE(2L),
    INDIVIDUAL(3L);

    private final Long id;

    UserType(Long id) {
        this.id = id;
    }

    public boolean isCorporate() {
        return this == CORPORATE;
    }
}
