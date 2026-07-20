SELECT 
    p.id,
    p.description,
    SUM(sd.amount) AS quantity_sold
FROM products p
    LEFT JOIN sales_details sd ON p.id = sd.id_producto
WHERE p.id_company = ?
GROUP BY p.id, p.descripcion
ORDER BY quantity_sold DESC
LIMIT 1;
