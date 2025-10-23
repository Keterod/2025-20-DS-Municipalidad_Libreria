-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: BuscarLibroPorTitulo
-- ===========================================================
CREATE PROCEDURE BuscarLibroPorTitulo
    @parte_titulo VARCHAR(100)  -- Parte del título para buscar
AS
BEGIN
    -- Consultar libros cuyo título contenga la palabra proporcionada
    SELECT 
        titulo, 
        imagen_url, 
        estado
    FROM 
        Libros
    WHERE 
        titulo LIKE '%' + @parte_titulo + '%';  -- Búsqueda parcial en el título
    
    -- Verificar si se encontraron resultados
    IF @@ROWCOUNT = 0
    BEGIN
        PRINT 'No se encontraron libros con esa parte de título.';
    END
    ELSE
    BEGIN
        PRINT 'Búsqueda completada con éxito.';
    END
END
GO



