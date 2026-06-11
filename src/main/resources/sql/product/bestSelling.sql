SELECT 
    p.id,
    p.descripcion,
    SUM(dv.cantidad) AS cantidad_vendidas
FROM producto p
LEFT JOIN detalle_venta dv ON p.id = dv.id_producto
GROUP BY p.id, p.descripcion
ORDER BY cantidad_vendidas DESC
LIMIT 1;
