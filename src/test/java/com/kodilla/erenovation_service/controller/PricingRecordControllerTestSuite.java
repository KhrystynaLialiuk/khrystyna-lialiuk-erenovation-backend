package com.kodilla.erenovation_service.controller;

import com.google.gson.Gson;
import com.kodilla.erenovation_service.dto.PricingRecordDto;
import com.kodilla.erenovation_service.service.PricingRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.ArgumentMatchers;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingRecordController.class)
public class PricingRecordControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricingRecordService pricingRecordService;

    @Test
    public void shouldCreatePricingRecord() throws Exception {
        //Given
        PricingRecordDto pricingRecordDto = new PricingRecordDto(1L, 100, new BigDecimal(1),
                "TestingType", 1L, 1L, "TestingTitle");
        when(pricingRecordService.createPricingRecord(ArgumentMatchers.any(PricingRecordDto.class)))
                .thenReturn(HttpStatus.CREATED);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(pricingRecordDto);

        //When & Then
        mockMvc.perform(post("/v1/pricingposition")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(201));
    }

    @Test
    public void shouldUpdatePricingRecord() throws Exception {
        //Given
        PricingRecordDto pricingRecordDto = new PricingRecordDto(1L, 100, new BigDecimal(1),
                "TestingType", 1L, 1L, "TestingTitle");
        when(pricingRecordService.updatePricingRecord(ArgumentMatchers.any(PricingRecordDto.class)))
                .thenReturn(pricingRecordDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(pricingRecordDto);

        //When & Then
        mockMvc.perform(put("/v1/pricingposition")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.quantityOrMeters", is(100)))
                .andExpect(jsonPath("$.price", is(1)))
                .andExpect(jsonPath("$.priceType", is("TestingType")))
                .andExpect(jsonPath("$.pricingId", is(1)))
                .andExpect(jsonPath("$.serviceId", is(1)))
                .andExpect(jsonPath("$.serviceTitle", is("TestingTitle")));
    }

    @Test
    public void shouldDeletePricingRecord() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/pricingposition")
                .param("pricingRecordId", "1")
                .param("pricingId", "1"))
                .andExpect(status().isOk());
    }
}
