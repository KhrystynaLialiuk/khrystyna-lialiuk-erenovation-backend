package com.kodilla.erenovation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRICING_ID")
    private Pricing pricing;

    @NotNull
    @Column(name = "DATE")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "BUILDING_ADDRESS_ID")
    private BuildingAddress buildingAddress;
}
