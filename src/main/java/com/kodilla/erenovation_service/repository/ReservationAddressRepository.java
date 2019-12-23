package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.ReservationAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationAddressRepository extends CrudRepository<ReservationAddress, Long> {
}
