/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix.mesas;

import java.sql.Connection;
import java.time.LocalTime;
import servix.ConexionBBDD;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DAM2ALU11
 */
public class AsignarMesa {  
    LocalTime inicio_comida = LocalTime.of(12, 0);
    LocalTime fin_comida = LocalTime.of(16, 0);
    LocalTime inicio_cena = LocalTime.of(21, 0);
    LocalTime fin_cena = LocalTime.of(23, 59);
    ConexionBBDD nueva = new ConexionBBDD();
    Connection conexion=nueva.getConnection();
    
    public boolean buscarMesa(int idReserva, int n_personas, java.time.LocalDateTime ldt){
        nueva.conectar();
        
        int id_mesa = buscarMesaDisponible(n_personas, ldt);
        if(id_mesa != -1){
            try {
                String sql = "UPDATE Reserva SET id_mesa = ?, estado_reserva = ? WHERE id_reserva = ?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_mesa);
                ps.setString(2, "confirmada");
                ps.setInt(3, idReserva);
                ps.executeUpdate();
                
                return true;
            } catch (SQLException ex) {
                System.getLogger(AsignarMesa.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                return false;
            }  
        }else{
            return false;
        }
    }
    
    private String horario(java.time.LocalDateTime ldt){
       LocalTime horaLocal = ldt.toLocalTime();
       
       if (!horaLocal.isBefore(inicio_comida) && horaLocal.isBefore(fin_comida)) {
            return "comida";
        } else if (!horaLocal.isBefore(inicio_cena) && horaLocal.isBefore(fin_cena)) {
            return "cena";
        } else {
            return "fuera de horario";
        }
    }
    
    private int buscarMesaDisponible(int numPersonas, java.time.LocalDateTime ldt){
        try {
            String sql ="SELECT m.id_mesa " +
                "FROM Mesa m " +
                "WHERE m.capacidad >= ? AND m.estado = true " +
                "AND NOT EXISTS ( " +
                "    SELECT 1 FROM Reserva r " +
                "    WHERE r.id_mesa = m.id_mesa " +
                "      AND ABS(TIMESTAMPDIFF(MINUTE, r.fecha_hora, ?)) < 120 " + // <-- r.fecha es tu nuevo campo DATETIME
                "      AND r.estado_reserva IN ('confirmada', 'ocupada')" +
                ") " +
                "ORDER BY m.capacidad ASC " +
                "LIMIT 1";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, numPersonas);
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(ldt));
            
            ResultSet rs = ps.executeQuery();
        
            if (rs.next()) {
                return rs.getInt("id_mesa");
            }
        
        
        } catch (SQLException ex) {
            System.getLogger(AsignarMesa.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return -1;
    }
}
