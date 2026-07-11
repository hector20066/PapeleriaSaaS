SELECT
    COUNT(purchase_number) AS total_records
FROM purchases
WHERE id_company = ?;