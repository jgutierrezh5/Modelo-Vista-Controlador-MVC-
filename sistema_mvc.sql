CREATE DATABASE IF NOT EXISTS sistema_mvc CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE sistema_mvc;

CREATE TABLE IF NOT EXISTS marcas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    pais VARCHAR(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    marca_id INT NOT NULL,
    FOREIGN KEY (marca_id) REFERENCES marcas(id)
);

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    telefono VARCHAR(30) NOT NULL,
    correo VARCHAR(120) NOT NULL
);

INSERT INTO marcas (nombre, pais) VALUES
('Samsung', 'Corea del Sur'),
('Logitech', 'Suiza');

INSERT INTO productos (nombre, precio, stock, marca_id) VALUES
('Teclado inalámbrico', 185.00, 12, 2),
('Monitor 24 pulgadas', 950.00, 8, 1);

INSERT INTO clientes (nombre, telefono, correo) VALUES
('Carlos Pérez', '5555-1234', 'carlos@email.com'),
('María López', '5555-5678', 'maria@email.com');
