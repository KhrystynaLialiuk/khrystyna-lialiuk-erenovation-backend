package com.kodilla.erenovation_service.strategy;

import com.kodilla.erenovation_service.domain.Service;

import java.math.BigDecimal;

public interface PriceCalculator {
    BigDecimal calculatePrice(Service service, int quantity);
}
