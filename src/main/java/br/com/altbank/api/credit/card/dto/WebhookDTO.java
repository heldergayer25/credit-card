package br.com.altbank.api.credit.card.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebhookDTO {

    @NotBlank(message = "Tracking ID is required")
    private String trackingId;

    @NotBlank(message = "Delivery status is required")
    private String deliveryStatus;

}
