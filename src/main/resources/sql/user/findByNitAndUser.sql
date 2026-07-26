SELECT
    u.id,
    u.user,
    u.password,
    u.status,
    u.id_company,
    c.status AS company_status
FROM users u
    INNER JOIN companies c ON c.id = u.id_company
WHERE c.nit = ?
  AND u.user = ?;