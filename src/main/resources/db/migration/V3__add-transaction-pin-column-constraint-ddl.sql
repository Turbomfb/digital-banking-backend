ALTER TABLE customer
    add COLUMN transaction_pin VARCHAR(255) NULL,
    add COLUMN is_transaction_pin_set BOOLEAN NOT NULL DEFAULT FALSE;
