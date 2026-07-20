SELECT *
FROM vw_customers_debts
WHERE customer_name = ?
  AND id_company = ?;