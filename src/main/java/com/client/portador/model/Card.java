package com.client.portador.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Builder;

public record Card(
        BigDecimal limit,
        String cardNumber,
        Integer cvv,
        LocalDate dueDate,
        UUID idPortador
) {
    @Builder(toBuilder = true)
    public Card(BigDecimal limit, String cardNumber, Integer cvv, LocalDate dueDate, UUID idPortador) {
        this.limit = limit;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dueDate = dueDate;
        this.idPortador = idPortador;
    }

    static final Integer DURACAO_DA_VALIDADE_EM_ANOS = 3;
    static final Integer TAMANHO_DIGITOS_DA_SEQUENCIA_DO_CARTAO = 15;

    private LocalDate criarValidadeCartao() {
        final LocalDate agora = LocalDate.now();
        return agora.plusYears(DURACAO_DA_VALIDADE_EM_ANOS);
    }

    private void geradorNumeroCartao() {
        final StringBuilder sequencia = new StringBuilder();
        for (int x = 0; x < TAMANHO_DIGITOS_DA_SEQUENCIA_DO_CARTAO; x++) {
            ThreadLocalRandom.current().ints(0, 11);
        }
    }

    public Card updateIdPortador(UUID idPortador) {
        final LocalDate dataValidade = criarValidadeCartao();

        System.out.println("has " + dataValidade.hashCode());

        return this.toBuilder()
                .idPortador(idPortador)
                .dueDate(dataValidade)
                .build();
    }
}
