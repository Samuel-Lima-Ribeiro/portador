package com.client.portador.controller.request;

import java.util.UUID;

public record PortadorRequest(
        UUID clientId,
        UUID creditAnalysisId,
        BankAccountRequest bankAccount) {

    public record BankAccountRequest(
            // Verificar se são string ou números
            String account,
            String agency,
            String bankCode
    ) {

    }
}
