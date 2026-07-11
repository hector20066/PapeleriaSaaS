UPDATE sales
SET total_paid = ?
WHERE sale_number = ? AND
      id_company = ?;