package Controller;

import Model.Libro;
import Model.LibroDAO;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class LibroController {
    private final LibroDAO dao = new LibroDAO();

    // --- método que ya tenías para registrar (no lo toco) ---
    public void registrarLibro(String titulo, String autor, String genero, int año, String estado, String descripcion, String imagenUrl) {
        try {
            // Convertir la imagen en bytes (si se pasó ruta)
            byte[] imagenBytes = null;

            if (imagenUrl != null && !imagenUrl.isEmpty()) {
                File imagenFile = new File(imagenUrl);
                if (imagenFile.exists()) {
                    try (FileInputStream fis = new FileInputStream(imagenFile)) {
                        imagenBytes = fis.readAllBytes();
                    }
                }
            }

            // Crear el objeto Libro
            Libro libro = new Libro();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setGenero(genero);
            libro.setAñoPublicacion(año);
            libro.setEstado(estado);
            libro.setDescripcion(descripcion);
            libro.setImagen(imagenBytes);

            // Enviar al DAO
            boolean exito = dao.insertarLibro(libro);

            if (exito) {
                JOptionPane.showMessageDialog(null, "✅ Libro agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Error al agregar el libro.");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer la imagen: " + e.getMessage());
        }
    }

    // --- buscar libros (ya lo tenías) ---
    public List<Libro> buscarLibros(String texto) {
        return dao.buscarLibros(texto);
    }

    // --- NUEVO: actualizar libro recibiendo directamente byte[] de imagen ---
    /**
     * Actualiza un libro en la base de datos.
     * @param idLibro id del libro a actualizar
     * @param titulo
     * @param autor
     * @param genero
     * @param año
     * @param estado (ej. "Disponible" o estado_L en la BD)
     * @param descripcion
     * @param imagenBytes imagen en bytes (puede ser null para mantener la anterior)
     * @return true si se actualizó correctamente
     */
    public boolean actualizarLibro(int idLibro, String titulo, String autor, String genero,
                                   int año, String estado, String descripcion, byte[] imagenBytes) {
        try {
            Libro libro = new Libro();
            libro.setIdLibro(idLibro);
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setGenero(genero);
            libro.setAñoPublicacion(año);
            libro.setEstado(estado);
            libro.setDescripcion(descripcion);

            // Solo setear la imagen si no es null (si es null, DAO debe mantener la anterior)
            if (imagenBytes != null) {
                libro.setImagen(imagenBytes);
            } else {
                // Si quieres forzar null explícito en BD, descomenta la línea siguiente:
                // libro.setImagen(null);
                // En general dejamos el campo sin modificar si imagenBytes == null
            }

            return dao.actualizarLibro(libro);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error actualizando libro: " + e.getMessage());
            return false;
        }
    }
}