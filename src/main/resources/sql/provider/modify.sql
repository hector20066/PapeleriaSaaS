UPDATE
providers
SET
    rSocial = ?,
    address = ?,
    phone = ?,
    email = ?
WHERE nit = ? AND
      id_company = ?;