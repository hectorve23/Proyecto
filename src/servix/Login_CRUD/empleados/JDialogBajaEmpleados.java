/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package servix.Login_CRUD.empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import servix.ConexionBBDD;
import servix.Empleado;
import servix.Seguridad;

/**
 *
 * @author DAM2Alu11
 */
public class JDialogBajaEmpleados extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JDialogBajaEmpleados.class.getName());
    ConexionBBDD nueva;
    Connection conexion;
    int id;
    
    /**
     * Creates new form JDialogBajaEmpleados
     */
    public JDialogBajaEmpleados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        nueva = new ConexionBBDD();
        conexion=nueva.getConnection();
        initComponents();
    }
    
    public JDialogBajaEmpleados(java.awt.Frame parent, boolean modal, int idEmpleado) {
        super(parent, modal);
        nueva = new ConexionBBDD();
        conexion = nueva.getConnection();
        this.id = idEmpleado;
        initComponents();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
     
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
    // End of variables declaration//GEN-END:variables
    
    public String seleccionarDatos(int id){
        String nombreApellido = null;
        try {
            nueva.conectar();
            String sql = "SELECT nombre, apellido1 FROM Camarero WHERE id_camarero = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String nombre= rs.getString("nombre");
                String apellido = rs.getString("apellido1");
                nombreApellido = nombre + " " + apellido;
            }else{
                JOptionPane.showConfirmDialog(rootPane,
                        "No existe ese empleado",
                        "Error",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.ERROR_MESSAGE);
            }
            
            rs.close();
            ps.close();
            return nombreApellido;
        } catch (SQLException ex) {
            System.getLogger(JDialogBajaEmpleados.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return nombreApellido;
    }
    
    public void eliminarEmpleados(int id){
        try {
            nueva.conectar();
                    String sql = "DELETE FROM Camarero WHERE id_camarero = ? ";
                    PreparedStatement ps = conexion.prepareStatement(sql);
                    ps.setInt(1, id );    
                    ps.executeUpdate();

                    ps.close();
        } catch (SQLException ex) {
             System.getLogger(JDialogBajaEmpleados.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);   
        }
    }
}
