UPDATE customers
SET
    name = ?,
    phone = ?,
    email = ?
WHERE identification = ? AND
      id_company = ?;