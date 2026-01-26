/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix.Login_CRUD.Usuario;

/**
 *
 * @author albap
 */
public class Usuario {
    int id;
    String nombre, apellido1, apellido2, telefono, correo, usuario_login, contrasenya_login, rol, fecha_creacion;

    public Usuario(int id, String nombre, String apellido1, String apellido2, String telefono, String correo, String usuario_login, String contrasenya_login, String rol, String fecha_creacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario_login = usuario_login;
        this.contrasenya_login = contrasenya_login;
        this.rol = rol;
        this.fecha_creacion = fecha_creacion;
    }
    
    public Usuario(int id, String nombre, String apellido1, String apellido2, String telefono, String correo, String usuario_login, String rol, String fecha_creacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario_login = usuario_login;
        this.rol = rol;
        this.fecha_creacion = fecha_creacion;
    }
    
    public Usuario(int id, String nombre, String apellido1, String apellido2, String telefono, String correo, String usuario_login, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario_login = usuario_login;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
    public static String[] getColumnas(){
        String[] columnas = {"Id", "Nombre", "Primer apellido", "Segundo apellido", "Telefono", "Correo", "Usuario"};
        return columnas;
    }
    
    public String[] devuelveFila(){
        String[] fila = {
            String.valueOf(id),
            nombre,
            apellido1,
            apellido2,
            telefono,
            correo,
            usuario_login
        };
        return fila;
    }
}
