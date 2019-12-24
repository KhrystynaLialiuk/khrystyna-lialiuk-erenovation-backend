package com.kodilla.erenovation_service.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.erenovation_service.configuration.LocalDateAdapter;
import com.kodilla.erenovation_service.dto.QuestionDto;
import com.kodilla.erenovation_service.service.QuestionService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @Test
    public void shouldGetQuestionsByUserId() throws Exception {
        //Given
        QuestionDto questionDto = new QuestionDto(1L, 1L, "TestQuestion",
                LocalDate.now(), "TestAnswer");
        List<QuestionDto> questionDtoList = new ArrayList<>();
        questionDtoList.add(questionDto);

        when(questionService.getQuestionsByUserId(1)).thenReturn(questionDtoList);

        //When & Then
        mockMvc.perform(get("/v1/question/all")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].question", is("TestQuestion")))
                //.andExpect(jsonPath("$[0].date", is("2019-12-24")))       should be edited for tests
                .andExpect(jsonPath("$[0].answer", is("TestAnswer")));
    }

    @Test
    public void shouldCreateQuestion() throws Exception {
        //Given
        QuestionDto questionDto = new QuestionDto(1L, 1L, "TestQuestion",
                LocalDate.of(2019, 12, 24), "TestAnswer");
        when(questionService.createQuestion(ArgumentMatchers.any(QuestionDto.class))).thenReturn(HttpStatus.CREATED);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(questionDto);

        //When & Then
        mockMvc.perform(post("/v1/question")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(201));
    }
}
