-- ===========================================================
--  BASE DE DATOS: Biblioteca
--  Proyecto: Sistema de Gestión de Bibliotecas Públicas
--  Arquitectura: 4 capas (MVC)
-- ===========================================================

-- CREAR BASE DE DATOS
CREATE DATABASE Biblioteca;
GO

USE Biblioteca;
GO

-- ===========================================================
-- TABLA: RolesPermisos
-- ===========================================================
CREATE TABLE RolesPermisos (
    id_rol INT IDENTITY(1,1) PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL,
    descripcion VARCHAR(150)
);
GO

-- Datos iniciales (solo dos roles)
INSERT INTO RolesPermisos (nombre_rol, descripcion) VALUES
('Bibliotecario', 'Gestión completa del sistema (catálogo, préstamos, devoluciones, usuarios)'),
('Usuario', 'Acceso al catálogo, préstamos y recomendaciones personalizadas');
GO

-- ===========================================================
-- TABLA: Usuarios
-- ===========================================================
CREATE TABLE Usuarios (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARBINARY(MAX) NOT NULL,
    celular VARCHAR(15) NULL,
    DNI CHAR(8) NOT NULL UNIQUE, -- Nuevo campo DNI (8 dígitos)
    id_rol INT NOT NULL,
    fecha_registro DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Usuarios_Roles FOREIGN KEY (id_rol)
        REFERENCES RolesPermisos(id_rol)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);
GO

-- ===========================================================
-- TABLA: Libros
-- ===========================================================
CREATE TABLE Libros (
    id_libro INT IDENTITY(1,1) PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NULL,
    año_publicacion SMALLINT NULL,
    estado_L VARCHAR(20) DEFAULT 'Disponible' CHECK (estado_L IN ('Disponible', 'En préstamo', 'No stock')),
    fecha_registro DATETIME DEFAULT GETDATE(),
    descripcion VARCHAR(500) NULL, -- Descripción con máximo de 500 caracteres (~100 palabras)
    imagen VARBINARY(MAX) NULL -- Ruta o URL de la imagen
);
GO

-- ===========================================================
-- TABLA: Prestamos
-- ===========================================================
CREATE TABLE Prestamos (
    id_prestamo INT IDENTITY(1,1) PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_libro INT NOT NULL,
    fecha_prestamo DATETIME DEFAULT GETDATE(),
    fecha_vencimiento DATETIME NOT NULL,
    fecha_devolucion DATETIME NULL,
    estado_P VARCHAR(20) DEFAULT 'Prestado' CHECK (estado_P IN ('Prestado', 'Devuelto', 'Vencido')),
    CONSTRAINT FK_Prestamos_Usuarios FOREIGN KEY (id_usuario)
        REFERENCES Usuarios(id_usuario)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FK_Prestamos_Libros FOREIGN KEY (id_libro)
        REFERENCES Libros(id_libro)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);
GO

-- ===========================================================
-- TABLA: HistorialLectura
-- ===========================================================
CREATE TABLE HistorialLectura (
    id_historial INT IDENTITY(1,1) PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_libro INT NOT NULL,
    fecha_lectura DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Historial_Usuarios FOREIGN KEY (id_usuario)
        REFERENCES Usuarios(id_usuario)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FK_Historial_Libros FOREIGN KEY (id_libro)
        REFERENCES Libros(id_libro)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);
GO

-- ===========================================================
-- TABLA: Recomendaciones
-- ===========================================================
CREATE TABLE Recomendaciones (
    id_recomendacion INT IDENTITY(1,1) PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_libro INT NOT NULL,
    puntaje FLOAT NOT NULL,
    fecha_generacion DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_Recom_Usuarios FOREIGN KEY (id_usuario)
        REFERENCES Usuarios(id_usuario)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FK_Recom_Libros FOREIGN KEY (id_libro)
        REFERENCES Libros(id_libro)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);
GO

-- ===========================================================
-- TABLA: Notificaciones
-- ===========================================================
CREATE TABLE Notificaciones (
    id_notificacion INT IDENTITY(1,1) PRIMARY KEY,  -- Identificador único
    id_usuario INT NOT NULL,  -- Usuario que recibe la notificación
    id_libro INT NULL,  -- Libro relacionado con la notificación (opcional)
    tipo VARCHAR(50) NOT NULL,  -- Tipo de notificación (por ejemplo: "Vencimiento", "Recomendación", etc.)
    mensaje VARCHAR(250) NOT NULL,  -- Mensaje que se mostrará al usuario
    fecha_envio DATETIME DEFAULT GETDATE(),  -- Fecha en que se envió la notificación
    CONSTRAINT FK_Notif_Usuarios FOREIGN KEY (id_usuario)
        REFERENCES Usuarios(id_usuario)
        ON UPDATE CASCADE
        ON DELETE CASCADE,  -- Si se borra el usuario, se eliminan sus notificaciones
    CONSTRAINT FK_Notif_Libros FOREIGN KEY (id_libro)
        REFERENCES Libros(id_libro)
        ON UPDATE CASCADE
        ON DELETE SET NULL  -- Si se borra un libro, la notificación permanece pero sin ID de libro
);
GO

-- =============================================






