/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix;
import java.time.LocalDateTime;

/**
 *
 * @author DAM2ALU11
 */
public class Reserva {

    int id_reserva;
    String estado_reserva;
    int n_comensales;
    LocalDateTime fecha_hora;
    int id_cliente;
    int id_mesa;

    public Reserva(int id_reserva, String estado_reserva, int n_comensales, LocalDateTime fecha_hora, int id_cliente, Integer id_mesa) {
        this.id_reserva = id_reserva;
        this.estado_reserva = estado_reserva;
        this.n_comensales = n_comensales;
        this.fecha_hora = fecha_hora;
        this.id_cliente = id_cliente;
        this.id_mesa = id_mesa;
    }

    //Sin id
    public Reserva(String estado_reserva, int n_comensales, LocalDateTime fecha_hora, int id_cliente, Integer id_mesa) {
        this.estado_reserva = estado_reserva;
        this.n_comensales = n_comensales;
        this.fecha_hora = fecha_hora;
        this.id_cliente = id_cliente;
        this.id_mesa = id_mesa;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(String estado_reserva) {
        this.estado_reserva = estado_reserva;
    }

    public int getN_comensales() {
        return n_comensales;
    }

    public void setN_comensales(int n_comensales) {
        this.n_comensales = n_comensales;
    }

    public LocalDateTime getFecha() {
        return fecha_hora;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha_hora = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(Integer id_mesa) {
        this.id_mesa = id_mesa;
    }
    
    public static String[] getColumnas() {
        return new String[]{
            "ID Reserva",
            "Estado",
            "Comensales",
            "Fecha",
            "ID Cliente",
            "ID Mesa"
        };
    }

    public Object[] devuelveFila() {
        return new Object[]{
            id_reserva,
            estado_reserva,
            n_comensales,
            fecha_hora,
            id_cliente,
            id_mesa
        };
    }
}
