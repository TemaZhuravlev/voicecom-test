package com.testvoicecom.managerclient.util.mapper;

import com.testvoicecom.managerclient.dto.ManagerFullInfoReadDto;
import com.testvoicecom.managerclient.dto.ManagerReadDto;
import com.testvoicecom.managerclient.model.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ManagerFullInfoMapper implements Mapper<Manager, ManagerFullInfoReadDto> {
    private final ManagerReadMapper managerReadMapper;

    @Override
    public ManagerFullInfoReadDto map(Manager object) {
        ManagerReadDto managerReadDto = Optional.ofNullable(object.getAlternate())
                .map(managerReadMapper::map)
                .orElse(null);

        return new ManagerFullInfoReadDto(
                object.getFullName(),
                object.getPhoneNumber(),
                managerReadDto);
    }
}
