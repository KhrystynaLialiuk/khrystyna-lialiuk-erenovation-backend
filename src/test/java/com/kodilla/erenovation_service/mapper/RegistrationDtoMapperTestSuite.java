package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.dto.RegistrationDto;
import com.kodilla.erenovation_service.dto.UserAddressDto;
import com.kodilla.erenovation_service.dto.UserDto;
import com.kodilla.erenovation_service.dto.UserPasswordDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RegistrationDtoMapperTestSuite {

    @Autowired
    private RegistrationDtoMapper registrationDtoMapper;

    private static final String NAME = "John";
    private static final String SURNAME = "Smith";
    private static final String EMAIL = "test@test.com";
    private static final String PHONE = "123456789";
    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";
    private static final String PASSWORD = "Password";

    @Test
    public void testToUserDto() {
        //Given
        RegistrationDto registrationDto = new RegistrationDto(NAME, SURNAME, PHONE, EMAIL, PASSWORD,
                CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        //When
        UserDto userDto = registrationDtoMapper.toUserDto(registrationDto);

        //Then
        assertNotNull(userDto);
        assertEquals(userDto.getName(), registrationDto.getName());
        assertEquals(userDto.getSurname(), registrationDto.getSurname());
        assertEquals(userDto.getPhone(), registrationDto.getPhone());
        assertEquals(userDto.getEmail(), registrationDto.getEmail());
        assertEquals(userDto.getUserAddressDto().getCity(), registrationDto.getCity());
        assertEquals(userDto.getUserAddressDto().getStreet(), registrationDto.getStreet());
        assertEquals(userDto.getUserAddressDto().getBuilding(), registrationDto.getApartment());
        assertEquals(userDto.getUserAddressDto().getApartment(), registrationDto.getApartment());
        assertEquals(userDto.getUserAddressDto().getPostalCode(), registrationDto.getPostalCode());
        assertEquals(userDto.getUserPasswordDto().getPassword(), registrationDto.getPassword());
    }

    @Test
    public void testToUserPasswordDto() {
        //Given
        RegistrationDto registrationDto = new RegistrationDto(NAME, SURNAME, PHONE, EMAIL, PASSWORD,
                CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        //When
        UserPasswordDto userPasswordDto = registrationDtoMapper.toUserPasswordDto(registrationDto);

        //Then
        assertNotNull(userPasswordDto);
        assertEquals(userPasswordDto.getPassword(), registrationDto.getPassword());
    }

    @Test
    public void testToUserAddressDto() {
        //Given
        RegistrationDto registrationDto = new RegistrationDto(NAME, SURNAME, PHONE, EMAIL, PASSWORD,
                CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        //When
        UserAddressDto userAddressDto = registrationDtoMapper.toUserAddressDto(registrationDto);

        //Then
        assertNotNull(userAddressDto);
        assertEquals(userAddressDto.getCity(), registrationDto.getCity());
        assertEquals(userAddressDto.getStreet(), registrationDto.getStreet());
        assertEquals(userAddressDto.getBuilding(), registrationDto.getBuilding());
        assertEquals(userAddressDto.getApartment(), registrationDto.getApartment());
        assertEquals(userAddressDto.getPostalCode(), registrationDto.getPostalCode());
    }
}
