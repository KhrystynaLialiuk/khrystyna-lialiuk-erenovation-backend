package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.dto.PricingDto;
import com.kodilla.erenovation_service.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
public class PricingControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricingService pricingService;

    @Test
    public void shouldCreatePricingForUser() throws Exception {
        //Given
        PricingDto pricingDto = new PricingDto(1L, 1L, LocalDate.now(), new BigDecimal(0),
                null,null);
        when(pricingService.createPricingFor(1)).thenReturn(pricingDto);

        //When & Then
        mockMvc.perform(post("/v1/pricing")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                //.andExpect(jsonPath("$.date", is("2019-12-24")))   should be edited for tests
                .andExpect(jsonPath("$.price", is(0)));
    }

    @Test
    public void shouldGetAllPricingForUser() throws Exception {
        //Given
        PricingDto pricingDto1 = new PricingDto(1L, 1L, LocalDate.now(), new BigDecimal(1),
                null,null);
        PricingDto pricingDto2 = new PricingDto(2L, 1L, LocalDate.now(), new BigDecimal(2),
                null,null);
        List<PricingDto> pricingDtoList = new ArrayList<>();
        pricingDtoList.add(pricingDto1);
        pricingDtoList.add(pricingDto2);
        when(pricingService.getPricingsForUser(1)).thenReturn(pricingDtoList);

        //When & Then
        mockMvc.perform(get("/v1/pricing/all")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                //.andExpect(jsonPath("$[0].date", is("2019-12-24")))   should be edited for tests
                .andExpect(jsonPath("$[0].price", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].userId", is(1)))
                .andExpect(jsonPath("$[1].date", is("2019-12-24")))
                .andExpect(jsonPath("$[1].price", is(2)));
    }

    @Test
    public void shouldGetPricingById() throws Exception {
        //Given
        PricingDto pricingDto = new PricingDto(1L, 1L, LocalDate.now(), new BigDecimal(0),
                null,null);
        when(pricingService.getPricing(1)).thenReturn(pricingDto);

        //When & Then
        mockMvc.perform(get("/v1/pricing")
                .param("pricingId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                //.andExpect(jsonPath("$.date", is("2019-12-24")))   should be edited for tests
                .andExpect(jsonPath("$.price", is(0)));
    }
}
