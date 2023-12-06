package br.com.bd2.funcionario.resource;

import java.util.UUID;

import br.com.bd2.funcionario.controller.FuncionarioController;
import br.com.bd2.funcionario.dto.FuncionarioDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/funcionario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    
    @Inject
    FuncionarioController funcionarioController;

    @POST
    public Response create(FuncionarioDto funcionarioDto) {
        return Response.ok().entity(funcionarioController.create(funcionarioDto)).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveById(@PathParam("id") String uuid) {
        return Response.ok().entity(funcionarioController.retrieve(UUID.fromString(uuid))).build();
    }

    @GET
    public Response retrieveAll() {
        return Response.ok().entity(funcionarioController.retrieveAll()).build();
    }

    @PUT
    public Response update(FuncionarioDto funcionarioDto) {
        return Response.ok().entity(funcionarioController.update(funcionarioDto)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String uuid) {
        if (funcionarioController.delete(UUID.fromString(uuid))) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }

}
