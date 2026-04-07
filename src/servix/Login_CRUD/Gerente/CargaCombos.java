/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix.Login_CRUD.Gerente;

import java.sql.Connection;
import java.sql.*;
import javax.swing.JComboBox;
import servix.ConexionBBDD;

/**
 *
 * @author usuario
 */
public class CargaCombos {
    ConexionBBDD nueva = new ConexionBBDD();
    Connection conexion=nueva.getConnection();
    
    public void cargaCombos(JComboBox jComboBoxRestaurantes, JComboBox jComboBoxEncargados){
        try {
            
            jComboBoxRestaurantes.removeAllItems();
            PreparedStatement ps1 = conexion.prepareStatement("SELECT CONCAT(nombre, ' | ', direccion) as nombre_direccion FROM restaurante "
                                                            + "WHERE id_restaurante NOT IN(SELECT id_restaurante FROM usuario_restaurante)");
            
            ResultSet r = ps1.executeQuery();
            while(r.next()){
                jComboBoxRestaurantes.addItem(r.getString("nombre_direccion"));
            }
            
            jComboBoxEncargados.removeAllItems();
            PreparedStatement ps2 = conexion.prepareStatement("SELECT CONCAT(nombre, ' ', apellido1, ' ', apellido2) as nombre_apellidos "
                                                            + "FROM usuario WHERE id NOT IN(SELECT id_usuario FROM usuario_restaurante) AND rol=?");
            ps2.setString(1, "encargado");
            
            ResultSet r2 = ps2.executeQuery();
            while(r2.next()){
                jComboBoxEncargados.addItem(r2.getString("nombre_apellidos"));
            }
            
        } catch (SQLException ex) {
            System.getLogger(CargaCombos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public void cargaComboRestaurantes(JComboBox jComboBoxRestaurantes){//Metodo necesario para usarlo en la seleccion para crear reservas
        try {
            
            jComboBoxRestaurantes.removeAllItems();
            PreparedStatement ps1 = conexion.prepareStatement("SELECT CONCAT(nombre, ' | ', direccion) as nombre_direccion FROM restaurante ");
            
            ResultSet r = ps1.executeQuery();
            while(r.next()){
                jComboBoxRestaurantes.addItem(r.getString("nombre_direccion"));
            }
            
        } catch (SQLException ex) {
            System.getLogger(CargaCombos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
