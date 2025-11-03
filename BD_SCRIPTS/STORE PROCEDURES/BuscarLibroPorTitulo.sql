-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: BuscarLibroPorTitulo
-- ===========================================================
CREATE PROCEDURE BuscarLibroPorTitulo
    @parte_titulo VARCHAR(150)
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        SELECT 
            id_libro,
            titulo,
            autor,
            genero,
            año_publicacion,
            estado_L AS estado,
            descripcion,
            imagen -- columna tipo VARBINARY(MAX)
        FROM Libros
        WHERE titulo LIKE '%' + @parte_titulo + '%'
        ORDER BY titulo ASC;
    END TRY
    BEGIN CATCH
        PRINT 'Error al buscar libros: ' + ERROR_MESSAGE();
    END CATCH
END;
GO






