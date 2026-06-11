CREATE DATABASE IF NOT EXISTS db_papeleria;

USE db_papeleria;

CREATE TABLE producto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL DEFAULT 0 CHECK (cantidad >= 0),
    precio_compra DECIMAL(10,2) CHECK (precio_compra >= 0),
    precio_venta DECIMAL(10,2) CHECK (precio_venta >= 0)
);

CREATE TABLE cliente (
    id INT PRIMARY KEY NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    telefono VARCHAR(15)
);

CREATE TABLE proveedor (
    nit INT PRIMARY KEY NOT NULL,
    rSocial VARCHAR(100) NOT NULL,
    direccion VARCHAR(50),
    telefono VARCHAR(15),
    correo VARCHAR(50)
);

CREATE TABLE compra (
    num_compra INT PRIMARY KEY AUTO_INCREMENT,
    nit_proveedor INT,
    fecha DATE,
    total_pagado DECIMAL(10,2) CHECK (total_pagado >= 0),
    FOREIGN KEY (nit_proveedor) REFERENCES proveedor(nit) ON DELETE SET NULL
);

CREATE TABLE venta (
    num_venta INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NULL,
    fecha DATE,
    total_pagado DECIMAL(10,2) CHECK (total_pagado >= 0),
    total_venta DECIMAL(10,2) CHECK (total_venta >= 0),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE SET NULL
);

CREATE TABLE deuda (
    num_deuda INT PRIMARY KEY AUTO_INCREMENT,
    num_venta INT,
    total_deuda DECIMAL(10,2) CHECK (total_deuda >= 0),
    estado VARCHAR(15),
    FOREIGN KEY (num_venta) REFERENCES venta(num_venta) ON DELETE CASCADE
);

CREATE TABLE detalle_compra (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    num_compra INT,
    id_producto INT,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10,2) CHECK (precio_unitario >= 0),
    FOREIGN KEY (num_compra) REFERENCES compra(num_compra) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(id) ON DELETE SET NULL
);

CREATE TABLE detalle_venta (
    id_detalle INT PRIMARY KEY AUTO_INCREMENT,
    num_venta INT,
    id_producto INT,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario_producto DECIMAL(10,2) CHECK (precio_unitario_producto >= 0),
    FOREIGN KEY (num_venta) REFERENCES venta(num_venta) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(id) ON DELETE SET NULL
);

CREATE TABLE factura (
    num_factura INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    num_venta INT NOT NULL,
    fecha_emision DATE,
    FOREIGN KEY (num_venta) REFERENCES venta(num_venta) ON DELETE CASCADE
);

-- TRIGGERS

-- Evita ventas con cantidades inválidas o stock insuficiente
DELIMITER $$

CREATE TRIGGER validar_stock_detalle_venta
BEFORE INSERT ON detalle_venta
FOR EACH ROW
BEGIN
    DECLARE v_stock INT;

    SELECT cantidad
    INTO v_stock
    FROM producto
    WHERE id = NEW.id_producto
    FOR UPDATE;

    IF v_stock IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El producto no existe';
    END IF;

    IF v_stock < NEW.cantidad THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No hay stock suficiente para vender este producto';
    END IF;
END $$

DELIMITER ;

-- Reducir la cantidad disponible de un producto cuando se registra un detalle de venta
DELIMITER $$

CREATE TRIGGER reducir_stock_detalle_venta
AFTER INSERT ON detalle_venta
FOR EACH ROW
BEGIN
    UPDATE producto
    SET cantidad = cantidad - NEW.cantidad
    WHERE id = NEW.id_producto;
END $$

DELIMITER ;

-- Devolver al inventario la cantidad de productos cuando se elimina un detalle venta
DELIMITER $$

CREATE TRIGGER devolver_stock_detalle_venta
AFTER DELETE ON detalle_venta
FOR EACH ROW
BEGIN
    UPDATE producto
    SET cantidad = cantidad + OLD.cantidad
    WHERE id = OLD.id_producto;
END $$

DELIMITER ;

-- Incrementar el stock del producto cuando se registra un detalle de compra
DELIMITER $$

CREATE TRIGGER incrementar_stock_detalle_compra
AFTER INSERT ON detalle_compra
FOR EACH ROW
BEGIN
    UPDATE producto
    SET cantidad = cantidad + NEW.cantidad
    WHERE id = NEW.id_producto;
END $$

DELIMITER ;

-- Disminuye el stock cuando se elimina un detalle de compra previamente registrado
DELIMITER $$

CREATE TRIGGER disminuir_stock_detalle_compra
AFTER DELETE ON detalle_compra
FOR EACH ROW
BEGIN
    UPDATE producto
    SET cantidad = cantidad - OLD.cantidad
    WHERE id = OLD.id_producto;
END $$

DELIMITER ;

-- Crea una deuda cuando no se paga completo en la venta
DELIMITER $$

CREATE TRIGGER generar_deuda
AFTER INSERT ON venta
FOR EACH ROW
BEGIN
    IF NEW.total_pagado < NEW.total_venta THEN
        INSERT INTO deuda (
            num_venta,
            total_deuda,
            estado
        ) VALUES (
            NEW.num_venta,
            NEW.total_venta - NEW.total_pagado,
            'PENDING'
        );
    END IF;
END $$

DELIMITER ;

-- Actualizar el estado de una deuda cuando se realizan pagos de dicha deuda
DELIMITER $$

CREATE TRIGGER trg_after_update_venta_actualizar_deuda
    AFTER UPDATE ON venta
    FOR EACH ROW
