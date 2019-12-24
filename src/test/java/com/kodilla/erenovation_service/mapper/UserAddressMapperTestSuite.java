package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.UserAddress;
import com.kodilla.erenovation_service.dto.UserAddressDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAddressMapperTestSuite {

    @Autowired
    private UserAddressMapper userAddressMapper;

    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";

    @Test
    public void testToUserAddress() {
        //Given
        UserAddressDto userAddressDto = new UserAddressDto(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        //When
        UserAddress userAddress = userAddressMapper.toUserAddress(userAddressDto);

        //Then
        assertNotNull(userAddress);
        assertEquals(userAddressDto.getId(), userAddress.getId());
        assertEquals(userAddressDto.getCity(), userAddress.getCity());
        assertEquals(userAddressDto.getStreet(), userAddress.getStreet());
        assertEquals(userAddressDto.getBuilding(), userAddress.getBuilding());
        assertEquals(userAddressDto.getApartment(), userAddress.getApartment());
        assertEquals(userAddressDto.getPostalCode(), userAddress.getPostalCode());
    }

    @Test
    public void testToUserAddressDto() {
        //Given
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);

        //When
        UserAddressDto userAddressDto = userAddressMapper.toUserAddressDto(userAddress);

        //Then
        assertNotNull(userAddressDto);
        assertEquals(userAddress.getId(), userAddressDto.getId());
        assertEquals(userAddress.getCity(), userAddressDto.getCity());
        assertEquals(userAddress.getStreet(), userAddressDto.getStreet());
        assertEquals(userAddress.getBuilding(), userAddressDto.getBuilding());
        assertEquals(userAddress.getApartment(), userAddressDto.getApartment());
        assertEquals(userAddress.getPostalCode(), userAddressDto.getPostalCode());
    }
}
