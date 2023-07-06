package com.client.portador.controller.response;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

public record LimitUpdateResponse(
        UUID cardId,
        BigDecimal updatedLimit
) {
    @Builder
    public LimitUpdateResponse(UUID cardId, BigDecimal updatedLimit) {
        this.cardId = cardId;
        this.updatedLimit = updatedLimit;
    }
}
