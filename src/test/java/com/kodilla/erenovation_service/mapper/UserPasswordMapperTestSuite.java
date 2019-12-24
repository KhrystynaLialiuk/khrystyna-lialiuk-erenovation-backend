package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.UserPassword;
import com.kodilla.erenovation_service.dto.UserPasswordDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPasswordMapperTestSuite {

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    private static final String PASSWORD = "Password";

    @Test
    public void testToUserPassword() {
        //Given
        UserPasswordDto userPasswordDto = new UserPasswordDto(1L, PASSWORD);

        //When
        UserPassword userPassword = userPasswordMapper.toUserPassword(userPasswordDto);

        //Then
        assertNotNull(userPassword);
        assertEquals(userPasswordDto.getId(), userPassword.getId());
        assertEquals(userPasswordDto.getPassword(), userPassword.getPassword());
    }

    @Test
    public void testToUserPasswordDto() {
        //Given
        UserPassword userPassword = new UserPassword(1L, "Password");

        //When
        UserPasswordDto userPasswordDto = userPasswordMapper.toUserPasswordDto(userPassword);

        //Then
        assertNotNull(userPasswordDto);
        assertEquals(userPassword.getId(), userPasswordDto.getId());
        assertEquals(userPassword.getPassword(), userPasswordDto.getPassword());
    }
}
