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

    public Material() {
    }

    public Material(Long idmaterial, String material, String descripcion, String valor) {
        this.idmaterial = idmaterial;
        this.material = material;
        this.descripcion = descripcion;
        this.valor = valor;
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
    
    
    
}
