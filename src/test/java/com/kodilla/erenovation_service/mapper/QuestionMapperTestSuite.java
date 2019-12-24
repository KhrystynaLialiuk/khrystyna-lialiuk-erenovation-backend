package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.Question;
import com.kodilla.erenovation_service.domain.User;
import com.kodilla.erenovation_service.domain.UserAddress;
import com.kodilla.erenovation_service.domain.UserPassword;
import com.kodilla.erenovation_service.dto.QuestionDto;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class QuestionMapperTestSuite {

    @InjectMocks
    private QuestionMapper questionMapper;

    @Mock
    private UserRepository userRepository;

    private static final String CITY = "Warsaw";
    private static final String STREET = "Main";
    private static final int BUILDING = 1;
    private static final int APARTMENT = 1;
    private static final String POSTAL_CODE = "12345";
    private static final String PASSWORD = "Password";
    private static final String NAME = "John";
    private static final String SURNAME = "Smith";
    private static final String EMAIL = "test@test.com";
    private static final String PHONE = "123456789";

    private static final String QUESTION = "What is the price for...";
    private static final String QUESTION2 = "What is ...";
    private static final String ANSWER = "100 PLN";
    private static final String ANSWER2 = "10 PLN";
    private static final LocalDate DATE = LocalDate.now();

    @Test
    public void testToQuestion() {
        //Given
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);
        QuestionDto questionDto = new QuestionDto(1L, 1L, QUESTION, DATE, ANSWER);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        //When
        try {
            Question question = questionMapper.toQuestion(questionDto);

            //Then
            assertNotNull(question);
            assertEquals(questionDto.getId(), question.getId());
            assertEquals(questionDto.getQuestion(), question.getQuestion());
            assertEquals(questionDto.getDate(), question.getDate());
            assertEquals(questionDto.getUserId(), question.getUser().getId());
            assertEquals(questionDto.getAnswer(), question.getAnswer());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToQuestionDto() {
        //Given
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);
        Question question = new Question(1L, user, QUESTION, DATE, ANSWER);

        //When
        QuestionDto questionDto = questionMapper.toQuestionDto(question);

        //Then
        assertNotNull(question);
        assertEquals(question.getId(), questionDto.getId());
        assertEquals(question.getQuestion(), questionDto.getQuestion());
        assertEquals(question.getDate(), questionDto.getDate());
        assertEquals(question.getUser().getId(), questionDto.getUserId());
        assertEquals(question.getAnswer(), questionDto.getAnswer());
    }

    @Test
    public void testToQuestionDtoList() {
        //Given
        UserAddress userAddress = new UserAddress(1L, CITY, STREET, BUILDING, APARTMENT, POSTAL_CODE);
        UserPassword userPassword = new UserPassword(1L, PASSWORD);
        User user = new User(1L, NAME, SURNAME, PHONE, EMAIL, userAddress, userPassword,
                null, null, null);
        Question question1 = new Question(1L, user, QUESTION, DATE, ANSWER);
        Question question2 = new Question(2L, user, QUESTION2, DATE, ANSWER2);
        List<Question> questionList = new ArrayList<>();
        questionList.add(question1);
        questionList.add(question2);

        //When
        List<QuestionDto> questionDtoList = questionMapper.toQuestionDtoList(questionList);

        //Then
        assertEquals(2, questionDtoList.size());
        assertThat(questionDtoList.get(0)).isNotNull()
                .hasFieldOrPropertyWithValue("id", questionList.get(0).getId())
                .hasFieldOrPropertyWithValue("userId", questionList.get(0).getUser().getId())
                .hasFieldOrPropertyWithValue("question", questionList.get(0).getQuestion())
                .hasFieldOrPropertyWithValue("date", questionList.get(0).getDate())
                .hasFieldOrPropertyWithValue("answer", questionList.get(0).getAnswer());
        assertThat(questionDtoList.get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("id", questionList.get(1).getId())
                .hasFieldOrPropertyWithValue("userId", questionList.get(1).getUser().getId())
                .hasFieldOrPropertyWithValue("question", questionList.get(1).getQuestion())
                .hasFieldOrPropertyWithValue("date", questionList.get(1).getDate())
                .hasFieldOrPropertyWithValue("answer", questionList.get(1).getAnswer());
    }
}
