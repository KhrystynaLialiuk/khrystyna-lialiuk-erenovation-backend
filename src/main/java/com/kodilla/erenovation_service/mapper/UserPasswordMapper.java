package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.UserPassword;
import com.kodilla.erenovation_service.dto.UserPasswordDto;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordMapper {
    public UserPassword toUserPassword(UserPasswordDto userPasswordDto) {
        UserPassword userPassword = new UserPassword();
        userPassword.setId(userPasswordDto.getId());
        userPassword.setPassword(userPasswordDto.getPassword());
        return userPassword;
    }

    public UserPasswordDto toUserPasswordDto(UserPassword userPassword) {
        UserPasswordDto userPasswordDto = new UserPasswordDto();
        userPasswordDto.setId(userPassword.getId());
        userPasswordDto.setPassword(userPassword.getPassword());
        return userPasswordDto;
    }
}
