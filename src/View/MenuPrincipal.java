package View;
import View.PanelAgregarLibro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Model.Usuario;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.panelBuscarLibro;
import View.panelPrestamos;

public class MenuPrincipal extends javax.swing.JFrame {
        
    private Usuario usuario;
    private JPanel panelLateral;
    private JPanel panelContenido;
    private JMenuItem miAgregarLibroMenu;

    public MenuPrincipal(Usuario usuario) {
        this.usuario = usuario;

        // Normalizar rol
        String r = usuario.getRol() != null ? usuario.getRol().trim().toUpperCase() : "";
        if (r.contains("ADMIN") || r.contains("BIBLIO")) {
            usuario.setRol("ADMIN");
        } else {
            usuario.setRol("USUARIO");
        }

        initComponents();
        configurarInterfaz();

        lblUsuario.setText("Bienvenido, " + usuario.getNombre()
                + " (ID: " + usuario.getIdUsuario() + ")");

        // Cargar préstamos por defecto si es admin
        if (usuario.getRol().equals("ADMIN")) {
            actualizarBarraLateral("Préstamos");
        }
    }

    private void configurarInterfaz() {
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Sistema de Biblioteca Pública Municipal de Huancayo");

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitulo = new JLabel("Biblioteca Pública Municipal de Huancayo");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);

        panelSuperior.add(lblTitulo, BorderLayout.WEST);
        panelSuperior.add(lblUsuario, BorderLayout.EAST);

        // Panel lateral
        panelLateral = new JPanel(new GridLayout(0, 1, 0, 10));
        panelLateral.setBackground(new Color(230, 230, 230));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Panel contenido
        panelContenido = new JPanel(new BorderLayout());
        panelContenido.add(new JLabel("Selecciona una opción del menú superior", SwingConstants.CENTER));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelSuperior, BorderLayout.NORTH);
        getContentPane().add(panelLateral, BorderLayout.WEST);
        getContentPane().add(panelContenido, BorderLayout.CENTER);

        configurarMenus();
        this.setVisible(true);
        
        jMenu3.addMenuListener(new javax.swing.event.MenuListener() {
            @Override
            public void menuSelected(javax.swing.event.MenuEvent e) {
                mostrarPanel(new panelRecomendaciones(usuario.getIdUsuario()));
        }

    @Override
    public void menuDeselected(javax.swing.event.MenuEvent e) {}

    @Override
    public void menuCanceled(javax.swing.event.MenuEvent e) {}
});
    }

    private void configurarMenus() {
        jMenu1.removeAll();
        jMenu2.removeAll();

        // ----------------- MENÚ CATÁLOGO -----------------
        JMenuItem miCatalogoOpciones = new JMenuItem("Mostrar opciones (Catálogo)");
        JMenuItem miCatalogoAgregar = new JMenuItem("Agregar libro");
        JMenuItem miCatalogoBuscar = new JMenuItem("Buscar libro");

        miAgregarLibroMenu = miCatalogoAgregar;

        miCatalogoOpciones.addActionListener(e -> actualizarBarraLateral("Catálogo"));
        miCatalogoAgregar.addActionListener(e -> mostrarPanel(new PanelAgregarLibro()));

        miCatalogoBuscar.addActionListener(e -> {
            if (usuario.getRol().equals("ADMIN")) {
                mostrarPanel(new panelBuscarLibroEditar(usuario.getIdUsuario()));
            } else {
                mostrarPanel(new panelBuscarLibro(usuario.getIdUsuario()));
            }
        });

        jMenu1.add(miCatalogoOpciones);
        jMenu1.add(miCatalogoAgregar);
        jMenu1.add(miCatalogoBuscar);

        // ----------------- MENÚ PRÉSTAMOS -----------------
        JMenuItem miPrestamosOpciones = new JMenuItem("Mostrar opciones (Préstamos)");
        JMenuItem miPrestamosVer = new JMenuItem("Ver préstamos");

        miPrestamosOpciones.addActionListener(e -> actualizarBarraLateral("Préstamos"));
        miPrestamosVer.addActionListener(e -> mostrarPanel(new panelPrestamos(usuario.getIdUsuario())));

        jMenu2.add(miPrestamosOpciones);
        jMenu2.add(miPrestamosVer);

        // Admin: ver todos los préstamos
        if (usuario.getRol().equals("ADMIN")) {
            JMenuItem miTodos = new JMenuItem("Todos los préstamos");
            miTodos.addActionListener(e -> mostrarPanel(new JScrollPane(new panelTodosPrestamos())));
            jMenu2.add(miTodos);
        }

        aplicarPermisosPorRol();
    }

    private void aplicarPermisosPorRol() {
        if (!usuario.getRol().equals("ADMIN")) {
            miAgregarLibroMenu.setVisible(false);
        }
    }

    private void actualizarBarraLateral(String categoria) {
        panelLateral.removeAll();

        // ---------- CATÁLOGO ----------
        if (categoria.equals("Catálogo")) {

            JButton btnBuscarLibro = new JButton("🔍 Buscar Libro");
            JButton btnAgregarLibro = new JButton("➕ Agregar Libro");
            if (usuario.getRol().equals("ADMIN")) {
                btnBuscarLibro.addActionListener(e -> mostrarPanel(new panelBuscarLibroEditar(usuario.getIdUsuario())));
            }
            else{
            btnBuscarLibro.addActionListener(e -> mostrarPanel(new panelBuscarLibro(usuario.getIdUsuario())));
            }
            btnAgregarLibro.addActionListener(e -> mostrarPanel(new PanelAgregarLibro()));

            panelLateral.add(btnBuscarLibro);

            if (usuario.getRol().equals("ADMIN")) {
                panelLateral.add(btnAgregarLibro);
            }
        }

        // ---------- PRÉSTAMOS ----------
        if (categoria.equals("Préstamos")) {

            JButton btnVerPrestamos = new JButton("📋 Ver Préstamos");
            btnVerPrestamos.addActionListener(e -> mostrarPanel(new panelPrestamos(usuario.getIdUsuario())));
            panelLateral.add(btnVerPrestamos);

            if (usuario.getRol().equals("ADMIN")) {
                JButton btnTodos = new JButton("📚 Todos los Préstamos");
                btnTodos.addActionListener(
                        e -> mostrarPanel(new JScrollPane(new panelTodosPrestamos())));
                panelLateral.add(btnTodos);
            }
        }

        // ---------- BOTÓN CERRAR SESIÓN ----------
        JButton btnCerrar = new JButton("🚪 Cerrar sesión");
        btnCerrar.addActionListener(e -> cerrarSesion());

        panelLateral.add(Box.createVerticalStrut(30));
        panelLateral.add(btnCerrar);

        panelLateral.revalidate();
        panelLateral.repaint();
    }

    public void mostrarPanel(Component nuevoPanel) {
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void cerrarSesion() {
        if (JOptionPane.showConfirmDialog(this, "¿Cerrar sesión?", "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            new IniciarSesion().setVisible(true);
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUsuario.setText("jLabel1");

        jMenu1.setText("Catálogo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Prestamos");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Recomendaciones");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(523, Short.MAX_VALUE)
                .addComponent(lblUsuario))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblUsuario)
                .addGap(0, 512, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            Model.Usuario u = new Model.Usuario();
            u.setIdUsuario(1);
            u.setNombre("Kevin");
            u.setRol("ADMIN");
            new MenuPrincipal(u).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables

}
