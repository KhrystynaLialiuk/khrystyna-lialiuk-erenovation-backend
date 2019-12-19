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
public class ServiceDto {
    private long id;
    private String title;
    private BigDecimal regularUnitPrice;
    private BigDecimal discountUnitPrice;
    private int thresholdValue;
    private long serviceTypeId;
}
