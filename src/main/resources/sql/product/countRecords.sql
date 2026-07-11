SELECT
    COUNT(description) AS total_records
FROM products
WHERE id_company = ?;