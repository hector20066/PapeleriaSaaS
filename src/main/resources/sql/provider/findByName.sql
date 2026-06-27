SELECT *
FROM proveedor
WHERE LOWER(TRIM(rSocial)) = LOWER(TRIM(?));