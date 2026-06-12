CREATE DATABASE IF NOT EXISTS db_papeleria_saas;

USE db_papeleria;

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    amount INT NOT NULL DEFAULT 0 CHECK (amount >= 0),
    purchase_price DECIMAL(10, 2) CHECK (purchase_price >= 0),
    sale_price DECIMAL(10, 2) CHECK (sale_price >= 0)
);

CREATE TABLE customers (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    phone VARCHAR(15)
);

CREATE TABLE providers (
    nit INT PRIMARY KEY NOT NULL,
    rSocial VARCHAR(100) NOT NULL,
    address VARCHAR(50),
    phone VARCHAR(15),
    email VARCHAR(50)
);

CREATE TABLE purchases (
    num_purchase INT PRIMARY KEY AUTO_INCREMENT,
    provider_nit INT,
    purchase_date DATE,
    total_paid DECIMAL(10, 2) CHECK (total_paid >= 0),
    FOREIGN KEY (provider_nit) REFERENCES providers (nit) ON DELETE SET NULL
);

CREATE TABLE sales (
    num_sale INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NULL,
    sale_date DATE,
    total_paid DECIMAL(10, 2) CHECK (total_paid >= 0),
    total_sale DECIMAL(10, 2) CHECK (total_sale >= 0),
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE SET NULL
);

CREATE TABLE debts (
    num_debt INT PRIMARY KEY AUTO_INCREMENT,
    num_sale INT,
    total_debt DECIMAL(10, 2) CHECK (total_debt >= 0),
    status VARCHAR(15),
    FOREIGN KEY (num_sale) REFERENCES sales (num_sale) ON DELETE CASCADE
);

CREATE TABLE purchases_details (
    id_details INT PRIMARY KEY AUTO_INCREMENT,
    num_purchase INT,
    product_id INT,
    amount INT NOT NULL CHECK (amount > 0),
    unit_price DECIMAL(10, 2) CHECK (unit_price >= 0),
    FOREIGN KEY (num_purchase) REFERENCES purchases (num_purchase) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE SET NULL
);

CREATE TABLE sales_details (
    id_details INT PRIMARY KEY AUTO_INCREMENT,
    num_sale INT,
    product_id INT,
    amount INT NOT NULL CHECK (amount > 0),
    unit_price DECIMAL(10, 2) CHECK (unit_price >= 0),
    FOREIGN KEY (num_sale) REFERENCES sales (num_sale) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE SET NULL
);

CREATE TABLE invoices (
    num_invoice INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    num_sale INT NOT NULL,
    issue_date DATE,
    FOREIGN KEY (num_sale) REFERENCES sales (num_sale) ON DELETE CASCADE
);

-- TRIGGERS

-- Evita ventas con cantidades inválidas o stock insuficiente
DELIMITER $$

CREATE TRIGGER trg_validate_stock_sales_details
    BEFORE INSERT
    ON sales_details
    FOR EACH ROW
BEGIN
    DECLARE v_stock INT;

    SELECT amount
    INTO v_stock
    FROM products
    WHERE id = NEW.product_id
    FOR UPDATE;

    IF v_stock IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El producto no existe';
    END IF;

    IF v_stock < NEW.amount THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No hay stock suficiente para vender este producto';
    END IF;
END $$

DELIMITER ;

-- Reducir la cantidad disponible de un producto cuando se registra un detalle de venta
DELIMITER $$

CREATE TRIGGER trg_reduce_stock_sales_details
    AFTER INSERT
    ON sales_details
    FOR EACH ROW
BEGIN
    UPDATE products
    SET amount = amount - NEW.amount
    WHERE id = NEW.product_id;
END $$

DELIMITER ;

-- Devolver al inventario la cantidad de productos cuando se elimina un detalle venta
DELIMITER $$

CREATE TRIGGER trg_return_stock_sales_details
    AFTER DELETE
    ON sales_details
    FOR EACH ROW
BEGIN
    UPDATE products
    SET amount = amount + OLD.amount
    WHERE id = OLD.product_id;
END $$

DELIMITER ;

-- Incrementar el stock del producto cuando se registra un detalle de compra
DELIMITER $$

CREATE TRIGGER trg_increase_stock_purchases_details
    AFTER INSERT
    ON purchases_details
    FOR EACH ROW
BEGIN
    UPDATE products
    SET amount = amount + NEW.amount
    WHERE id = NEW.product_id;
END $$

DELIMITER ;

-- Disminuye el stock cuando se elimina un detalle de compra previamente registrado
DELIMITER $$

CREATE TRIGGER trg_decrease_stock_purchases_details
    AFTER DELETE
    ON purchases_details
    FOR EACH ROW
BEGIN
    UPDATE products
    SET amount = amount - OLD.amount
    WHERE id = OLD.product_id;
END $$

DELIMITER ;

-- Crea una deuda cuando no se paga completo en la venta
DELIMITER $$

CREATE TRIGGER trg_generate_debt
    AFTER INSERT
    ON sales
    FOR EACH ROW
BEGIN
    IF NEW.total_paid < NEW.total_sale THEN
        INSERT INTO debts (num_sale, total_debt, status)
        VALUES (NEW.num_sale, NEW.total_sale - NEW.total_paid, 'PENDING');
    END IF;
END $$

DELIMITER ;

-- Actualizar el estado de una deuda cuando se realizan pagos de dicha deuda
DELIMITER $$

CREATE TRIGGER trg_update_debt
    AFTER UPDATE
    ON sales
    FOR EACH ROW
