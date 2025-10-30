-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: VerTodosLosPrestamos
-- ===========================================================
CREATE PROCEDURE VerTodosLosPrestamos
AS
BEGIN
    SET NOCOUNT ON;

    -- Consulta general de todos los préstamos (para bibliotecario)
    SELECT 
        P.id_prestamo,
        L.titulo,
        U.nombre AS usuario_solicitante,
        P.fecha_prestamo,
        P.fecha_vencimiento,
        P.estado_P
    FROM Prestamos P
    INNER JOIN Libros L ON P.id_libro = L.id_libro
    INNER JOIN Usuarios U ON P.id_usuario = U.id_usuario
    ORDER BY 
        CASE 
            WHEN P.estado_P = 'Prestado' THEN 1
            WHEN P.estado_P = 'Vencido' THEN 2
            WHEN P.estado_P = 'Devuelto' THEN 3
            ELSE 4
        END,
        P.fecha_prestamo DESC;

    PRINT 'Consulta de todos los préstamos realizada correctamente.';
END;
GO
