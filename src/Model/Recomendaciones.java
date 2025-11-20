package Model;

import java.util.List;

public class Recomendaciones {
    private List<Libro> libros;

    public Recomendaciones(List<Libro> libros) {
        this.libros = libros;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}