SELECT
    id,
    token,
    user_id,
    company_id,
    created_at,
    expires_at,
    last_used_at,
    device_name
FROM remember_tokens
WHERE token = ?;