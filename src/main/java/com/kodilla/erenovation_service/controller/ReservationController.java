package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.dto.ReservationDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.ReservationAddressNotFoundException;
import com.kodilla.erenovation_service.exception.ReservationNotFoundException;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/reservation")
@CrossOrigin("*")
@Slf4j
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationDto> getReservationsByUserId(@RequestParam long userId) {
        log.info("Getting list of reservations for user with ID {}", userId);
        return reservationService.getReservationsByUserId(userId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createReservation(@RequestBody ReservationDto reservationDto) throws
            UserNotFoundException, PricingNotFoundException, ReservationAddressNotFoundException {
        log.info("Creating new reservation");
        return new ResponseEntity<>(reservationService.createReservation(reservationDto));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public void updateReservation(@RequestBody ReservationDto reservationDto) throws UserNotFoundException,
            PricingNotFoundException, ReservationAddressNotFoundException, ReservationNotFoundException {
        log.info("Updating reservation with ID {}", reservationDto.getId());
        reservationService.updateReservation(reservationDto);
    }

    @DeleteMapping
    public void deleteReservationById(@RequestParam long reservationId) throws ReservationNotFoundException {
        log.info("Deleting reservation with ID {}", reservationId);
        reservationService.deleteById(reservationId);
    }
}
