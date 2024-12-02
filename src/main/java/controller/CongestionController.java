/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dominio.Congestion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/congestiones")
public class CongestionController {

    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCongestion(Congestion congestion) {
        entityManager.persist(congestion);
        return Response.status(Response.Status.CREATED).entity(congestion).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Congestion> getAllCongestions() {
        return entityManager.createQuery("SELECT v FROM Congestion v", Congestion.class).getResultList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCongestion(@PathParam("id") Long id) {
        Congestion congestion = entityManager.find(Congestion.class, id);
        if (congestion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(congestion).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCongestion(@PathParam("id") Long id, Congestion congestion) {
        Congestion existingCongestion = entityManager.find(Congestion.class, id);
        if (existingCongestion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingCongestion.setFechaHora(congestion.getFechaHora());
        existingCongestion.setLocalizacion(congestion.getLocalizacion());
        existingCongestion.setNivelGravedad(congestion.getNivelGravedad());
        existingCongestion.setCausa(congestion.getCausa());
        existingCongestion.setSemaforo(congestion.getSemaforo());
        existingCongestion.setVehiculo(congestion.getVehiculo());
        return Response.ok(existingCongestion).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCongestion(@PathParam("id") Long id) {
        Congestion congestion = entityManager.find(Congestion.class, id);
        if (congestion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(congestion);
        return Response.noContent().build();
    }
}
