package br.com.altbank.api.credit.card.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Data
//@Builder(toBuilder = true)
@Entity
@Table(name="customer")
public class Customer extends PanacheEntity {

    @NotBlank(message = "Customer name is required")    
    public String name;
    @Email(message = "Invalid email format")
    public String email;
    @NotBlank(message = "Document is required")
    public String document;

    //@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    //public Account account;
}
