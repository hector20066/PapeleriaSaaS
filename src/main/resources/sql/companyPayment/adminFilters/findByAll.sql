SELECT
    cp.id,
    c.name AS company_name,
    p.name AS plan_name,
    cp.amount_paid,
    cp.payment_date,
    cp.expiration_date,
    cp.status
FROM company_payments cp
    LEFT JOIN companies c ON c.id = cp.company_id
    LEFT JOIN plans p ON p.id = cp.plan_id;