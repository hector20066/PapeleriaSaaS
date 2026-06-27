SELECT
    c.id,
    c.nit,
    c.name,
    c.address,
    c.phone,
    c.registration_date,
    p.name,
    (
        SELECT
            payment_date
        FROM company_payments
        WHERE company_id = c.id
        GROUP BY payment_date ASC
        LIMIT 1
    ) as last_payment,
    (
        SELECT
            expiration_date
        FROM company_payments
        WHERE company_id = c.id
        GROUP BY expiration_date ASC
        LIMIT 1
    ) as next_payment,
    status
FROM companies c
JOIN plans p ON p.id = c.plan_id;
