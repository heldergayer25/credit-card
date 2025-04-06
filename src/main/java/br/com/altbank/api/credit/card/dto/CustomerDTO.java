package br.com.altbank.api.credit.card.dto;

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
public class CustomerDTO {

    @NotBlank(message = "Name cannot be blank")
    public String name;

    @NotBlank(message = "Document cannot be blank")
    public String document;

    @Email(message = "Invalid email format")
    public String email;

}