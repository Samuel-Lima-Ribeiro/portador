package com.client.portador.controller.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PortadorResponse(
        UUID cardHolderId,
        String status,
        BigDecimal limit,
        LocalDateTime createdAt

) {
}
