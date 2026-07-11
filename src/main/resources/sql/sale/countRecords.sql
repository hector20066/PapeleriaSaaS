SELECT
    COUNT(sale_number) AS total_records
FROM sales
WHERE id_company = ?;