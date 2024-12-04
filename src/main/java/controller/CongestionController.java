/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dominio.Congestion;
import dominio.Semaforo;
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

@Path("congestiones")
public class CongestionController {

    @PersistenceContext(unitName = "miUnidadDePersistencia")
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("CrearCongestion")
    public Response createCongestion(Congestion congestion, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        
        Long idSemaforo = congestion.getSemaforo().getIdsemaforo(); // Obtener solo el ID del vehículo
        Long idVehiculo= congestion.getVehiculo().getIdvehiculo(); // Obtener solo el ID del material

        // Buscar en la base de datos los objetos completos de Vehiculo y Material
        Semaforo semaforo = entityManager.find(Semaforo.class, idSemaforo);
        Vehiculo vehiculo = entityManager.find(Vehiculo.class, idVehiculo);
        

        // Verificar si los objetos fueron encontrados
        if (semaforo == null || vehiculo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Semaforo o vehículo no encontrado").build();
        }

        // Asociar los objetos Semaforo y Vehiculo con el reporte
        congestion.setSemaforo(semaforo);
        congestion.setVehiculo(vehiculo);
        
        
        entityManager.persist(congestion);
        return Response.status(Response.Status.CREATED).entity(congestion).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getCongestiones")
    public List<Congestion> getAllCongestions() {
        return entityManager.createQuery("SELECT v FROM Congestion v", Congestion.class).getResultList();
    }

    @GET
    @Path("getCongestion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCongestion(@PathParam("id") Long id) {
        Congestion congestion = entityManager.find(Congestion.class, id);
        if (congestion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(congestion).build();
    }

    @PUT
    @Path("actualizarCongestion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCongestion(@PathParam("id") Long id, Congestion congestion, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
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
    @Path("borrarCongestion")
    @Transactional
    public Response deleteCongestion(@PathParam("id") Long id, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        Congestion congestion = entityManager.find(Congestion.class, id);
        if (congestion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(congestion);
        return Response.noContent().build();
    }
}
