package com.testvoicecom.managerclient.util.mapper;

import com.testvoicecom.managerclient.dto.ManagerCreateEditDto;
import com.testvoicecom.managerclient.model.Manager;
import com.testvoicecom.managerclient.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ManagerCreateEditMapper implements Mapper<ManagerCreateEditDto, Manager> {
    private final ManagerRepository managerRepository;

    @Override
    public Manager map(ManagerCreateEditDto object) {
        Manager manager = new Manager();
        copy(object, manager);
        return manager;
    }

    public Manager map(ManagerCreateEditDto object, Manager manager) {
        copy(object, manager);
        return manager;
    }

    private void copy(ManagerCreateEditDto object, Manager manager) {
        manager.setFullName(object.getFullName());
        manager.setPhoneNumber(object.getPhoneNumber());
        manager.setAlternate(getManager(object.getAlternateManagerId()));
    }

    private Manager getManager(Integer managerId) {
        return Optional.ofNullable(managerId)
                .flatMap(managerRepository::findById)
                .orElse(null);
    }
}
