/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix;

/**
 *
 * @author DAM2ALU11
 */
public class Mesa {
    
    int id_mesa;
    int capacidad;
    boolean estado;

    public Mesa(int id_mesa, int capacidad, boolean estado) {
        this.id_mesa = id_mesa;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Mesa(int capacidad) {
        this.capacidad = capacidad;
    }
    
    

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public static String[] getColumnas() {
        return new String[]{
            "ID Mesa",
            "Capacidad", 
            "Estado"
        };
    }

    public Object[] devuelveFila() {
        return new Object[]{
            id_mesa,
            capacidad,
            estado
        };
    }
}
