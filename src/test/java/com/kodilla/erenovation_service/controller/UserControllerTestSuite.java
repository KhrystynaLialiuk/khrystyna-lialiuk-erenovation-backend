package com.kodilla.erenovation_service.controller;

import com.google.gson.Gson;
import com.kodilla.erenovation_service.data.ServiceDataCreator;
import com.kodilla.erenovation_service.dto.RegistrationDto;
import com.kodilla.erenovation_service.dto.UserAddressDto;
import com.kodilla.erenovation_service.dto.UserDto;
import com.kodilla.erenovation_service.dto.UserPasswordDto;
import com.kodilla.erenovation_service.service.UserService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ServiceDataCreator serviceDataCreator;

    private static final String NAME = "John";
    private static final String SURNAME = "Smith";
    private static final String EMAIL = "test@test.com";
    private static final String PHONE = "123456789";
    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";
    private static final String PASSWORD = "Password";

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        RegistrationDto registrationDto = new RegistrationDto(NAME, SURNAME, PHONE, EMAIL, PASSWORD, CITY,
                STREET, BUILDING, APARTMENT, POSTAL_CODE);
        when(userService.saveUser(ArgumentMatchers.any(RegistrationDto.class))).thenReturn(HttpStatus.CREATED);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(registrationDto);

        //When & Then
        mockMvc.perform(post("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(201));
    }

    @Test
    public void shouldGetUserById() throws Exception {
        //Given
        UserAddressDto userAddressDto = new UserAddressDto(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPasswordDto userPasswordDto = new UserPasswordDto(1L, PASSWORD);
        UserDto userDto = new UserDto(1L, NAME, SURNAME, PHONE, EMAIL, userAddressDto, userPasswordDto);

        when(userService.findUserById(1)).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/user")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.surname", is(SURNAME)))
                .andExpect(jsonPath("$.phone", is(PHONE)))
                .andExpect(jsonPath("$.email", is(EMAIL)))
                .andExpect(jsonPath("$.userAddressDto.id", is(1)))
                .andExpect(jsonPath("$.userAddressDto.city", is(CITY)))
                .andExpect(jsonPath("$.userAddressDto.street", is(STREET)))
                .andExpect(jsonPath("$.userAddressDto.building", is(BUILDING)))
                .andExpect(jsonPath("$.userAddressDto.apartment", is(APARTMENT)))
                .andExpect(jsonPath("$.userAddressDto.postalCode", is(POSTAL_CODE)))
                .andExpect(jsonPath("$.userPasswordDto.id", is(1)))
                .andExpect(jsonPath("$.userPasswordDto.password", is(PASSWORD)));
    }

    @Test
    public void shouldGetUserByEmailAndPassword() throws Exception {
        //Given
        UserAddressDto userAddressDto = new UserAddressDto(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPasswordDto userPasswordDto = new UserPasswordDto(1L, PASSWORD);
        UserDto userDto = new UserDto(1L, NAME, SURNAME, PHONE, EMAIL, userAddressDto, userPasswordDto);

        when(userService.findUserByEmailAndPassword(EMAIL, PASSWORD)).thenReturn(userDto);

        //When & Then
        mockMvc.perform(get("/v1/user/validation")
                .param("email", EMAIL)
                .param("password", PASSWORD))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.surname", is(SURNAME)))
                .andExpect(jsonPath("$.phone", is(PHONE)))
                .andExpect(jsonPath("$.email", is(EMAIL)))
                .andExpect(jsonPath("$.userAddressDto.id", is(1)))
                .andExpect(jsonPath("$.userAddressDto.city", is(CITY)))
                .andExpect(jsonPath("$.userAddressDto.street", is(STREET)))
                .andExpect(jsonPath("$.userAddressDto.building", is(BUILDING)))
                .andExpect(jsonPath("$.userAddressDto.apartment", is(APARTMENT)))
                .andExpect(jsonPath("$.userAddressDto.postalCode", is(POSTAL_CODE)))
                .andExpect(jsonPath("$.userPasswordDto.id", is(1)))
                .andExpect(jsonPath("$.userPasswordDto.password", is(PASSWORD)));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        UserAddressDto userAddressDto = new UserAddressDto(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPasswordDto userPasswordDto = new UserPasswordDto(1L, PASSWORD);
        UserDto userDto = new UserDto(1L, NAME, SURNAME, PHONE, EMAIL, userAddressDto, userPasswordDto);

        when(userService.updateUser(ArgumentMatchers.any(UserDto.class))).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(put("/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.surname", is(SURNAME)))
                .andExpect(jsonPath("$.phone", is(PHONE)))
                .andExpect(jsonPath("$.email", is(EMAIL)))
                .andExpect(jsonPath("$.userAddressDto.id", is(1)))
                .andExpect(jsonPath("$.userAddressDto.city", is(CITY)))
                .andExpect(jsonPath("$.userAddressDto.street", is(STREET)))
                .andExpect(jsonPath("$.userAddressDto.building", is(BUILDING)))
                .andExpect(jsonPath("$.userAddressDto.apartment", is(APARTMENT)))
                .andExpect(jsonPath("$.userAddressDto.postalCode", is(POSTAL_CODE)))
                .andExpect(jsonPath("$.userPasswordDto.id", is(1)))
                .andExpect(jsonPath("$.userPasswordDto.password", is(PASSWORD)));
    }
}
