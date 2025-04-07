package br.com.altbank.api.credit.card.service;

import java.time.LocalDateTime;

import br.com.altbank.api.credit.card.domain.Card;
import br.com.altbank.api.credit.card.dto.WebhookDTO;
import br.com.altbank.api.credit.card.exception.BusinessException;
import br.com.altbank.api.credit.card.repository.CardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

/**
 * Serviço para operações relacionadas ao cartão.
 */
@ApplicationScoped
public class CardService {

    @Inject
    private CardRepository cardRepository;

    /**
     * Valida um cartão.
     *
     * @param cardId ID do cartão.
     * @return O cartão validado.
     */
    @Transactional
    public Card validateCard(Long cardId) {
        Card card = cardRepository.findById(cardId);
        card.validated = true;
        cardRepository.persist(card);
        return card;
    }

    /**
     * Reemite um cartão.
     *
     * @param cardId ID do cartão.
     * @return O novo cartão emitido.
     */
    @Transactional
    public Card reissueCard(Long cardId) {
        Card card = cardRepository.findById(cardId);
        Card newCard = new Card();
        newCard.account = card.account;
        newCard.type = card.type;
        newCard.active = true;
        cardRepository.persist(newCard);
        return newCard;
    }

    /**
     * Atualiza o CVV de um cartão.
     *
     * @param cardId ID do cartão.
     * @param newCvv Novo CVV.
     * @param expirationDate Data de expiração do CVV.
     * @return O cartão com o CVV atualizado.
     */
    @Transactional
    public Card updateCvv(Long cardId, String newCvv, LocalDateTime expirationDate) {
        Card card = cardRepository.findById(cardId);
        card.cvv = newCvv;
        card.cvvExpiration = expirationDate;
        cardRepository.persist(card);
        return card;
    }

    /**
     * Obtém um cartão virtual pelo ID.
     *
     * @param cardId ID do cartão.
     * @return O cartão virtual.
     */
    public Card getVirtualCard(Long cardId) {
        return cardRepository.findById(cardId);
    }

    @Transactional
    public void processWebhook(WebhookDTO request) throws BusinessException {
        Card card = Card.find("trackingId", request.getTrackingId()).firstResult();
        if (card != null) {
            card.setDeliveryStatus(request.getDeliveryStatus());
            card.persist();
        } else {
            throw new BusinessException("Cartão não encontrado");
        }
    }
}