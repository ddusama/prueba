package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.svc.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "GET BOOK")
    @APIResponse(responseCode = "200", description = "Se encontró el libro", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @APIResponse(responseCode = "400", description = "Hay un problema con la búsqueda")
    public Book findById(@Parameter(description = "ID BOOK", required = true) @PathParam("id") Integer id)  {
        return bookService.listarUno(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "GET BOOKS")
    @APIResponse(responseCode = "200", description = "Se encontraron todos los libros", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Book.class)))
    @APIResponse(responseCode = "400", description = "Hay un problema con la búsqueda")
    public List<Book> findAll() {
        return bookService.listarTodos();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "CREATE BOOK")
    @APIResponse(responseCode = "201", description = "Se creó el libro")
    @APIResponse(responseCode = "500", description = "Hay un problema con la creación")
    public Response create(
            @RequestBody(description = "Datos del libro a insertar", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book b){
        bookService.crear(b);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(description = "UPDATE BOOK")
    @APIResponse(responseCode = "200", description = "Se actualizó el libro")
    @APIResponse(responseCode = "500", description = "Hay un problema con la actualización")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @RequestBody(description = "Datos del libro a actualizar", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book b,
            @Parameter(description = "ID BOOK", required = true)
            @PathParam("id") Integer id){
        bookService.actualizar(id, b);
        return Response.status((Response.Status.OK)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "DELETE BOOK")
    @APIResponse(responseCode = "204", description = "Se eliminó el libro")
    @APIResponse(responseCode = "500", description = "Hay un problema con la elminación")
    public Response delete(
            @Parameter(description = "ID BOOK", required = true)
            @PathParam("id") Integer id) {
        bookService.eliminar(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }
}
