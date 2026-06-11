SELECT
    COUNT(num_deuda) AS total_records
FROM deuda
WHERE status = 'PENDING';