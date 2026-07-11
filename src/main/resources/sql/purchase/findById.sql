SELECT *
FROM vw_purchasing_report
WHERE purchase_number = ? AND
      id_company = ?;