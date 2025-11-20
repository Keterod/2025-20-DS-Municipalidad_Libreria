package Model;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class UsuarioDAO {

    // Registrar usuario (igual que antes)
    public boolean insertarUsuario(Usuario usuario) {
        boolean exito = false;

        try (Connection conn = Conexion.conectar()) {
            if (conn != null) {
                String sql = "{CALL RegistrarUsuario(?, ?, ?, ?, ?, ?)}";
                try (CallableStatement cs = conn.prepareCall(sql)) {

                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    byte[] hash = md.digest(usuario.getContraseña().getBytes(StandardCharsets.UTF_8));

                    cs.setString(1, usuario.getNombre());
                    cs.setString(2, usuario.getCorreo());
                    cs.setBytes(3, hash);
                    cs.setString(4, usuario.getCelular());
                    cs.setString(5, usuario.getDni());
                    cs.setInt(6, usuario.getIdRol());

                    exito = cs.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al registrar usuario: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error encriptando contraseña: " + e.getMessage());
        }

        return exito;
    }

    // Iniciar sesión (corregido)
    public Usuario iniciarSesion(String correo, String contraseña) {
        Usuario usuario = null;

        try (Connection conn = Conexion.conectar()) {
            if (conn != null) {

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashContraseña = md.digest(contraseña.getBytes(StandardCharsets.UTF_8));

                String sql = "{CALL IniciarSesion(?, ?)}";
                try (CallableStatement cs = conn.prepareCall(sql)) {
                    cs.setString(1, correo);
                    cs.setBytes(2, hashContraseña);

                    try (ResultSet rs = cs.executeQuery()) {
                        if (rs.next()) {
                            usuario = new Usuario();

                            usuario.setIdUsuario(rs.getInt("id_usuario"));
                            usuario.setNombre(rs.getString("nombre"));
                            usuario.setCorreo(rs.getString("correo"));

                            try {
                                Timestamp ts = rs.getTimestamp("fecha_registro");
                                if (ts != null) usuario.setFechaRegistro(ts);
                            } catch (SQLException ignored) {}

                            // ================================
                            // NORMALIZACIÓN DE ROL CORREGIDA
                            // ================================
                            String rolBD = rs.getString("rol");
                            System.out.println("ROL CRUDO DE BD = [" + rolBD + "]");

                            if (rolBD != null) {
                                rolBD = rolBD.trim().toUpperCase();

                                if (
                                       rolBD.equals("1")
                                    || rolBD.contains("ADMIN")
                                    || rolBD.equals("ADMINISTRADOR")
                                    || rolBD.equals("ADMINISTRADOR PRINCIPAL")
                                    || rolBD.equals("BIBLIOTECARIO")  // ← *** AÑADIDO ***
                                ) {
                                    usuario.setRol("ADMIN");
                                } else {
                                    usuario.setRol("USUARIO");
                                }
                            } else {
                                usuario.setRol("USUARIO");
                            }

                            System.out.println("ROL NORMALIZADO: " + usuario.getRol());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQL en iniciarSesion: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error al encriptar la contraseña: " + e.getMessage());
        }

        return usuario;
    }
}