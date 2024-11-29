/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author icedo
 */
@Entity
@Table (name = "usuario") 
public class Usuario implements Serializable {

    
    @Id
    @Column(name="idusuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "pass", nullable = false, length = 100)
    private String pass;
    
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    public Usuario() {
    }

    public Usuario(Long idusuario, String nombre, String pass, String correo) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.pass = pass;
        this.correo = correo;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    
    
    
    

    

    
    
}
