package Model;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class LibroDAO {

    // 🔹 Método para insertar un libro (ya existente)
    public boolean insertarLibro(Libro libro) {
        boolean exito = false;
        String sql = "{CALL AgregarLibro(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = Conexion.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, libro.getTitulo());
            cs.setString(2, libro.getAutor());
            cs.setString(3, libro.getGenero());
            cs.setObject(4, libro.getAñoPublicacion());
            cs.setString(5, libro.getEstado());
            cs.setString(6, libro.getDescripcion());
            cs.setBytes(7, libro.getImagen());

            cs.execute();
            exito = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al agregar el libro: " + e.getMessage());
        }

        return exito;
    }

    // 🔹 NUEVO: método para buscar libros por título
    public List<Libro> buscarLibros(String texto) {
        List<Libro> resultados = new ArrayList<>();
        String sql = "{CALL BuscarLibroPorTitulo(?)}";

        try (Connection conn = Conexion.conectar(); CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, texto);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setGenero(rs.getString("genero"));
                libro.setAñoPublicacion(rs.getInt("año_publicacion"));
                libro.setEstado(rs.getString("estado"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setImagen(rs.getBytes("imagen")); // bytes de la imagen
                resultados.add(libro);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar libros: " + e.getMessage());
        }

        return resultados;
    }
    
    // 🔹 MÉTODO PARA ACTUALIZAR LIBRO
    public boolean actualizarLibro(Libro libro) {
        boolean exito = false;

        String sql = "{CALL ActualizarLibro(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = Conexion.conectar(); CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, libro.getIdLibro());
            cs.setString(2, libro.getTitulo());
            cs.setString(3, libro.getAutor());
            cs.setString(4, libro.getGenero());
            cs.setObject(5, libro.getAñoPublicacion());
            cs.setString(6, libro.getEstado());
            cs.setString(7, libro.getDescripcion());
            cs.setBytes(8, libro.getImagen()); // Puede ser null si no cambiaste imagen

            cs.execute();
            exito = true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar el libro: " + e.getMessage());
        }

        return exito;
    }
}
