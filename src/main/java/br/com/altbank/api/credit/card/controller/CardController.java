package br.com.altbank.api.credit.card.controller;

import br.com.altbank.api.credit.card.domain.Card;
import br.com.altbank.api.credit.card.dto.WebhookDTO;
import br.com.altbank.api.credit.card.exception.BusinessException;
import br.com.altbank.api.credit.card.service.CardService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Controller para operações relacionadas ao cartão físico e virtual.
 */
@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardController implements ICardController {

    // Módulo de serviço de cartões
    @Inject
    private CardService cardService;

    @Override
    public Response health() {
        return Response.ok("OK").build();
    }

    @Override
    public Response validateCard(@PathParam("cardId") Long cardId) {
        Card card = cardService.validateCard(cardId);
        return Response.ok(card).build();
    }
    
    @Override
    public Response reissueCard(@PathParam("cardId") Long cardId) {
        Card card = cardService.reissueCard(cardId);
        return Response.ok(card).build();
    }

    @Override
    public Response getVirtualCard(@PathParam("cardId") Long cardId) {
        Card card = cardService.getVirtualCard(cardId);
        return Response.ok(card).build();
    }

    @Override
    public Response processWebhook(WebhookDTO request) throws BusinessException {
        this.cardService.processWebhook(request);
        return Response.ok().build();
    }

}