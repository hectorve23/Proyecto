/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package servix.Login_CRUD.empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import servix.ConexionBBDD;
import servix.Seguridad;

/**
 *
 * @author DAM2Alu11
 */
public class JDialogBajaEmpleados extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JDialogBajaEmpleados.class.getName());
    String nombre;
    String apellido;
    ConexionBBDD nueva;
    Connection conexion;
    String user;
    
    /**
     * Creates new form JDialogBajaEmpleados
     */
    public JDialogBajaEmpleados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        nueva = new ConexionBBDD();
        conexion=nueva.getConnection();
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordFieldContrasena = new javax.swing.JPasswordField();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ELIMINAR CUENTA DE EMPLEADO");

        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 30, 30));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Usuario del empleado");
        jPanel1.add(jLabel7);
        jPanel1.add(jTextFieldUser);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Introduzca la contraseña del encargado");
        jPanel1.add(jLabel2);
        jPanel1.add(jPasswordFieldContrasena);

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        user= jTextFieldUser.getText();
        char[] contrasena = jPasswordFieldContrasena.getPassword();
          String stringContrasena = new String(contrasena);
        int opcion = JOptionPane.showConfirmDialog(rootPane,
                                            "Esta seguro de que quiere eliminar al empleado " + seleccionarDatos(user) , 
                                            "Error", 
                                            JOptionPane.OK_CANCEL_OPTION, 
                                            JOptionPane.QUESTION_MESSAGE);
        
        if(opcion == JOptionPane.YES_OPTION){
           eliminarEmpleados(user, stringContrasena); 
        }
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JDialogBajaEmpleados dialog = new JDialogBajaEmpleados(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldContrasena;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
    
    public String seleccionarDatos(String user){
        String nombreApellido = "";
         try {
            if(user.isEmpty() || user.equals("")){
           
                JOptionPane.showConfirmDialog(rootPane,
                                            "Porfavor rellene todos los campos", 
                                            "Error", 
                                            JOptionPane.OK_CANCEL_OPTION, 
                                            JOptionPane.ERROR_MESSAGE);
            }else{   
            
                nueva.conectar();
                String sql = "SELECT nombre, apellido1 FROM Camarero WHERE usuario_login = ?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, user);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String nombre= rs.getString("nombre");
                    String apellido = rs.getString("apellido1");
                    nombreApellido = nombre + " " + apellido;
                }else{
                     JOptionPane.showConfirmDialog(rootPane,
                                            "Usuario o contraseña incorrectos", 
                                            "Error", 
                                            JOptionPane.OK_CANCEL_OPTION, 
                                            JOptionPane.ERROR_MESSAGE);
                }
                    
                rs.close();
                ps.close();
            } //else
            } catch (SQLException ex) {
                 System.getLogger(JDialogBajaEmpleados.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } finally {
                nueva.cerrar();
            }
         return nombreApellido;
    }
    
    public void eliminarEmpleados(String user, String contrasena){
        try {
            nueva.conectar();
            if(user.isEmpty() || user.equals("")|| contrasena.isEmpty() || contrasena.equals("")){
           
                JOptionPane.showConfirmDialog(rootPane,
                                            "Porfavor rellene todos los campos", 
                                            "Error", 
                                            JOptionPane.OK_CANCEL_OPTION, 
                                            JOptionPane.ERROR_MESSAGE);
            }else{   
                String sql1="SELECT * FROM Encargado";
                PreparedStatement select =conexion.prepareStatement(sql1);
                ResultSet rs = select.executeQuery();
                if(rs.next()){
                   String contrasenaHash = rs.getString("contrasenya_login");
                
                    if (!user.isEmpty() && Seguridad.checkPassword(contrasena, contrasenaHash)) {
                    String sql = "DELETE FROM Camarero WHERE usuario_login = ? ";
                    PreparedStatement ps = conexion.prepareStatement(sql);
                    ps.setString(1, user);    
                    ps.executeUpdate();

                    ps.close();
                    }
                }
            }
        } catch (SQLException ex) {
             System.getLogger(JDialogBajaEmpleados.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);   
        }
    }
}
