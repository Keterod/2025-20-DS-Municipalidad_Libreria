-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: ActualizarLibro
-- ===========================================================
CREATE PROCEDURE ActualizarLibro
    @id_libro INT,  -- ID del libro a editar
    @titulo VARCHAR(150),
    @autor VARCHAR(100),
    @genero VARCHAR(50) = NULL,  -- Género es opcional
    @año_publicacion SMALLINT = NULL,  -- Año de publicación es opcional
    @estado_L VARCHAR(20) = 'Disponible',  -- Estado por defecto
    @descripcion VARCHAR(500) = NULL,  -- Descripción es opcional
    @imagen VARBINARY(MAX) = NULL  -- Ruta de la imagen
AS
BEGIN
    -- Comenzar la transacción para asegurar la integridad de los datos
    BEGIN TRY
        BEGIN TRANSACTION
        
        -- Actualizar los campos del libro (excepto id_libro y fecha_registro)
        UPDATE Libros
        SET 
            titulo = @titulo,
            autor = @autor,
            genero = @genero,
            año_publicacion = @año_publicacion,
            estado_L = ISNULL(@estado_L, estado_L),
            descripcion = @descripcion,
            imagen = @imagen
        WHERE id_libro = @id_libro;
        
        -- Confirmar la transacción
        COMMIT;
        
        PRINT 'Libro actualizado exitosamente.';
    END TRY
    BEGIN CATCH
        -- Si ocurre un error, hacer rollback de la transacción
        ROLLBACK;
        
        PRINT 'Error al actualizar el libro: ' + ERROR_MESSAGE();
    END CATCH
END
GO












