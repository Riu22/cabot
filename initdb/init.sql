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

INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('lbx', 'alb', 45);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('$', 'aquaterm', 57.25);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('gex', 'geberit', 52);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('mex', 'genebre', 52);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('fix', 'celo', 70);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('wtx', 'uponor', 62);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('kmx', 'tubo plasticom', 72);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('bax', 'creamplast', 72);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('jax', 'armaflex', 70);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('eax', 'collak', 30);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('bdx', 'aliaxis', 40);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('dlx', 'dallmer', 55);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('fux', 'aquaterm', 57.25);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('bcx', 'aliaxis', 45);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('sox', 'accesorio', 49);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('kdx', 'aiscan', 60);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('hux', 'huliot', 45);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('ppx', 'cepex', 53);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('ecx', 'aux', 30);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('rox', 'super-ego', 10);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('hex', 'bosch', 30);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('rox', 'rotherberg', 30);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('ecx', 'tucai', 50);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('vax', 'arco', 50);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('ecx', 'genebre', 52);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('lax', 'genebre', 52);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('ecx', 'arco', 50);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('agx', 'watts', 45);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('ppx', 'hidroten', 40);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('gax', 'accesorio', 65);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('eax', 'aux', 35);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('dlx', 'dallmer', 55);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('mwx', 'uponor', 62);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('sof', 'sbi', 60);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('bcx', 'plasson', 55);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('aex', 'roca', 40);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('acx', 'roca', 38);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('agx', 'baxi', 35);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('syp', 's&p', 35);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('jng', 'jung', 50);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('bhx', 'standard', 42);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('pox', 'potermic', 40);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('mcx', 'conthidra', 35);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('phx', 'atusa', 35);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('fsx', 'fischer', 30);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('gir', 'gira', 45);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado) VALUES ('mux', 'ferro-plast', 38);
INSERT INTO reglas_facturacion (prefijo, marca, descuento_esperado)
VALUES ('DEF', 'GENERAL', 0.0);