package Controller;

import javax.swing.JOptionPane;
import Model.Usuario;
import Model.UsuarioDAO;

public class UsuarioController {

    // Método para registrar usuario
    public boolean registrarUsuario(String nombre, String correo, String contraseña, String celular, String dni) {
        try {
            Usuario u = new Usuario(nombre, correo, contraseña, celular, dni, 2);
            UsuarioDAO dao = new UsuarioDAO();
            boolean exito = dao.insertarUsuario(u);

            if (exito) {
                JOptionPane.showMessageDialog(null, "✅ Usuario registrado exitosamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "⚠️ No se pudo registrar el usuario.");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        }
    }

    // Método para iniciar sesión
    public Usuario loginUsuario(String correo, String contraseña) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.iniciarSesion(correo, contraseña);

        if (usuario != null) {
            JOptionPane.showMessageDialog(null,
                    "✅ Bienvenido, " + usuario.getNombre() + " (" + usuario.getRol() + ")");
        } else {
            JOptionPane.showMessageDialog(null,
                    "❌ Correo o contraseña incorrectos.",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE);
        }
        
        return usuario;
    }
}
