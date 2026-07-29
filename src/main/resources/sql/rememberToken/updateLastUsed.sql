UPDATE remember_tokens
SET last_used_at = CURRENT_TIMESTAMP
WHERE token = ?;