/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dominio.Material;
import dominio.ReporteMaterial;
import dominio.Vehiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import jwt.JwtUtil;

@Path("reportematerial")
public class ReporteMaterialController {

    @PersistenceContext(unitName = "miUnidadDePersistencia")
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("CrearReportematerial")
    @Transactional
    public Response createReporteMaterial(ReporteMaterial reporteMaterial, @Context HttpHeaders headers) {
        // Obtener el token de autorización
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }

        // Recuperar los objetos Vehiculo y Material usando sus IDs
        Long idVehiculo = reporteMaterial.getVehiculo().getIdvehiculo(); // Obtener solo el ID del vehículo
        Long idMaterial = reporteMaterial.getMaterial().getIdmaterial(); // Obtener solo el ID del material

        // Buscar en la base de datos los objetos completos de Vehiculo y Material
        Vehiculo vehiculo = entityManager.find(Vehiculo.class, idVehiculo);
        Material material = entityManager.find(Material.class, idMaterial);

        // Verificar si los objetos fueron encontrados
        if (vehiculo == null || material == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Vehículo o material no encontrado").build();
        }

        // Asociar los objetos Vehiculo y Material con el reporte
        reporteMaterial.setVehiculo(vehiculo);
        reporteMaterial.setMaterial(material);

        // Persistir el reporte
        entityManager.persist(reporteMaterial);

        return Response.status(Response.Status.CREATED).entity(reporteMaterial).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getReportes")
    public List<ReporteMaterial> getAllReporteMaterials() {
        return entityManager.createQuery("SELECT v FROM ReporteMaterial v", ReporteMaterial.class).getResultList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReporteMaterial(@PathParam("id") Long id) {
        ReporteMaterial reporteMaterial = entityManager.find(ReporteMaterial.class, id);
        if (reporteMaterial == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(reporteMaterial).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response updateReporteMaterial(@PathParam("id") Long id, ReporteMaterial reporteMaterial, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        ReporteMaterial existingReporteMaterial = entityManager.find(ReporteMaterial.class, id);
        if (existingReporteMaterial == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingReporteMaterial.setFechaHoraMaterial(reporteMaterial.getFechaHoraMaterial());
        existingReporteMaterial.setCantidadMaterial(reporteMaterial.getCantidadMaterial());
        existingReporteMaterial.setVehiculo(reporteMaterial.getVehiculo());
        existingReporteMaterial.setMaterial(reporteMaterial.getMaterial());
        return Response.ok(existingReporteMaterial).build();
    }

    @DELETE
    @Path("/{id}")

    public Response deleteReporteMaterial(@PathParam("id") Long id, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        ReporteMaterial reporteMaterial = entityManager.find(ReporteMaterial.class, id);
        if (reporteMaterial == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(reporteMaterial);
        return Response.noContent().build();
    }
}
