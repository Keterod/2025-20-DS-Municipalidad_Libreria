-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: VerPrestamosPorUsuario
-- ===========================================================
CREATE PROCEDURE VerPrestamosPorUsuario
    @id_usuario INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Consulta los préstamos realizados por un usuario específico,
    -- mostrando primero los "Prestado", luego "Vencido" y al final "Devuelto"
    SELECT 
        L.titulo,
        P.fecha_prestamo,
        P.fecha_vencimiento,
        P.estado_P
    FROM Prestamos P
    INNER JOIN Libros L ON P.id_libro = L.id_libro
    WHERE P.id_usuario = @id_usuario
    ORDER BY 
        CASE 
            WHEN P.estado_P = 'Prestado' THEN 1
            WHEN P.estado_P = 'Vencido' THEN 2
            WHEN P.estado_P = 'Devuelto' THEN 3
            ELSE 4
        END,
        P.fecha_prestamo DESC;  -- Dentro de cada grupo, los más recientes primero

    PRINT 'Consulta de préstamos por usuario realizada correctamente.';
END;
GO
