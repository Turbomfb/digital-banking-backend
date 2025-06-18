package com.techservices.digitalbanking.customer.domian;

public enum CustomerKycTier {
    TIER_1("1", "Tier 1"),
    TIER_2("2", "Tier 2"),
    TIER_3("3", "Tier 3");

    private final String code;
    private final String description;

    CustomerKycTier(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CustomerKycTier findByCode(String code) {
        for (CustomerKycTier tier : CustomerKycTier.values()) {
            if (tier.getCode().equals(code)) {
                return tier;
            }
        }
        throw new IllegalArgumentException("No KYC Tier found for code: " + code);
    }

    public static boolean isTierOne(CustomerKycTier kycTier) {
        return CustomerKycTier.TIER_1.equals(kycTier);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
