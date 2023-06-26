package com.client.portador.apicreditanalysis.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

public record CreditAnalysisDto(
        UUID id,
        UUID clientId,
        BigDecimal approvedLimit,
        Boolean approved
) {
    @Builder(toBuilder = true)
    public CreditAnalysisDto(UUID id, UUID clientId, BigDecimal approvedLimit, Boolean approved) {
        this.id = id;
        this.clientId = clientId;
        this.approvedLimit = approvedLimit;
        this.approved = approved;
    }
}
