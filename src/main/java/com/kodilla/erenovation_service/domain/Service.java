package com.kodilla.erenovation_service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Service {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "TITLE", unique = true)
    private String title;

    @NotNull
    @Column(name = "REGULAR_UNIT_PRICE")
    private BigDecimal regularUnitPrice;

    @NotNull
    @Column(name = "DISCOUNT_UNIT_PRICE")
    private BigDecimal discountUnitPrice;

    @NotNull
    @Column(name = "THRESHOLD_VALUE")
    private int thresholdValue;

    @ManyToOne
    @JoinColumn(name = "SERVICE_TYPE")
    private ServiceType serviceType;

    @OneToMany(
            targetEntity = PricingRecord.class,
            mappedBy = "service"
    )
    private List<PricingRecord> records = new ArrayList<>();
}
