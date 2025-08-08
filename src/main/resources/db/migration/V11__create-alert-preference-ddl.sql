CREATE TABLE alert_preferences (
                                   id BIGSERIAL PRIMARY KEY,
                                   customer_id BIGINT NOT NULL,
                                   alert_type VARCHAR(50) NOT NULL,
                                   via_sms BOOLEAN NOT NULL DEFAULT FALSE,
                                   via_email BOOLEAN NOT NULL DEFAULT FALSE,
                                   via_push BOOLEAN NOT NULL DEFAULT FALSE,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                   CONSTRAINT fk_customer
                                       FOREIGN KEY (customer_id)
                                           REFERENCES customer(id)
                                           ON DELETE CASCADE,

                                   CONSTRAINT chk_alert_type
                                       CHECK (alert_type IN ('LOGIN', 'TRANSACTION'))
);

CREATE UNIQUE INDEX ux_customer_alert_type
    ON alert_preferences (customer_id, alert_type);
