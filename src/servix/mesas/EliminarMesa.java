/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix.mesas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import servix.ConexionBBDD;

/**
 *
 * @author DAM2ALU11
 */
public class EliminarMesa {
    ConexionBBDD nueva = new ConexionBBDD();
    Connection conexion=nueva.getConnection();
      
    public void eliminarMesa(int id){
        try {
            nueva.conectar();
                    String sql = "DELETE FROM Mesa WHERE id_mesa = ? ";
                    PreparedStatement ps = conexion.prepareStatement(sql);
                    ps.setInt(1, id);    
                    ps.executeUpdate();

                    ps.close();
        } catch (SQLException ex) {
             System.getLogger(EliminarMesa.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);   
        }
    }
}
