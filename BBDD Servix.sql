DROP DATABASE IF EXISTS Servix;
CREATE DATABASE Servix;
USE Servix;

CREATE TABLE Ingrediente(
	nombre_ingrediente VARCHAR(30) PRIMARY KEY
);

CREATE TABLE Menu(
	id_menu INT PRIMARY KEY auto_increment
);

CREATE TABLE Plato(
	id_plato  INT PRIMARY KEY auto_increment,
    nombre VARCHAR(40) NOT NULL,
    precio FLOAT NOT NULL,
    categoria ENUM("bebida", "entrante", "primer_plato", "segundo_plato", "postre") NOT NULL,
    id_menu INT,
    FOREIGN KEY (id_menu) REFERENCES Menu(id_menu)
);

CREATE TABLE ingrediente_plato(
	id_plato INT,
    nombre_ingrediente VARCHAR(30) NOT NULL,
    PRIMARY KEY (id_plato, nombre_ingrediente),
    FOREIGN KEY (id_plato) REFERENCES Plato(id_plato),
    FOREIGN KEY (nombre_ingrediente) REFERENCES Ingrediente(nombre_ingrediente)
);

CREATE TABLE Usuario(
	id INT PRIMARY KEY auto_increment,
	nombre VARCHAR(30) NOT NULL,
    apellido1 VARCHAR(30) NOT NULL,
    apellido2 VARCHAR(30),
	telefono VARCHAR(30) NOT NULL,
    correo VARCHAR(40) NOT NULL,
    usuario_login VARCHAR(30) NOT NULL,
    contrasenya_login VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    haIniciadoSesion BOOLEAN DEFAULT FALSE
);

CREATE TABLE Restaurante(
	id_restaurante INT PRIMARY KEY auto_increment,
    nombre VARCHAR(50) NOT NULL,
    capacidad INT NOT NULL,
    apertura INT NOT NULL,
    cierre INT NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    direccion VARCHAR(75) NOT NULL,
	id_encargado INT NOT NULL
);

CREATE TABLE Mesa(
	id_mesa  INT PRIMARY KEY auto_increment,
    capacidad INT NOT NULL,
    estado BOOLEAN NOT NULL
);

CREATE TABLE Reserva(
	id_reserva INT PRIMARY KEY auto_increment,
    estado_reserva ENUM ("pendiente", "confirmada", "cancelada") NOT NULL,
    n_comensales INT NOT NULL,
    hora TIME NOT NULL,
    fecha DATE NOT NULL,
    id_cliente INT NOT NULL,
    id_mesa INT,
    FOREIGN KEY (id_cliente) REFERENCES Usuario(id),
    FOREIGN KEY (id_mesa) REFERENCES Mesa(id_mesa)
);
INSERT INTO Usuario(nombre, apellido1, telefono, correo, usuario_login, contrasenya_login, rol) VALUES ("Admin", "Admin", "12334567889", "admin@gmail.com", "admin", "$2a$10$vTeqkkqwmGbctARRQcRRf.NR.yIJoh0mRnrksV36DUaZpNIoESMh.", "Encargado");