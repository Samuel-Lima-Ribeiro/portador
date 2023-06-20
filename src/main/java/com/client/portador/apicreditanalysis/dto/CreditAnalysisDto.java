package com.client.portador.apicreditanalysis.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreditAnalysisDto(
        UUID id,
        UUID clientId,
        BigDecimal approvedLimit
) {
}
