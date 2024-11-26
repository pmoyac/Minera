/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author icedo
 */
@Entity
@Table (name = "vehiculo")
public class Vehiculo implements Serializable {

    @Id
    @Column(name="idvehiculo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idvehiculo;

    @Column(name = "posicionActual", nullable = false, length = 100)
    private String posicionActual;
    
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    public Vehiculo() {
    }

    public Vehiculo(Long idvehiculo, String posicionActual, String estado) {
        this.idvehiculo = idvehiculo;
        this.posicionActual = posicionActual;
        this.estado = estado;
    }

    public Long getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Long idvehiculo) {
        this.idvehiculo = idvehiculo;
    }


    public String getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(String posicionActual) {
        this.posicionActual = posicionActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
    
}
