package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.dto.QuestionDto;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/question")
@CrossOrigin("*")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "all")
    public List<QuestionDto> getQuestionsByUserId(@RequestParam long userId) {
        log.info("Getting list of questions for user with ID {}", userId);
        return questionService.getQuestionsByUserId(userId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createQuestion(@RequestBody QuestionDto questionDto) throws UserNotFoundException {
        log.info("Creating new question");
        return new ResponseEntity<>(questionService.createQuestion(questionDto));
    }
}
