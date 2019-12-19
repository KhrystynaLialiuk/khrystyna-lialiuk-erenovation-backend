package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository <Reservation, Long> {
}
