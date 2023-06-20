package com.client.portador.apicreditanalysis;

import com.client.portador.apicreditanalysis.dto.CreditAnalysisDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "creditAnalysis", url = "${url.creditanalysis.host}")
public interface ApiCreditAnalysis {

    @GetMapping(path = "/{id}")
    CreditAnalysisDto getCreditAnalysis(@PathVariable (value = "id")UUID id);
}
