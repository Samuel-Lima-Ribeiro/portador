package com.client.portador.service;

import com.client.portador.controller.request.CardRequest;
import com.client.portador.controller.response.CardResponse;
import com.client.portador.exception.CardHolderNotFoundException;
import com.client.portador.exception.LimitInvalidException;
import com.client.portador.repository.CardRepository;
import com.client.portador.repository.PortadorRepository;
import com.client.portador.repository.entity.CardEntity;
import com.client.portador.repository.entity.PortadorEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final PortadorRepository portadorRepository;
    private final CardRepository cardRepository;

    public CardResponse criarCartao(UUID idPortador, CardRequest cardRequest) {
        final BigDecimal limiteCartaoSolicitado = cardRequest.limit();

        final BigDecimal limitePortador = checarPortador(idPortador, limiteCartaoSolicitado);

        calcularLimiteUsado(idPortador, limiteCartaoSolicitado, limitePortador);
        // aq
        return null;
    }

    public BigDecimal checarPortador(UUID idPortador, BigDecimal limiteSolicitadoCartao) {
        final PortadorEntity portadorEntity = portadorRepository.findById(idPortador).orElseThrow(() ->
                new CardHolderNotFoundException("Portador do id %s não encontrado".formatted(idPortador)));

        final BigDecimal limitePortador = portadorEntity.getLimit();

        final Integer verificandoLimiteCartao = limiteSolicitadoCartao.compareTo(limitePortador);

        if (verificandoLimiteCartao > 0) {
            throw new LimitInvalidException("Limite solicitado ultrapassa limite do portador");
        }

        return limitePortador;
    }

    public void calcularLimiteUsado(UUID idPortador, BigDecimal limiteCartaoSolicitado, BigDecimal limitePortador) {
        final List<CardEntity> cardEntities = cardRepository.findByIdPortador(idPortador);

        final BigDecimal limiteUsado = cardEntities.stream()
                .map(CardEntity::getLimit)
                .reduce(limiteCartaoSolicitado, BigDecimal::add);

        final Integer verificandoLimiteUsado = limiteUsado.compareTo(limitePortador);

        if (verificandoLimiteUsado > 0) {
            throw new LimitInvalidException("Soma dos limites de cartões ultrapassa limite do portador");
        }

        System.out.println("Teu limite " + limiteUsado);
    }
}
