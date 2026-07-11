UPDATE products
SET
    description = ?,
    amount = ?,
    sale_price = ?,
    purchase_price = ?
WHERE id = ? AND
      id_company = ?;