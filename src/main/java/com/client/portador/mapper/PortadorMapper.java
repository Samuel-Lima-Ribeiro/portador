package com.client.portador.mapper;

import com.client.portador.controller.request.PortadorRequest;
import com.client.portador.model.BankAccount;
import com.client.portador.model.Portador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortadorMapper {

    Portador from(PortadorRequest portadorRequest);

    BankAccount from(PortadorRequest.BankAccountRequest bankAccountRequest);
}
