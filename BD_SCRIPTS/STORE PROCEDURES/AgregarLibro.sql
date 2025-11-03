-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: AgregarLibro
-- ===========================================================
CREATE OR ALTER PROCEDURE AgregarLibro
    @titulo VARCHAR(150),
    @autor VARCHAR(150),
    @genero VARCHAR(50),
    @anio INT,
    @estado VARCHAR(50),
    @descripcion VARCHAR(MAX),
    @imagen VARBINARY(MAX)  -- <-- aquí es lo importante
AS
BEGIN
    INSERT INTO Libros(titulo, autor, genero, año_publicacion, estado_L, descripcion, imagen)
    VALUES (@titulo, @autor, @genero, @anio, @estado, @descripcion, @imagen);
END;


