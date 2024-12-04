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
@Table (name = "material")
public class Material implements Serializable {

    @Id
    @Column(name="idmaterial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmaterial;

    @Column(name = "material", nullable = false, length = 45)
    private String material;
    
    @Column(name = "descripcion", nullable = false, length = 45)
    private String descripcion;
    
    @Column(name = "valor", nullable = false, length = 45)
    private String valor;
    
    @OneToMany(mappedBy = "material")
    @JsonbTransient
    private List<ReporteMaterial> reportesMaterial;

    public Material() {
    }

    public Material(Long idmaterial, String material, String descripcion, String valor) {
        this.idmaterial = idmaterial;
        this.material = material;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Material(String material, String descripcion, String valor, List<ReporteMaterial> reportesMaterial) {
        this.material = material;
        this.descripcion = descripcion;
        this.valor = valor;
        this.reportesMaterial = reportesMaterial;
    }

    public Material(Long idmaterial, String material, String descripcion, String valor, List<ReporteMaterial> reportesMaterial) {
        this.idmaterial = idmaterial;
        this.material = material;
        this.descripcion = descripcion;
        this.valor = valor;
        this.reportesMaterial = reportesMaterial;
    }
    
    
    

    public Long getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Long idmaterial) {
        this.idmaterial = idmaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<ReporteMaterial> getReportesMaterial() {
        return reportesMaterial;
    }

    public void setReportesMaterial(List<ReporteMaterial> reportesMaterial) {
        this.reportesMaterial = reportesMaterial;
    }
    
    

    @Override
    public String toString() {
        return "Material{" + "material=" + material + ", descripcion=" + descripcion + ", valor=" + valor + '}';
    }

    
}
