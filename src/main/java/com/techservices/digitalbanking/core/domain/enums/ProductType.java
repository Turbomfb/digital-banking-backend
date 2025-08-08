package com.techservices.digitalbanking.core.domain.enums;

public enum ProductType {
    ALL("All"),
    RETAIL("Retail"),
    BUSINESS_BANKING("Business Banking");

    private final String displayName;

    ProductType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
