package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.ReservationAddress;
import com.kodilla.erenovation_service.dto.ReservationAddressDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationAddressMapper {

    public ReservationAddressDto toReservationAddressDto(final ReservationAddress reservationAddress) {
        ReservationAddressDto reservationAddressDto = new ReservationAddressDto();
        reservationAddressDto.setId(reservationAddress.getId());
        reservationAddressDto.setCity(reservationAddress.getCity());
        reservationAddressDto.setStreet(reservationAddress.getStreet());
        reservationAddressDto.setBuilding(reservationAddress.getBuilding());
        reservationAddressDto.setApartment(reservationAddress.getApartment());
        reservationAddressDto.setPostalCode(reservationAddress.getPostalCode());
        return reservationAddressDto;
    }

    public ReservationAddress toReservationAddress(final ReservationAddressDto reservationAddressDto) {
        ReservationAddress reservationAddress = new ReservationAddress();
        reservationAddress.setId(reservationAddressDto.getId());
        reservationAddress.setCity(reservationAddressDto.getCity());
        reservationAddress.setStreet(reservationAddressDto.getStreet());
        reservationAddress.setBuilding(reservationAddressDto.getBuilding());
        reservationAddress.setApartment(reservationAddressDto.getApartment());
        reservationAddress.setPostalCode(reservationAddressDto.getPostalCode());
        return reservationAddress;
    }
}
