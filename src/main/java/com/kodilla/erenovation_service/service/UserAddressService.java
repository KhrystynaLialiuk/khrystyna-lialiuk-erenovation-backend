package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.dto.UserAddressDto;
import com.kodilla.erenovation_service.exception.UserAddressNotFoundException;
import com.kodilla.erenovation_service.mapper.UserAddressMapper;
import com.kodilla.erenovation_service.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserAddressMapper userAddressMapper;

    public UserAddressDto findUserAddressById(final long userAddressId) throws UserAddressNotFoundException {
        return userAddressMapper.toUserAddressDto(userAddressRepository.findById(userAddressId)
                .orElseThrow(UserAddressNotFoundException::new));
    }

    public UserAddressDto updateUserAddress(final UserAddressDto userAddressDto)
            throws UserAddressNotFoundException {
        if (userAddressRepository.findById(userAddressDto.getId()).isPresent()) {
            return userAddressMapper.toUserAddressDto(userAddressRepository
                    .save(userAddressMapper.toUserAddress(userAddressDto)));
        }
        throw new UserAddressNotFoundException();
    }
}
