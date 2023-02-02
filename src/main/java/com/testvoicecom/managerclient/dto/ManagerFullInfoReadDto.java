package com.testvoicecom.managerclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerFullInfoReadDto {

    private String fullName;
    private String phoneNumber;
    private ManagerReadDto alternateManager;
}
