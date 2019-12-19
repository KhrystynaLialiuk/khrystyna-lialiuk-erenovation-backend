package com.kodilla.erenovation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pricing {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @Column(name = "DATE")
    private LocalDate date;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToMany(
            targetEntity = PricingRecord.class,
            mappedBy = "pricing",
            cascade = CascadeType.ALL
    )
    private List<PricingRecord> pricingRecords = new ArrayList<>();

    @OneToMany(
            targetEntity = Reservation.class,
            mappedBy = "pricing"
    )
    private List<Reservation> reservations = new ArrayList<>();
}
