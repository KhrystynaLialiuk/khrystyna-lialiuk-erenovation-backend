package com.kodilla.erenovation_service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.kodilla.erenovation_service.domain.*;
import com.kodilla.erenovation_service.dto.PricingDto;
import com.kodilla.erenovation_service.dto.PricingRecordDto;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
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

@RunWith(MockitoJUnitRunner.class)
public class PricingMapperTestSuite {

    @InjectMocks
    private PricingMapper pricingMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PricingRecordMapper pricingRecordMapper;

    private static final LocalDate DATE = LocalDate.now();
    private static final BigDecimal PRICE = new BigDecimal(0);
    private static final BigDecimal PRICE2 = new BigDecimal(5);

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

    private static final String PRICE_TYPE = "Regular";
    private static final Integer QUANTITY_OR_METERS = 2;
    private static final String TITLE = "Wall preparation";

    @Test
    public void testToPricing() {
        //Given
        PricingDto pricingDto = new PricingDto(1L, 1L, DATE, PRICE, null, null);
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        //When
        try {
            Pricing pricing = pricingMapper.toPricing(pricingDto);

            //Then
            assertNotNull(pricing);
            assertEquals(pricingDto.getId(), pricing.getId());
            assertEquals(pricingDto.getDate(), pricing.getDate());
            assertEquals(pricingDto.getPrice(), pricing.getPrice());
            assertEquals(pricingDto.getUserId(), pricing.getUser().getId());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToPricingDto() {
        //Given
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);

        PricingRecord pricingRecord = new PricingRecord(1L, QUANTITY_OR_METERS, PRICE, PRICE_TYPE,
                null, null);
        PricingRecordDto pricingRecordDto = new PricingRecordDto(1L, QUANTITY_OR_METERS, PRICE,
                PRICE_TYPE, 1L, 1L, TITLE);

        List<PricingRecord> pricingRecordList = new ArrayList<>();
        pricingRecordList.add(pricingRecord);
        List<PricingRecordDto> pricingRecordDtoList = new ArrayList<>();
        pricingRecordDtoList.add(pricingRecordDto);

        Pricing pricing = new Pricing(1L, user, DATE, PRICE, pricingRecordList, null);

        when(pricingRecordMapper.toPricingRecordDtoList(pricingRecordList)).thenReturn(pricingRecordDtoList);

        //When
        PricingDto pricingDto = pricingMapper.toPricingDto(pricing);

        //Then
        assertNotNull(pricingDto);
        assertEquals(pricing.getId(), pricingDto.getId());
        assertEquals(pricing.getDate(), pricingDto.getDate());
        assertEquals(pricing.getPrice(), pricingDto.getPrice());
        assertEquals(pricing.getUser().getId(), pricingDto.getUserId());
        assertEquals(pricingRecordDtoList, pricingDto.getPricingRecordDtos());
    }

    @Test
    public void testToPricingDtoList() {
        //Given
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);

        Pricing pricing = new Pricing(1L, user, DATE, PRICE, null, null);
        Pricing pricing2 = new Pricing(2L, user, DATE, PRICE2, null, null);
        List<Pricing> pricingList = new ArrayList<>();
        pricingList.add(pricing);
        pricingList.add(pricing2);

        //When
        List<PricingDto> pricingDtoList = pricingMapper.toPricingDtoList(pricingList);

        //Then
        assertEquals(2, pricingDtoList.size());
        assertThat(pricingDtoList.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", pricingList.get(0).getId())
                .hasFieldOrPropertyWithValue("userId", pricingList.get(0).getUser().getId())
                .hasFieldOrPropertyWithValue("date", pricingList.get(0).getDate())
                .hasFieldOrPropertyWithValue("price", pricingList.get(0).getPrice());
        assertThat(pricingDtoList.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", pricingList.get(1).getId())
                .hasFieldOrPropertyWithValue("userId", pricingList.get(1).getUser().getId())
                .hasFieldOrPropertyWithValue("date", pricingList.get(1).getDate())
                .hasFieldOrPropertyWithValue("price", pricingList.get(1).getPrice());
    }
}
