-- Llamada al procedimiento para agregar un libro
EXEC AgregarLibro 
    @titulo = 'El Gran Libro',
    @autor = 'Juan Pérez',
    @genero = 'Ficción',
    @año_publicacion = 2021,
    @estado_L = 'Disponible',
    @descripcion = 'Este es un gran libro que narra una historia épica.',
    @imagen_url = '/imagenes/libros/libro_123.jpg';  -- Ruta de la imagen

EXEC AgregarLibro 
    @titulo = 'El libro para triunfar',  -- Nuevo título
    @autor = 'Ana Martínez',  -- Autor
    @genero = 'Autoayuda',  -- Género
    @año_publicacion = 2023,  -- Año de publicación
    @estado_L = 'Disponible',  -- Estado
    @descripcion = 'Un libro lleno de estrategias y consejos para alcanzar el éxito en la vida personal y profesional.',  -- Descripción
    @imagen_url = '/imagenes/libros/libro_124.jpg';  -- Ruta de la imagen



EXEC AgregarLibro 
    @titulo = 'La maravillosa vida',  -- Nuevo título
    @autor = 'David Gómez',  -- Autor
    @genero = 'Biografía',  -- Género
    @año_publicacion = 2022,  -- Año de publicación
    @estado_L = 'Disponible',  -- Estado
    @descripcion = 'Una biografía que narra la increíble vida de una persona que superó grandes obstáculos para lograr sus sueños.',  -- Descripción
    @imagen_url = '/imagenes/libros/libro_125.jpg';  -- Ruta de la imagen






-- Llamada al procedimiento para registrar un usuario
EXEC RegistrarUsuario 
    @nombre = 'Juan Pérez', 
    @correo = 'juan.perez@example.com', 
    @contraseña = 0x1234567890ABCDEF,  -- Ejemplo de contraseña cifrada (en VARBINARY)
    @celular = '987654321', 
    @DNI = '12345678', 
    @id_rol = 2; -- 2 para Usuario



-- Llamada al procedimiento para editar el libro con ID 1
EXEC EditarLibro 
    @id_libro = 1, 
    @titulo = 'El Gran Libro Editado',  -- Nuevo título
    @autor = 'Carlos Sánchez',  -- Nuevo autor
    @genero = 'Misterio',  -- Nuevo género
    @año_publicacion = 2021,  -- Mantener el valor original (sin cambio)
    @estado_L = 'Disponible',  -- Mantener el valor original (sin cambio)
    @descripcion = 'Este es un gran libro que narra una historia épica.',  -- Mantener el valor original (sin cambio)
    @imagen_url = '/imagenes/libros/libro_123.jpg';  -- Mantener el valor original (sin cambio)



-- Llamada al procedimiento para buscar libros cuyo título contiene la palabra 'libro'
EXEC BuscarLibroPorTitulo 

    @parte_titulo = 'libro';


--llamada al procedimiento para evaluar inicair sesion
EXEC IniciarSesion 
    @correo = 'juan.perez@example.com',
    @contraseña = 0x1234567890ABCDEF;
GO

--llamada al procedimiento para que de toda la info de un libro

EXEC ConsultarLibroPorID 
    @id_libro = 2;
GO

--llamada al procedimiento para que muestre la previsualizacion del prestamo
EXEC PrevisualizarPrestamo 
    @id_usuario = 1,
    @id_libro = 2;
GO


-- llamada al procedimiento para hacer el prestamo de libro

EXEC RegistrarPrestamo 
    @id_usuario = 1,
    @id_libro = 2;
GO


-- llamada al procedimento para mostrar todos los libros prestado por usuario

EXEC VerPrestamosPorUsuario 
    @id_usuario = 1;
GO



