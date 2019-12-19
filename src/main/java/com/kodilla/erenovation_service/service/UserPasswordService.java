package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.dto.UserPasswordDto;
import com.kodilla.erenovation_service.exception.UserPasswordNotFoundException;
import com.kodilla.erenovation_service.mapper.UserPasswordMapper;
import com.kodilla.erenovation_service.repository.UserPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPasswordService {

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    public UserPasswordDto findUserPasswordById(final long userPasswordId) throws UserPasswordNotFoundException {
        return userPasswordMapper.toUserPasswordDto(userPasswordRepository.findById(userPasswordId)
                .orElseThrow(UserPasswordNotFoundException::new));
    }

    public UserPasswordDto updateUserPassword(final UserPasswordDto userPasswordDto)
            throws UserPasswordNotFoundException {
        if (userPasswordRepository.findById(userPasswordDto.getId()).isPresent()) {
            return userPasswordMapper.toUserPasswordDto(userPasswordRepository
                    .save(userPasswordMapper.toUserPassword(userPasswordDto)));
        }
        throw new UserPasswordNotFoundException();
    }
}
