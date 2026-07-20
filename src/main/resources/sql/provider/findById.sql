SELECT
    id,
    nit,
    rSocial,
    address,
    phone,
    email
FROM providers
WHERE nit = ?
  AND id_company = ?;