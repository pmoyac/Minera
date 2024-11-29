/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dominio.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.mindrot.jbcrypt.BCrypt;
import jwt.JwtUtil;
import token.TokenResponse;

@Path("auth")
public class UsuarioAuthController {

    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Usuario usuario) {
        
        try {
        Usuario existingUser  = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class)
                .setParameter("nombre", usuario.getNombre())
                .getSingleResult();
        
        // Si se encuentra un usuario existente, devolver un error
        return Response.status(Response.Status.CONFLICT).entity("El nombre de usuario ya está registrado").build();
    } catch (NoResultException e) {
        // No hay usuario existente, continuar con el registro
    }
        // Cifrar la contraseña
        String hashedPassword = BCrypt.hashpw(usuario.getPass(), BCrypt.gensalt());
        usuario.setPass(hashedPassword);

        // Guardar el usuario en la base de datos
        entityManager.persist(usuario);
        return Response.status(Response.Status.CREATED).entity("Usuario registrado exitosamente").build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Usuario usuario) {
        try{
        Usuario existingUser  = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class)
                .setParameter("nombre", usuario.getNombre())
                .getSingleResult();
        if (existingUser  != null && BCrypt.checkpw(usuario.getPass(), existingUser .getPass())) {
            String token = JwtUtil.generateToken(existingUser .getNombre());
                return Response.ok().entity(new TokenResponse(token)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no encontrado").build();
        }
    }
}

    
