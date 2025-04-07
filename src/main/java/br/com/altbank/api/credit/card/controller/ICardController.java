package br.com.altbank.api.credit.card.controller;

import br.com.altbank.api.credit.card.dto.WebhookDTO;
import br.com.altbank.api.credit.card.exception.BusinessException;
import br.com.altbank.api.credit.card.filter.ApiFilter;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

/**
 * Interface de controle da API de cartões.
 */
public interface ICardController {
    
    /**
     * Endpoint para monitoramento da API de cartões.
     * @return Resposta de sucesso.
     */
    @GET
    @Path("health")
    public Response health();

    /**
     * Valida o cartão físico.
     * @param cardId ID do cartão.
     * @return Resposta com o cartão validado.
     */
    @PUT
    @Path("/{cardId}/validate")
    public Response validateCard(@PathParam("cardId") Long cardId);

    /**
     * Reemite um cartão.
     * @param cardId ID do cartão.
     * @return Resposta com o novo cartão emitido.
     */
    @PUT
    @Path("/{cardId}/reissue")
    public Response reissueCard(@PathParam("cardId") Long cardId);

    /**
     * Obtém um cartão virtual pelo ID.
     * @param cardId ID do cartão.
     * @return Resposta com o cartão virtual.
     */
    @GET
    @Path("/{cardId}/virtual")
    public Response getVirtualCard(@PathParam("cardId") Long cardId);

    /**
     * Atualiza o status de entrega do cartão.
     * @param request Dados de entrega do cartão.
     * @return Dados do cartão.
     * @throws BusinessException Em caso de erro de negócio.
     */
    @ApiFilter
    @POST
    @Path("/webhooks/delivery")
    @Transactional
    public Response processWebhook(WebhookDTO request) throws BusinessException;

}
