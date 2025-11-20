package Model;

import java.util.Date;

public class Prestamo {

    // --- CAMPOS PARA PREVISUALIZACIÓN ---
    private String titulo;
    private byte[] imagen;
    private String nombreSolicitante;
    private Date fechaSolicitud;
    private Date fechaDevolucion;

    // --- CAMPOS PARA LISTADO DE PRÉSTAMOS ---
    private Date fechaPrestamo;
    private Date fechaVencimiento;
    private String estado;

    private int idPrestamo;
    private String usuarioSolicitante;
    // --- Getters y Setters ---

    // Previsualización
    public String getTitulo() { 
        return titulo; 
    }
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public byte[] getImagen() { 
        return imagen; 
    }
    public void setImagen(byte[] imagen) { 
        this.imagen = imagen; 
    }

    public String getNombreSolicitante() { 
        return nombreSolicitante; 
    }
    public void setNombreSolicitante(String nombreSolicitante) { 
        this.nombreSolicitante = nombreSolicitante; 
    }

    public Date getFechaSolicitud() { 
        return fechaSolicitud; 
    }
    public void setFechaSolicitud(Date fechaSolicitud) { 
        this.fechaSolicitud = fechaSolicitud; 
    }

    public Date getFechaDevolucion() { 
        return fechaDevolucion; 
    }
    public void setFechaDevolucion(Date fechaDevolucion) { 
        this.fechaDevolucion = fechaDevolucion; 
    }

    // Listado de préstamos
    public Date getFechaPrestamo() { 
        return fechaPrestamo; 
    }
    public void setFechaPrestamo(Date fechaPrestamo) { 
        this.fechaPrestamo = fechaPrestamo; 
    }

    public Date getFechaVencimiento() { 
        return fechaVencimiento; 
    }
    public void setFechaVencimiento(Date fechaVencimiento) { 
        this.fechaVencimiento = fechaVencimiento; 
    }

    public String getEstado() { 
        return estado; 
    }
    public void setEstado(String estado) { 
        this.estado = estado; 
    }
     // Todos los préstamos
    public int getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(int idPrestamo) { this.idPrestamo = idPrestamo; }

    public String getUsuarioSolicitante() { return usuarioSolicitante; }
    public void setUsuarioSolicitante(String usuarioSolicitante) { this.usuarioSolicitante = usuarioSolicitante; }
}
