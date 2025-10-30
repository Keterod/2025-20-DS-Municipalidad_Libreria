-- ===========================================================
--  PROCEDIMIENTO ALMACENADO: IniciarSesion
-- ===========================================================
CREATE PROCEDURE IniciarSesion
    @correo VARCHAR(100),
    @contraseña VARBINARY(MAX)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE 
        @id_usuario INT,
        @nombre VARCHAR(100),
        @rol VARCHAR(50),
        @contraseñaBD VARBINARY(MAX);

    -- Buscar usuario por correo
    SELECT 
        @id_usuario = U.id_usuario,
        @nombre = U.nombre,
        @rol = R.nombre_rol,
        @contraseñaBD = U.contraseña
    FROM Usuarios U
    INNER JOIN RolesPermisos R ON U.id_rol = R.id_rol
    WHERE U.correo = @correo;

    -- Verificar si el usuario existe
    IF @id_usuario IS NULL
    BEGIN
        PRINT 'Usuario no encontrado.';
        RETURN;
    END;

    -- Comparar contraseñas cifradas
    IF @contraseñaBD = @contraseña
    BEGIN
        -- Devolver datos del usuario
        SELECT 
            U.id_usuario,
            U.nombre,
            U.correo,
            R.nombre_rol AS rol,
            U.fecha_registro
        FROM Usuarios U
        INNER JOIN RolesPermisos R ON U.id_rol = R.id_rol
        WHERE U.id_usuario = @id_usuario;

        PRINT 'Inicio de sesión exitoso.';
    END
    ELSE
    BEGIN
        PRINT 'Contraseña incorrecta.';
    END;
END;
GO
