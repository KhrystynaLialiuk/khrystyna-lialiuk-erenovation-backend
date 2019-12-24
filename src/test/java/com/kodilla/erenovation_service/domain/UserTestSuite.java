package com.kodilla.erenovation_service.domain;

import com.kodilla.erenovation_service.repository.UserAddressRepository;
import com.kodilla.erenovation_service.repository.UserPasswordRepository;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;


import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

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

    private User user;
    private UserPassword userPassword;
    private UserAddress userAddress;

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
    }

    @Test
    public void testUserDelete() {
        //Given
        User savedUser = userRepository.save(user);

        //When
        userRepository.deleteById(savedUser.getId());

        //Then
        List<UserPassword> userPasswords = userPasswordRepository.findAll();
        List<UserAddress> userAddresses = userAddressRepository.findAll();
        Assert.assertEquals(0, userPasswords.size());
        Assert.assertEquals(0, userAddresses.size());
    }

    @Test
    public void testSaveUser() {
        //Given

        //When
        User savedUser = userRepository.save(user);

        //Then
        long userId = savedUser.getId();
        Optional<User> foundUser = userRepository.findById(userId);
        Assert.assertTrue(foundUser.isPresent());

        long userPasswordId = savedUser.getUserPassword().getId();
        Optional<UserPassword> foundPassword = userPasswordRepository.findById(userPasswordId);
        Assert.assertTrue(foundPassword.isPresent());

        long userAddressId = savedUser.getUserAddress().getId();
        Optional<UserAddress> foundAddress = userAddressRepository.findById(userAddressId);
        Assert.assertTrue(foundAddress.isPresent());

        //CleanUp
        userRepository.deleteById(userId);
    }
}
