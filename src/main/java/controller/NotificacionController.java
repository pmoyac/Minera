/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dominio.Notificacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/notificaciones")
public class NotificacionController {

    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNotificacion(Notificacion notificacion) {
        entityManager.persist(notificacion);
        return Response.status(Response.Status.CREATED).entity(notificacion).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notificacion> getAllNotificacions() {
        return entityManager.createQuery("SELECT v FROM Notificacion v", Notificacion.class).getResultList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotificacion(@PathParam("id") Long id) {
        Notificacion notificacion = entityManager.find(Notificacion.class, id);
        if (notificacion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(notificacion).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateNotificacion(@PathParam("id") Long id, Notificacion notificacion) {
        Notificacion existingNotificacion = entityManager.find(Notificacion.class, id);
        if (existingNotificacion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingNotificacion.setTipoNotificacion(notificacion.getTipoNotificacion());
        existingNotificacion.setFechaHora(notificacion.getFechaHora());
        existingNotificacion.setMensaje(notificacion.getMensaje());
        existingNotificacion.setCongestion(notificacion.getCongestion());
        existingNotificacion.setUsuario(notificacion.getUsuario());
        return Response.ok(existingNotificacion).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteNotificacion(@PathParam("id") Long id) {
        Notificacion notificacion = entityManager.find(Notificacion.class, id);
        if (notificacion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(notificacion);
        return Response.noContent().build();
    }
}
