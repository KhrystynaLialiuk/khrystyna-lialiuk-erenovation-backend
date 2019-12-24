package com.kodilla.erenovation_service.controller;

import com.google.gson.Gson;
import com.kodilla.erenovation_service.dto.UserAddressDto;
import com.kodilla.erenovation_service.service.UserAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserAddressController.class)
public class UserAddressControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserAddressService userAddressService;

    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";

    @Test
    public void shouldGetAddressById() throws Exception {
        //Given
        UserAddressDto userAddressDto = new UserAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        when(userAddressService.findUserAddressById(1)).thenReturn(userAddressDto);

        //When & Then
        mockMvc.perform(get("/v1/address")
                .param("userAddressId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.city", is(CITY)))
                .andExpect(jsonPath("$.street", is(STREET)))
                .andExpect(jsonPath("$.building", is(BUILDING)))
                .andExpect(jsonPath("$.apartment", is(APARTMENT)))
                .andExpect(jsonPath("$.postalCode", is(POSTAL_CODE)));
    }

    @Test
    public void shouldUpdateAddress() throws Exception {
        //Given
        UserAddressDto userAddressDto = new UserAddressDto(1L, CITY, STREET, BUILDING,
                APARTMENT, POSTAL_CODE);
        when(userAddressService.updateUserAddress(ArgumentMatchers.any(UserAddressDto.class)))
                .thenReturn(userAddressDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userAddressDto);

        //When
        mockMvc.perform(put("/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.city", is(CITY)))
                .andExpect(jsonPath("$.street", is(STREET)))
                .andExpect(jsonPath("$.building", is(BUILDING)))
                .andExpect(jsonPath("$.apartment", is(APARTMENT)))
                .andExpect(jsonPath("$.postalCode", is(POSTAL_CODE)));
    }
}
