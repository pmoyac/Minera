/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dominio.Material;
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

@Path("/materiales")
public class MaterialController {

    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createMaterial(Material material, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        entityManager.persist(material);
        return Response.status(Response.Status.CREATED).entity(material).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Material> getAllMaterials() {
        return entityManager.createQuery("SELECT v FROM Material v", Material.class).getResultList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaterial(@PathParam("id") Long id) {
        Material material = entityManager.find(Material.class, id);
        if (material == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(material).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateMaterial(@PathParam("id") Long id, Material material, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        Material existingMaterial = entityManager.find(Material.class, id);
        if (existingMaterial == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingMaterial.setMaterial(material.getMaterial());
        existingMaterial.setDescripcion(material.getDescripcion());
        existingMaterial.setValor(material.getValor());
        return Response.ok(existingMaterial).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMaterial(@PathParam("id") Long id, @Context HttpHeaders headers) {
        String token = headers.getHeaderString("Authorization");
        if (token == null || !JwtUtil.validateToken(token.replace("Bearer ", ""))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        Material material = entityManager.find(Material.class, id);
        if (material == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entityManager.remove(material);
        return Response.noContent().build();
    }
}
