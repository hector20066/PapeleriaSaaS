SELECT
    c.id,
    c.nit,
    c.name,
    c.address,
    c.phone,
    c.registration_date,
    p.name as plan_name,
    (
        SELECT
            payment_date
        FROM company_payments
        WHERE company_id = c.id
        ORDER BY payment_date DESC
        LIMIT 1
    ) as last_payment,
    (
        SELECT
            expiration_date
        FROM company_payments
        WHERE company_id = c.id
        ORDER BY expiration_date DESC
        LIMIT 1
    ) as next_payment,
    status
FROM companies c
JOIN plans p ON p.id = c.plan_id;
