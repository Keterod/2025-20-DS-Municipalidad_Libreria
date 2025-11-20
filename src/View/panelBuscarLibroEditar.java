package View;

import Controller.LibroController;
import java.util.List;
import java.util.ArrayList;
import Model.Libro;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class panelBuscarLibroEditar extends javax.swing.JPanel {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(panelBuscarLibroEditar.class.getName());
    private List<Libro> resultados = new ArrayList<>();
    LibroController controller = new LibroController();
    private int idUsuarioActual;
    public panelBuscarLibroEditar(int idUsuarioActual) {
        this.idUsuarioActual = idUsuarioActual;
        initComponents();
        txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                buscar();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                buscar();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                buscar();
            }
        });

        // Aquí va el MouseListener de la lista de resultados
        listaResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // doble clic
                    int index = listaResultados.getSelectedIndex();
                    if (index != -1) {
                        Libro libroSeleccionado = resultados.get(index); // <--- aquí debe ser la lista de objetos Libro
                        new DetalleLibro(libroSeleccionado, idUsuarioActual).setVisible(true);
                    }
                }
            }
        });
    }
    
    private void buscar() {
        String texto = txtBuscar.getText().trim();
        if (texto.isEmpty()) {
            listaResultados.setListData(new String[0]);
            resultados.clear();
            return;
        }

        resultados = controller.buscarLibros(texto); // Lista de libros
        List<String> titulos = new ArrayList<>();
        for (Libro l : resultados) {
            titulos.add(l.getTitulo() + " — " + l.getAutor());
        }

        listaResultados.setListData(titulos.toArray(new String[0]));
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaResultados = new javax.swing.JList<>();
        jButtonEditar = new javax.swing.JToggleButton();

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 1, 24)); // NOI18N
        jLabel1.setText("Buscar Libros");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listaResultados);

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jButtonEditar)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButtonEditar)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        int index = listaResultados.getSelectedIndex();
        if (index == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un libro de la lista.", "Error", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        Model.Libro libroSeleccionado = resultados.get(index);

        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (!(win instanceof View.MenuPrincipal)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No se pudo abrir el editor: la ventana principal no es MenuPrincipal (tipo: " + (win == null ? "null" : win.getClass().getName()) + ").",
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        View.MenuPrincipal menu = (View.MenuPrincipal) win;

        try {
            menu.mostrarPanel(new View.PanelEditarLibro(libroSeleccionado));
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al abrir PanelEditarLibro:\n" + e.getClass().getName() + ": " + e.getMessage(),
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButtonEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jButtonEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listaResultados;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
