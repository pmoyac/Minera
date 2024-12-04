/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dominio.Vehiculo;
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

@Path("vehiculos")
public class VehiculoController {

    @PersistenceContext(unitName = "miUnidadDePersistencia")
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("registrar")
    public Response createVehiculo(Vehiculo vehiculo, @Context HttpHeaders headers) {
        
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        
        entityManager.persist(vehiculo);
        return Response.status(Response.Status.CREATED).entity(vehiculo).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getVehiculos")
    public List<Vehiculo> getAllVehiculos() {
        return entityManager.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class).getResultList();
    }

    @GET
    @Path("getVehiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehiculo(@PathParam("id") Long id) {
        Vehiculo vehiculo = entityManager.find(Vehiculo.class, id);
        if (vehiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vehiculo).build();
    }

    @PUT
    @Path("actualizarVehiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateVehiculo(@PathParam("id") Long id, Vehiculo vehiculo, @Context HttpHeaders headers) {
        
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        
        Vehiculo existingVehiculo = entityManager.find(Vehiculo.class, id);
        if (existingVehiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingVehiculo.setPosicionActual(vehiculo.getPosicionActual());
        existingVehiculo.setEstado(vehiculo.getEstado());
        return Response.ok(existingVehiculo).build();
    }

    @DELETE
    @Path("borrarVehiculo")
    @Transactional
    public Response deleteVehiculo(@PathParam("id") Long id, @Context HttpHeaders headers) {
        
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        
        Vehiculo vehiculo = entityManager.find(Vehiculo.class, id);
        if (vehiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(vehiculo);
        return Response.noContent().build();
    }
}
