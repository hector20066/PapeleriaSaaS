SELECT
    COUNT(rSocial) AS total_records
FROM providers
WHERE id_company = ?;