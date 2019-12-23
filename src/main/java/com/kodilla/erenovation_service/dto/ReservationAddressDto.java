package com.kodilla.erenovation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationAddressDto {
    private long id;
    private String city;
    private String street;
    private int building;
    private int apartment;
    private String postalCode;
}
