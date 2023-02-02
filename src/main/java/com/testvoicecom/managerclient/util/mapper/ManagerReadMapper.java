package com.testvoicecom.managerclient.util.mapper;

import com.testvoicecom.managerclient.dto.ManagerReadDto;
import com.testvoicecom.managerclient.model.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerReadMapper implements Mapper<Manager, ManagerReadDto> {
    @Override
    public ManagerReadDto map(Manager object) {
        return new ManagerReadDto(
                object.getFullName(),
                object.getPhoneNumber());
    }
}
