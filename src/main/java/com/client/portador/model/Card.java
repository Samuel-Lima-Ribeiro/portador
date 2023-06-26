package com.client.portador.model;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Card(
        @Positive
        BigDecimal limit,
        String cardNumber,
        Integer cvv,
        LocalDateTime dueDate
) {
}
