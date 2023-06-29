package com.client.portador.controller.request;

import com.client.portador.utils.ValidationCustom;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record LimitUpdateRequest(
        @Positive
        BigDecimal limit) {
    public LimitUpdateRequest(
            BigDecimal limit) {
        this.limit = limit;
        ValidationCustom.validator(this);
    }
}
