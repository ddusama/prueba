package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.dao.AuthorDao;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/authors")
public class AuthorRest {

    @Inject
    AuthorDao dao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "GET AUTHOR")
    @APIResponse(responseCode = "200", description = "Se encontró el autor", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @APIResponse(responseCode = "400", description = "Hay un problema con la búsqueda")
    public Author findById(@PathParam("id") Long id) {
        return dao.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "GET AUTHORS")
    @APIResponse(responseCode = "200", description = "Se encontraron todos los autores", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Author.class)))
    @APIResponse(responseCode = "400", description = "Hay un problema con la búsqueda")
    public List<Author> findAll() {
        return dao
                .findAll()
                .list();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "CREATE AUTHOR")
    @APIResponse(responseCode = "201", description = "Ae creó el libro")
    @APIResponse(responseCode = "500", description = "Hay un problema con la creación")
    public void insert(Author obj) {
        dao.persist(obj);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "UPDATE AUTHOR")
    @APIResponse(responseCode = "200", description = "Se actualizó el libro")
    @APIResponse(responseCode = "500", description = "Hay un problema con la actualización")
    public void update(Author obj, @PathParam("id") Long id) {
        var author = dao.findById(id);
        author.setFirst_name(obj.getFirst_name());
        author.setLast_name(obj.getLast_name());
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "DELETE AUTHOR")
    @APIResponse(responseCode = "204",description = "Se eliminó el libro")
    @APIResponse(responseCode = "500", description = "Hay un problema con la elminación")
    public void delete( @PathParam("id") Long id ) {
        dao.deleteById(id);
    }
}
