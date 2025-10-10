CREATE TABLE IF NOT EXISTS name_enquiry_cache (
                                                  id VARCHAR(100) PRIMARY KEY,
                                                  account_number VARCHAR(20) NOT NULL,
                                                  bank_code VARCHAR(20) NOT NULL,
                                                  account_name VARCHAR(255),
                                                  bank_name VARCHAR(255),
                                                  account_currency VARCHAR(10),
                                                  account_type VARCHAR(50),
                                                  status VARCHAR(50),
                                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                  last_modified_at TIMESTAMP,
                                                  UNIQUE(account_number, bank_code)
);

CREATE INDEX IF NOT EXISTS idx_name_enquiry_account_number
    ON name_enquiry_cache(account_number);

CREATE INDEX IF NOT EXISTS idx_name_enquiry_bank_code
    ON name_enquiry_cache(bank_code);

CREATE INDEX IF NOT EXISTS idx_name_enquiry_composite
    ON name_enquiry_cache(account_number, bank_code);
