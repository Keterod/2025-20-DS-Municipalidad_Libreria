package Controller;

import Model.Libro;
import Model.RecomendacionesDAO;
import java.util.List;

public class RecomendacionesController {

    private final RecomendacionesDAO dao = new RecomendacionesDAO();

    public List<Libro> obtenerRecomendaciones(int idUsuario) {
        return dao.obtenerRecomendaciones(idUsuario);
    }
}
