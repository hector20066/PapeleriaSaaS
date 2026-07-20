SELECT
    p.name AS plan_name,
    cp.amount_paid,
    cp.payment_date,
    cp.expiration_date,
    cp.status
FROM company_payments cp
    JOIN plans p ON p.id = cp.plan_id
WHERE cp.company_id = ?;
