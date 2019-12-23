package com.kodilla.erenovation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private long id;
    private long userId;
    private long pricingId;
    private LocalDate date;
    private BigDecimal transportationCost;
    private ReservationAddressDto reservationAddressDto;
}
