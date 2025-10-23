-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: AgregarLibro
-- ===========================================================
CREATE PROCEDURE AgregarLibro
    @titulo VARCHAR(150),
    @autor VARCHAR(100),
    @genero VARCHAR(50) = NULL,  -- Género es opcional
    @año_publicacion SMALLINT = NULL,  -- Año de publicación es opcional
    @estado VARCHAR(20) = 'Disponible',  -- Estado por defecto
    @descripcion VARCHAR(500) = NULL,  -- Descripción es opcional
    @imagen_url VARCHAR(255) = NULL  -- Ruta de la imagen
AS
BEGIN
    -- Comenzar la transacción para asegurar la integridad de los datos
    BEGIN TRY
        BEGIN TRANSACTION
        
        -- Insertar el nuevo libro en la tabla Libros
        INSERT INTO Libros (titulo, autor, genero, año_publicacion, estado, descripcion, imagen_url)
        VALUES (@titulo, @autor, @genero, @año_publicacion, @estado, @descripcion, @imagen_url);
        
        -- Confirmar la transacción
        COMMIT;
        
        PRINT 'Libro agregado exitosamente.';
    END TRY
    BEGIN CATCH
        -- Si ocurre un error, hacer rollback de la transacción
        ROLLBACK;
        
        PRINT 'Error al agregar el libro: ' + ERROR_MESSAGE();
    END CATCH
END
GO



