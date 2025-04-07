package br.com.altbank.api.credit.card.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.altbank.api.credit.card.dto.AccountDTO;
import br.com.altbank.api.credit.card.dto.AccountResponse;
import br.com.altbank.api.credit.card.exception.BusinessException;
import br.com.altbank.api.credit.card.service.AccountService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Controller das contas e clientes.
 */
@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController implements IAccountController {

    // Módulo de serviço de contas
    @Inject
    private AccountService accountService;

    @Override
    public Response health() {
        return Response.ok("OK").build();
    }

    @Override    
    public Response createAccount(@Valid AccountDTO dto) throws BusinessException { 
        AccountResponse account = this.accountService.createAccount(dto);
        return Response.ok(account).build();        
    }    

    @Override
    public Response cancelAccount(@PathParam("document") String document) {
            this.accountService.cancelAccountByDocument(document);
            return Response.ok().build();
    }

}
