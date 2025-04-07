package br.com.altbank.api.credit.card.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import br.com.altbank.api.credit.card.dto.AccountDTO;
import br.com.altbank.api.credit.card.exception.BusinessException;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface IAccountController {

    /**
     * Endpoint para monitoramento da API de contas.
     * @return Resposta de sucesso.
     */
    @GET
    @Path("/health")
    public Response health();

    /**
     * Cria uma nova conta com cartão para o cliente.
     * @param dto Dados do cliente.
     * @return Dados da conta e cartão.
     * @throws BusinessException Em caso de erro de negócio.
     */
    @Operation(summary = "Criar conta", description = "Cria uma nova conta com cartão para o cliente.")
    @APIResponse(responseCode = "201", description = "Conta criada com sucesso", content = @Content(schema = @Schema(implementation = AccountDTO.class)))
    @APIResponse(responseCode = "400", description = "Dados inválidos")
    @POST    
    public Response createAccount(@Valid AccountDTO dto) throws BusinessException;

    /**
     * Cancela uma conta e seus cartões com base no documento do cliente.
     * @param document CPF do cliente.
     * @return Resposta de sucesso.
     */
    @Operation(summary = "Cancelar conta", description = "Cancela uma conta e seus cartões.")
    @APIResponse(responseCode = "200", description = "Conta cancelada com sucesso")
    @APIResponse(responseCode = "400", description = "Dados inválidos")
    @DELETE
    @Path("/{document}")
    public Response cancelAccount(@PathParam("document") String document);

}
