package com.client.portador.service;

import com.client.portador.apicreditanalysis.ApiCreditAnalysis;
import com.client.portador.apicreditanalysis.dto.CreditAnalysisDto;
import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.controller.response.PortadorResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortadorService {

    private final ApiCreditAnalysis apiCreditAnalysis;

    public PortadorResponse criandoPortador(PortadorRequest portadorRequest) {
        final CreditAnalysisDto dto = apiCreditAnalysis.getCreditAnalysis(UUID.fromString("f26d4ff8-baa0-4a95-99eb-a4288f3e7105"));
        System.out.println(dto);
        return null;
    }
}
