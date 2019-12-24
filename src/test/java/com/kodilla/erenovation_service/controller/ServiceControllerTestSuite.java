package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.service.ServiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ServiceController.class)
public class ServiceControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceService serviceService;

    @Test
    public void shouldGetAllServiceTitles() throws Exception {
        //Given
        List<String> serviceTitles = new ArrayList<>();
        serviceTitles.add("Wall preparation");
        serviceTitles.add("Painting, 1 layer");
        serviceTitles.add("Lavatory pan installation");

        when(serviceService.findAllServiceTitles()).thenReturn(serviceTitles);

        //When & Then
        mockMvc.perform(get("/v1/service/titles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("Wall preparation")))
                .andExpect(jsonPath("$[1]", is("Painting, 1 layer")))
                .andExpect(jsonPath("$[2]", is("Lavatory pan installation")));
    }
}
