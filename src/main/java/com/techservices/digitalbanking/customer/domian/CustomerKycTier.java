package com.techservices.digitalbanking.customer.domian;

public enum CustomerKycTier {
    INVALID(0L, "Invalid Tier"),
    TIER_1(1L, "Tier 1"),
    TIER_2(2L, "Tier 2"),
    TIER_3(3L, "Tier 3");

    private final Long code;
    private final String description;

    CustomerKycTier(Long code, String description) {
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

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
