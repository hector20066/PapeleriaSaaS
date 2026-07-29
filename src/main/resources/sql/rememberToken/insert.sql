INSERT INTO remember_tokens (
    token,
    user_id,
    company_id,
    expires_at,
    device_name
) VALUES (?, ?, ?, ?, ?);