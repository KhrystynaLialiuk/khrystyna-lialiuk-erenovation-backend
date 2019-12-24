package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.Reservation;
import com.kodilla.erenovation_service.dto.ReservationDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.ReservationAddressNotFoundException;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.repository.PricingRepository;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private ReservationAddressMapper reservationAddressMapper;

    public Reservation toReservation(ReservationDto reservationDto)
            throws UserNotFoundException, PricingNotFoundException {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setDate(reservationDto.getDate());
        reservation.setTransportationCost(reservationDto.getTransportationCost());
        reservation.setUser(userRepository.findById(reservationDto.getUserId())
                .orElseThrow(UserNotFoundException::new));
        reservation.setPricing(pricingRepository.findById(reservationDto.getPricingId())
                .orElseThrow(PricingNotFoundException::new));
        if (reservationDto.getReservationAddressDto() != null)  {
            reservation.setReservationAddress(reservationAddressMapper
                    .toReservationAddress(reservationDto.getReservationAddressDto()));
        }
        return reservation;
    }

    public ReservationDto toReservationDto(final Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setDate(reservation.getDate());
        reservationDto.setTransportationCost(reservation.getTransportationCost());
        reservationDto.setUserId(reservation.getUser().getId());
        reservationDto.setPricingId(reservation.getPricing().getId());
        if (reservation.getReservationAddress() != null) {
            reservationDto.setReservationAddressDto(reservationAddressMapper
                    .toReservationAddressDto(reservation.getReservationAddress()));
        }
        return reservationDto;
    }

    public List<ReservationDto> toReservationDtoList(List<Reservation> reservationList) {
        return reservationList.stream()
                .map(this::toReservationDto)
                .collect(Collectors.toList());
    }
}
