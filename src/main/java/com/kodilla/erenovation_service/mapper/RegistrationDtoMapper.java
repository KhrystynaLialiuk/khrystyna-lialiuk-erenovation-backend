package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.dto.RegistrationDto;
import com.kodilla.erenovation_service.dto.UserAddressDto;
import com.kodilla.erenovation_service.dto.UserDto;
import com.kodilla.erenovation_service.dto.UserPasswordDto;
import org.springframework.stereotype.Component;

@Component
public class RegistrationDtoMapper {

    public UserDto toUserDto(RegistrationDto registrationDto) {
        UserDto userDto = new UserDto();
        userDto.setName(registrationDto.getName());
        userDto.setSurname(registrationDto.getSurname());
        userDto.setPhone(registrationDto.getPhone());
        userDto.setEmail(registrationDto.getEmail());
        userDto.setUserPasswordDto(toUserPasswordDto(registrationDto));
        userDto.setUserAddressDto(toUserAddressDto(registrationDto));
        return userDto;
    }

    public UserPasswordDto toUserPasswordDto(RegistrationDto registrationDto) {
        UserPasswordDto userPasswordDto = new UserPasswordDto();
        userPasswordDto.setPassword(registrationDto.getPassword());
        return userPasswordDto;
    }

    public UserAddressDto toUserAddressDto(RegistrationDto registrationDto) {
        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setCity(registrationDto.getCity());
        userAddressDto.setStreet(registrationDto.getStreet());
        userAddressDto.setBuilding(registrationDto.getBuilding());
        userAddressDto.setApartment(registrationDto.getApartment());
        userAddressDto.setPostalCode(registrationDto.getPostalCode());
        return userAddressDto;
    }
}
