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
    static final Integer DURACAO_DA_VALIDADE_EM_ANOS = 5;
    static final Integer TAMANHO_DIGITOS_ALEATORIO_DO_CARTAO = 14;
    static final Integer MAXIMO_NUMERO_ALEATORIO = 10;
    static final Integer PRIMEIRO_DIGITO = 7;
    static final Integer VALIDOR_NUMERO_PAR = 2;
    static final Integer MULTIPLO_DOIS = 2;
    static final Integer VALIDOR_MAIOR_QUE_NOVE = 9;
    static final Integer SUBTRAIR_NOVE = 9;

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

    private String geradorNumeroCartao() {
        final StringBuilder sequencia = new StringBuilder();
        Integer somaNumeros = 0;

        sequencia.append(PRIMEIRO_DIGITO);
        for (int x = 0; x < TAMANHO_DIGITOS_ALEATORIO_DO_CARTAO; x++) {
            sequencia.append(ThreadLocalRandom.current().nextInt(0, MAXIMO_NUMERO_ALEATORIO));
        }

        // aq
        System.out.println("gerou isso dai " + sequencia);

        sequencia.reverse();

        // aq
        System.out.println("gerou isso dai inverso " + sequencia);

        for (int x = 0; x < sequencia.length(); x++) {
            Integer numeroAtual = Character.getNumericValue(sequencia.charAt(x));

            if (x % VALIDOR_NUMERO_PAR == 0) {
                numeroAtual *= MULTIPLO_DOIS;

                if (numeroAtual > VALIDOR_MAIOR_QUE_NOVE) {
                    numeroAtual -= SUBTRAIR_NOVE;
                }
            }
            somaNumeros += numeroAtual;
        }
        sequencia.reverse();

        final int multiploDez = (int) Math.ceil((double) somaNumeros / 10) * 10;
        final int digitoVerificador = multiploDez - somaNumeros;

        sequencia.append(digitoVerificador);

        // aq
        System.out.println("Soma deu " + somaNumeros);
        // aq
        System.out.println("Número verificador " + digitoVerificador);

        return String.valueOf(sequencia);
    }

    public Card updateIdPortador(UUID idPortador) {
        final LocalDate dataValidade = criarValidadeCartao();
        final String numeroCartao = geradorNumeroCartao();
        final Integer cvvGerado = 2;

        return this.toBuilder()
                .idPortador(idPortador)
                .cvv(cvvGerado)
                .cardNumber(numeroCartao)
                .dueDate(dataValidade)
                .build();
    }
}
