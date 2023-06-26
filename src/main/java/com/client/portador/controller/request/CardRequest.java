package com.client.portador.controller.request;

import java.math.BigDecimal;

public record CardRequest(
        BigDecimal limit
) {
}
