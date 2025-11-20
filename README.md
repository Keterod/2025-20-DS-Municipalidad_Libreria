# 2025-20-DS-Municipalidad_Libreria


String url = "jdbc:sqlserver://UHYXE30104\\MSSQLSERVER1:52643;databaseName=Biblioteca;encrypt=false;trustServerCertificate=true";


DECLARE @pass VARBINARY(MAX) = HASHBYTES('SHA2_256', 'admin123');

EXEC RegistrarUsuario
    @nombre = 'Administrador Principal',
    @correo = 'admin@biblioteca.com',
    @contraseña = @pass,
    @celular = '999999999',
    @DNI = '87654321',
    @id_rol = 1;  -- 🔥 ESTE ES EL ROL DE ADMIN
