package com.client.portador.service;

import com.client.portador.apicreditanalysis.ApiCreditAnalysis;
import com.client.portador.apicreditanalysis.dto.CreditAnalysisDto;
import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.controller.response.PortadorResponse;
import com.client.portador.exception.ClientInvalidException;
import com.client.portador.exception.CreditAnalysisNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortadorService {

    private final ApiCreditAnalysis apiCreditAnalysis;

    public PortadorResponse criandoPortador(PortadorRequest portadorRequest) {
        checarAnaliseCredito(portadorRequest);
        return null;
    }

    public Double checarAnaliseCredito(PortadorRequest portador) {
        final CreditAnalysisDto dto = apiCreditAnalysis.getCreditAnalysis(portador.creditAnalysisId());

        if (dto.id() == null) {
            throw new CreditAnalysisNotFoundException("Credit Analysis not found by id %s".formatted(portador.creditAnalysisId()));
        } else if (!dto.clientId().equals(portador.clientId())) {
            throw new ClientInvalidException("Invalid client by id %s".formatted(portador.clientId()));
        }
        return dto.approvedLimit();
    }
}
