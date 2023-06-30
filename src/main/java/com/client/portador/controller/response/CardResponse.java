package com.client.portador.controller.response;

import java.time.LocalDate;
import java.util.UUID;

public record CardResponse(
        UUID cardId,
        String cardNumber,
        Integer cvv,
        LocalDate dueDate
) {
}
