package com.testvoicecom.managerclient.util.mapper;

import com.testvoicecom.managerclient.dto.ClienFullInfoReadDto;
import com.testvoicecom.managerclient.dto.ManagerCardReadDto;
import com.testvoicecom.managerclient.model.Client;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientFullInfoReadMapper implements Mapper<Client, ClienFullInfoReadDto> {

    @Override
    public ClienFullInfoReadDto map(Client object) {
        ManagerCardReadDto managerWithAlternateReadDto = Optional.ofNullable(object.getManager())
                .map(manager -> {
                    ManagerCardReadDto managerWithAlternate = new ManagerCardReadDto();
                    managerWithAlternate.setFullName(manager.getFullName());
                    managerWithAlternate.setPhoneNumber(manager.getPhoneNumber());
                    String alternatePhoneNumber = Optional.ofNullable(manager.getAlternate())
                            .map(alternate -> alternate.getPhoneNumber())
                            .orElse(null);
                    managerWithAlternate.setAlternatePhoneNumber(alternatePhoneNumber);
                    return managerWithAlternate;
                })
                .orElse(null);


        return new ClienFullInfoReadDto(
                object.getUuid(),
                object.getFullName(),
                object.getLegalAddress(),
                managerWithAlternateReadDto);
    }
}
