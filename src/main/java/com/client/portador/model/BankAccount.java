package com.client.portador.model;

import com.client.portador.utils.ValidationCustom;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record BankAccount(
        @NotBlank
        String account,
        @NotBlank
        String agency,
        @NotBlank
        String bankCode
) {
    @Builder
    public BankAccount(String account, String agency, String bankCode) {
        this.account = account;
        this.agency = agency;
        this.bankCode = bankCode;
        ValidationCustom.validator(this);
    }
}
