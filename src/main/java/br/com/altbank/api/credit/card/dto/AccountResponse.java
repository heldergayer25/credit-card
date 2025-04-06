package br.com.altbank.api.credit.card.dto;

import br.com.altbank.api.credit.card.domain.Account;
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

    public AccountResponse(Account account) {
        this.id = account.id;
        this.accountNumber = account.getAccountNumber();
        this.agency = account.getAgency();
    }

}
