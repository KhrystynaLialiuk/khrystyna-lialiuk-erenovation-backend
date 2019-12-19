package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.ServiceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends CrudRepository<ServiceType, Long> {
}
