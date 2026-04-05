DROP DATABASE IF EXISTS Servix;
CREATE DATABASE Servix;
USE Servix;

CREATE TABLE Restaurante(
    id_restaurante INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    correo VARCHAR(40),
	capacidad INT NOT NULL,
    apertura INT NOT NULL,
    cierre INT NOT NULL
);

CREATE TABLE Ingrediente(
	nombre_ingrediente VARCHAR(30) PRIMARY KEY
);

CREATE TABLE Plato(
	id_plato  INT PRIMARY KEY auto_increment,
    nombre VARCHAR(40) NOT NULL,
    precio DECIMAL(6,2) NOT NULL,
    categoria ENUM("bebida", "entrante", "primer_plato", "segundo_plato", "postre") NOT NULL,
    id_restaurante INT,
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante)
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
    usuario_login VARCHAR(30) UNIQUE NOT NULL,
    contrasenya_login VARCHAR(255) NOT NULL,
    rol ENUM ("cliente", "encargado", "empleado", "gerente") NOT NULL,
    haIniciadoSesion BOOLEAN DEFAULT FALSE,
    fecha_creacion DATETIME DEFAULT current_timestamp
);

CREATE TABLE usuario_restaurante(
	id_usuario INT,
    id_restaurante INT,
    PRIMARY KEY (id_usuario, id_restaurante),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante)
);

CREATE TABLE Mesa(
	id_mesa  INT PRIMARY KEY auto_increment,
    capacidad INT NOT NULL,
    estado BOOLEAN NOT NULL,
    id_restaurante INT NOT NULL,
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante)
);

CREATE TABLE Reserva(
	id_reserva INT PRIMARY KEY auto_increment,
    estado_reserva ENUM ("pendiente", "confirmada", "cancelada") NOT NULL,
    n_comensales INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    id_cliente INT NOT NULL,
    id_mesa INT,
    id_restaurante INT,
    FOREIGN KEY (id_cliente) REFERENCES Usuario(id),
    FOREIGN KEY (id_mesa) REFERENCES Mesa(id_mesa),
    FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante)
);

