package com.kodilla.erenovation_service.domain;

import com.kodilla.erenovation_service.repository.ServiceRepository;
import com.kodilla.erenovation_service.repository.ServiceTypeRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTestSuite {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    private static final String SERVICE_TYPE_TITLE = "Painting";
    private static final String TITLE = "Wall preparation";
    private static final String OTHER_TITLE = "Painting, 1 layer";
    private static final BigDecimal REGULAR_UNIT_PRICE = new BigDecimal(5);
    private static final BigDecimal DISCOUNT_UNIT_PRICE = new BigDecimal(4);
    private static final Integer THRESHOLD_VALUE = 100;

    private ServiceType savedServiceType;
    private Service service;
    private Service savedService;

    @Before
    public void prepare() {
        ServiceType serviceType = new ServiceType();
        serviceType.setTitle(SERVICE_TYPE_TITLE);
        savedServiceType = serviceTypeRepository.save(serviceType);

        service = new Service();
        service.setTitle(TITLE);
        service.setRegularUnitPrice(REGULAR_UNIT_PRICE);
        service.setDiscountUnitPrice(DISCOUNT_UNIT_PRICE);
        service.setThresholdValue(THRESHOLD_VALUE);
        service.setServiceType(savedServiceType);
    }

    @Test
    public void testSaveService() {
        //Given

        //When
        savedService = serviceRepository.save(service);

        //Then
        long id = savedService.getId();
        Assert.assertNotEquals(0, id);

        Optional<Service> foundById = serviceRepository.findById(id);
        Assert.assertTrue(foundById.isPresent());
    }

    @Test
    public void testFindAll() {
        //Given
        savedService = serviceRepository.save(service);

        //When
        List<Service> serviceList = serviceRepository.findAll();

        //Then
        Assert.assertEquals(1, serviceList.size());
    }

    @Test
    public void testFindByTitle() {
        //Given
        savedService = serviceRepository.save(service);

        //When
        Optional<Service> foundByTitle = serviceRepository.findByTitle(TITLE);

        //Then
        Assert.assertTrue(foundByTitle.isPresent());
    }

    @Test
    public void testFindByTitleShouldNotExist() {
        //Given
        savedService = serviceRepository.save(service);

        //When
        Optional<Service> foundByTitle = serviceRepository.findByTitle(OTHER_TITLE);

        //Then
        Assert.assertFalse(foundByTitle.isPresent());
    }

    @After
    public void cleanUp() {
        //CleanUp
        serviceRepository.deleteById(savedService.getId());
        serviceTypeRepository.deleteById(savedServiceType.getId());
    }
}
