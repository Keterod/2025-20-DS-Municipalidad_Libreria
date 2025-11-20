-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: ObtenerRecomendaciones
-- ===========================================================
CREATE Or Alter PROCEDURE ObtenerRecomendaciones
    @id_usuario INT
AS
BEGIN
    SET NOCOUNT ON;

	--Obtener el género más leído por el usuario
    DECLARE @genero_favorito VARCHAR(100);

    SELECT TOP 1 
        @genero_favorito = L.genero
    FROM Prestamos P
    INNER JOIN Libros L ON P.id_libro = L.id_libro
    WHERE P.id_usuario = @id_usuario
    GROUP BY L.genero
    ORDER BY COUNT(*) DESC;

    -- Si el usuario no tiene historial, no hay recomendaciones
    IF @genero_favorito IS NULL
    BEGIN
        SELECT 'SIN_RECOMENDACIONES' AS mensaje;
        RETURN;
    END;

    -- Obtener libros recomendados del género favorito
    SELECT 
        L.id_libro,
        L.titulo,
        L.autor,
        L.genero,
        L.año_publicacion,
        L.descripcion,
        L.estado_L,
		l.imagen 
    FROM Libros L
    WHERE L.genero = @genero_favorito
      AND L.estado_L = 'Disponible'
      AND L.id_libro NOT IN (
            SELECT id_libro 
            FROM Prestamos 
            WHERE id_usuario = @id_usuario
      );

END;
GO

