/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacion;

import Clases.Usuario;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static programacion.FrmLog.ATENCION;

/** Formulario para buscar e listar os/as usuarios/as rexistrados,
 *  filtrados segundo caracteres tecleados
 *
 * @author Víctor Díaz
 */
public class FrmBuscarUsuarios extends javax.swing.JDialog {

    
    private DefaultTableModel dtmTabUsuarios = new DefaultTableModel();
    
    private int saltarUsuarios; // nº de rexistros a saltarse na búsqueda
        
    /**
     * Creates new form FrmBuscarUsuarios
     */
    public FrmBuscarUsuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setModeloTaboa();
    }
    
    // Método para asignar modelo á táboa de usuarios
    private void setModeloTaboa() {
        String[] cabeceira = {"Nome", "Usuario"};
        dtmTabUsuarios.setColumnIdentifiers(cabeceira);
        tabUsuarios.setModel(dtmTabUsuarios);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabUsuarios = new javax.swing.JTable();
        btnAnterior = new javax.swing.JButton();
        btnPosterior = new javax.swing.JButton();
        btnSeguir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Buscar usuarios");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Buscar usuarios/as que conteñan:");

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar_click(evt);
            }
        });

        tabUsuarios.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabUsuarios);

        btnAnterior.setText("<<");
        btnAnterior.setEnabled(false);
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnPosterior.setText(">>");
        btnPosterior.setEnabled(false);
        btnPosterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPosteriorActionPerformed(evt);
            }
        });

        btnSeguir.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSeguir.setText("Seguir a usuario/a seleccionado/a");
        btnSeguir.setToolTipText("");
        btnSeguir.setEnabled(false);
        btnSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeguir_click(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAnterior)
                .addGap(18, 18, 18)
                .addComponent(btnPosterior)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnSeguir, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnPosterior))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSeguir)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnBuscar_click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar_click
        // inicialmente non saltamos ningún usuario
        saltarUsuarios = 0;
        buscarUsuarios();
    
                       
    }//GEN-LAST:event_btnBuscar_click

private void buscarUsuarios() {
    
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        // obtemos a listaxe de usuarios que superan o filtro        
        usuarios = FrmLog.db.getUsuarios(saltarUsuarios, 5, txtBuscar.getText());
        
        Object[] fila = new Object[2];
        dtmTabUsuarios.setRowCount(0);
        
        // Establecemos a última fila a amosar na táboa
        // se o array ten 5 ou 6 elementos só amosamos 5        
        int ultimaFila = Math.min(5, usuarios.size());
            
        for (int i=0; i<ultimaFila; i++) {
            fila[0] = usuarios.get(i).getNome();
            fila[1] = usuarios.get(i).getUsuario();
            dtmTabUsuarios.addRow(fila);
        }

        tabUsuarios.setModel(dtmTabUsuarios);
        
        // comprobamos se habilitamos o botón de ir cara atrás na listaxe
        if (saltarUsuarios == 0) {
            // estamos ao comezo da listaxe. Non permitimos ir a usuarios anteriores
            btnAnterior.setEnabled(false);
        } else {
            // permitimos ir a usuarios anteriores
            btnAnterior.setEnabled(true);            
        }
        
        // comprobamos se habilitamos o botón de ir cara adiante na listaxe
        if (usuarios.size() <= 5) {
            // chegamos ao final da listaxe. Non permitimos ir a usuarios posteriores
            btnPosterior.setEnabled(false);            
            if (usuarios.isEmpty()) {
                // se non hai usuarios, no permitimos pulsar o botón "seguir"
                btnSeguir.setEnabled(false);
            } else {
                // hai usuarios. permitimos pulsar o botón "seguir"
                btnSeguir.setEnabled(true);
            }
        } else {
            // hai máis usuarios, permitimos ir cara adiante.
            btnPosterior.setEnabled(true);
        }
                
        usuarios = null;
        
}    
    
    
    private void btnSeguir_click(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeguir_click
        
        DefaultTableModel modelo = (DefaultTableModel) tabUsuarios.getModel();
        
        if (tabUsuarios.getSelectedRow() < 0) {
            
            JOptionPane.showMessageDialog(rootPane, "Non hai ningún/ha usuario/a seleccionado/a", ATENCION, HEIGHT);
            
        } else {
            
            String usuarioSeleccionado = (String) modelo.getValueAt(tabUsuarios.getSelectedRow(), 1);

            if (usuarioSeleccionado.equals(FrmMenu.usuario.getUsuario())) {
                
                JOptionPane.showMessageDialog(rootPane, "Non se permite seguir a si mesmo.", ATENCION, HEIGHT);
                
            } else {
                
                seguirUsuario(usuarioSeleccionado);
            }
            
        }
        
    }//GEN-LAST:event_btnSeguir_click

    private void btnPosteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPosteriorActionPerformed
        // para ir a usuarios posteriores, engadimos 5 ao número de usuarios a saltar
        saltarUsuarios += 5;
        buscarUsuarios();
    }//GEN-LAST:event_btnPosteriorActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // para ir a usuarios anteriores, restamos 5 ao número de usuarios a saltar
        saltarUsuarios -= 5;
        buscarUsuarios();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    // Método para engadir o usuario seleccionado á listaxe de usuarios que sigo
    private void seguirUsuario(String usuarioSeleccionado) {
        
        // para non repetir usuarios, usamos un treeset
        
        TreeSet<String> arboreSeguir = new TreeSet();
        Usuario u = FrmLog.db.getUsuario(FrmMenu.usuario.getUsuario());
        
        // engadimos ao treeset os usuarios actuais
        for (int i=0; i<u.getSegueA().size(); i++) {
            arboreSeguir.add(u.getSegueA().get(i));
        }        
        
        // engadimos ao treeset o usuario seleccionado
        arboreSeguir.add(usuarioSeleccionado);
        
        ArrayList<String> arr = new ArrayList();
        for (String elemento : arboreSeguir) {            
            if (!elemento.equals("")) {
                arr.add(elemento);
            }
        }
        
        FrmLog.db.actualizarUsuariosSegueA(u.getUsuario(), arr);
        JOptionPane.showMessageDialog(rootPane,"Rexistrado o/a novo/a as seguir.",ATENCION,HEIGHT);
        
    }
    
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
            java.util.logging.Logger.getLogger(FrmBuscarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBuscarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmBuscarUsuarios dialog = new FrmBuscarUsuarios(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSeguir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabUsuarios;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
