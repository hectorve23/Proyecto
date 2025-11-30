/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author DAM2ALU11
 */
public class Reserva {

    int id_reserva;
    String estado_reserva;
    int n_comensales;
    Time hora;
    Date fecha;
    int id_cliente;
    int id_mesa;

    public Reserva(int id_reserva, String estado_reserva, int n_comensales, Time hora, Date fecha, int id_cliente, Integer id_mesa) {
        this.id_reserva = id_reserva;
        this.estado_reserva = estado_reserva;
        this.n_comensales = n_comensales;
        this.hora = hora;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_mesa = id_mesa;
    }

    //Sin id
    public Reserva(String estado_reserva, int n_comensales, Time hora, Date fecha, int id_cliente, Integer id_mesa) {
        this.estado_reserva = estado_reserva;
        this.n_comensales = n_comensales;
        this.hora = hora;
        this.fecha = fecha;
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

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
            "Hora",
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
            hora,
            fecha,
            id_cliente,
            id_mesa
        };
    }
}
