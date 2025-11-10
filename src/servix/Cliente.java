/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix;

/**
 *
 * @author DAM2Alu11
 */
public class Cliente {
    int id_cliente;
    String nombre, apellido1, apellido2, telefono, correo, usuario_login, contrasenya_login;

    public Cliente(String nombre, String apellido1, String apellido2, String telefono, String correo, String usuario_login, String contrasenya_login) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario_login = usuario_login;
        this.contrasenya_login = contrasenya_login;
    }

    public int getId_cliente() {
        return id_cliente;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", telefono=" + telefono + ", correo=" + correo + ", usuario_login=" + usuario_login + ", contrasenya_login=" + contrasenya_login + '}';
    }
    
    
    
}
