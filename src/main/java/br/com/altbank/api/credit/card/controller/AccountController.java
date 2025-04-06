package br.com.altbank.api.credit.card.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.altbank.api.credit.card.dto.AccountDTO;
import br.com.altbank.api.credit.card.dto.AccountResponse;
import br.com.altbank.api.credit.card.service.AccountService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
import lombok.extern.slf4j.Slf4j;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class AccountController {

    @Inject
    private AccountService accountService;

    @GET
    @Path("health")
    public Response health() throws Exception {
        //throw new Exception("Teste...");
        return Response.ok("OK").build();
    }

    @POST    
    public Response createAccount(@Valid AccountDTO dto) throws JsonProcessingException { 
        AccountResponse account = this.accountService.createAccount(dto);
        return Response.ok(account).build();        
    }    

    @DELETE
    @Path("/{document}")
    public Response cancelAccount(@PathParam("document") String document) {
            accountService.cancelAccountByDocument(document);
            return Response.ok().build();
    }

}
