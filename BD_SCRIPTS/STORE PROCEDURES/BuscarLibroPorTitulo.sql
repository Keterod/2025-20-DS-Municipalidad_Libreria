-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: BuscarLibroPorTitulo
-- ===========================================================
CREATE PROCEDURE BuscarLibroPorTitulo
    @parte_titulo VARCHAR(150)  -- Parte del título para buscar
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        -- Buscar libros cuyo título contenga la palabra proporcionada
        SELECT 
            id_libro,
            titulo, 
            imagen_url, 
            estado_L
        FROM Libros
        WHERE titulo LIKE '%' + @parte_titulo + '%'
        ORDER BY titulo ASC;

        -- Verificar si se encontraron resultados
        IF @@ROWCOUNT = 0
            PRINT 'No se encontraron libros con esa parte de título.';
        ELSE
            PRINT 'Búsqueda completada con éxito.';
    END TRY

    BEGIN CATCH
        PRINT 'Error al buscar libros: ' + ERROR_MESSAGE();
    END CATCH
END;
GO





