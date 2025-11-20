package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    // ==========================================
    //   PREVISUALIZAR PRÉSTAMO
    // ==========================================
    public Prestamo previsualizarPrestamo(int idUsuario, int idLibro) {
        Prestamo prestamo = null;
        String sql = "{CALL PrevisualizarPrestamo(?, ?)}";

        try (Connection conn = Conexion.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idUsuario);
            cs.setInt(2, idLibro);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setTitulo(rs.getString("titulo"));
                prestamo.setNombreSolicitante(rs.getString("nombre_solicitante"));
                prestamo.setFechaSolicitud(rs.getTimestamp("fecha_solicitud"));
                prestamo.setFechaDevolucion(rs.getTimestamp("fecha_devolucion"));
                prestamo.setImagen(rs.getBytes("imagen"));
            }

        } catch (SQLException e) {
            System.err.println("Error al previsualizar préstamo: " + e.getMessage());
        }

        return prestamo;
    }

    // ==========================================
    //   REGISTRAR PRÉSTAMO
    // ==========================================
    public boolean registrarPrestamo(int idUsuario, int idLibro) {
        String sql = "{CALL RegistrarPrestamo(?, ?)}";

        try (Connection conn = Conexion.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idUsuario);
            cs.setInt(2, idLibro);

            cs.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al registrar préstamo: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    //   VER PRÉSTAMOS POR USUARIO 
    // ==========================================
    public List<Prestamo> verPrestamosPorUsuario(int idUsuario) {

        List<Prestamo> lista = new ArrayList<>();
        String sql = "{CALL VerPrestamosPorUsuario(?)}";

        try (Connection conn = Conexion.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idUsuario);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.setTitulo(rs.getString("titulo"));
                p.setFechaPrestamo(rs.getTimestamp("fecha_prestamo"));
                p.setFechaVencimiento(rs.getTimestamp("fecha_vencimiento"));
                p.setEstado(rs.getString("estado_P")); 
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener préstamos: " + e.getMessage());
        }

        return lista;
    }
    public List<Prestamo> verTodosLosPrestamos() {
    List<Prestamo> lista = new ArrayList<>();
    String sql = "{CALL VerTodosLosPrestamos()}";

    try (Connection conn = Conexion.conectar();
         CallableStatement cs = conn.prepareCall(sql);
         ResultSet rs = cs.executeQuery()) {

        while (rs.next()) {
            Prestamo p = new Prestamo();

            p.setIdPrestamo(rs.getInt("id_prestamo"));
            p.setTitulo(rs.getString("titulo"));
            p.setUsuarioSolicitante(rs.getString("usuario_solicitante"));
            p.setFechaPrestamo(rs.getTimestamp("fecha_prestamo"));
            p.setFechaVencimiento(rs.getTimestamp("fecha_vencimiento"));
            p.setEstado(rs.getString("estado_P"));

            lista.add(p);
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener todos los préstamos: " + e.getMessage());
    }

    return lista;
    }
    public Prestamo obtenerPrestamoPorId(int idPrestamo) {
    Prestamo p = null;
    String sql = "{CALL ObtenerPrestamoPorId(?)}";

    try (Connection con = Conexion.conectar();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, idPrestamo);
        ResultSet rs = cs.executeQuery();

        if (rs.next()) {
            p = new Prestamo();

            p.setIdPrestamo(rs.getInt("id_prestamo"));
            p.setTitulo(rs.getString("titulo"));
            p.setUsuarioSolicitante(rs.getString("usuario_solicitante"));
            p.setFechaPrestamo(rs.getTimestamp("fecha_prestamo"));
            p.setFechaVencimiento(rs.getTimestamp("fecha_vencimiento"));
            p.setEstado(rs.getString("estado_P"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return p;
    }
public boolean registrarDevolucion(int idPrestamo) {
    String sql = "{CALL RegistrarDevolucion(?)}";

    try (Connection conn = Conexion.conectar();
         CallableStatement cs = conn.prepareCall(sql)) {

        cs.setInt(1, idPrestamo);
        cs.execute();
        return true;

    } catch (SQLException e) {
        System.err.println("Error al registrar devolución: " + e.getMessage());
        return false;
    }
    }
}