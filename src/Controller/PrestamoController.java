package Controller;

import Model.Prestamo;
import Model.PrestamoDAO;
import java.util.List;

public class PrestamoController {

    private final PrestamoDAO dao = new PrestamoDAO();

    // Previsualización de un préstamo
    public Prestamo obtenerPrevisualizacion(int idUsuario, int idLibro) {
        return dao.previsualizarPrestamo(idUsuario, idLibro);
    }

    // Registrar un préstamo
    public boolean registrarPrestamo(int idUsuario, int idLibro) {
        return dao.registrarPrestamo(idUsuario, idLibro);
    }

    // Ver préstamos de un usuario
    public List<Prestamo> verPrestamosPorUsuario(int idUsuario) {
        return dao.verPrestamosPorUsuario(idUsuario);
    }
    public List<Prestamo> obtenerTodosLosPrestamos() {
        return dao.verTodosLosPrestamos();
    }
    public Prestamo obtenerPrestamoPorId(int idPrestamo) {
    return dao.obtenerPrestamoPorId(idPrestamo);
    }   

    public boolean registrarDevolucion(int idPrestamo) {
    return dao.registrarDevolucion(idPrestamo);
    }
}