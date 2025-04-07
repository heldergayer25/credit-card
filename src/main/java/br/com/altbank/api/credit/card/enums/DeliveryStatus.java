package br.com.altbank.api.credit.card.enums;

/**
 * Enum que representa os status de entrega de um cartão.
 */
public enum DeliveryStatus {

    PENDING("Pendente"),
    IN_TRANSIT("Em trânsito"),
    DELIVERED("Entregue"),
    RETURNED("Devolvido"),
    CANCELED("Cancelado");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}