-- Migration script to create beneficiary table

CREATE TABLE IF NOT EXISTS beneficiary (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    account_number VARCHAR(20) NOT NULL,
    bank_name VARCHAR(255) NOT NULL,
    bank_code VARCHAR(20) NOT NULL,
    nickname VARCHAR(100),
    is_verified BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP,
    last_used_at TIMESTAMP,
    usage_count INT DEFAULT 0,
    CONSTRAINT fk_beneficiary_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE,
    CONSTRAINT uk_customer_account_bank UNIQUE (customer_id, account_number, bank_code)
);

-- Add indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_beneficiary_customer_id 
ON beneficiary(customer_id);

CREATE INDEX IF NOT EXISTS idx_beneficiary_account_number 
ON beneficiary(account_number);

CREATE INDEX IF NOT EXISTS idx_beneficiary_bank_code 
ON beneficiary(bank_code);

CREATE INDEX IF NOT EXISTS idx_beneficiary_active 
ON beneficiary(is_active);

CREATE INDEX IF NOT EXISTS idx_beneficiary_composite 
ON beneficiary(customer_id, is_active);

CREATE INDEX IF NOT EXISTS idx_beneficiary_last_used 
ON beneficiary(customer_id, last_used_at DESC);