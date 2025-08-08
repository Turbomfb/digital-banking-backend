CREATE TABLE customer_status_audit (
                                   id BIGSERIAL PRIMARY KEY,
                                   customer_id BIGINT NOT NULL,
                                   type VARCHAR(50) NOT NULL,
                                   reason VARCHAR(50),
                                   is_active BOOLEAN NOT NULL DEFAULT FALSE,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                   CONSTRAINT fk_customer
                                       FOREIGN KEY (customer_id)
                                           REFERENCES customer(id)
                                           ON DELETE CASCADE
);

CREATE UNIQUE INDEX ux_customer_status_audit
    ON customer_status_audit (customer_id);
