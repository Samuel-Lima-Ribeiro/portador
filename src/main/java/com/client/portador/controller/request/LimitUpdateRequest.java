package com.client.portador.controller.request;

import com.client.portador.utils.ValidationCustom;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Builder;

public record LimitUpdateRequest(
        @Positive
        BigDecimal limit) {
    @Builder
    public LimitUpdateRequest(
            BigDecimal limit) {
        this.limit = limit;
        ValidationCustom.validator(this);
    }
}
