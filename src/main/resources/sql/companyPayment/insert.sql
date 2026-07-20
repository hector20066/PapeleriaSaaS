INSERT INTO
company_payments (
    company_id,
    plan_id,
    amount_paid,
    payment_date,
    expiration_date,
    payment_reference,
    observations,
    status
) VALUES (?, ?, ?, ?, ?, ?, ?, ?);