
package View;
import Controller.PrestamoController;
import Model.Prestamo;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import View.VentanaGestionPrestamo;

public class panelTodosPrestamos extends javax.swing.JPanel {


    public panelTodosPrestamos() {
        initComponents();
        cargarTodosLosPrestamos();
        agregarEventoDobleClick();
    }
    private void cargarTodosLosPrestamos() {

    PrestamoController controller = new PrestamoController();
    List<Prestamo> prestamos = controller.obtenerTodosLosPrestamos();

    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0);  // limpiar la tabla

    for (Prestamo p : prestamos) {
        model.addRow(new Object[]{
            p.getIdPrestamo(),
            p.getTitulo(),
            p.getUsuarioSolicitante(),
            p.getFechaPrestamo(),
            p.getFechaVencimiento(),
            p.getEstado()
        });
    }
    
}
private void agregarEventoDobleClick() {
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getClickCount() == 2) {  // doble clic
                int fila = jTable1.getSelectedRow();
                if (fila == -1) return;

                // Leer el estado (columna 5)
                String estado = jTable1.getValueAt(fila, 5).toString();

                // Si ya está devuelto → bloquear
                if (estado.equalsIgnoreCase("Devuelto")) {
                    javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Este préstamo ya fue devuelto.\nNo se puede gestionar nuevamente.",
                        "Información",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                // Si no está devuelto → abrir ventana normalmente
                int idPrestamo = Integer.parseInt(
                    jTable1.getValueAt(fila, 0).toString()
                );

                VentanaGestionPrestamo ventana =
                    new VentanaGestionPrestamo(idPrestamo);

                ventana.setVisible(true);
            }
        }
    });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(121, 68, 23));
        jPanel1.setForeground(new java.awt.Color(255, 153, 153));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_PRESTAMO", "LIBRO PRESTADO ", "SOLICITANTE", "FECHA DE  PRÉSTAMO", "FECHA DE  VENCIMIENTO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 1, 24)); // NOI18N
        jLabel1.setText("Todos Los Prestamos Realizados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
