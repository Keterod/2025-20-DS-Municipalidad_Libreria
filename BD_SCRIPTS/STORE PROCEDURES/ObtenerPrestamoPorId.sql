CREATE PROCEDURE ObtenerPrestamoPorId
    @id_prestamo INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        P.id_prestamo,
        L.titulo AS titulo,
        U.nombre AS usuario_solicitante,
        P.fecha_prestamo,
        P.fecha_vencimiento,
        P.estado_P
    FROM Prestamos P
    INNER JOIN Libros L ON P.id_libro = L.id_libro
    INNER JOIN Usuarios U ON P.id_usuario = U.id_usuario
    WHERE P.id_prestamo = @id_prestamo;
END;
GO
