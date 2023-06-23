package com.client.portador.controller.request;

import java.util.UUID;
import lombok.Builder;

public record PortadorRequest(
        UUID clientId,
        UUID creditAnalysisId,
        BankAccountRequest bankAccount) {

    @Builder
    public PortadorRequest {
    }

    public record BankAccountRequest(
            String account,
            String agency,
            String bankCode
    ) {
        @Builder
        public BankAccountRequest {
        }
    }
}