BEGIN
    DECLARE v_saldo DECIMAL(10,2);

    SET v_saldo = NEW.total_sale - NEW.total_paid;

    UPDATE debts
    SET total_debt = IF(v_saldo <= 0, 0, v_saldo),
        status = IF(v_saldo <= 0, 'PAID', 'PENDING')
    WHERE num_sale = NEW.num_sale;
END $$

DELIMITER ;

-- VISTAS

-- Muestra las deudas asociadas a cada cliente con la venta correspondiente
CREATE VIEW vw_customers_debts AS
SELECT 
    d.num_debt,
    c.id AS customer_id,
    c.name AS customer_name,
    c.phone,
    v.num_sale,
    v.sale_date,
    v.total_sale,
    v.total_paid,
    d.total_debt AS current_debt,
    d.status
FROM debts d 
    JOIN sales v ON d.num_sale = v.num_sale
    LEFT JOIN customers c ON v.customer_id = c.id;

-- Muestra los productos vendidos en cada venta con sus cantidades y precios
CREATE VIEW vw_sales_report AS
SELECT 
    v.num_sale,
    p.id AS product_id,
    p.description AS product,
    d.amount AS quantity_sold,
    v.sale_date,
    c.name AS customer,
    v.total_sale
FROM sales_details d
    JOIN sales v ON d.num_sale = v.num_sale
    LEFT JOIN products p ON d.product_id = p.id
    LEFT JOIN customers c ON v.customer_id = c.id;

-- Muestra el estado actual del inventario de productos
CREATE VIEW vw_inventory AS
SELECT
    p.id,
    p.description,
    p.amount AS available_quantity,
    p.purchase_price,
    p.sale_price,
    COALESCE(GROUP_CONCAT(DISTINCT pr.rSocial SEPARATOR ', '), 'Sin Proveedor') AS provider,
    ((p.sale_price * p.amount) - (p.purchase_price * p.amount)) AS earnings
FROM products p
    LEFT JOIN purchases_details d ON p.id = d.product_id
    LEFT JOIN purchases c ON d.num_purchase = c.num_purchase
    LEFT JOIN providers pr ON c.provider_nit = pr.nit
GROUP BY p.id, p.description, p.amount, p.purchase_price, p.sale_price;

-- Lista las compras realizadas con sus proveedores y productos adquiridos
CREATE VIEW vw_purchasing_report AS
SELECT 
    c.num_purchase,
    p.description AS product,
    p.id AS product_id,
    d.amount AS quantity_purchased,
    p.purchase_price,
    p.sale_price,
    c.purchase_date,
    pr.rSocial AS provider,
    pr.nit AS provider_nit,
    c.total_paid AS total_purchase,
    ((p.sale_price * d.amount) - (p.purchase_price * d.amount)) AS earnings
FROM purchases_details d
    JOIN purchases c ON d.num_purchase = c.num_purchase
    LEFT JOIN products p ON d.product_id = p.id
    LEFT JOIN providers pr ON c.provider_nit = pr.nit;

-- STORED PROCEDURES

-- Inserta una venta y retorna el número de la venta
DELIMITER $$

CREATE PROCEDURE sp_create_sale(
    IN p_customer_id INT,
    IN p_total_sale DECIMAL(10, 2),
    IN p_total_paid DECIMAL(10, 2)
)
BEGIN
    INSERT INTO sales (customer_id, sale_date, total_sale, total_paid)
    VALUES (p_customer_id, CURDATE(), p_total_sale, p_total_paid);

    SELECT LAST_INSERT_ID() AS num_sale;
END $$

DELIMITER ;

-- Inserta una compra y retorna el numero de la compra
DELIMITER $$

CREATE PROCEDURE sp_create_purchase(
    IN p_provider_nit INT,
    IN p_total_paid DECIMAL(10, 2)
)
BEGIN
    INSERT INTO purchases (provider_nit, purchase_date, total_paid)
    VALUES (p_provider_nit, CURDATE(), p_total_paid);

    SELECT LAST_INSERT_ID() AS num_purchase;
END $$

DELIMITER ;

-- Insertar la información de una factura y devolver el número de la factura
DELIMITER $$

CREATE PROCEDURE sp_create_invoice(IN p_num_sale INT)
BEGIN
    INSERT INTO invoices (num_sale, issue_date)
    VALUES (p_num_sale, CURDATE());

    SELECT LAST_INSERT_ID() AS last_invoice_num;
END $$

DELIMITER ;

-- Índices en búsquedas frecuentes
CREATE INDEX idx_product_description ON products (description);
CREATE INDEX idx_customer_name ON customers (name);
CREATE INDEX idx_provider_rSocial ON providers (rSocial);

-- Índices en foreign keys para joins más rápidos
CREATE INDEX idx_provider_nit_purchase ON purchases (provider_nit);
CREATE INDEX idx_sale_customer_id ON sales (customer_id);
CREATE INDEX idx_debt_num_sale ON debts (num_sale);
CREATE INDEX idx_purchase_details_num_purchase ON purchases_details (num_purchase);
CREATE INDEX idx_purchase_detail_product_id ON purchases_details (product_id);
CREATE INDEX idx_sale_detail_num_sale ON sales_details (num_sale);
CREATE INDEX idx_sale_details_product_id ON sales_details (product_id);

-- Índices para reportes y búsquedas por fecha
CREATE INDEX idx_purchase_date ON purchases (purchase_date);
CREATE INDEX idx_sale_date ON sales (sale_date);
