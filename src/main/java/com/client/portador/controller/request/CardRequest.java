package com.client.portador.controller.request;

import com.client.portador.utils.ValidationCustom;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Builder;

public record CardRequest(
        @Positive(message = "Limite deve maior que zero")
        BigDecimal limit
) {
    @Builder
    public CardRequest(BigDecimal limit) {
        this.limit = limit;

        ValidationCustom.validator(this);
    }
}
