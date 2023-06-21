package com.client.portador.model;

import lombok.Builder;

public record Portador(
        String status,
        Double limit,
        BankAccount bankAccount
) {
    @Builder(toBuilder = true)
    public Portador(String status, Double limit, BankAccount bankAccount) {
        this.status = status;
        this.limit = limit;
        this.bankAccount = bankAccount;
    }

    public Portador updateLimiteFrom(Double limit) {
        return this.toBuilder()
                .status("ativo")
                .limit(limit).build();
    }
}
