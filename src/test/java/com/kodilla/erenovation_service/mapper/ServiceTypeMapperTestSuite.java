package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.ServiceType;
import com.kodilla.erenovation_service.dto.ServiceTypeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTypeMapperTestSuite {

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    private static final String TITLE = "Painting";

    @Test
    public void testToServiceType() {
        //Given
        ServiceTypeDto serviceTypeDto = new ServiceTypeDto(1L, TITLE, null);

        //When
        ServiceType serviceType = serviceTypeMapper.toServiceType(serviceTypeDto);

        //Then
        assertNotNull(serviceType);
        assertEquals(serviceTypeDto.getId(), serviceType.getId());
        assertEquals(serviceType.getTitle(), serviceType.getTitle());
    }

    @Test
    public void testToServiceTypeDto() {
        ServiceType serviceType = new ServiceType();
        serviceType.setId(1L);
        serviceType.setTitle(TITLE);

        //When
        ServiceTypeDto serviceTypeDto = serviceTypeMapper.toServiceTypeDto(serviceType);

        //Then
        assertNotNull(serviceTypeDto);
        assertEquals(serviceType.getId(), serviceTypeDto.getId());
        assertEquals(serviceType.getTitle(), serviceType.getTitle());
    }
}
