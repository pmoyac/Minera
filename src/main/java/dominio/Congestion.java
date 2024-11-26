/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author icedo
 */
@Entity
@Table (name = "congestion")
public class Congestion implements Serializable {

    @Id
    @Column(name="idcongestion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcongestion;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(name = "localizacion", nullable = false, length = 45)
    private String localizacion;
    
    @Column(name = "nivelGravedad", nullable = false, length = 45)
    private String nivelGravedad;
    
    @Column(name = "causa", nullable = false, length = 45)
    private String causa;
    
    @ManyToOne
    @JoinColumn(name = "idsemaforo")
    private Semaforo semaforo;
    
    @ManyToOne
    @JoinColumn(name = "idvehiculo")
    private Vehiculo vehiculo;

    public Congestion() {
    }

    public Congestion(Long idcongestion, LocalDateTime fechaHora, String localizacion, String nivelGravedad, String causa, Semaforo semaforo, Vehiculo vehiculo) {
        this.idcongestion = idcongestion;
        this.fechaHora = fechaHora;
        this.localizacion = localizacion;
        this.nivelGravedad = nivelGravedad;
        this.causa = causa;
        this.semaforo = semaforo;
        this.vehiculo = vehiculo;
    }

    public Long getIdcongestion() {
        return idcongestion;
    }

    public void setIdcongestion(Long idcongestion) {
        this.idcongestion = idcongestion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNivelGravedad() {
        return nivelGravedad;
    }

    public void setNivelGravedad(String nivelGravedad) {
        this.nivelGravedad = nivelGravedad;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    
    
    
}
