SELECT
    c.id,
    c.nit,
    c.name,
    c.address,
    c.phone,
    c.registration_date,
    c.status,
    p.name AS plan_name,
    p.price AS plan_price
FROM companies c
    JOIN plans p ON p.id = c.plan_id
WHERE c.id = ?;
