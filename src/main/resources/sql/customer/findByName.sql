SELECT
    identification,
    name,
    phone,
    email
FROM customers
WHERE LOWER(TRIM(name)) = LOWER(TRIM(?));