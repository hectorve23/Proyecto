/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix;

/**
 *
 * @author DAM2Alu11
 */
public class Empleado {
    int id_camarero;
    String nombre, apellido1, apellido2, telefono, usuario_login, contrasenya_login;

    public Empleado(int id_camarero, String nombre, String apellido1, String apellido2, String telefono, String usuario_login, String contrasenya_login) {
        this.id_camarero = id_camarero;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.usuario_login = usuario_login;
        this.contrasenya_login = contrasenya_login;
    }

    public Empleado(int id_camarero, String nombre, String apellido1, String apellido2, String telefono, String usuario_login) {
        this.id_camarero = id_camarero;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.usuario_login = usuario_login;
    }

    public Empleado(String nombre, String apellido1, String apellido2,  String telefono, String usuario_login) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.usuario_login = usuario_login;
    }
      

    public int getId_camarero() {
        return id_camarero;
    }

    public void setId_camarero(int id_camarero) {
        this.id_camarero = id_camarero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario_login() {
        return usuario_login;
    }

    public void setUsuario_login(String usuario_login) {
        this.usuario_login = usuario_login;
    }

    public String getContrasenya_login() {
        return contrasenya_login;
    }

    public void setContrasenya_login(String contrasenya_login) {
        this.contrasenya_login = contrasenya_login;
    }

    public static String[] getColumnas(){
        String[] columnas = {"Id", "Nombre", "Primer apellido", " Segundo apellido", "Telefono", "Usuario"};
        return columnas;
    }
    
    public String[] devuelveFila(){
        String[] fila = {
            String.valueOf(id_camarero),
            nombre,
            apellido1,
            apellido2,
            telefono,
            usuario_login
        };
        return fila;
    }
    
}
