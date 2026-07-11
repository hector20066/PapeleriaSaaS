SELECT
    COUNT(identification) AS total_records
FROM customers
WHERE id_company = ?;