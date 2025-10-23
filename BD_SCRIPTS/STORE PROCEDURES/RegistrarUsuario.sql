
-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: RegistrarUsuario
-- ===========================================================
CREATE PROCEDURE RegistrarUsuario
    @nombre VARCHAR(100),
    @correo VARCHAR(100),
    @contraseña VARBINARY(MAX),
    @celular VARCHAR(15) = NULL,
    @DNI CHAR(8),
    @id_rol INT
AS
BEGIN
    -- Comenzar la transacción para asegurar la integridad de los datos
    BEGIN TRY
        BEGIN TRANSACTION
        
        -- Insertar el nuevo usuario en la tabla Usuarios
        INSERT INTO Usuarios (nombre, correo, contraseña, celular, DNI, id_rol, fecha_registro)
        VALUES (@nombre, @correo, @contraseña, @celular, @DNI, @id_rol, GETDATE());
        
        -- Confirmar la transacción
        COMMIT;
        
        PRINT 'Usuario registrado exitosamente.';
    END TRY
    BEGIN CATCH
        -- Si ocurre un error, hacer rollback de la transacción
        ROLLBACK;
        
        PRINT 'Error al registrar el usuario: ' + ERROR_MESSAGE();
    END CATCH
END
GO




