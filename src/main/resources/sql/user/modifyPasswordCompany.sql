UPDATE users
SET
    password = ?
WHERE id = ?
  AND id_company = ?;