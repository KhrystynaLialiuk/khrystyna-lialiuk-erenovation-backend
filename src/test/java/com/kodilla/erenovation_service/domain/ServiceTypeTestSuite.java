package com.kodilla.erenovation_service.domain;

import com.kodilla.erenovation_service.repository.ServiceTypeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTypeTestSuite {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    private static final String TITLE = "Painting";

    @Test
    public void testSaveServiceType() {
        //Given
        ServiceType serviceType = new ServiceType();
        serviceType.setTitle(TITLE);

        //When
        ServiceType savedServiceType = serviceTypeRepository.save(serviceType);

        //Then
        long id = savedServiceType.getId();
        Assert.assertNotEquals(0, id);

        Optional<ServiceType> foundById = serviceTypeRepository.findById(id);
        Assert.assertTrue(foundById.isPresent());

        //CleanUp
        serviceTypeRepository.deleteAll();
    }
}
