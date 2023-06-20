package com.client.portador.controller;

import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.controller.response.PortadorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "v1.0/card-holders")
public class PortadorController {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PortadorResponse createPortador(@RequestBody PortadorRequest portadorRequest) {
        return null;
    }
}
