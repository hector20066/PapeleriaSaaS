UPDATE providers
SET
    nit = ?,
    rSocial = ?,
    address = ?,
    phone = ?,
    email = ?
WHERE id = ?
  AND id_company = ?;