BEGIN
    DECLARE v_saldo DECIMAL(10,2);

    SET v_saldo = NEW.total_venta - NEW.total_pagado;

    UPDATE deuda
    SET
        total_deuda = IF(v_saldo <= 0, 0, v_saldo),
        estado = IF(v_saldo <= 0, 'PAID', 'PENDING')
    WHERE num_venta = NEW.num_venta;
END $$

DELIMITER ;

-- VISTAS

-- Muestra las deudas asociadas a cada cliente con la venta correspondiente
CREATE VIEW deudas_clientes AS
SELECT
    d.num_deuda,
    c.id AS id_cliente,
    c.nombre AS nombre_cliente,
    c.telefono,
    v.num_venta,
    v.fecha,
    v.total_venta,
    v.total_pagado,
    d.total_deuda AS deuda_actual,
    d.estado
FROM deuda d
JOIN venta v ON d.num_venta = v.num_venta
LEFT JOIN cliente c ON v.id_cliente = c.id;

-- Muestra los productos vendidos en cada venta con sus cantidades y precios
CREATE VIEW reporte_ventas AS
SELECT
    v.num_venta,
    p.id AS id_producto,
    p.descripcion AS producto,
    d.cantidad AS cantidad_vendida,
    v.fecha,
    c.nombre AS cliente,
    v.total_venta
FROM detalle_venta d
JOIN venta v ON d.num_venta = v.num_venta
LEFT JOIN producto p ON d.id_producto = p.id
LEFT JOIN cliente c ON v.id_cliente = c.id;

-- Muestra el estado actual del inventario de productos
CREATE VIEW inventario AS
SELECT
    p.id,
    p.descripcion,
    p.cantidad AS cantidad_disponible,
    p.precio_compra,
    p.precio_venta,
    COALESCE(GROUP_CONCAT(DISTINCT pr.rSocial SEPARATOR ', '), 'Sin Proveedor') AS proveedor,
    ((p.precio_venta * p.cantidad) - (p.precio_compra * p.cantidad)) AS ganancias
FROM producto p
         LEFT JOIN detalle_compra d ON p.id = d.id_producto
         LEFT JOIN compra c ON d.num_compra = c.num_compra
         LEFT JOIN proveedor pr ON c.nit_proveedor = pr.nit
GROUP BY p.id, p.descripcion, p.cantidad, p.precio_compra, p.precio_venta;

-- Lista las compras realizadas con sus proveedores y productos adquiridos
CREATE VIEW reporte_compras AS
SELECT
    c.num_compra,
    p.descripcion AS producto,
    p.id AS id_producto,
    d.cantidad AS cant_comprada,
    p.precio_compra,
    p.precio_venta,
    c.fecha,
    pr.rSocial AS proveedor,
    pr.nit AS nit_proveedor,
    c.total_pagado AS total_compra,
    ((p.precio_venta * d.cantidad) - (p.precio_compra * d.cantidad)) AS ganancias
FROM detalle_compra d
JOIN compra c ON d.num_compra = c.num_compra
LEFT JOIN producto p ON d.id_producto = p.id
LEFT JOIN proveedor pr ON c.nit_proveedor = pr.nit;

-- STORED PROCEDURES

-- Inserta una venta y retorna el número de la venta
DELIMITER $$

CREATE PROCEDURE sp_crear_venta (
    IN p_id_cliente INT,
    IN p_total_venta DECIMAL(10,2),
    IN p_total_pagado DECIMAL(10,2)
)
BEGIN
    INSERT INTO venta (id_cliente, fecha, total_venta, total_pagado)
    VALUES (p_id_cliente, CURDATE(), p_total_venta, p_total_pagado);

    SELECT LAST_INSERT_ID() AS num_venta;
END $$

DELIMITER ;

-- Inserta una compra y retorna el numero de la compra
DELIMITER $$

CREATE PROCEDURE sp_crear_compra (
    IN p_nit_proveedor INT,
    IN p_total_pagado DECIMAL(10,2)
)
BEGIN
    INSERT INTO compra (nit_proveedor, fecha, total_pagado)
    VALUES (p_nit_proveedor, CURDATE(), p_total_pagado);

    SELECT LAST_INSERT_ID() AS num_compra;
END $$

DELIMITER ;

-- Insertar la informacion de una factura y devolver el numero de la factura
DELIMITER $$

CREATE PROCEDURE sp_crear_factura (IN p_num_venta INT)
BEGIN
    INSERT INTO factura (num_venta, fecha_emision)
    VALUES (p_num_venta, CURDATE());

    SELECT LAST_INSERT_ID() AS last_num_factura;
END $$

DELIMITER ;

-- Índices en búsquedas frecuentes
CREATE INDEX idx_producto_descripcion ON producto(descripcion);
CREATE INDEX idx_cliente_nombre ON cliente(nombre);
CREATE INDEX idx_proveedor_rSocial ON proveedor(rSocial);

-- Índices en foreign keys para joins más rápidos
CREATE INDEX idx_compra_nit_proveedor ON compra(nit_proveedor);
CREATE INDEX idx_venta_id_cliente ON venta(id_cliente);
CREATE INDEX idx_deuda_num_venta ON deuda(num_venta);
CREATE INDEX idx_detalle_compra_num_compra ON detalle_compra(num_compra);
CREATE INDEX idx_detalle_compra_id_producto ON detalle_compra(id_producto);
CREATE INDEX idx_detalle_venta_num_venta ON detalle_venta(num_venta);
CREATE INDEX idx_detalle_venta_id_producto ON detalle_venta(id_producto);

-- Índices para reportes y búsquedas por fecha
CREATE INDEX idx_compra_fecha ON compra(fecha);
CREATE INDEX idx_venta_fecha ON venta(fecha);
