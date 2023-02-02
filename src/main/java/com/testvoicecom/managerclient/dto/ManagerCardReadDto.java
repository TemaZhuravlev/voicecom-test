package com.testvoicecom.managerclient.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ManagerCardReadDto {

    private String fullName;
    private String phoneNumber;
    private String alternatePhoneNumber;
}
