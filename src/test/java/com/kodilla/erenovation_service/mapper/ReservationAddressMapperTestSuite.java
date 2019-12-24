package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.ReservationAddress;
import com.kodilla.erenovation_service.dto.ReservationAddressDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationAddressMapperTestSuite {

    @Autowired
    private ReservationAddressMapper reservationAddressMapper;

    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";

    @Test
    public void testToReservationAddressDto() {
        //Given
        ReservationAddress reservationAddress = new ReservationAddress(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);

        //When
        ReservationAddressDto reservationAddressDto = reservationAddressMapper
                .toReservationAddressDto(reservationAddress);

        //Then
        assertNotNull(reservationAddressDto);
        assertEquals(reservationAddress.getId(), reservationAddressDto.getId());
        assertEquals(reservationAddress.getCity(), reservationAddressDto.getCity());
        assertEquals(reservationAddress.getStreet(), reservationAddressDto.getStreet());
        assertEquals(reservationAddress.getBuilding(), reservationAddressDto.getBuilding());
        assertEquals(reservationAddress.getApartment(), reservationAddressDto.getApartment());
        assertEquals(reservationAddress.getPostalCode(), reservationAddressDto.getPostalCode());
    }

    @Test
    public void testToReservationAddress() {
        //Given
        ReservationAddressDto reservationAddressDto = new ReservationAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);

        //When
        ReservationAddress reservationAddress = reservationAddressMapper
                .toReservationAddress(reservationAddressDto);

        //Then
        assertNotNull(reservationAddress);
        assertEquals(reservationAddressDto.getId(), reservationAddress.getId());
        assertEquals(reservationAddressDto.getCity(), reservationAddress.getCity());
        assertEquals(reservationAddressDto.getStreet(), reservationAddress.getStreet());
        assertEquals(reservationAddressDto.getBuilding(), reservationAddress.getBuilding());
        assertEquals(reservationAddressDto.getApartment(), reservationAddress.getApartment());
        assertEquals(reservationAddressDto.getPostalCode(), reservationAddress.getPostalCode());
    }
}
