-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: ConsultarLibroPorID
-- ===========================================================
CREATE PROCEDURE ConsultarLibroPorID
    @id_libro INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        -- Consultar los datos del libro
        SELECT 
            id_libro,
            titulo,
            autor,
            genero,
            año_publicacion,
            estado_L,
            descripcion,
            imagen
        FROM Libros
        WHERE id_libro = @id_libro;

        PRINT 'Consulta de libro realizada correctamente.';
    END TRY

    BEGIN CATCH
        PRINT 'Error al consultar el libro: ' + ERROR_MESSAGE();
    END CATCH
END;
GO


