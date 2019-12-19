package com.kodilla.erenovation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PricingDto {
    private long id;
    private long userId;
    private LocalDate date;
    private BigDecimal price;
    private List<PricingRecordDto> pricingRecordDtos;
    private List<ReservationDto> reservationDtos;
}
