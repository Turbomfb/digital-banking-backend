package com.techservices.digitalbanking.core.domain.enums;

public enum BusinessCategoryType {
    PRIVATE_SECTOR("PRIVATE_SECTOR", "Private Sector"),
    PUBLIC_SECTOR("PUBLIC_SECTOR", "Public Sector");

    private final String code;
    private final String description;

    BusinessCategoryType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
