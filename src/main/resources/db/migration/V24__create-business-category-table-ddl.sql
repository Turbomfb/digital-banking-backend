CREATE TABLE IF NOT EXISTS business_category (
                                                 id BIGSERIAL PRIMARY KEY,
                                                 name VARCHAR(100) NOT NULL UNIQUE,
                                                 code VARCHAR(50) NOT NULL UNIQUE,
                                                 description VARCHAR(255),
                                                 category_type VARCHAR(50) NOT NULL,
                                                 display_order INT,
                                                 is_active BOOLEAN DEFAULT TRUE,
                                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                 last_modified_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS business_category_item (
                                                      id BIGSERIAL PRIMARY KEY,
                                                      category_id BIGINT NOT NULL,
                                                      name VARCHAR(255) NOT NULL,
                                                      code VARCHAR(100) NOT NULL,
                                                      description VARCHAR(500),
                                                      display_order INT,
                                                      is_active BOOLEAN DEFAULT TRUE,
                                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                      last_modified_at TIMESTAMP,
                                                      CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES business_category(id) ON DELETE CASCADE,
                                                      CONSTRAINT uk_category_item_code UNIQUE (category_id, code)
);

-- Add indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_business_category_type
    ON business_category(category_type);

CREATE INDEX IF NOT EXISTS idx_business_category_active
    ON business_category(is_active);

CREATE INDEX IF NOT EXISTS idx_business_category_item_category
    ON business_category_item(category_id);

CREATE INDEX IF NOT EXISTS idx_business_category_item_active
    ON business_category_item(is_active);

CREATE INDEX IF NOT EXISTS idx_business_category_item_composite
    ON business_category_item(category_id, is_active);


-- Data initialization script for business categories and items
-- Run this after creating the tables

-- Insert Private Sector Category
INSERT INTO business_category (name, code, description, category_type, display_order, is_active)
VALUES ('Private Sector', 'PRIVATE_SECTOR', 'Private sector industries and businesses', 'PRIVATE_SECTOR', 1, TRUE)
ON CONFLICT (code) DO NOTHING;

-- Insert Public Sector Category
INSERT INTO business_category (name, code, description, category_type, display_order, is_active)
VALUES ('Public Sector', 'PUBLIC_SECTOR', 'Public sector ministries, agencies and institutions', 'PUBLIC_SECTOR', 2, TRUE)
ON CONFLICT (code) DO NOTHING;

-- Insert Private Sector Items
INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Finance & Fintech', 'FINANCE_FINTECH', 1, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Technology and Digital Payments', 'TECHNOLOGY_DIGITAL_PAYMENTS', 2, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Oil & Gas', 'OIL_GAS', 3, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Telecommunications', 'TELECOMMUNICATIONS', 4, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Manufacturing and Consumer Goods', 'MANUFACTURING_CONSUMER_GOODS', 5, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Media and Broadcasting', 'MEDIA_BROADCASTING', 6, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Transportation and Logistics', 'TRANSPORTATION_LOGISTICS', 7, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Health & Pharmaceuticals', 'HEALTH_PHARMACEUTICALS', 8, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Construction & Real Estate', 'CONSTRUCTION_REAL_ESTATE', 9, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Education and Training', 'EDUCATION_TRAINING', 10, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Retail, E-commerce & Consumer Goods', 'RETAIL_ECOMMERCE_CONSUMER_GOODS', 11, TRUE
FROM business_category WHERE code = 'PRIVATE_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

-- Insert Public Sector Items - Federal Government Ministries
INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Finance', 'FED_MIN_FINANCE', 1, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Education', 'FED_MIN_EDUCATION', 2, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Health', 'FED_MIN_HEALTH', 3, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Agriculture and Rural Development', 'FED_MIN_AGRICULTURE', 4, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Works and Housing', 'FED_MIN_WORKS_HOUSING', 5, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Power', 'FED_MIN_POWER', 6, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Labour and Employment', 'FED_MIN_LABOUR', 7, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Interior', 'FED_MIN_INTERIOR', 8, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Foreign Affairs', 'FED_MIN_FOREIGN_AFFAIRS', 9, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Information and National Orientation', 'FED_MIN_INFORMATION', 10, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Justice', 'FED_MIN_JUSTICE', 11, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Communications, Innovation and Digital Economy', 'FED_MIN_COMMUNICATIONS', 12, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Women Affairs', 'FED_MIN_WOMEN_AFFAIRS', 13, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Youth and Sports Development', 'FED_MIN_YOUTH_SPORTS', 14, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Ministry of Petroleum Resources', 'FED_MIN_PETROLEUM', 15, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

-- Insert Public Sector Items - Federal Agencies, Commissions & Parastatals
INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Central Bank of Nigeria (CBN)', 'CBN', 16, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigerian National Petroleum Company Limited (NNPCL)', 'NNPCL', 17, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Independent National Electoral Commission (INEC)', 'INEC', 18, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'National Agency for Food and Drug Administration and Control (NAFDAC)', 'NAFDAC', 19, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigerian Communications Commission (NCC)', 'NCC', 20, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigerian Ports Authority (NPA)', 'NPA', 21, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Inland Revenue Service (FIRS)', 'FIRS', 22, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigerian Customs Service (NCS)', 'NCS', 23, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigerian Immigration Service (NIS)', 'NIS', 24, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'National Identity Management Commission (NIMC)', 'NIMC', 25, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Corporate Affairs Commission (CAC)', 'CAC', 26, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Economic and Financial Crimes Commission (EFCC)', 'EFCC', 27, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Independent Corrupt Practices and Other Related Offences Commission (ICPC)', 'ICPC', 28, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'National Bureau of Statistics (NBS)', 'NBS', 29, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'National Pension Commission (PENCOM)', 'PENCOM', 30, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

-- Insert Public Sector Items - Public Educational Institutions
INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'University of Lagos (UNILAG)', 'UNILAG', 31, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'University of Nigeria, Nsukka (UNN)', 'UNN', 32, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Ahmadu Bello University (ABU), Zaria', 'ABU_ZARIA', 33, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'University of Ibadan (UI)', 'UI', 34, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Obafemi Awolowo University (OAU)', 'OAU', 35, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal University of Technology, Minna', 'FUT_MINNA', 36, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Lagos State University (LASU)', 'LASU', 37, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Rivers State University (RSU)', 'RSU', 38, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'University of Calabar (UNICAL)', 'UNICAL', 39, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Polytechnic, Nekede', 'FED_POLY_NEKEDE', 40, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

-- Insert Public Sector Items - Public Health Institutions
INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'University College Hospital (UCH), Ibadan', 'UCH_IBADAN', 41, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Lagos University Teaching Hospital (LUTH)', 'LUTH', 42, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'National Primary Health Care Development Agency (NPHCDA)', 'NPHCDA', 43, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'National Health Insurance Authority (NHIA)', 'NHIA', 44, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigerian Centre for Disease Control (NCDC)', 'NCDC', 45, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

-- Insert Public Sector Items - Public Enterprises & Corporations
INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigerian Television Authority (NTA)', 'NTA', 46, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Federal Radio Corporation of Nigeria (FRCN)', 'FRCN', 47, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigerian Postal Service (NIPOST)', 'NIPOST', 48, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Transmission Company of Nigeria (TCN)', 'TCN', 49, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;

INSERT INTO business_category_item (category_id, name, code, display_order, is_active)
SELECT id, 'Nigeria Railway Corporation (NRC)', 'NRC', 50, TRUE
FROM business_category WHERE code = 'PUBLIC_SECTOR'
ON CONFLICT (category_id, code) DO NOTHING;
