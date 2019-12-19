package com.kodilla.erenovation_service.factory;

import com.kodilla.erenovation_service.domain.Service;
import com.kodilla.erenovation_service.strategy.PriceCalculator;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class DiscountPattern implements PricingPattern {

    final private String title;
    private PriceCalculator priceCalculator;

    public DiscountPattern(String title, PriceCalculator priceCalculator) {
        this.title = title;
        this.priceCalculator = priceCalculator;
        log.info("Discount pricing pattern created");
    }

    public String getTitle() {
        return this.title;
    }

    public BigDecimal getPrice(Service service, int quantity) {
        return priceCalculator.calculatePrice(service, quantity);
    }
}
