/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix.Login_CRUD.Usuario;

import java.sql.SQLException;
import java.sql.*;
import servix.ConexionBBDD;

/**
 *
 * @author DAM2Alu11
 */
public class EliminarEmpleados {    
    ConexionBBDD nueva = new ConexionBBDD();
    Connection conexion=nueva.getConnection();
      
    public void eliminarEmpleados(String id){
        int pk =  Integer.parseInt(id);
        try {
            nueva.conectar();
                    String sql = "DELETE FROM Camarero WHERE id_camarero = ? ";
                    PreparedStatement ps = conexion.prepareStatement(sql);
                    ps.setInt(1, pk);    
                    ps.executeUpdate();

                    ps.close();
        } catch (SQLException ex) {
             System.getLogger(EliminarEmpleados.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);   
        }
    }

}
