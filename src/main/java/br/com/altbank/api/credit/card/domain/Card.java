package br.com.altbank.api.credit.card.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name="card")
public class Card extends PanacheEntity {
    
    public String cardNumber;
    public String type; // PHYSICAL ou VIRTUAL
    public String cvv;
    public LocalDateTime cvvExpiration;
    public boolean active = true;
    public boolean validated = false;

    public String trackingId;
    public String deliveryStatus;

    @JsonIgnore
    @ManyToOne
    public Account account;
}