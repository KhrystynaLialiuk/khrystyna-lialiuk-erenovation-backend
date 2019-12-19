package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.User;
import com.kodilla.erenovation_service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    public User toUser(final UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        if (userDto.getUserAddressDto() != null){
            user.setUserAddress(userAddressMapper.toUserAddress(userDto.getUserAddressDto()));
        }
        if (userDto.getUserPasswordDto() != null) {
            user.setUserPassword(userPasswordMapper.toUserPassword(userDto.getUserPasswordDto()));
        }
        return user;
    }

    public UserDto toUserDto(final User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setUserPasswordDto(userPasswordMapper.toUserPasswordDto(user.getUserPassword()));
        userDto.getUserPasswordDto().setPassword("Confidential");
        if(user.getUserAddress() != null) {
            userDto.setUserAddressDto(userAddressMapper.toUserAddressDto(user.getUserAddress()));
        }
        return userDto;
    }

    public List<UserDto> toUserDtoList(List<User> userList) {
        return userList.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
