package com.client.portador.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;

public record Card(
        BigDecimal limit,
        String cardNumber,
        Integer cvv,
        LocalDate dueDate,
        UUID idPortador
) {
    static final Integer DURACAO_DA_VALIDADE_EM_ANOS = 3;
    static final Integer TAMANHO_DIGITOS_DA_SEQUENCIA_DO_CARTAO = 15;
    static final Integer FIM_DA_SEQUENCIA = 10;
    @Builder(toBuilder = true)
    public Card(BigDecimal limit, String cardNumber, Integer cvv, LocalDate dueDate, UUID idPortador) {
        this.limit = limit;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dueDate = dueDate;
        this.idPortador = idPortador;
    }

    private LocalDate criarValidadeCartao() {
        final LocalDate agora = LocalDate.now();
        return agora.plusYears(DURACAO_DA_VALIDADE_EM_ANOS);
    }

    private void geradorNumeroCartao() {
        final StringBuilder sequencia = new StringBuilder();
        //        for (int x = 0; x < TAMANHO_DIGITOS_DA_SEQUENCIA_DO_CARTAO; x++) {
        //            sequencia.append(ThreadLocalRandom.current().nextInt(0, FIM_DA_SEQUENCIA));
        //        }
        sequencia.append("92763896");

        System.out.println("gerou isso dai " + sequencia);
        sequencia.reverse();
        System.out.println("gerou isso dai inverso " + sequencia);

        // fazer o calculo agora
        // transformando os numeros
        for (int x = 0; x < sequencia.length(); x += 2) {
            final Integer numeroAtual = Character.getNumericValue(sequencia.charAt(x));
            Integer numeroMultiplicado = numeroAtual * 2;
            if (numeroMultiplicado > 9) {
                numeroMultiplicado -= 9;
            }
            sequencia.setCharAt(x, Character.highSurrogate(numeroMultiplicado));
        }

    }

    public Card updateIdPortador(UUID idPortador) {
        final LocalDate dataValidade = criarValidadeCartao();
        geradorNumeroCartao();

        return this.toBuilder()
                .idPortador(idPortador)
                .dueDate(dataValidade)
                .build();
    }
}
