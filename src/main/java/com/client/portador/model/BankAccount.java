package com.client.portador.model;

import com.client.portador.utils.ValidationCustom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

public record BankAccount(
        //        @Pattern(regexp = , message = "")
        @NotBlank
        String account,
        // ta passando tudo null
        @Pattern(regexp = "\\d{4}", message = "agência deve conter 4 digitos e apenas números")
        @NotBlank
        String agency,
        @Pattern(regexp = "\\d{3}", message = "código bancário deve conter 3 digitos e apenas números")
        @NotBlank
        String bankCode
) {
    @Builder
    public BankAccount(String account, String agency, String bankCode) {
        // se tudo tiver vazio builda null,
        this.account = account;
        this.agency = agency;
        this.bankCode = bankCode;
        ValidationCustom.validator(this);
    }
}
