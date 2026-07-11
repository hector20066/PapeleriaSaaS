SELECT
    id,
    nit,
    rSocial,
    address,
    phone,
    email
FROM providers
WHERE id_company = ?;