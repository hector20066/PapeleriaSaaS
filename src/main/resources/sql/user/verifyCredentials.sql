SELECT
    u.id,
    u.user,
    u.password,
    u.role,
    u.status,
    u.id_company
    c.name
FROM users u
    INNER JOIN companies c ON c.id = u.id_company
WHERE c.nit = ?
  AND u.user = ?;