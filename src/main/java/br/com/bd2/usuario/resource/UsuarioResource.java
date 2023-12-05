package br.com.bd2.usuario.resource;

import br.com.bd2.usuario.controller.UsuarioController;
import br.com.bd2.usuario.dto.UsuarioDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/database-user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioController usuarioController;

    @GET
    @Path("/current-user")
    public Response getCurrentUser() {
        String databaseUser = usuarioController.retrieveDatabaseUser();
        return Response.ok("Usuário do Banco de Dados Atual: " + databaseUser).build();
    }

    @POST
    @Path("/trocar-usuario")
    @Transactional
    public Response trocarUsuario(UsuarioDto usuarioDto) {

        String novoUsuarioAtual = usuarioController.changeDatabaseUser(usuarioDto.getUsuario(), usuarioDto.getSenha());
        return Response.ok("Novo usuário do Banco de Dados Atual: " + novoUsuarioAtual).build();
    }

    @POST
    @Path("/login")
    public Response create(UsuarioDto usuarioDto) {
        return Response.ok().entity(usuarioController.login(usuarioDto)).build();
    }
}
