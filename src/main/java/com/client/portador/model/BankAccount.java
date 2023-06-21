package com.client.portador.model;

import com.client.portador.utils.ValidationCustom;
import lombok.Builder;

public record BankAccount(
        String account,
        String agency,
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
