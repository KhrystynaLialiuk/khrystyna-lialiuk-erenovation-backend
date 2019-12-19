package com.kodilla.erenovation_service.strategy;

import com.kodilla.erenovation_service.domain.Service;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class DiscountCalculator implements PriceCalculator {

    @Override
    public BigDecimal calculatePrice(Service service, int quantity) {
        log.info("Calculating discount price for {} service", service.getTitle());
        return service.getDiscountUnitPrice().multiply(new BigDecimal(quantity));
    }
}
