package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.domain.User;
import com.kodilla.erenovation_service.dto.RegistrationDto;
import com.kodilla.erenovation_service.dto.UserDto;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.mapper.RegistrationDtoMapper;
import com.kodilla.erenovation_service.mapper.UserMapper;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationDtoMapper registrationDtoMapper;

    @Autowired
    private UserMapper userMapper;

    public HttpStatus saveUser(final RegistrationDto registrationDto) {
        UserDto userDto = registrationDtoMapper.toUserDto(registrationDto);
        userRepository.save(userMapper.toUser(userDto));
        return HttpStatus.CREATED;
    }

    public UserDto findUserById(final long userId) throws UserNotFoundException {
        return userMapper.toUserDto(userRepository.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    public UserDto findUserByEmailAndPassword(final String email, final String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            String foundPassword = user.get().getUserPassword().getPassword();
            if (foundPassword.equals(password)) {
                return userMapper.toUserDto(user.get());
            }
        }
        return new UserDto();
    }

    public UserDto updateUser(final UserDto userDto) throws UserNotFoundException {
        if (userRepository.findById(userDto.getId()).isPresent()) {
            return userMapper.toUserDto(userRepository.save(userMapper.toUser(userDto)));
        }
        throw new UserNotFoundException();
    }
}
