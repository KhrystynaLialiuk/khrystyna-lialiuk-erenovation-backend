package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.dto.UserPasswordDto;
import com.kodilla.erenovation_service.exception.UserPasswordNotFoundException;
import com.kodilla.erenovation_service.service.UserPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/password")
@CrossOrigin("*")
@Slf4j
public class UserPasswordController {

    @Autowired
    private UserPasswordService userPasswordService;

    @GetMapping()
    public UserPasswordDto getPasswordById(@RequestParam long userPasswordId) throws UserPasswordNotFoundException {
        log.info("Searching for user's password");
        return userPasswordService.findUserPasswordById(userPasswordId);
    }

    @PutMapping
    public UserPasswordDto updatePassword(@RequestBody UserPasswordDto userPasswordDto)
            throws UserPasswordNotFoundException {
        log.info("Updating password with ID {}", userPasswordDto.getId());
        return userPasswordService.updateUserPassword(userPasswordDto);
    }
}
