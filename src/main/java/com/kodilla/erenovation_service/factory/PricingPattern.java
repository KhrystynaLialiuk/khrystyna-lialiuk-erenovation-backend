package com.kodilla.erenovation_service.factory;

import com.kodilla.erenovation_service.domain.Service;

import java.math.BigDecimal;

public interface PricingPattern {
    String getTitle();
    BigDecimal getPrice(Service service, int quantity);
}
