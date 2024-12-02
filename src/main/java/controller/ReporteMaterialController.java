/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dominio.ReporteMaterial;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/reportemateriales")
public class ReporteMaterialController {

    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createReporteMaterial(ReporteMaterial reporteMaterial) {
        entityManager.persist(reporteMaterial);
        return Response.status(Response.Status.CREATED).entity(reporteMaterial).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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
    @Transactional
    public Response updateReporteMaterial(@PathParam("id") Long id, ReporteMaterial reporteMaterial) {
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
    @Transactional
    public Response deleteReporteMaterial(@PathParam("id") Long id) {
        ReporteMaterial reporteMaterial = entityManager.find(ReporteMaterial.class, id);
        if (reporteMaterial == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(reporteMaterial);
        return Response.noContent().build();
    }
}
