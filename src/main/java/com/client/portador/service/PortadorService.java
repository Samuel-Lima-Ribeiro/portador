package com.client.portador.service;

import com.client.portador.apicreditanalysis.ApiCreditAnalysis;
import com.client.portador.apicreditanalysis.dto.CreditAnalysisDto;
import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.controller.response.PortadorResponse;
import com.client.portador.exception.CardHolderAlreadyExistException;
import com.client.portador.exception.ClientInvalidException;
import com.client.portador.exception.CreditAnalysisDeniedException;
import com.client.portador.exception.CreditAnalysisNotFoundException;
import com.client.portador.mapper.PortadorEntityMapper;
import com.client.portador.mapper.PortadorMapper;
import com.client.portador.mapper.PortadorResponseMapper;
import com.client.portador.model.Portador;
import com.client.portador.repository.PortadorRepository;
import com.client.portador.repository.entity.PortadorEntity;
import com.client.portador.utils.Status;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortadorService {

    private final ApiCreditAnalysis apiCreditAnalysis;
    private final PortadorRepository portadorRepository;
    private final PortadorMapper portadorMapper;
    private final PortadorEntityMapper portadorEntityMapper;
    private final PortadorResponseMapper portadorResponseMapper;

    public PortadorResponse criarPortador(PortadorRequest portadorRequest) {
        final Double limite = checarAnaliseCredito(portadorRequest);

        final Portador portador = portadorMapper.from(portadorRequest);

        final Portador portadorLimiteUpdate = portador.updateLimiteFrom(limite);

        final PortadorEntity portadorEntity = portadorEntityMapper.from(portadorLimiteUpdate);

        final PortadorEntity portadorSalvado = savePortador(portadorEntity);

        return portadorResponseMapper.from(portadorSalvado);
    }

    public PortadorEntity savePortador(PortadorEntity portadorEntity) {
        final PortadorEntity portadorSalvado;
        try {
            portadorSalvado = portadorRepository.save(portadorEntity);
        } catch (DataIntegrityViolationException exception) {
            throw new CardHolderAlreadyExistException("Portador já cadastrado, verifique os dados enviados para o registro");
        }
        return portadorSalvado;
    }

    public Double checarAnaliseCredito(PortadorRequest portador) {
        final CreditAnalysisDto dto = apiCreditAnalysis.getCreditAnalysis(portador.creditAnalysisId());

        if (dto.id() == null) {
            throw new CreditAnalysisNotFoundException("Análise de Crédito não encontrada pelo id %s".formatted(portador.creditAnalysisId()));
        } else if (!dto.clientId().equals(portador.clientId())) {
            throw new ClientInvalidException("Cliente do id %s não corresponde ao cliente da Análise".formatted(portador.clientId()));
        } else if (!dto.approved()) {
            throw new CreditAnalysisDeniedException(("Análise de Crédito do id %s não aprovada"
                    + ", não há limite para calculo").formatted(portador.creditAnalysisId()));
        }
        return dto.approvedLimit();
    }

    public List<PortadorResponse> getAllPortadoresBy(Status status) {
        final List<PortadorEntity> portadorEntities;

        if (status != null) {
            portadorEntities = portadorRepository.getAllPortadoresByStatus(status);
        } else {
            portadorEntities = portadorRepository.findAll();
        }

        return portadorEntities.stream()
                .map(portadorResponseMapper::from)
                .collect(Collectors.toList());
    }
}
