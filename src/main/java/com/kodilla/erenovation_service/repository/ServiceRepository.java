package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {

    List<Service> findAll();

    Optional<Service> findByTitle(final String serviceTitle);
}
