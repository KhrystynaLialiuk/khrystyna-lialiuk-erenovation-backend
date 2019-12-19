package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.data.ServiceDataCreator;
import com.kodilla.erenovation_service.domain.User;
import com.kodilla.erenovation_service.dto.RegistrationDto;
import com.kodilla.erenovation_service.dto.UserDto;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin("*")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceDataCreator serviceDataCreator;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody RegistrationDto registrationDto) {
        log.info("Creating a user {} {}", registrationDto.getName(), registrationDto.getSurname());
        userService.saveUser(registrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public UserDto getUserById(@RequestParam long userId) throws UserNotFoundException {
        log.info("Searching for the user with ID {}", userId);
        return userService.findUserById(userId);
    }

    @GetMapping(value = "validation")
    public UserDto getUserByEmailAndPassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        log.info("Searching for the user by email {} and password ...", email);
        serviceDataCreator.createServiceData();
        return userService.findUserByEmailAndPassword(email, password);
    }

    @GetMapping(value = "all")
    public List<UserDto> getAllUsers() {
        log.info("Searching for all users");
        return userService.getUsers();
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        log.info("Updating user with ID {}", userDto.getId());
        return userService.updateUser(userDto);
    }

    @DeleteMapping
    public void deleteUserById(@RequestParam long userId) throws UserNotFoundException {
        log.info("Deleting user with ID {}", userId);
        userService.deleteById(userId);
    }
}
