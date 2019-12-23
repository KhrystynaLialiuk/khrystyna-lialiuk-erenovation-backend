package com.kodilla.erenovation_service.domain;

import com.kodilla.erenovation_service.repository.PricingRepository;
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
public class PricingTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PricingRepository pricingRepository;

    private static final String NAME = "John";
    private static final String OTHER_NAME = "Mike";
    private static final String SURNAME = "Smith";
    private static final String OTHER_SURNAME = "Doe";
    private static final String EMAIL = "test@test.com";
    private static final String PHONE = "123456789";
    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";
    private static final String PASSWORD = "Password";
    private static final String OTHER_PASSWORD = "pass";

    private User user;
    private UserPassword userPassword;
    private UserAddress userAddress;
    private User savedUser;
    private Pricing pricing;


    @Before
    public void prepare() {
        userPassword = new UserPassword();
        userPassword.setPassword(PASSWORD);

        userAddress = new UserAddress();
        userAddress.setCity(CITY);
        userAddress.setStreet(STREET);
        userAddress.setBuilding(BUILDING);
        userAddress.setApartment(APARTMENT);
        userAddress.setPostalCode(POSTAL_CODE);

        user = new User();
        user.setName(NAME);
        user.setSurname(SURNAME);
        user.setEmail(EMAIL);
        user.setPhone(PHONE);
        user.setUserPassword(userPassword);
        user.setUserAddress(userAddress);
        savedUser = userRepository.save(user);

        pricing = new Pricing();
        pricing.setDate(LocalDate.now());
        pricing.setPrice(new BigDecimal("0"));
        pricing.setUser(savedUser);
    }

    @Test
    public void testSavePricing() {
        //Given

        //When
        Pricing savedPricing = pricingRepository.save(pricing);

        //Then
        long id = savedPricing.getId();
        Assert.assertNotEquals(0, id);

        Optional<Pricing> foundById = pricingRepository.findById(id);
        Assert.assertTrue(foundById.isPresent());
    }

    @Test
    public void testFindAll() {
        //Given
        pricingRepository.save(pricing);

        //When
        List<Pricing> pricingList = pricingRepository.findAll();

        //Then
        Assert.assertEquals(1, pricingList.size());
    }

    @Test
    public void testFindByUser() {
        //Given
        pricingRepository.save(pricing);

        //When
        List<Pricing> pricingListOfUser = pricingRepository.findByUser(savedUser);

        //Then
        Assert.assertEquals(1, pricingListOfUser.size());
    }

    @Test
    public void testFindByUserShouldReturnEmptyList() {
        //Given
        pricingRepository.save(pricing);

        UserPassword otherUserPassword = new UserPassword();
        otherUserPassword.setPassword(OTHER_PASSWORD);

        UserAddress otherUserAddress = new UserAddress();
        otherUserAddress.setCity(CITY);
        otherUserAddress.setStreet(STREET);
        otherUserAddress.setBuilding(BUILDING);
        otherUserAddress.setApartment(APARTMENT);
        otherUserAddress.setPostalCode(POSTAL_CODE);

        User otherUser = new User();
        otherUser.setName(OTHER_NAME);
        otherUser.setSurname(OTHER_SURNAME);
        otherUser.setEmail(EMAIL);
        otherUser.setPhone(PHONE);
        otherUser.setUserPassword(otherUserPassword);
        otherUser.setUserAddress(otherUserAddress);

        User otherSavedUser = userRepository.save(otherUser);

        //When
        List<Pricing> pricingListOfOtherUser = pricingRepository.findByUser(otherSavedUser);

        //Then
        Assert.assertEquals(0, pricingListOfOtherUser.size());
    }

    @After
    public void cleanUp() {
        //CleanUp
        pricingRepository.deleteAll();
        userRepository.deleteAll();
    }
}
