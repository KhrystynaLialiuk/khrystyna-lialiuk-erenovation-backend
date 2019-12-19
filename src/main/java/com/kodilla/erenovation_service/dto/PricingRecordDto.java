package com.kodilla.erenovation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PricingRecordDto {
    private long id;
    private int quantityOrMeters;
    private BigDecimal price;
    private String priceType;
    private long pricingId;
    private long serviceId;
    private String serviceTitle;
}
