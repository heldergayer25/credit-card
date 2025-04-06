package br.com.altbank.api.credit.card.repository;

import br.com.altbank.api.credit.card.domain.Card;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CardRepository implements PanacheRepository<Card> {
    
}