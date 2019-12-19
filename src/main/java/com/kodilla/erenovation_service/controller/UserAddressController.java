package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.dto.UserAddressDto;
import com.kodilla.erenovation_service.exception.UserAddressNotFoundException;
import com.kodilla.erenovation_service.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/address")
@CrossOrigin("*")
@Slf4j
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @GetMapping()
    public UserAddressDto getAddressById(@RequestParam long userAddressId) throws UserAddressNotFoundException {
        log.info("Searching for user's address");
        return userAddressService.findUserAddressById(userAddressId);
    }

    @PutMapping
    public UserAddressDto updateAddress(@RequestBody UserAddressDto userAddressDto)
            throws UserAddressNotFoundException {
        log.info("Updating address with ID {}", userAddressDto.getId());
        return userAddressService.updateUserAddress(userAddressDto);
    }
}
