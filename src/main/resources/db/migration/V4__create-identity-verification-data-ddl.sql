CREATE TABLE identity_verification_data (
                                            id VARCHAR(100) PRIMARY KEY,
                                            identifier VARCHAR(50) NOT NULL, -- BVN or NIN
                                            first_name VARCHAR(100),
                                            middle_name VARCHAR(100),
                                            last_name VARCHAR(100),
                                            mobile VARCHAR(20),
                                            email VARCHAR(100),
                                            gender VARCHAR(100),
                                            date_of_birth VARCHAR(100),
                                            address_line VARCHAR(255),
                                            town VARCHAR(100),
                                            lga VARCHAR(100),
                                            state VARCHAR(100),
                                            birth_state VARCHAR(100),
                                            birth_lga VARCHAR(100),
                                            birth_country VARCHAR(100),
                                            religion VARCHAR(50),
                                            type VARCHAR(20), -- e.g., "bvn" or "nin"
                                            status VARCHAR(50),
                                            image TEXT, -- Base64 encoded image
                                            signature TEXT, -- Base64 encoded signature
                                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                            last_modified_at TIMESTAMP,
                                            UNIQUE(identifier, type) -- Ensure no duplicate combination of BVN/NIN and type
);
