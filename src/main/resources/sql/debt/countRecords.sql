SELECT
    COUNT(sale_id) AS total_records
FROM debts
WHERE status = 'PENDING'
  AND id_company = ?;