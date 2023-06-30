package com.client.portador.controller.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record PortadorResponse(
        UUID cardHolderId,
        String status,
        Double limit,
        LocalDateTime createdAt

) {
}
