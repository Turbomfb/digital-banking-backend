ALTER TABLE customer DROP CONSTRAINT IF EXISTS customer_email_address_key;
ALTER TABLE customer DROP CONSTRAINT IF EXISTS customer_phone_number_key;

DO $$
    BEGIN
        IF NOT EXISTS (
            SELECT 1
            FROM information_schema.table_constraints
            WHERE constraint_name = 'unique_email_user_type'
              AND table_name = 'customer'
              AND constraint_type = 'UNIQUE'
        ) THEN
            ALTER TABLE customer
                ADD CONSTRAINT unique_email_user_type UNIQUE (email_address, user_type);
        END IF;

        IF NOT EXISTS (
            SELECT 1
            FROM information_schema.table_constraints
            WHERE constraint_name = 'unique_phone_user_type'
              AND table_name = 'customer'
              AND constraint_type = 'UNIQUE'
        ) THEN
            ALTER TABLE customer
                ADD CONSTRAINT unique_phone_user_type UNIQUE (phone_number, user_type);
        END IF;
    END $$;
