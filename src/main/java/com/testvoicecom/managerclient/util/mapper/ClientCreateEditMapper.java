package com.testvoicecom.managerclient.util.mapper;

import com.testvoicecom.managerclient.dto.ClientCreateEditDto;
import com.testvoicecom.managerclient.model.Client;
import com.testvoicecom.managerclient.model.Manager;
import com.testvoicecom.managerclient.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientCreateEditMapper implements Mapper<ClientCreateEditDto, Client> {
    private final ManagerRepository managerRepository;

    @Override
    public Client map(ClientCreateEditDto object) {
        Client client = new Client();
        client.setUuid(UUID.randomUUID());
        client.setFullName(object.getFullName());
        client.setLegalAddress(object.getLegalAddress());
        client.setManager(getManager(object.getManagerId()));
        return client;
    }


    public Client map(ClientCreateEditDto object, Client client) {
        client.setFullName(object.getFullName());
        client.setLegalAddress(object.getLegalAddress());
        return client;
    }

    private Manager getManager(Integer managerId) {
        return Optional.ofNullable(managerId)
                .flatMap(managerRepository::findById)
                .orElse(null);
    }
}
