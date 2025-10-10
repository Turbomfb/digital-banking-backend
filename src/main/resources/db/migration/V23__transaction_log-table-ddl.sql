CREATE TABLE IF NOT EXISTS transaction_log (
                                                         id VARCHAR(100) PRIMARY KEY,
                                                         customer_id BIGINT NOT NULL,
                                                         beneficiary_account_number VARCHAR(20) NOT NULL,
                                                         beneficiary_account_name VARCHAR(255),
                                                         beneficiary_bank_name VARCHAR(255),
                                                         beneficiary_bank_code VARCHAR(20) NOT NULL,
                                                         amount DECIMAL(24, 6) NOT NULL,
                                                         narration VARCHAR(500),
                                                         transaction_reference VARCHAR(100) UNIQUE NOT NULL,
                                                         external_reference VARCHAR(100),
                                                         status VARCHAR(50) NOT NULL,
                                                         response_code VARCHAR(20),
                                                         response_message TEXT,
                                                         transaction_type VARCHAR(50) NOT NULL,
                                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                         last_modified_at TIMESTAMP
);

-- Add indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_interbank_log_customer_id
    ON transaction_log(customer_id);

CREATE INDEX IF NOT EXISTS idx_interbank_log_reference
    ON transaction_log(transaction_reference);

CREATE INDEX IF NOT EXISTS idx_interbank_log_status
    ON transaction_log(status);
