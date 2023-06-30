package com.client.portador.mapper;

import com.client.portador.model.BankAccount;
import com.client.portador.model.Portador;
import com.client.portador.repository.entity.BankAccountEntity;
import com.client.portador.repository.entity.PortadorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortadorEntityMapper {
    PortadorEntity from(Portador portador);

    BankAccountEntity from(BankAccount bankAccount);
}
