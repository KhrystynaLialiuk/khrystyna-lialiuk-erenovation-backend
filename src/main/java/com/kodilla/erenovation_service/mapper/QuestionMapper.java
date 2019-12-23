package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.Question;
import com.kodilla.erenovation_service.dto.QuestionDto;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    @Autowired
    private UserRepository userRepository;

    public Question toQuestion(final QuestionDto questionDto) throws UserNotFoundException {
        Question question = new Question();
        question.setId(questionDto.getId());
        question.setQuestion(questionDto.getQuestion());
        question.setDate(questionDto.getDate());
        question.setAnswer(questionDto.getAnswer());
        question.setUser(userRepository.findById(questionDto.getUserId()).orElseThrow(UserNotFoundException::new));
        return question;
    }

    public QuestionDto toQuestionDto(final Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setQuestion(question.getQuestion());
        questionDto.setDate(question.getDate());
        questionDto.setAnswer(question.getAnswer());
        questionDto.setUserId(question.getUser().getId());
        return questionDto;
    }

    public List<QuestionDto> toQuestionDtoList(List<Question> questionList) {
        return questionList.stream()
                .map(this::toQuestionDto)
                .collect(Collectors.toList());
    }
}
