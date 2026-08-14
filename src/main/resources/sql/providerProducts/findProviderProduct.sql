SELECT
    pr.id,
    pr.nit,
    pr.rSocial,
    pr.address,
    pr.phone,
    pr.email
FROM provider_products pp
JOIN products p ON p.id = pp.product_id
JOIN providers pr ON pr.id = pp.provider_id
WHERE p.id = ?
  AND p.id_company = ?;