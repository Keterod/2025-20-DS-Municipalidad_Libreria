-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: RegistrarPrestamo
-- ===========================================================
CREATE PROCEDURE RegistrarPrestamo
    @id_usuario INT,
    @id_libro INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- 1 Verificar si el libro está disponible
        IF NOT EXISTS (SELECT 1 FROM Libros WHERE id_libro = @id_libro AND estado_L = 'Disponible')
        BEGIN
            PRINT ' El libro no está disponible para préstamo.';
            ROLLBACK;
            RETURN;
        END;

        -- 2 Calcular fechas
        DECLARE @fecha_prestamo DATETIME = GETDATE();
        DECLARE @fecha_vencimiento DATETIME = DATEADD(DAY, 7, GETDATE());

        -- 3 Registrar el préstamo en la tabla Prestamos
        INSERT INTO Prestamos (id_usuario, id_libro, fecha_prestamo, fecha_vencimiento,  estado_P)
        VALUES (@id_usuario, @id_libro, @fecha_prestamo, @fecha_vencimiento, 'Prestado');

        -- 4 Actualizar el estado del libro a "En préstamo"
        UPDATE Libros
        SET estado_L = 'En préstamo'
        WHERE id_libro = @id_libro;

        -- 5 Registrar el libro en el historial de lectura
        INSERT INTO HistorialLectura (id_usuario, id_libro, fecha_lectura)
        VALUES (@id_usuario, @id_libro, @fecha_prestamo);

        -- 6 Mostrar la confirmación con datos relevantes
        SELECT 
            L.titulo,
            L.imagen_url,
            U.nombre AS nombre_solicitante,
            @fecha_prestamo AS fecha_solicitud,
            @fecha_vencimiento AS fecha_devolucion
        FROM Libros L
        INNER JOIN Usuarios U ON U.id_usuario = @id_usuario
        WHERE L.id_libro = @id_libro;

        -- 7 Confirmar la transacción
        COMMIT;
        PRINT 'Préstamo registrado correctamente.';
    END TRY

    BEGIN CATCH
        -- Si algo falla, revertimos todo
        ROLLBACK;
        PRINT 'Error al registrar el préstamo: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
