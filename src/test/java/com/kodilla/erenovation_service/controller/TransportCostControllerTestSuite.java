package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.service.TransportCostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransportCostController.class)
public class TransportCostControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransportCostService transportCostService;

    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final String POSTAL_CODE = "12345";

    @Test
    public void shouldGetCost() throws Exception {
        //Given
        when(transportCostService.calculateTransportCost(CITY, STREET, POSTAL_CODE))
                .thenReturn(new BigDecimal(10));

        //When & Then
        mockMvc.perform(get("/v1/transport")
                .param("city", CITY)
                .param("street", STREET)
                .param("postalcode", POSTAL_CODE))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$", is(10)));
    }
}
