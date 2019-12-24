package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.*;
import com.kodilla.erenovation_service.dto.PricingRecordDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.ServiceNotFoundException;
import com.kodilla.erenovation_service.repository.PricingRepository;
import com.kodilla.erenovation_service.repository.ServiceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PricingRecordMapperTestSuite {

    @InjectMocks
    private PricingRecordMapper pricingRecordMapper;

    @Mock
    private PricingRepository pricingRepository;

    @Mock
    private ServiceRepository serviceRepository;

    private static final String PRICE_TYPE = "Regular";
    private static final String PRICE_TYPE2 = "Standard";
    private static final Integer QUANTITY_OR_METERS = 2;
    private static final Integer QUANTITY_OR_METERS2 = 3;
    private static final BigDecimal PRICE = new BigDecimal(0);
    private static final BigDecimal PRICE2 = new BigDecimal(10);
    private static final String TITLE = "Wall preparation";
    private static final LocalDate DATE = LocalDate.now();

    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";
    private static final String PASSWORD = "Password";
    private static final String NAME = "John";
    private static final String SURNAME = "Smith";
    private static final String EMAIL = "test@test.com";
    private static final String PHONE = "123456789";

    private static final String SERVICE_TYPE_TITLE1 = "Painting";
    private static final String TITLE1 = "Wall preparation";
    private static final BigDecimal REGULAR_UNIT_PRICE = new BigDecimal(5);
    private static final BigDecimal DISCOUNT_UNIT_PRICE = new BigDecimal(4);
    private static final Integer THRESHOLD_VALUE = 100;

    @Test
    public void toPricingRecord() {
        //Given
        PricingRecordDto pricingRecordDto = new PricingRecordDto(1L, QUANTITY_OR_METERS, PRICE, PRICE_TYPE,
                1L, 1L, TITLE);

        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);

        Pricing pricing = new Pricing(1L, user, DATE, PRICE, null, null);

        ServiceType serviceType = new ServiceType(1L, SERVICE_TYPE_TITLE1, null);

        Service service = new Service(1L, TITLE1, REGULAR_UNIT_PRICE, DISCOUNT_UNIT_PRICE, THRESHOLD_VALUE,
                serviceType, null);
        when(pricingRepository.findById(1L)).thenReturn(java.util.Optional.of(pricing));
        when(serviceRepository.findById(1L)).thenReturn(java.util.Optional.of(service));

        //When
        try {
            PricingRecord pricingRecord = pricingRecordMapper.toPricingRecord(pricingRecordDto);

            //Then
            assertNotNull(pricingRecord);
            assertEquals(pricingRecord.getId(), pricingRecordDto.getId());
            assertEquals(pricingRecord.getPrice(), pricingRecordDto.getPrice());
            assertEquals(pricingRecord.getPriceType(), pricingRecordDto.getPriceType());
            assertEquals(pricingRecord.getQuantityOrMeters(), pricingRecordDto.getQuantityOrMeters());
            assertEquals(pricingRecord.getPricing().getId(), pricingRecordDto.getPricingId());
            assertEquals(pricingRecord.getService().getId(), pricingRecordDto.getServiceId());

        } catch (PricingNotFoundException e) {
            e.printStackTrace();
        } catch (ServiceNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toPricingRecordDto() {
        //Given
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);

        Pricing pricing = new Pricing(1L, user, DATE, PRICE, null, null);

        ServiceType serviceType = new ServiceType(1L, SERVICE_TYPE_TITLE1, null);
        Service service = new Service(1L, TITLE1, REGULAR_UNIT_PRICE, DISCOUNT_UNIT_PRICE, THRESHOLD_VALUE,
                serviceType, null);

        PricingRecord pricingRecord = new PricingRecord(1L, QUANTITY_OR_METERS, PRICE, PRICE_TYPE,
                pricing, service);

        //When
        PricingRecordDto pricingRecordDto = pricingRecordMapper.toPricingRecordDto(pricingRecord);

        //Then
        assertNotNull(pricingRecordDto);
        assertEquals(pricingRecord.getId(), pricingRecordDto.getId());
        assertEquals(pricingRecord.getQuantityOrMeters(), pricingRecordDto.getQuantityOrMeters());
        assertEquals(pricingRecord.getPrice(), pricingRecordDto.getPrice());
        assertEquals(pricingRecord.getPriceType(), pricingRecordDto.getPriceType());
        assertEquals(pricingRecord.getPricing().getId(), pricingRecordDto.getPricingId());
        assertEquals(pricingRecord.getService().getId(), pricingRecordDto.getServiceId());
    }

    @Test
    public void toPricingRecordDtoList() {
        //Given
        PricingRecord pricingRecord = new PricingRecord(1L, QUANTITY_OR_METERS, PRICE, PRICE_TYPE,
                null, null);
        PricingRecord pricingRecord2 = new PricingRecord(2L, QUANTITY_OR_METERS2, PRICE2, PRICE_TYPE2,
                null, null);
        List<PricingRecord> pricingRecordList = new ArrayList<>();
        pricingRecordList.add(pricingRecord);
        pricingRecordList.add(pricingRecord2);

        //When
        List<PricingRecordDto> pricingRecordDtoList = pricingRecordMapper.toPricingRecordDtoList(pricingRecordList);

        //Then
        assertEquals(2, pricingRecordDtoList.size());
        assertThat(pricingRecordDtoList.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", pricingRecordList.get(0).getId())
                .hasFieldOrPropertyWithValue("quantityOrMeters", pricingRecordList.get(0).getQuantityOrMeters())
                .hasFieldOrPropertyWithValue("price", pricingRecordList.get(0).getPrice())
                .hasFieldOrPropertyWithValue("priceType", pricingRecordList.get(0).getPriceType());
        assertThat(pricingRecordDtoList.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", pricingRecordList.get(1).getId())
                .hasFieldOrPropertyWithValue("quantityOrMeters", pricingRecordList.get(1).getQuantityOrMeters())
                .hasFieldOrPropertyWithValue("price", pricingRecordList.get(1).getPrice())
                .hasFieldOrPropertyWithValue("priceType", pricingRecordList.get(1).getPriceType());
    }
}

