package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.Reservation;
import com.kodilla.erenovation_service.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository <Reservation, Long> {

    List<Reservation> findByUser(User user);

    List<Reservation> findAll();
}
