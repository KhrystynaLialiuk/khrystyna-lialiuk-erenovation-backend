package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.Service;
import com.kodilla.erenovation_service.domain.ServiceType;
import com.kodilla.erenovation_service.dto.ServiceDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceMapperTestSuite {

    @Autowired
    private ServiceMapper serviceMapper;

    private static final String SERVICE_TYPE_TITLE1 = "Painting";
    private static final String TITLE1 = "Wall preparation";
    private static final String SERVICE_TYPE_TITLE2 = "Painting";
    private static final String TITLE2 = "Wall preparation";
    private static final BigDecimal REGULAR_UNIT_PRICE = new BigDecimal(5);
    private static final BigDecimal DISCOUNT_UNIT_PRICE = new BigDecimal(4);
    private static final Integer THRESHOLD_VALUE = 100;

    @Test
    public void testToServiceDto() {
        //Given
        ServiceType serviceType = new ServiceType(1L, SERVICE_TYPE_TITLE1, null);

        Service service = new Service(1L, TITLE1, REGULAR_UNIT_PRICE, DISCOUNT_UNIT_PRICE, THRESHOLD_VALUE,
                serviceType, null);

        //When
        ServiceDto serviceDto = serviceMapper.toServiceDto(service);

        //Then
        assertNotNull(serviceDto);
        assertEquals(service.getId(), serviceDto.getId());
        assertEquals(service.getTitle(), serviceDto.getTitle());
        assertEquals(service.getRegularUnitPrice(), serviceDto.getRegularUnitPrice());
        assertEquals(service.getDiscountUnitPrice(), serviceDto.getDiscountUnitPrice());
        assertEquals(service.getThresholdValue(), serviceDto.getThresholdValue());
        assertEquals(service.getServiceType().getId(), serviceDto.getServiceTypeId());
    }

    @Test
    public void testToServiceDtoList() {
        //Given
        ServiceType serviceType1 = new ServiceType(1L, SERVICE_TYPE_TITLE1, null);
        ServiceType serviceType2 = new ServiceType(2L, SERVICE_TYPE_TITLE2, null);

        Service service1 = new Service(1L, TITLE1, REGULAR_UNIT_PRICE, DISCOUNT_UNIT_PRICE, THRESHOLD_VALUE,
                serviceType1, null);
        Service service2 = new Service(2L, TITLE2, REGULAR_UNIT_PRICE, DISCOUNT_UNIT_PRICE, THRESHOLD_VALUE,
                serviceType2, null);

        List<Service> serviceList = new ArrayList<>();
        serviceList.add(service1);
        serviceList.add(service2);

        //When
        List<ServiceDto> serviceDtoList = serviceMapper.toServiceDtoList(serviceList);

        //Then
        Assert.assertEquals(2, serviceDtoList.size());
        assertThat(serviceDtoList.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", serviceList.get(0).getId())
                .hasFieldOrPropertyWithValue("title", serviceList.get(0).getTitle())
                .hasFieldOrPropertyWithValue("regularUnitPrice", serviceList.get(0).getRegularUnitPrice())
                .hasFieldOrPropertyWithValue("discountUnitPrice", serviceList.get(0).getDiscountUnitPrice())
                .hasFieldOrPropertyWithValue("thresholdValue", serviceList.get(0).getThresholdValue())
                .hasFieldOrPropertyWithValue("serviceTypeId", serviceList.get(0).getServiceType().getId());
        assertThat(serviceDtoList.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", serviceList.get(1).getId())
                .hasFieldOrPropertyWithValue("title", serviceList.get(1).getTitle())
                .hasFieldOrPropertyWithValue("regularUnitPrice", serviceList.get(1).getRegularUnitPrice())
                .hasFieldOrPropertyWithValue("discountUnitPrice", serviceList.get(1).getDiscountUnitPrice())
                .hasFieldOrPropertyWithValue("thresholdValue", serviceList.get(1).getThresholdValue())
                .hasFieldOrPropertyWithValue("serviceTypeId", serviceList.get(1).getServiceType().getId());
    }
}
