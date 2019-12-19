package com.kodilla.erenovation_service.factory;

import com.kodilla.erenovation_service.strategy.DiscountCalculator;
import com.kodilla.erenovation_service.strategy.RegularCalculator;
import org.springframework.stereotype.Component;

@Component
public final class PatternFactory {
    public static final String DISCOUNT = "DISCOUNT";
    public static final String STANDARD = "STANDARD";

    public final PricingPattern createPricingPattern(final String pattern) {
        switch (pattern) {
            case DISCOUNT:
                return new DiscountPattern("Discount", new DiscountCalculator());
            default:
                return new StandardPattern("Standard", new RegularCalculator());
        }
    }
}
