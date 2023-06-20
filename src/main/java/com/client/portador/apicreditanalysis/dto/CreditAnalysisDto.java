package com.client.portador.apicreditanalysis.dto;

import java.util.UUID;

public record CreditAnalysisDto(
        UUID id,
        UUID clientId,
        Double approvedLimit
) {
}
