package com.kodilla.erenovation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private UserAddressDto userAddressDto;
    private UserPasswordDto userPasswordDto;
}
