-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: RegistrarDevolucion
-- ===========================================================
CREATE PROCEDURE RegistrarDevolucion
    @id_prestamo INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

        DECLARE @id_libro INT;

        -- 1 Obtener el ID del libro asociado al préstamo
        SELECT @id_libro = id_libro
        FROM Prestamos
        WHERE id_prestamo = @id_prestamo;

        -- Validar que el préstamo exista
        IF @id_libro IS NULL
        BEGIN
            PRINT 'El préstamo no existe.';
            ROLLBACK;
            RETURN;
        END;

        -- 2 Verificar si el préstamo ya fue devuelto
        IF EXISTS (SELECT 1 FROM Prestamos WHERE id_prestamo = @id_prestamo AND estado_P = 'Devuelto')
        BEGIN
            PRINT 'Este préstamo ya fue devuelto anteriormente.';
            ROLLBACK;
            RETURN;
        END;

        -- 3 Registrar la fecha de devolución y cambiar el estado del préstamo
        UPDATE Prestamos
        SET 
            fecha_devolucion = GETDATE(),
            estado_P = 'Devuelto'
        WHERE id_prestamo = @id_prestamo;

        -- 4 Cambiar el estado del libro a "Disponible"
        UPDATE Libros
        SET estado_L = 'Disponible'
        WHERE id_libro = @id_libro;

        -- 5 Mostrar confirmación
        SELECT 
            P.id_prestamo,
            L.titulo,
            P.fecha_prestamo,
            P.fecha_devolucion,
            P.estado_P
        FROM Prestamos P
        INNER JOIN Libros L ON P.id_libro = L.id_libro
        WHERE P.id_prestamo = @id_prestamo;

        COMMIT;
        PRINT 'Devolución registrada correctamente.';
    END TRY

    BEGIN CATCH
        ROLLBACK;
        PRINT 'Error al registrar la devolución: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
