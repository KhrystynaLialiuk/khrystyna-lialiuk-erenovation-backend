package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.Question;
import com.kodilla.erenovation_service.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByUser(User user);

    List<Question> findByAnswer(String answer);
}
