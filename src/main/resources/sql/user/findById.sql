SELECT
    id,
    identification,
    name,
    user,
    role,
    status
FROM users
WHERE id = ?;