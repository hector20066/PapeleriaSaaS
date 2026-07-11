SELECT
    id,
    nit,
    rSocial,
    address,
    phone,
    email
FROM providers
WHERE LOWER(TRIM(rSocial)) = LOWER(TRIM(?)) AND
      id_company = ?;