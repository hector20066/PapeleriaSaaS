SELECT
    id,
    user,
    password,
    status,
FROM users
WHERE user = ?
  AND role = ?;