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
@Table (name = "notificacion")
public class Notificacion implements Serializable {

    @Id
    @Column(name="idnotificacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnotificacion;

    @Column(name = "tipoNotificacion", nullable = false, length = 20)
    private String tipoNotificacion;
    
    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(name = "mensaje", nullable = false, length = 100)
    private String mensaje;
    
    @ManyToOne
    @JoinColumn(name = "idcongestion")
    private Congestion congestion;
    
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    public Notificacion() {
    }

    public Notificacion(Long idnotificacion, String tipoNotificacion, LocalDateTime fechaHora, String mensaje, Congestion congestion, Usuario usuario) {
        this.idnotificacion = idnotificacion;
        this.tipoNotificacion = tipoNotificacion;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.congestion = congestion;
        this.usuario = usuario;
    }

    public Long getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(Long idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Congestion getCongestion() {
        return congestion;
    }

    public void setCongestion(Congestion congestion) {
        this.congestion = congestion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
}
