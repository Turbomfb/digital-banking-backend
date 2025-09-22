/* Developed by MKAN Engineering (C)2024 */
CREATE TABLE industry_sector
(
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    position    BIGINT,
    active      BOOLEAN      NOT NULL DEFAULT TRUE,
    mandatory   BOOLEAN      NOT NULL DEFAULT FALSE
);

-- Insert default values
INSERT INTO industry_sector (id, name, description, position, active, mandatory)
VALUES (1, 'Agriculture', 'Agriculture sector', 1, TRUE, FALSE),
       (2, 'Energy & Utilities', 'Energy & Utilities sector', 2, TRUE, FALSE),
       (3, 'Manufacturing & Industrial', 'Manufacturing & Industrial sector', 3, TRUE, FALSE),
       (4, 'Construction & Real Estate', 'Construction & Real Estate sector', 4, TRUE, FALSE),
       (5, 'Financial Services', 'Financial Services sector', 5, TRUE, FALSE),
       (6, 'Retail & Wholesale Trade', 'Retail & Wholesale Trade sector', 6, TRUE, FALSE),
       (7, 'Transportation & Logistics', 'Transportation & Logistics sector', 7, TRUE, FALSE),
       (8, 'Healthcare & Pharmaceuticals', 'Healthcare & Pharmaceuticals sector', 8, TRUE, FALSE),
       (9, 'Education & Training', 'Education & Training sector', 9, TRUE, FALSE),
       (10, 'Hospitality & Tourism', 'Hospitality & Tourism sector', 10, TRUE, FALSE),
       (11, 'ICT & Telecommunications', 'ICT & Telecommunications sector', 11, TRUE, FALSE),
       (12, 'Mining & Natural', 'Mining & Natural Resources sector', 12, TRUE, FALSE),
       (13, 'Media & Entertainment', 'Media & Entertainment sector', 13, TRUE, FALSE),
       (14, 'Professional Services', 'Professional Services sector (legal, consulting, accounting, architecture)', 14,
        TRUE, FALSE),
       (15, 'Public Sector & NGOs', 'Public Sector & NGOs', 15, TRUE, FALSE),
       (16, 'Beauty & Personal Care', 'Beauty & Personal Care sector', 16, TRUE, FALSE),
       (17, 'Fashion & Tailoring', 'Fashion & Tailoring sector', 17, TRUE, FALSE);
