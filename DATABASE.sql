CREATE DATABASE IF NOT EXISTS db_papeleria_saas;

USE db_papeleria_saas;

CREATE TABLE plans (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    description VARCHAR(500),
    billing_cycle VARCHAR(10) NOT NULL -- MONTHLY, YEARLY
);

CREATE TABLE companies (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nit INT UNIQUE NULL,
    name VARCHAR(100),
    address VARCHAR(50) NULL,
    phone VARCHAR(15) NULL,
    email VARCHAR(60) NULL,
    registration_date DATE,
    status VARCHAR(30), -- ACTIVE, INACTIVE
    plan_id INT,
    FOREIGN KEY (plan_id) REFERENCES plans(id)
);

CREATE TABLE company_payments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    company_id INT NOT NULL,
    plan_id INT NOT NULL,
    amount_paid DECIMAL(12,2) NOT NULL,
    payment_date DATE NOT NULL,
    expiration_date DATE NOT NULL,
    payment_reference VARCHAR(100),
    observations VARCHAR(255),
    status VARCHAR(20), -- PAID, PENDING
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE,
    FOREIGN KEY (plan_id) REFERENCES plans(id)
);

CREATE TABLE company_sequences (
    id_company INT PRIMARY KEY,
    last_sale_number INT DEFAULT 0,
    last_purchase_number INT DEFAULT 0,
    last_invoice_number INT DEFAULT 0,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    identification INT NOT NULL,
    name VARCHAR(50),
    user VARCHAR(20),
    password VARCHAR(400),
    role VARCHAR(20),
    status VARCHAR(20), -- ACTIVE, INACTIVE
    id_company INT,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE,
    UNIQUE (id_company, user)
);

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    description VARCHAR(100) NOT NULL,
    amount INT NOT NULL DEFAULT 0 CHECK (amount >= 0),
    purchase_price DECIMAL(12, 2) CHECK (purchase_price >= 0),
    sale_price DECIMAL(12, 2) CHECK (sale_price >= 0),
    id_company INT,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE
);

CREATE TABLE providers (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nit INT NULL,
    rSocial VARCHAR(100) NOT NULL,
    address VARCHAR(50),
    phone VARCHAR(15),
    email VARCHAR(60) NULL,
    id_company INT,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE,
    UNIQUE (id_company, nit)
);

