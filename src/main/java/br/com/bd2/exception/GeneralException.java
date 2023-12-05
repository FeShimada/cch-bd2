package br.com.bd2.exception;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GeneralException implements ExceptionMapper<Exception> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Exception exception) {

        MessageError messageError = new MessageError();
        
        if(exception instanceof EstoqueException) {
            messageError.setMessage(exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(messageError).build();
        }

        if(exception instanceof PermitionException) {
            messageError.setMessage(exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(messageError).build();
        }

        messageError.setMessage("Erro não mapeado");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(messageError).build();
    }
    
}
