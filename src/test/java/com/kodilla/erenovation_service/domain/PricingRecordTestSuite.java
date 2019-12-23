package com.kodilla.erenovation_service.domain;

import com.kodilla.erenovation_service.repository.*;
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
public class PricingRecordTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PricingRecordRepository pricingRecordRepository;

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

    private static final String SERVICE_TYPE_TITLE = "Painting";
    private static final String TITLE = "Wall preparation";
    private static final BigDecimal REGULAR_UNIT_PRICE = new BigDecimal(5);
    private static final BigDecimal DISCOUNT_UNIT_PRICE = new BigDecimal(4);
    private static final Integer THRESHOLD_VALUE = 100;

    private static final BigDecimal PRICE = new BigDecimal(10);
    private static final String PRICE_TYPE = "Regular";
    private static final Integer QUANTITY_OR_METERS = 2;

    private PricingRecord pricingRecord;

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
        User savedUser = userRepository.save(user);

        Pricing pricing = new Pricing();
        pricing.setDate(LocalDate.now());
        pricing.setPrice(new BigDecimal("0"));
        pricing.setUser(savedUser);
        Pricing savedPricing = pricingRepository.save(pricing);

        ServiceType serviceType = new ServiceType();
        serviceType.setTitle(SERVICE_TYPE_TITLE);
        ServiceType savedServiceType = serviceTypeRepository.save(serviceType);

        Service service = new Service();
        service.setTitle(TITLE);
        service.setRegularUnitPrice(REGULAR_UNIT_PRICE);
        service.setDiscountUnitPrice(DISCOUNT_UNIT_PRICE);
        service.setThresholdValue(THRESHOLD_VALUE);
        service.setServiceType(savedServiceType);
        Service savedService = serviceRepository.save(service);

        pricingRecord = new PricingRecord();
        pricingRecord.setPrice(PRICE);
        pricingRecord.setPriceType(PRICE_TYPE);
        pricingRecord.setQuantityOrMeters(QUANTITY_OR_METERS);
        pricingRecord.setPricing(savedPricing);
        pricingRecord.setService(savedService);
    }

    @Test
    public void testSavePricingRecord() {
        //Given

        //When
        PricingRecord savedRecord = pricingRecordRepository.save(pricingRecord);

        //Then
        long id = savedRecord.getId();
        Assert.assertNotEquals(0, id);

        Optional<PricingRecord> foundById = pricingRecordRepository.findById(id);
        Assert.assertTrue(foundById.isPresent());
    }

    @Test
    public void testFindAll() {
        //Given
        pricingRecordRepository.save(pricingRecord);

        //When
        List<PricingRecord> pricingRecordList = pricingRecordRepository.findAll();

        //Then
        Assert.assertEquals(1, pricingRecordList.size());
    }

    @Test
    public void testDeleteById() {
        //Given
        PricingRecord savedRecord = pricingRecordRepository.save(pricingRecord);
        List<PricingRecord> pricingRecordListBeforeDeletion = pricingRecordRepository.findAll();

        //When
        pricingRecordRepository.deleteById(savedRecord.getId());
        List<PricingRecord> pricingRecordListAfterDeletion = pricingRecordRepository.findAll();

        //Then
        Assert.assertEquals(1, pricingRecordListBeforeDeletion.size());
        Assert.assertEquals(0, pricingRecordListAfterDeletion.size());
    }

    @After
    public void cleanUp() {
        //CleanUp
        pricingRepository.deleteAll();
        userRepository.deleteAll();
        serviceRepository.deleteAll();
        serviceTypeRepository.deleteAll();
        pricingRecordRepository.deleteAll();
    }
}
