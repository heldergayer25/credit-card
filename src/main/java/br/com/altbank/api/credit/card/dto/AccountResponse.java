package br.com.altbank.api.credit.card.dto;

import java.util.List;

import br.com.altbank.api.credit.card.domain.Account;
import br.com.altbank.api.credit.card.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountResponse {

    private Long id;
    private String accountNumber;
    private String agency;
    private CustomerResponse customer;
    private List<Card> cards;

    public AccountResponse(Account account) {
        this.id = account.id;
        this.accountNumber = account.getAccountNumber();
        this.agency = account.getAgency();
        this.customer = new CustomerResponse(account.getCustomer());
        this.cards = account.getCards();
    }

}
