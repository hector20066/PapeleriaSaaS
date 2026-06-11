SELECT *
FROM inventario
WHERE LOWER(TRIM(descripcion)) = LOWER(TRIM(?));