package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.*;
import com.kodilla.erenovation_service.dto.*;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.repository.PricingRepository;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationMapperTestSuite {

    @InjectMocks
    private ReservationMapper reservationMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PricingRepository pricingRepository;

    @Mock
    private ReservationAddressMapper reservationAddressMapper;

    private static final String NAME = "John";
    private static final String NAME2 = "Mike";
    private static final String SURNAME = "Smith";
    private static final String SURNAME2 = "Doe";
    private static final String EMAIL = "test@test.com";
    private static final String EMAIL2 = "testing@test.com";
    private static final String PHONE = "123456789";
    private static final String PHONE2 = "123123123";
    private static final String CITY = "Warsaw";
    private static final String CITY2 = "Wroclaw";
    private static final String STREET = "Main";
    private static final String STREET2 = "Main2";
    private static final int BUILDING = 1;
    private static final int BUILDING2 = 2;
    private static final int APARTMENT = 1;
    private static final int APARTMENT2 = 2;
    private static final String POSTAL_CODE = "12345";
    private static final String POSTAL_CODE2 = "123456";
    private static final String PASSWORD = "Password";
    private static final String PASSWORD2 = "Password2";

    private static final LocalDate DATE = LocalDate.now();
    private static final BigDecimal TRANSPORTATION_COST = new BigDecimal(100);
    private static final BigDecimal TRANSPORTATION_COST2 = new BigDecimal(10);
    private static final BigDecimal PRICE = new BigDecimal(0);
    private static final BigDecimal PRICE2 = new BigDecimal(5);

    @Test
    public void testToReservation() {
        //Given
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);

        ReservationAddressDto reservationAddressDto = new ReservationAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        ReservationAddress reservationAddress = new ReservationAddress(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);

        Pricing pricing = new Pricing(1L, user, DATE, PRICE, null, null);

        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L, DATE,
                TRANSPORTATION_COST, reservationAddressDto);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(pricingRepository.findById(1L)).thenReturn(java.util.Optional.of(pricing));
        when(reservationAddressMapper.toReservationAddress(reservationAddressDto))
                .thenReturn(reservationAddress);

        //When
        try {
            Reservation reservation = reservationMapper.toReservation(reservationDto);

            //Then
            assertNotNull(reservation);
            assertEquals(reservation.getId(), reservationDto.getId());
            assertEquals(reservation.getDate(), reservationDto.getDate());
            assertEquals(reservation.getTransportationCost(), reservationDto.getTransportationCost());
            assertEquals(reservation.getPricing().getId(), reservationDto.getPricingId());
            assertEquals(reservation.getUser().getId(), reservationDto.getUserId());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (PricingNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToReservationDto() {
        //Given
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);
        ReservationAddress reservationAddress = new ReservationAddress(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        Pricing pricing = new Pricing(1L, user, DATE, PRICE, null, null);
        Reservation reservation = new Reservation(1L, user, pricing, DATE, TRANSPORTATION_COST, reservationAddress);

        ReservationAddressDto reservationAddressDto = new ReservationAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        when(reservationAddressMapper.toReservationAddressDto(reservationAddress))
                .thenReturn(reservationAddressDto);

        //When
        ReservationDto reservationDto = reservationMapper.toReservationDto(reservation);

        //Then
        assertNotNull(reservationDto);
        assertEquals(reservation.getId(), reservationDto.getId());
        assertEquals(reservation.getDate(), reservationDto.getDate());
        assertEquals(reservation.getTransportationCost(), reservationDto.getTransportationCost());
        assertEquals(reservation.getUser().getId(), reservationDto.getUserId());
        assertEquals(reservation.getPricing().getId(), reservationDto.getPricingId());
        assertEquals(reservation.getReservationAddress().getId(), reservationDto.getReservationAddressDto().getId());
    }

    @Test
    public void testToReservationDtoList() {
        //Given
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);
        ReservationAddress reservationAddress = new ReservationAddress(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        Pricing pricing = new Pricing(1L, user, DATE, PRICE, null, null);
        Reservation reservation = new Reservation(1L, user, pricing, DATE, TRANSPORTATION_COST, reservationAddress);
        ReservationAddressDto reservationAddressDto = new ReservationAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        when(reservationAddressMapper.toReservationAddressDto(reservationAddress))
                .thenReturn(reservationAddressDto);

        UserPassword userPassword2 = new UserPassword(2L, PASSWORD2);
        UserAddress userAddress2 = new UserAddress(2L, CITY2, STREET2, BUILDING2, APARTMENT2, POSTAL_CODE2);
        User user2 = new User(2L, NAME2, SURNAME2, PHONE2, EMAIL2, userAddress2, userPassword2,
                null, null, null);
        ReservationAddress reservationAddress2 = new ReservationAddress(2L, CITY2, STREET2, BUILDING2,
                APARTMENT2, POSTAL_CODE2);
        Pricing pricing2 = new Pricing(2L, user2, DATE, PRICE2, null, null);
        Reservation reservation2 = new Reservation(2L, user2, pricing2, DATE, TRANSPORTATION_COST2,
                reservationAddress2);
        ReservationAddressDto reservationAddressDto2 = new ReservationAddressDto(2L, CITY2, STREET2, BUILDING2,
                APARTMENT2, POSTAL_CODE2);
        when(reservationAddressMapper.toReservationAddressDto(reservationAddress2))
                .thenReturn(reservationAddressDto2);

        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation);
        reservationList.add(reservation2);

        //When
        List<ReservationDto> reservationDtoList = reservationMapper.toReservationDtoList(reservationList);

        //Then
        verify(reservationAddressMapper, times(1))
                .toReservationAddressDto(reservationAddress2);
        verify(reservationAddressMapper, times(1))
                .toReservationAddressDto(reservationAddress);

        assertEquals(2, reservationDtoList.size());

        assertThat(reservationDtoList.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", reservationList.get(0).getId())
                .hasFieldOrPropertyWithValue("userId", reservationList.get(0).getUser().getId())
                .hasFieldOrPropertyWithValue("pricingId", reservationList.get(0).getPricing().getId())
                .hasFieldOrPropertyWithValue("date", reservationList.get(0).getDate())
                .hasFieldOrPropertyWithValue("transportationCost", reservationList.get(0).getTransportationCost())
                .hasFieldOrPropertyWithValue("reservationAddressDto", reservationAddressDto);

        assertThat(reservationDtoList.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", reservationList.get(1).getId())
                .hasFieldOrPropertyWithValue("userId", reservationList.get(1).getUser().getId())
                .hasFieldOrPropertyWithValue("pricingId", reservationList.get(1).getPricing().getId())
                .hasFieldOrPropertyWithValue("date", reservationList.get(1).getDate())
                .hasFieldOrPropertyWithValue("transportationCost", reservationList.get(1).getTransportationCost())
                .hasFieldOrPropertyWithValue("reservationAddressDto", reservationAddressDto2);
    }
}
