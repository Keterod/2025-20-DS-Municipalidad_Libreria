package Model;

import java.sql.Timestamp;

public class Usuario {
    private int idUsuario; //InSe
    private String nombre;
    private String correo;
    private String contraseña; //InSe
    private String celular;
    private String dni;
    private int idRol;
    private String rol; //InSe
    private Timestamp fechaRegistro; //InSe

    public Usuario() {}

    public Usuario(String nombre, String correo, String contraseña, String celular, String dni, int idRol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.celular = celular;
        this.dni = dni;
        this.idRol = idRol;
    }
    
    
    //Parte de Iniciar Sesión
    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
