package com.kodilla.erenovation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PricingRecord {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "QUANTITY_OR_METERS")
    private int quantityOrMeters;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull
    @Column(name = "PRICE_TYPE")
    private String priceType;

    @ManyToOne
    @JoinColumn(name = "PRICING_ID")
    private Pricing pricing;

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID")
    private Service service;
}
