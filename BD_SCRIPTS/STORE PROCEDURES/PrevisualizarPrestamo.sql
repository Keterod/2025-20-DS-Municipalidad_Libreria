-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: PrevisualizarPrestamo
-- ===========================================================
CREATE PROCEDURE PrevisualizarPrestamo
    @id_usuario INT,
    @id_libro INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Calcular fechas (solo para mostrar)
    DECLARE @fecha_solicitud DATETIME = GETDATE();
    DECLARE @fecha_devolucion DATETIME = DATEADD(DAY, 7, GETDATE());

    -- Mostrar los datos al usuario sin guardar nada
    SELECT 
        L.titulo,
        L.imagen_url,
        U.nombre AS nombre_solicitante,
        @fecha_solicitud AS fecha_solicitud,
        @fecha_devolucion AS fecha_devolucion
    FROM Libros L
    INNER JOIN Usuarios U ON U.id_usuario = @id_usuario
    WHERE L.id_libro = @id_libro;

    PRINT 'Consulta previa realizada correctamente.';
END;
GO
