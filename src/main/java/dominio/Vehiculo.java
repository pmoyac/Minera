/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

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
    
    @OneToMany(mappedBy = "vehiculo")
    @JsonbTransient
    private List<ReporteMaterial> reportesMaterial;
    
    @OneToMany(mappedBy = "vehiculo")
    @JsonbTransient
    private List<Congestion> congestion;

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

    @Override
    public String toString() {
        return "Vehiculo{" + "posicionActual=" + posicionActual + ", estado=" + estado + '}';
    }
    
}
