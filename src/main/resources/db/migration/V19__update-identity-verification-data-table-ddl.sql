ALTER TABLE identity_verification_data
    ADD COLUMN IF NOT EXISTS business_name VARCHAR(255),
    ADD COLUMN IF NOT EXISTS registration_number VARCHAR(100),
    ADD COLUMN IF NOT EXISTS registration_date VARCHAR(100),
    ADD COLUMN IF NOT EXISTS tin VARCHAR(50),
    ADD COLUMN IF NOT EXISTS vat_number VARCHAR(50),
    ADD COLUMN IF NOT EXISTS company_status VARCHAR(100),
    ADD COLUMN IF NOT EXISTS business_type VARCHAR(100),
    ADD COLUMN IF NOT EXISTS activity VARCHAR(255),
    ADD COLUMN IF NOT EXISTS business_email VARCHAR(100),
    ADD COLUMN IF NOT EXISTS business_phone VARCHAR(20),
    ADD COLUMN IF NOT EXISTS head_office_address VARCHAR(255),
    ADD COLUMN IF NOT EXISTS branch_address VARCHAR(255),
    ADD COLUMN IF NOT EXISTS objectives TEXT,
    ADD COLUMN IF NOT EXISTS share_capital VARCHAR(100),
    ADD COLUMN IF NOT EXISTS country_code VARCHAR(10);

CREATE INDEX IF NOT EXISTS idx_identity_verification_registration_number
    ON identity_verification_data(registration_number);

CREATE INDEX IF NOT EXISTS idx_identity_verification_tin
    ON identity_verification_data(tin);

ALTER TABLE identity_verification_data
    ALTER COLUMN type TYPE VARCHAR(50);
