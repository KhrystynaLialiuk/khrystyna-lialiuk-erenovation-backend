package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.Pricing;
import com.kodilla.erenovation_service.dto.PricingDto;
import com.kodilla.erenovation_service.exception.UserNotFoundException;
import com.kodilla.erenovation_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PricingMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PricingRecordMapper pricingRecordMapper;

    public Pricing toPricing(final PricingDto pricingDto) throws UserNotFoundException {
        Pricing pricing = new Pricing();
        pricing.setId(pricingDto.getId());
        pricing.setDate(pricingDto.getDate());
        pricing.setPrice(pricingDto.getPrice());
        pricing.setUser(userRepository.findById(pricingDto.getUserId()).orElseThrow(UserNotFoundException::new));
        return pricing;
    }

    public PricingDto toPricingDto(final Pricing pricing) {
        PricingDto pricingDto = new PricingDto();
        pricingDto.setId(pricing.getId());
        pricingDto.setDate(pricing.getDate());
        pricingDto.setPrice(pricing.getPrice());
        pricingDto.setUserId(pricing.getUser().getId());
        if (pricing.getPricingRecords() != null) {
            pricingDto.setPricingRecordDtos(pricingRecordMapper.toPricingRecordDtoList(pricing.getPricingRecords()));
        }
        return pricingDto;
    }

    public List<PricingDto> toPricingDtoList(final List<Pricing> pricingList) {
        return pricingList.stream()
                .map(this::toPricingDto)
                .collect(Collectors.toList());
    }
}
