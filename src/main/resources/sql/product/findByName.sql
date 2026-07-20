SELECT *
FROM vw_inventory
WHERE LOWER(TRIM(description)) = LOWER(TRIM(?))
  AND id_company = ?;