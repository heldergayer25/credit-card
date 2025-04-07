package br.com.altbank.api.credit.card.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Classe para tratamento das exceções de regras de negócio.
 */
@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode error = mapper.createObjectNode();
        error.put("message", exception.getMessage());
        error.put("code", Response.Status.BAD_REQUEST.getStatusCode()); // Código de erro opcional

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error.toString())
                .build();
    }
}
