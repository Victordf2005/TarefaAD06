/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion;

import Clases.BaseDatos;
import Clases.MensaxeLista;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/** Formulario para ver mensaxes
 *
 * @author Víctor Díaz
 */
public class FrmVerMensaxes extends javax.swing.JDialog {

    private String opcionVistaMensaxes;
    private int saltarMensaxes;
    
    private DefaultTableModel dtmTabMensaxes = new DefaultTableModel();
    
    /**
     * Creates new form FrmVerMensaxes
     */
    public FrmVerMensaxes(java.awt.Frame parent, boolean modal, String opcion) {
        super(parent, modal);
        initComponents();        
        this.opcionVistaMensaxes = opcion;
        setModeloTaboa();
        saltarMensaxes = 0;
        
        // Modificamos o texto das etiquetas dependendo da opción a listar
        // Tamén habilitaremos ou non outros obxectos.
        switch (opcionVistaMensaxes) {
            case "T":
                lblTitulo.setText("Listaxe de todas as mensaxes");
                deshabilitarObxectos();
                buscarMensaxes();                
                break;
            case "U":
                lblTitulo.setText("Listaxe de mensaxes de usuarios que segues");
                deshabilitarObxectos();
                buscarMensaxes();           
                break;
            case "H":
                lblTitulo.setText("Listaxe de mensaxes co hashtag");
                lblInfo.setText("Hashtag:");
                btnAnterior.setEnabled(false);
                btnPosterior.setEnabled(false);
        }
        
    }
    
    // Deseñamos o modelo de táboa que recollerá e amosará as mensaxes
    private void setModeloTaboa() {
        String[] cabeceira = {"Nome","Usuario","Mensaxe","Data"};
        dtmTabMensaxes.setColumnIdentifiers(cabeceira);
        tabMensaxes.setModel(dtmTabMensaxes);       
        
    }
    
    private void deshabilitarObxectos() {
        lblInfo.setText("");
        lblInfo.setEnabled(false);
        txtBuscar.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnPosterior.setEnabled(false);
    }
    
    
    private void buscarMensaxes() {
        
        ArrayList<MensaxeLista> mensaxes = new ArrayList<>();
        
        switch (opcionVistaMensaxes) {
            case "T": 
                mensaxes = FrmLog.db.getMensaxes(saltarMensaxes, 5);
                break;
            case "U":
                mensaxes = FrmLog.db.getMensaxesUsuarios(saltarMensaxes, 5, FrmMenu.usuario.getSegueA());
                break;
            case "H":
                mensaxes = FrmLog.db.getMensaxesHashtag(saltarMensaxes, 5, txtBuscar.getText());
    }
        
        Object[] fila = new Object[4];
        dtmTabMensaxes.setRowCount(0);
        int ultimaFila = Math.min(5,mensaxes.size());
        for (int i=0; i<ultimaFila; i++) {
            fila[0] = mensaxes.get(i).getNomeUsuario();
            fila[1] = mensaxes.get(i).getUserUsuario();
            fila[2] = mensaxes.get(i).getTexto();
            fila[3] = mensaxes.get(i).getData();
            dtmTabMensaxes.addRow(fila);
        }
        
        tabMensaxes.setModel(dtmTabMensaxes);
        
        // comprobamos se habilitamos o botón de ir cara atrás na listaxe
        if (saltarMensaxes == 0) {            
            // Estamos no principio; non permitimos ir cara atrás
            btnAnterior.setEnabled(false);
            
        } else {
            // Permitimos ir a mensaxes anteriores
            btnAnterior.setEnabled(true);
        }
        
        // comprobamos se habilitamos o botón de ir cara adiante na listaxe
        if (mensaxes.size() <= 5) {
            // chegamos ao final da listaxe. Non permitimos ir a mensaxes posteriores
            btnPosterior.setEnabled(false);
        } else {
            // permitimos ir a mensaxes posteriores
            btnPosterior.setEnabled(true);
        }
        
        mensaxes = null;
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabMensaxes = new javax.swing.JTable();
        btnAnterior = new javax.swing.JButton();
        btnPosterior = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabMensaxes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabMensaxes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabMensaxes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabMensaxes);

        btnAnterior.setText("<<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnPosterior.setText(">>");
        btnPosterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPosteriorActionPerformed(evt);
            }
        });

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(204, 0, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Mensaxes");

        lblInfo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblInfo.setText("jLabel1");

        txtBuscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtBuscar.setToolTipText("");

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 912, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAnterior)
                                .addGap(18, 18, 18)
                                .addComponent(btnPosterior))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfo)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnPosterior))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPosteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPosteriorActionPerformed
        // para ir a mensaxes posteriores, engadimos 5 ao número de mensaxes a saltar
        saltarMensaxes += 5;
        buscarMensaxes();
    }//GEN-LAST:event_btnPosteriorActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // para ir a mensaxes anteriroes, restamos 5 ao número de mensaxes a saltar
        saltarMensaxes -= 5;
        buscarMensaxes();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // inicialmente non saltamos ningunha mensaxe
        saltarMensaxes=0;
        buscarMensaxes();
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmVerMensaxes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVerMensaxes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVerMensaxes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVerMensaxes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmVerMensaxes dialog = new FrmVerMensaxes(new javax.swing.JFrame(), true, args[0]);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnPosterior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tabMensaxes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
