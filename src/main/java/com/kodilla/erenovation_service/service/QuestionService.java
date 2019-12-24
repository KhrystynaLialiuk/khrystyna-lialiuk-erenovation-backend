package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.domain.Question;
import com.kodilla.erenovation_service.domain.User;
import com.kodilla.erenovation_service.dto.QuestionDto;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.mapper.QuestionMapper;
import com.kodilla.erenovation_service.repository.QuestionRepository;
import com.kodilla.erenovation_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionDto> getQuestionsByUserId(final long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return questionMapper.toQuestionDtoList(questionRepository.findByUser(user.get()));
        }
        return new ArrayList<>();
    }

    public HttpStatus createQuestion(final QuestionDto questionDto) throws UserNotFoundException {
        questionDto.setDate(LocalDate.now());
        questionRepository.save(questionMapper.toQuestion(questionDto));
        return HttpStatus.CREATED;
    }

    @Scheduled(cron = "${frequency}")
    public void getQuestionsWithoutAnswer() {
        List<Question> questions = questionRepository.findByAnswer(null);
        log.info("PENDING QUESTIONS: " + questions.size());
    }
}
