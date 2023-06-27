//package com.client.portador.model;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//public class teste {
//    public static void main(String[] args) {
//        geradorNumeroCartao();
//    }
//    final static Integer TAMANHO_DIGITOS_DA_SEQUENCIA_DO_CARTAO = 15;
//    final static Integer FIM_DA_SEQUENCIA = 11;
//    private static void geradorNumeroCartao() {
//        final StringBuilder sequencia = new StringBuilder();
//        Integer somaNumeros = 0;
//                for (int x = 0; x < TAMANHO_DIGITOS_DA_SEQUENCIA_DO_CARTAO; x++) {
//                    sequencia.append(ThreadLocalRandom.current().nextInt(0, FIM_DA_SEQUENCIA));
//                }
////        sequencia.append("491664185936908");
//
//        System.out.println("gerou isso dai " + sequencia);
//        sequencia.reverse();
//        System.out.println("gerou isso dai inverso " + sequencia);
//
//        // fazer o calculo agora
//        // transformando os numeros
//        for (int x = 0; x < sequencia.length(); x++) {
//            Integer numeroAtual = Character.getNumericValue(sequencia.charAt(x));
//
//            if (x % 2 == 0) {
//                numeroAtual *= 2;
//
//                if (numeroAtual > 9) {
//                    numeroAtual -= 9;
//                }
//            }
//            somaNumeros += numeroAtual;
//            //            sequencia.setCharAt(x, Character.highSurrogate(numeroMultiplicado));
//        }
//
//        final int multiploDez = (int) Math.ceil((double) somaNumeros / 10) * 10;
//        final int digitoVerificador = multiploDez - somaNumeros;
//
//
//        System.out.println("Soma deu " + somaNumeros);
//        System.out.println("Número verificador " + digitoVerificador);
//    }
//}
