package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
