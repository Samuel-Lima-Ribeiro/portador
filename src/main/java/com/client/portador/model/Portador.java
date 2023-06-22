package com.client.portador.model;

import com.client.portador.utils.Status;
import lombok.Builder;

public record Portador(
        Status status,
        Double limit,
        BankAccount bankAccount
) {
    @Builder(toBuilder = true)
    public Portador(Status status, Double limit, BankAccount bankAccount) {
        this.status = status;
        this.limit = limit;
        this.bankAccount = bankAccount;
    }

    public Portador updateLimiteFrom(Double limit) {
        return this.toBuilder()
                .status(Status.ATIVO)
                .limit(limit).build();
    }
}