CREATE TABLE provider_products (
    provider_id INT,
    product_id INT,
    PRIMARY KEY (provider_id, product_id),
    FOREIGN KEY (provider_id) REFERENCES providers(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    identification INT,
    name VARCHAR(50),
    phone VARCHAR(15),
    email VARCHAR(60),
    id_company INT,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE,
    UNIQUE (id_company, identification)
);

CREATE TABLE purchases (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    purchase_number INT,
    provider_id INT NULL,
    purchase_date DATE,
    total_purchase DECIMAL(12, 2) CHECK (total_purchase >= 0),
    id_company INT,
    FOREIGN KEY (provider_id) REFERENCES providers(id) ON DELETE SET NULL,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE,
    UNIQUE (id_company, purchase_number)
);

CREATE TABLE purchases_details (
    id_detail INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    purchase_id INT,
    product_id INT NULL,
    amount INT NOT NULL CHECK (amount > 0),
    unit_price DECIMAL(12, 2) CHECK (unit_price >= 0),
    FOREIGN KEY (purchase_id) REFERENCES purchases(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE SET NULL
);

CREATE TABLE sales (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    sale_number INT,
    customer_id INT NULL,
    sale_date DATE,
    total_paid DECIMAL(12, 2) CHECK (total_paid >= 0),
    total_sale DECIMAL(12, 2) CHECK (total_sale >= 0),
    id_company INT,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE SET NULL,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE,
    UNIQUE (id_company, sale_number)
);

CREATE TABLE sales_details (
    id_detail INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    sale_id INT,
    product_id INT NULL,
    amount INT NOT NULL CHECK (amount > 0),
    unit_price DECIMAL(12, 2) CHECK (unit_price >= 0),
    FOREIGN KEY (sale_id) REFERENCES sales(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE SET NULL
);

CREATE TABLE debts (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    sale_id INT NOT NULL UNIQUE,
    total_debt DECIMAL(12, 2) CHECK (total_debt >= 0),
    status VARCHAR(15), -- PAID, PENDING
    id_company INT,
    FOREIGN KEY (sale_id) REFERENCES sales(id) ON DELETE CASCADE,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE
);

CREATE TABLE invoices (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    invoice_number INT,
    sale_id INT NOT NULL,
    issue_date DATE,
    id_company INT,
    FOREIGN KEY (sale_id) REFERENCES sales(id) ON DELETE CASCADE,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE,
    UNIQUE (id_company, invoice_number)
);

CREATE TABLE logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_company INT,
    id_user INT,
    ip_address VARCHAR(45) NULL,
    action VARCHAR(20),
    entity VARCHAR(30),
    entity_id INT,
    description VARCHAR(255),
    created_at DATETIME,
    FOREIGN KEY (id_company) REFERENCES companies(id) ON DELETE CASCADE,
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE SET NULL
);

-- TRIGGERS

-- Insertar valores en la tabla para las secuencias con valores por defecto
DELIMITER $$

CREATE TRIGGER trg_create_company_sequence
    AFTER INSERT
    ON companies
    FOR EACH ROW
BEGIN
    INSERT INTO company_sequences (id_company, last_sale_number, last_purchase_number, last_invoice_number)
    VALUES (NEW.id, 0, 0, 0);
END $$

DELIMITER ;

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
        INSERT INTO debts (sale_id, total_debt, status, id_company)
        VALUES (NEW.id, NEW.total_sale - NEW.total_paid, 'PENDING', NEW.id_company);
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
    WHERE sale_id = NEW.id;
END $$

DELIMITER ;

-- VISTAS

-- Muestra las deudas asociadas a cada cliente con la venta correspondiente
CREATE VIEW vw_customers_debts AS
SELECT 
    d.id,
    c.id AS customer_id,
    c.name AS customer_name,
    c.phone,
    s.sale_number,
    s.sale_date,
    s.total_sale,
    s.total_paid,
    d.total_debt AS current_debt,
    d.status,
    d.id_company
FROM debts d 
    JOIN sales s ON d.sale_id = s.id
    LEFT JOIN customers c ON s.customer_id = c.id;

-- Muestra los productos vendidos en cada venta con sus cantidades y precios
CREATE VIEW vw_sales_report AS
SELECT 
    s.sale_number,
    p.id AS product_id,
    p.description AS product,
    sd.amount AS quantity_sold,
    s.sale_date,
    c.name AS customer,
    s.total_sale,
    s.id_company
FROM sales_details sd
    JOIN sales s ON sd.sale_id = s.id
    LEFT JOIN products p ON sd.product_id = p.id
    LEFT JOIN customers c ON s.customer_id = c.id;

-- Muestra el estado actual del inventario de productos
CREATE VIEW vw_inventory AS
SELECT
    p.id,
    p.description,
    p.amount AS available_quantity,
    p.purchase_price,
    p.sale_price,
    COALESCE(GROUP_CONCAT(DISTINCT pr.rSocial SEPARATOR ', '), 'Sin Proveedor') AS provider,
    ((p.sale_price * p.amount) - (p.purchase_price * p.amount)) AS earnings,
    p.id_company
FROM products p
    LEFT JOIN purchases_details pd ON p.id = pd.product_id
    LEFT JOIN purchases ps ON pd.purchase_id = ps.id
    LEFT JOIN providers pr ON ps.provider_id = pr.id
GROUP BY p.id, p.description, p.amount, p.purchase_price, p.sale_price;

-- Lista las compras realizadas con sus proveedores y productos adquiridos
CREATE VIEW vw_purchasing_report AS
SELECT 
    ps.purchase_number,
    p.description AS product,
    p.id AS product_id,
    pd.amount AS quantity_purchased,
    p.purchase_price,
    p.sale_price,
    ps.purchase_date,
    pr.rSocial AS provider,
    pr.nit AS provider_nit,
    ps.total_purchase,
    ((p.sale_price * pd.amount) - (p.purchase_price * pd.amount)) AS earnings,
    ps.id_company
FROM purchases_details pd
    JOIN purchases ps ON pd.purchase_id = ps.id
    LEFT JOIN products p ON pd.product_id = p.id
    LEFT JOIN providers pr ON ps.provider_id = pr.id;

-- Lista los reportes de compras
CREATE VIEW vw_payments_report AS
SELECT
    c.id,
    c.nit,
    c.name,
    c.address,
    c.phone,
    c.registration_date,
    p.name as plan_name,
    cp.payment_date AS last_payment,
    cp.expiration_date AS next_payment,
    c.status
FROM companies c
    JOIN plans p ON p.id = c.plan_id
    LEFT JOIN company_payments cp ON cp.id = (
        SELECT id
        FROM company_payments
        WHERE company_id = c.id
        ORDER BY payment_date DESC
        LIMIT 1
    );

-- STORED PROCEDURES

-- Inserta una venta y retorna el número de la venta
DELIMITER $$

CREATE PROCEDURE sp_create_sale(
    IN p_customer_id INT,
    IN p_total_paid DECIMAL(12, 2),
    IN p_total_sale DECIMAL(12, 2),
    IN p_id_company INT
)
BEGIN
    DECLARE v_sale_number INT;

    UPDATE company_sequences
    SET last_sale_number = LAST_INSERT_ID(last_sale_number + 1)
    WHERE id_company = p_id_company;

    SET v_sale_number = LAST_INSERT_ID();

    INSERT INTO sales (sale_number, customer_id, sale_date, total_paid, total_sale, id_company)
    VALUES (v_sale_number, p_customer_id, CURDATE(), p_total_paid, p_total_sale, p_id_company);

    SELECT v_sale_number AS sale_number;
END $$

DELIMITER ;

-- Inserta una compra y retorna el número de la compra
DELIMITER $$

CREATE PROCEDURE sp_create_purchase(
    IN p_provider_id INT,
    IN p_total_purchase DECIMAL(12, 2),
    IN p_id_company INT
)
BEGIN
    DECLARE v_purchase_number INT;

    UPDATE company_sequences
    SET last_purchase_number = LAST_INSERT_ID(last_purchase_number + 1)
    WHERE id_company = p_id_company;

    SET v_purchase_number = LAST_INSERT_ID();

    INSERT INTO purchases (purchase_number, provider_id, purchase_date, total_purchase, id_company)
    VALUES (v_purchase_number, p_provider_id, CURDATE(), p_total_purchase, p_id_company);

    SELECT v_purchase_number AS purchase_number;
END $$

DELIMITER ;

-- Insertar la información de una factura y devolver el número de la factura
DELIMITER $$

CREATE PROCEDURE sp_create_invoice(
    IN p_sale_id INT,
    IN p_id_company INT
)
BEGIN
    DECLARE v_invoice_number INT;

    UPDATE company_sequences
    SET last_invoice_number = LAST_INSERT_ID(last_invoice_number + 1)
    WHERE id_company = p_id_company;

    SET v_invoice_number = last_insert_id();

    INSERT INTO invoices (invoice_number, sale_id, issue_date, id_company)
    VALUES (v_invoice_number, p_sale_id, CURDATE(), p_id_company);

    SELECT v_invoice_number AS invoice_number;
END $$

DELIMITER ;

-- Índices en búsquedas frecuentes

CREATE INDEX idx_product_description ON products(description);
CREATE INDEX idx_customer_name ON customers(name);
CREATE INDEX idx_provider_rSocial ON providers(rSocial);

CREATE INDEX idx_provider_id_purchase ON purchases(provider_id);
CREATE INDEX idx_sale_customer_id ON sales(customer_id);
CREATE INDEX idx_debt_id_sale ON debts(sale_id);
CREATE INDEX idx_purchase_details_id_purchase ON purchases_details(purchase_id);
CREATE INDEX idx_purchase_detail_product_id ON purchases_details(product_id);
CREATE INDEX idx_sale_detail_id_sale ON sales_details(sale_id);
CREATE INDEX idx_sale_details_product_id ON sales_details(product_id);

CREATE INDEX idx_purchase_date ON purchases(purchase_date);
CREATE INDEX idx_sale_date ON sales(sale_date);

CREATE INDEX idx_debts_company ON debts(id_company);
CREATE INDEX idx_products_company ON products(id_company);
CREATE INDEX idx_customers_company ON customers(id_company);
CREATE INDEX idx_providers_company ON providers(id_company);
CREATE INDEX idx_users_company ON users(id_company);

CREATE INDEX idx_company_payment ON company_payments(company_id, payment_date DESC);
