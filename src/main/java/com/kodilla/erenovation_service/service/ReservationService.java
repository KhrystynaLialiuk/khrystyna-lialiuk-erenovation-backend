package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.domain.User;
import com.kodilla.erenovation_service.dto.ReservationDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.ReservationAddressNotFoundException;
import com.kodilla.erenovation_service.exception.ReservationNotFoundException;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.mapper.ReservationMapper;
import com.kodilla.erenovation_service.repository.ReservationRepository;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private UserRepository userRepository;

    public List<ReservationDto> getReservationsByUserId(final long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return reservationMapper.toReservationDtoList(reservationRepository.findByUser(user.get()));
        }
        return new ArrayList<>();
    }

    public void createReservation(final ReservationDto reservationDto) throws
            UserNotFoundException, PricingNotFoundException, ReservationAddressNotFoundException {
        reservationRepository.save(reservationMapper.toReservation(reservationDto));
    }

    public void updateReservation(final ReservationDto reservationDto) throws UserNotFoundException,
            PricingNotFoundException, ReservationAddressNotFoundException, ReservationNotFoundException {
        if (reservationRepository.findById(reservationDto.getId()).isPresent()) {
            reservationRepository.save(reservationMapper.toReservation(reservationDto));
        } else {
            throw new ReservationNotFoundException();
        }
    }

    public void deleteById(final long reservationId) throws ReservationNotFoundException {
        try {
            reservationRepository.deleteById(reservationId);
        } catch (IllegalArgumentException e) {
            throw new ReservationNotFoundException();
        }
    }

}
