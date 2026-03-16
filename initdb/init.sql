CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS reglas_facturacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    prefijo VARCHAR(3) NOT NULL,
    marca VARCHAR(255) DEFAULT NULL,
    descripcion TEXT,
    descuento_esperado DECIMAL(5,2) NOT NULL,
    INDEX (prefijo),
    INDEX (marca)
    );