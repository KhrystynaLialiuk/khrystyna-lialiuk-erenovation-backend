package com.kodilla.erenovation_service.domain;

import com.kodilla.erenovation_service.repository.QuestionRepository;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

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

    private static final String QUESTION = "What is the price for...";
    private static final String ANSWER = "100 PLN";
    private static final LocalDate DATE = LocalDate.now();

    private User savedUser;
    private Question question;
    private Question savedQuestion;

    @Before
    public void prepare() {
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword(PASSWORD);

        UserAddress userAddress = new UserAddress();
        userAddress.setCity(CITY);
        userAddress.setStreet(STREET);
        userAddress.setBuilding(BUILDING);
        userAddress.setApartment(APARTMENT);
        userAddress.setPostalCode(POSTAL_CODE);

        User user = new User();
        user.setName(NAME);
        user.setSurname(SURNAME);
        user.setEmail(EMAIL);
        user.setPhone(PHONE);
        user.setUserPassword(userPassword);
        user.setUserAddress(userAddress);
        savedUser = userRepository.save(user);

        question = new Question();
        question.setQuestion(QUESTION);
        question.setDate(DATE);
        question.setUser(savedUser);
        question.setAnswer(ANSWER);
    }

    @Test
    public void testSaveQuestion() {
        //Given

        //When
        savedQuestion = questionRepository.save(question);

        //Then
        long id = savedQuestion.getId();
        Assert.assertNotEquals(0, id);

        Optional<Question> foundById = questionRepository.findById(id);
        Assert.assertTrue(foundById.isPresent());
    }

    @Test
    public void findByUser() {
        //Given
        savedQuestion = questionRepository.save(question);

        //When
        List<Question> foundByUser = questionRepository.findByUser(savedUser);

        //Then
        Assert.assertEquals(1, foundByUser.size());
    }

    @Test
    public void findByAnswer() {
        //Given
        savedQuestion = questionRepository.save(question);

        //When
        List<Question> foundByAnswer = questionRepository.findByAnswer(ANSWER);

        //Then
        Assert.assertEquals(1, foundByAnswer.size());
    }

    @Test
    public void shouldNotFindByAnswer() {
        //Given
        savedQuestion = questionRepository.save(question);

        //When
        List<Question> notFoundByAnswer = questionRepository.findByAnswer(null);

        //Then
        Assert.assertEquals(0, notFoundByAnswer.size());
    }

    @After
    public void cleanUp() {
        //CleanUp
        questionRepository.deleteById(savedQuestion.getId());
        userRepository.deleteById(savedUser.getId());
    }
}
