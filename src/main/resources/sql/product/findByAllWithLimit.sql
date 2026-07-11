SELECT
    description,
    amount,
    sale_price,
    (amount * sale_price) AS available_funds
FROM products
WHERE id_company = ?;