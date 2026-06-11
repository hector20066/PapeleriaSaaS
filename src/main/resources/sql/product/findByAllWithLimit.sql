SELECT
    descripcion,
    cantidad,
    precio_venta,
    (cantidad * precio_venta) AS total_dinero
FROM producto;