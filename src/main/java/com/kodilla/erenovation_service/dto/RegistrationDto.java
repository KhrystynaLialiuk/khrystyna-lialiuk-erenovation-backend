package com.kodilla.erenovation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationDto {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private String city;
    private String street;
    private int building;
    private int apartment;
    private String postalCode;
}
