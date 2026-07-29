DELETE
FROM remember_tokens
WHERE expires_at < CURRENT_TIMESTAMP;