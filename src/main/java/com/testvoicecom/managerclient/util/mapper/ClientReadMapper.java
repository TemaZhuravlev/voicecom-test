package com.testvoicecom.managerclient.util.mapper;

import com.testvoicecom.managerclient.dto.ClientReadDto;
import com.testvoicecom.managerclient.dto.ManagerReadDto;
import com.testvoicecom.managerclient.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientReadMapper implements Mapper<Client, ClientReadDto> {
    private final ManagerReadMapper managerReadMapper;

    @Override
    public ClientReadDto map(Client object) {
        ManagerReadDto managerReadDto = Optional.ofNullable(object.getManager())
                .map(manager -> managerReadMapper.map(manager))
                .orElse(null);

        return new ClientReadDto(
                object.getUuid(),
                object.getFullName(),
                object.getLegalAddress());
    }
}
