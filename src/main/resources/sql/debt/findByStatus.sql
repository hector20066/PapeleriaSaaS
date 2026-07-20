SELECT *
FROM vw_customers_debts
WHERE status = ?
  AND id_company = ?;