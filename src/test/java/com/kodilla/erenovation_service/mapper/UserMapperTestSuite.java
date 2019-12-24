package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.User;
import com.kodilla.erenovation_service.domain.UserAddress;
import com.kodilla.erenovation_service.domain.UserPassword;
import com.kodilla.erenovation_service.dto.UserAddressDto;
import com.kodilla.erenovation_service.dto.UserDto;
import com.kodilla.erenovation_service.dto.UserPasswordDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserMapperTestSuite {

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private UserAddressMapper userAddressMapper;

    @Mock
    private UserPasswordMapper userPasswordMapper;

    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";
    private static final String PASSWORD = "Password";
    private static final String NAME = "John";
    private static final String SURNAME = "Smith";
    private static final String EMAIL = "test@test.com";
    private static final String PHONE = "123456789";

    @Test
    public void testToUser() {
        //Given
        UserAddressDto userAddressDto = new UserAddressDto(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPasswordDto userPasswordDto = new UserPasswordDto(1L, PASSWORD);
        UserDto userDto = new UserDto(1L, NAME, SURNAME, PHONE, EMAIL, userAddressDto, userPasswordDto);

        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        when(userAddressMapper.toUserAddress(userAddressDto)).thenReturn(userAddress);
        when(userPasswordMapper.toUserPassword(userPasswordDto)).thenReturn(userPassword);

        //When
        User user = userMapper.toUser(userDto);

        //Then
        verify(userAddressMapper, times(1)).toUserAddress(userAddressDto);
        verify(userPasswordMapper, times(1)).toUserPassword(userPasswordDto);
        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getSurname(), user.getSurname());
        assertEquals(userDto.getPhone(), user.getPhone());
        assertEquals(userDto.getEmail(), user.getEmail());
    }

    @Test
    public void testToUserDto() {
        //Given
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        User user = new User();
        user.setId(1L);
        user.setName(NAME);
        user.setSurname(SURNAME);
        user.setPhone(PHONE);
        user.setEmail(EMAIL);
        user.setUserAddress(userAddress);
        user.setUserPassword(userPassword);

        UserPasswordDto userPasswordDto = new UserPasswordDto(1L, PASSWORD);
        UserAddressDto userAddressDto = new UserAddressDto(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        when(userAddressMapper.toUserAddressDto(userAddress)).thenReturn(userAddressDto);
        when(userPasswordMapper.toUserPasswordDto(userPassword)).thenReturn(userPasswordDto);

        //When
        UserDto userDto = userMapper.toUserDto(user);

        //Then
        verify(userAddressMapper, times(1)).toUserAddressDto(userAddress);
        verify(userPasswordMapper, times(1)).toUserPasswordDto(userPassword);
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getSurname(), userDto.getSurname());
        assertEquals(user.getPhone(), userDto.getPhone());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
    public void toUserDtoList() {
        //Given
        UserAddress userAddress1 = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPassword userPassword1 = new UserPassword(1L, PASSWORD);
        User user1 = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress1, userPassword1,
                null, null, null);

        UserAddress userAddress2 = new UserAddress(2L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPassword userPassword2 = new UserPassword(2L, PASSWORD);
        User user2 = new User(2L, NAME, SURNAME, PHONE, EMAIL, userAddress2, userPassword2,
                null, null, null);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        UserPasswordDto userPasswordDto1 = new UserPasswordDto(1L, PASSWORD);
        UserAddressDto userAddressDto1 = new UserAddressDto(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        UserPasswordDto userPasswordDto2 = new UserPasswordDto(2L, PASSWORD);
        UserAddressDto userAddressDto2 = new UserAddressDto(2L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        when(userAddressMapper.toUserAddressDto(userAddress1)).thenReturn(userAddressDto1);
        when(userAddressMapper.toUserAddressDto(userAddress2)).thenReturn(userAddressDto2);
        when(userPasswordMapper.toUserPasswordDto(userPassword1)).thenReturn(userPasswordDto1);
        when(userPasswordMapper.toUserPasswordDto(userPassword2)).thenReturn(userPasswordDto2);

        //When
        List<UserDto> userDtoList = userMapper.toUserDtoList(users);

        //Then
        verify(userAddressMapper, times(1)).toUserAddressDto(userAddress1);
        verify(userAddressMapper, times(1)).toUserAddressDto(userAddress2);
        verify(userPasswordMapper, times(1)).toUserPasswordDto(userPassword1);
        verify(userPasswordMapper, times(1)).toUserPasswordDto(userPassword2);
        assertEquals(2, userDtoList.size());

        assertThat(userDtoList.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", users.get(0).getId())
                .hasFieldOrPropertyWithValue("name", users.get(0).getName())
                .hasFieldOrPropertyWithValue("surname", users.get(0).getSurname())
                .hasFieldOrPropertyWithValue("email", users.get(0).getEmail())
                .hasFieldOrPropertyWithValue("phone", users.get(0).getPhone());

        assertThat(userDtoList.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", users.get(1).getId())
                .hasFieldOrPropertyWithValue("name", users.get(1).getName())
                .hasFieldOrPropertyWithValue("surname", users.get(1).getSurname())
                .hasFieldOrPropertyWithValue("email", users.get(1).getEmail())
                .hasFieldOrPropertyWithValue("phone", users.get(1).getPhone());
    }
}
