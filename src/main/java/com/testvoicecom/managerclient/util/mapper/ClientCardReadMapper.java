package com.testvoicecom.managerclient.util.mapper;

import com.testvoicecom.managerclient.dto.ClientCardReadDto;
import com.testvoicecom.managerclient.dto.ManagerReadDto;
import com.testvoicecom.managerclient.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientCardReadMapper implements Mapper<Client, ClientCardReadDto> {
    private final ManagerReadMapper managerReadMapper;

    @Override
    public ClientCardReadDto map(Client object) {
        ManagerReadDto managerReadDto = Optional.ofNullable(object.getManager())
                .map(managerReadMapper::map)
                .orElse(null);


        return new ClientCardReadDto(
                object.getUuid(),
                object.getFullName(),
                object.getLegalAddress(),
                managerReadDto);
    }
}
