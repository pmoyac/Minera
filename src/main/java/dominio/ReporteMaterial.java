/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author icedo
 */
@Entity
@Table (name = "reportematerial")
public class ReporteMaterial implements Serializable {

    @Id
    @Column(name="idreporteMaterial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idreporteMaterial;

    @Column(name = "fechaHoraMaterial", nullable = false)
    private LocalDateTime fechaHoraMaterial;
    
    @Column(name = "cantidadMaterial", nullable = false, length = 45)
    private float cantidadMaterial;
    
    @ManyToOne
    @JoinColumn(name = "idvehiculo")
    private Vehiculo vehiculo;
    
    @ManyToOne
    @JoinColumn(name = "idmaterial")
    private Material material;

    public ReporteMaterial() {
    }

    public ReporteMaterial(Long idreporteMaterial, LocalDateTime fechaHoraMaterial, float cantidadMaterial, Vehiculo vehiculo, Material material) {
        this.idreporteMaterial = idreporteMaterial;
        this.fechaHoraMaterial = fechaHoraMaterial;
        this.cantidadMaterial = cantidadMaterial;
        this.vehiculo = vehiculo;
        this.material = material;
    }

    public Long getIdreporteMaterial() {
        return idreporteMaterial;
    }

    public void setIdreporteMaterial(Long idreporteMaterial) {
        this.idreporteMaterial = idreporteMaterial;
    }

    public LocalDateTime getFechaHoraMaterial() {
        return fechaHoraMaterial;
    }

    public void setFechaHoraMaterial(LocalDateTime fechaHoraMaterial) {
        this.fechaHoraMaterial = fechaHoraMaterial;
    }

    public float getCantidadMaterial() {
        return cantidadMaterial;
    }

    public void setCantidadMaterial(float cantidadMaterial) {
        this.cantidadMaterial = cantidadMaterial;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "ReporteMaterial{" + "fechaHoraMaterial=" + fechaHoraMaterial + ", cantidadMaterial=" + cantidadMaterial + ", vehiculo=" + vehiculo + ", material=" + material + '}';
    }
}
