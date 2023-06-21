package com.client.portador.service;

import com.client.portador.apicreditanalysis.ApiCreditAnalysis;
import com.client.portador.apicreditanalysis.dto.CreditAnalysisDto;
import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.controller.response.PortadorResponse;
import com.client.portador.exception.ClientInvalidException;
import com.client.portador.exception.CreditAnalysisDeniedException;
import com.client.portador.exception.CreditAnalysisNotFoundException;
import com.client.portador.mapper.PortadorEntityMapper;
import com.client.portador.mapper.PortadorMapper;
import com.client.portador.model.Portador;
import com.client.portador.repository.PortadorRepository;
import com.client.portador.repository.entity.PortadorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortadorService {

    private final ApiCreditAnalysis apiCreditAnalysis;
    private final PortadorRepository portadorRepository;
    private final PortadorMapper portadorMapper;
    private final PortadorEntityMapper portadorEntityMapper;

    public PortadorResponse criandoPortador(PortadorRequest portadorRequest) {
        // aq
        System.out.println(portadorRequest);
        final Double limite = checarAnaliseCredito(portadorRequest);
        //aq
        System.out.println(limite);
        // mapear agora, envio o request junto com o limit
        final Portador portador = portadorMapper.from(portadorRequest);
        System.out.println(portador + " ele ");

        // atualizar
        final Portador portadorLimiteUpdate = portador.updateLimiteFrom(limite);
        System.out.println("Atualizado " + portadorLimiteUpdate);

        // Salvar
        final PortadorEntity portadorEntity = portadorEntityMapper.from(portadorLimiteUpdate);
        System.out.println("Entity " + portadorEntity);


        //        salvado
        final PortadorEntity portadorSalvado = portadorRepository.save(portadorEntity);
        System.out.println("salvado " + portadorSalvado);

        return null;
    }

    public Double checarAnaliseCredito(PortadorRequest portador) {
        final CreditAnalysisDto dto = apiCreditAnalysis.getCreditAnalysis(portador.creditAnalysisId());

        if (dto.id() == null) {
            throw new CreditAnalysisNotFoundException("Credit Analysis not found by id %s".formatted(portador.creditAnalysisId()));
        } else if (!dto.clientId().equals(portador.clientId())) {
            // falar que o idClient nao corresponde ao id mostrado da analise
            throw new ClientInvalidException("Invalid client by id %s".formatted(portador.clientId()));
        } else if (!dto.approved()) {
            //melhorar isso
            throw new CreditAnalysisDeniedException("Credit Analysis denied by id %s".formatted(portador.creditAnalysisId()));
        }
        return dto.approvedLimit();
    }
}
