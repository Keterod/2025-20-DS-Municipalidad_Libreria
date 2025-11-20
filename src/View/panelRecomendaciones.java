
package View;
import java.util.List;
import javax.swing.DefaultListModel;
import Controller.RecomendacionesController;
import Model.Libro;


public class panelRecomendaciones extends javax.swing.JPanel {
    private int idUsuario;
    public panelRecomendaciones(int idUsuario) {
        this.idUsuario = idUsuario;
        initComponents();
        cargarRecomendaciones();
        listaRecomendaciones.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            Libro seleccionado = listaRecomendaciones.getSelectedValue();
            if (seleccionado != null && seleccionado.getIdLibro() != 0) {
                new DetalleLibro(seleccionado, idUsuario).setVisible(true);
            }
        }
    }
});
    }

    private void cargarRecomendaciones() {
        RecomendacionesController controller = new RecomendacionesController();
        List<Libro> lista = controller.obtenerRecomendaciones(idUsuario);

        DefaultListModel<Libro> modelo = new DefaultListModel<>();

        if (lista.isEmpty()) {
            Libro mensaje = new Libro();
            mensaje.setIdLibro(0); // <- IMPORTANTE
            mensaje.setTitulo("No hay suficientes datos para recomendar libros 😢");
            mensaje.setAutor("");
            modelo.addElement(mensaje);
        } else {
            for (Libro libro : lista) {
                modelo.addElement(libro);
            }
        }

        listaRecomendaciones.setModel(modelo);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaRecomendaciones = new javax.swing.JList<>();

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel1.setText("Recomendaciones ");

        jScrollPane1.setViewportView(listaRecomendaciones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(jLabel1)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(89, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel1)
                .addContainerGap(318, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(143, 143, 143)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(144, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Libro> listaRecomendaciones;
    // End of variables declaration//GEN-END:variables
}
