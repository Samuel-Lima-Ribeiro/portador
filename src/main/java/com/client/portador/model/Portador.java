package com.client.portador.model;

import com.client.portador.utils.Status;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

public record Portador(
        Status status,
        UUID clientId,
        UUID creditAnalysisId,
        BigDecimal limit,
        BankAccount bankAccount
) {
    @Builder(toBuilder = true)
    public Portador(Status status, UUID clientId, UUID creditAnalysisId, BigDecimal limit, BankAccount bankAccount) {
        this.status = status;
        this.limit = limit;
        this.creditAnalysisId = creditAnalysisId;
        this.clientId = clientId;
        this.bankAccount = bankAccount;
    }

    public Portador updateLimiteFrom(BigDecimal limit) {
        return this.toBuilder()
                .status(Status.ATIVO)
                .limit(limit).build();
    }
}
