package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecomendacionesDAO {

    public List<Libro> obtenerRecomendaciones(int idUsuario) {
        List<Libro> lista = new ArrayList<>();

        String sql = "{CALL ObtenerRecomendaciones(?)}";

        try (Connection conn = Conexion.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idUsuario);

            ResultSet rs = cs.executeQuery();

            // Si el SP devuelve un mensaje, evitar errores
            if (rs.getMetaData().getColumnLabel(1).equalsIgnoreCase("mensaje")) {
                return lista; // Lista vacía → no hay suficientes datos
            }

            while (rs.next()) {
                Libro libro = new Libro();

                libro.setIdLibro(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setGenero(rs.getString("genero"));
                libro.setAñoPublicacion(rs.getInt("año_publicacion"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setEstado(rs.getString("estado_L"));
                libro.setImagen(rs.getBytes("imagen")); 
                lista.add(libro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}