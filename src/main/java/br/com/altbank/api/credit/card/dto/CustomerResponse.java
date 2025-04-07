package br.com.altbank.api.credit.card.dto;

import br.com.altbank.api.credit.card.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponse {

    public String name;
    public String document;
    public String email;

    public CustomerResponse(Customer customer) {
        this.name = customer.getName();
        this.document = customer.getDocument();
        this.email = customer.getEmail();
    }

}
