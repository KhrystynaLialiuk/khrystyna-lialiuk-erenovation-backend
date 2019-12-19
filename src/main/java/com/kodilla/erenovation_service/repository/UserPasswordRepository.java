package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.UserPassword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPasswordRepository extends CrudRepository<UserPassword, Long> {
    @Override
    List<UserPassword> findAll();
}
