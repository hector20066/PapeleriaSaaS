SELECT
    id,
    identification,
    name,
    phone,
    email
FROM customers
WHERE id_company = ?;