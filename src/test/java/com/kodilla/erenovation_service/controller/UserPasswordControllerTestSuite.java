package com.kodilla.erenovation_service.controller;

import com.google.gson.Gson;
import com.kodilla.erenovation_service.dto.UserPasswordDto;
import com.kodilla.erenovation_service.service.UserPasswordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserPasswordController.class)
public class UserPasswordControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserPasswordService userPasswordService;

    private static final String PASSWORD = "Password";

    @Test
    public void shouldGetPasswordById() throws Exception {
        //Given
        UserPasswordDto userPasswordDto = new UserPasswordDto(1L, PASSWORD);
        when(userPasswordService.findUserPasswordById(1)).thenReturn(userPasswordDto);

        //When & Then
        mockMvc.perform(get("/v1/password")
                .param("userPasswordId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.password", is(PASSWORD)));
    }

    @Test
    public void shouldUpdatePassword() throws Exception {
        //Given
        UserPasswordDto userPasswordDto = new UserPasswordDto(1L, PASSWORD);
        when(userPasswordService.updateUserPassword(ArgumentMatchers.any(UserPasswordDto.class)))
                .thenReturn(userPasswordDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userPasswordDto);

        //When
        mockMvc.perform(put("/v1/password")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.password", is(PASSWORD)));
    }
}
