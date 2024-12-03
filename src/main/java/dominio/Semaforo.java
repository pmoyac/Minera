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
@Table (name = "semaforo")
public class Semaforo implements Serializable {

    @Id
    @Column(name="idsemaforo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsemaforo;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;
    
    @Column(name = "posicionGeografica", nullable = false, length = 100)
    private String posicionGeografica;

    public Semaforo() {
    }

    public Semaforo(Long idsemaforo, String estado, String posicionGeografica) {
        this.idsemaforo = idsemaforo;
        this.estado = estado;
        this.posicionGeografica = posicionGeografica;
    }

    public Long getIdsemaforo() {
        return idsemaforo;
    }

    public void setIdsemaforo(Long idsemaforo) {
        this.idsemaforo = idsemaforo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPosicionGeografica() {
        return posicionGeografica;
    }

    public void setPosicionGeografica(String posicionGeografica) {
        this.posicionGeografica = posicionGeografica;
    }

    @Override
    public String toString() {
        return "Semaforo{" + "estado=" + estado + ", posicionGeografica=" + posicionGeografica + '}';
    }
    
}
