package com.kodilla.erenovation_service.domain;

import com.kodilla.erenovation_service.repository.PricingRepository;
import com.kodilla.erenovation_service.repository.ReservationAddressRepository;
import com.kodilla.erenovation_service.repository.ReservationRepository;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationAddressRepository reservationAddressRepository;

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

    private static final LocalDate DATE = LocalDate.now();
    private static final BigDecimal TRANSPORTATION_COST = new BigDecimal(100);

    private User savedUser;
    private ReservationAddress reservationAddress;
    private Reservation reservation;

    @Before
    public void prepare() {
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword(PASSWORD);

        UserAddress userAddress = new UserAddress();
        userAddress.setCity(CITY);
        userAddress.setStreet(STREET);
        userAddress.setBuilding(BUILDING);
        userAddress.setApartment(APARTMENT);
        userAddress.setPostalCode(POSTAL_CODE);

        User user = new User();
        user.setName(NAME);
        user.setSurname(SURNAME);
        user.setEmail(EMAIL);
        user.setPhone(PHONE);
        user.setUserPassword(userPassword);
        user.setUserAddress(userAddress);
        savedUser = userRepository.save(user);

        Pricing pricing = new Pricing();
        pricing.setDate(LocalDate.now());
        pricing.setPrice(new BigDecimal("0"));
        pricing.setUser(savedUser);
        Pricing savedPricing = pricingRepository.save(pricing);

        ReservationAddress reservationAddress = new ReservationAddress();
        reservationAddress.setCity(CITY);
        reservationAddress.setStreet(STREET);
        reservationAddress.setBuilding(BUILDING);
        reservationAddress.setApartment(APARTMENT);
        reservationAddress.setPostalCode(POSTAL_CODE);

        reservation = new Reservation();
        reservation.setDate(DATE);
        reservation.setTransportationCost(TRANSPORTATION_COST);
        reservation.setUser(savedUser);
        reservation.setPricing(savedPricing);
        reservation.setReservationAddress(reservationAddress);
    }

    @Test
    public void testSaveReservation() {
        //Given

        //When
        Reservation savedReservation = reservationRepository.save(reservation);

        //Then
        long id = savedReservation.getId();
        Assert.assertNotEquals(0, id);

        Optional<Reservation> foundById = reservationRepository.findById(id);
        Assert.assertTrue(foundById.isPresent());
    }

    @Test
    public void testSaveReservationAddress() {
        //Given
        Reservation savedReservation = reservationRepository.save(reservation);

        //When
        Optional<ReservationAddress> savedAddress = reservationAddressRepository
                .findById(savedReservation.getReservationAddress().getId());

        //Then
        Assert.assertTrue(savedAddress.isPresent());
    }


    @Test
    public void testFindByUser() {
        //Given
        reservationRepository.save(reservation);

        //When
        List<Reservation> foundByUser = reservationRepository.findByUser(savedUser);

        //Then
        Assert.assertEquals(1, foundByUser.size());
    }

    @Test
    public void testDeleteReservationById() {
        //Given
        Reservation savedReservation = reservationRepository.save(reservation);
        long reservationId = savedReservation.getId();

        //When
        reservationRepository.deleteById(reservationId);

        //Then
        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationAddress> reservationAddressList = reservationAddressRepository.findAll();
        Assert.assertEquals(0, reservationList.size());
        Assert.assertEquals(0, reservationAddressList.size());
    }

    @After
    public void cleanUp() {
        //CleanUp
        reservationRepository.deleteAll();
        pricingRepository.deleteAll();
        userRepository.deleteAll();
    }
}
