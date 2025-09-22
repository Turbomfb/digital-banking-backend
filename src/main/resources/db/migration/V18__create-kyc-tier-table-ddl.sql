DROP TABLE IF EXISTS kyc_tier;

CREATE TABLE kyc_tier (
                          id BIGINT PRIMARY KEY,
                          tier_name VARCHAR(50) NOT NULL,
                          maximum_balance DECIMAL(24,6) NOT NULL,
                          single_deposit_limit DECIMAL(24,6) NOT NULL,
                          single_withdrawal_limit DECIMAL(24,6) NOT NULL,
                          daily_withdrawal_limit DECIMAL(24,6) NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP NOT NULL
);

INSERT INTO kyc_tier (id, tier_name, maximum_balance, single_deposit_limit, single_withdrawal_limit, daily_withdrawal_limit, created_at, updated_at)
VALUES
    (1, 'Tier 1', 300000.00, 50000.00, 3000.00, 30000.00, '2025-06-16 17:50:13.536919', '2025-06-16 17:50:13.536919'),
    (2, 'Tier 2', 500000.00, 200000.00, 10000.00, 100000.00, '2025-06-16 17:50:13.536919', '2025-06-16 17:50:13.536919'),
    (3, 'Tier 3', 999999999999999.00, 5000000.00, 1000000.00, 5000000.00, '2025-06-16 17:50:13.536919', '2025-06-16 17:50:13.536919');
