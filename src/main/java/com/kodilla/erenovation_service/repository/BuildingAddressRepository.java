package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.BuildingAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingAddressRepository extends CrudRepository<BuildingAddress, Long> {
}
