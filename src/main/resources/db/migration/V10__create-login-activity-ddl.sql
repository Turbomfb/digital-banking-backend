CREATE TABLE user_login_activity (
                                     id BIGSERIAL PRIMARY KEY,
                                    customer_id   BIGINT NOT NULL,
                                     device_name   VARCHAR(255),
                                     location      VARCHAR(255),
                                     source        VARCHAR(255),
                                     created_at    TIMESTAMP,
                                     updated_at    TIMESTAMP
);
