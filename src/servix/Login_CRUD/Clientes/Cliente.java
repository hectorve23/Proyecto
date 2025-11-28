/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix.Login_CRUD.Clientes;

/**
 *
 * @author DAM2Alu15
 */
public class Cliente {
    String id_cliente;
    

    public Cliente() {
    }
    
    public Cliente(String id_cliente, String contrasena) {
        this.id_cliente = id_cliente;
        
    }
    
    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    
    
    
}
