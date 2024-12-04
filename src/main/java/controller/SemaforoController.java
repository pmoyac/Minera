/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dominio.Semaforo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jwt.JwtUtil;

@Path("semaforos")
public class SemaforoController {

    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("crear")
    public Response createSemaforo(Semaforo semaforo, @Context HttpHeaders headers) {
        
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        entityManager.persist(semaforo);
        return Response.status(Response.Status.CREATED).entity(semaforo).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getSemaforos")
    public List<Semaforo> getAllSemaforos() {
        return entityManager.createQuery("SELECT v FROM Semaforo v", Semaforo.class).getResultList();
    }

    @GET
    @Path("getSemaforo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSemaforo(@PathParam("id") Long id) {
        Semaforo semaforo = entityManager.find(Semaforo.class, id);
        if (semaforo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(semaforo).build();
    }

    @PUT
    @Path("actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateSemaforo(@PathParam("id") Long id, Semaforo semaforo, @Context HttpHeaders headers) {
        
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        
        Semaforo existingSemaforo = entityManager.find(Semaforo.class, id);
        if (existingSemaforo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingSemaforo.setEstado(semaforo.getEstado());
        existingSemaforo.setPosicionGeografica(semaforo.getPosicionGeografica());
        return Response.ok(existingSemaforo).build();
    }

    @DELETE
    @Path("borrar")
    @Transactional
    public Response deleteSemaforo(@PathParam("id") Long id, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        Semaforo semaforo = entityManager.find(Semaforo.class, id);
        if (semaforo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(semaforo);
        return Response.noContent().build();
    }
}