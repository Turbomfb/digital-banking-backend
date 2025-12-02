INSERT INTO bank_data (name, code)
SELECT 'KKU Microfinance Bank', '000899'
WHERE NOT EXISTS (
    SELECT 1 FROM bank_data WHERE code = '000899'
);
