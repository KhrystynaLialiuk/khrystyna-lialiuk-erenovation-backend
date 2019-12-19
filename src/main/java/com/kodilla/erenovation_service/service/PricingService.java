package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.dto.PricingDto;
import com.kodilla.erenovation_service.exception.PricingNotFoundException;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.mapper.PricingMapper;
import com.kodilla.erenovation_service.repository.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private PricingMapper pricingMapper;

    @Autowired
    private CalculationService calculationService;

    public PricingDto createPricingFor(final long userId) throws UserNotFoundException {
        PricingDto pricingDto = new PricingDto();
        pricingDto.setUserId(userId);
        pricingDto.setDate(LocalDate.now());
        pricingDto.setPrice(new BigDecimal(0));
        return pricingMapper.toPricingDto(pricingRepository.save(pricingMapper.toPricing(pricingDto)));
    }

    public List<PricingDto> getPricings() {
        return pricingMapper.toPricingDtoList(pricingRepository.findAll());
    }

    public PricingDto getPricing(final long pricingId) throws PricingNotFoundException {
        return pricingMapper.toPricingDto(pricingRepository.findById(pricingId)
                .orElseThrow(PricingNotFoundException::new));
    }

    public PricingDto updatePricing(final PricingDto pricingDto) throws UserNotFoundException{
        PricingDto updatedPricing = calculationService.calculatePricingDto(pricingDto);
        return pricingMapper.toPricingDto(pricingRepository.save(pricingMapper.toPricing(updatedPricing)));
    }
}
