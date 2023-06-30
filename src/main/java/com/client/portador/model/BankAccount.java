package com.client.portador.model;


import com.client.portador.utils.ValidationCustom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

public record BankAccount(
        @Pattern(regexp = "^[0-9]{4,9}(-)?[0-9XP]$", message = "conta deve conter de 4 a 5 a 10 digitos")
        @NotBlank(message = "conta é obrigatório")
        String account,
        @Pattern(regexp = "\\d{4}", message = "agência deve conter 4 digitos e apenas números")
        @NotBlank(message = "agência é obrigatório")
        String agency,
        @Pattern(regexp = "\\d{3}", message = "código bancário deve conter 3 digitos e apenas números")
        @NotBlank(message = "código de bancário é obrigatório")
        String bankCode
) {
    @Builder(toBuilder = true)
    public BankAccount(String account, String agency, String bankCode) {
        this.account = account;
        this.agency = agency;
        this.bankCode = bankCode;

        ValidationCustom.validator(this);
    }
}
