package com.kodilla.erenovation_service.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.erenovation_service.configuration.LocalDateAdapter;
import com.kodilla.erenovation_service.dto.ReservationAddressDto;
import com.kodilla.erenovation_service.dto.ReservationDto;
import com.kodilla.erenovation_service.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";

    @Test
    public void shouldGetReservationsByUserId() throws Exception {
        //Given
        ReservationAddressDto reservationAddressDto = new ReservationAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L, LocalDate.now(),
                new BigDecimal(10), reservationAddressDto);
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        reservationDtoList.add(reservationDto);

        when(reservationService.getReservationsByUserId(1)).thenReturn(reservationDtoList);

        //When & Then
        mockMvc.perform(get("/v1/reservation")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].pricingId", is(1)))
                //.andExpect(jsonPath("$[0].date", is("2019-12-24")))  should be edited for tests
                .andExpect(jsonPath("$[0].transportationCost", is(10)))
                .andExpect(jsonPath("$[0].reservationAddressDto.id", is(1)))
                .andExpect(jsonPath("$[0].reservationAddressDto.city", is(CITY)))
                .andExpect(jsonPath("$[0].reservationAddressDto.street", is(STREET)))
                .andExpect(jsonPath("$[0].reservationAddressDto.building", is(BUILDING)))
                .andExpect(jsonPath("$[0].reservationAddressDto.apartment", is(APARTMENT)))
                .andExpect(jsonPath("$[0].reservationAddressDto.postalCode", is(POSTAL_CODE)));
    }

    @Test
    public void shouldCreateReservation() throws Exception {
        //Given
        ReservationAddressDto reservationAddressDto = new ReservationAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L, LocalDate.now(),
                new BigDecimal(10), reservationAddressDto);

        when(reservationService.createReservation(ArgumentMatchers.any(ReservationDto.class)))
                .thenReturn(HttpStatus.CREATED);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(reservationDto);

        //When & Then
        mockMvc.perform(post("/v1/reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(201));
    }

    @Test
    public void shouldUpdateReservation() throws Exception {
        //Given
        ReservationAddressDto reservationAddressDto = new ReservationAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        ReservationDto reservationDto = new ReservationDto(1L, 1L, 1L, LocalDate.now(),
                new BigDecimal(10), reservationAddressDto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(reservationDto);

        //When & Then
        mockMvc.perform(put("/v1/reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteReservationById() throws Exception {
        mockMvc.perform(delete("/v1/reservation")
                .param("reservationId", "1"))
                .andExpect(status().isOk());
    }

}
