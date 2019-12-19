package com.kodilla.erenovation_service.mapper;

import com.kodilla.erenovation_service.domain.Service;
import com.kodilla.erenovation_service.dto.ServiceDto;
import com.kodilla.erenovation_service.exception.ServiceTypeNotFoundException;
import com.kodilla.erenovation_service.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public Service toService(final ServiceDto serviceDto) throws ServiceTypeNotFoundException {
        Service service = new Service();
        service.setId(serviceDto.getId());
        service.setTitle(serviceDto.getTitle());
        service.setRegularUnitPrice(serviceDto.getRegularUnitPrice());
        service.setDiscountUnitPrice(serviceDto.getDiscountUnitPrice());
        service.setThresholdValue(serviceDto.getThresholdValue());
        if (serviceDto.getServiceTypeId() != 0) {
            service.setServiceType(serviceTypeRepository.findById(serviceDto.getServiceTypeId())
                    .orElseThrow(ServiceTypeNotFoundException::new));
        }
        return service;
    }

    public ServiceDto toServiceDto(final Service service) {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(service.getId());
        serviceDto.setTitle(service.getTitle());
        serviceDto.setRegularUnitPrice(service.getRegularUnitPrice());
        serviceDto.setDiscountUnitPrice(service.getDiscountUnitPrice());
        serviceDto.setThresholdValue(service.getThresholdValue());
        serviceDto.setServiceTypeId(service.getServiceType().getId());
        return serviceDto;
    }

    public List<ServiceDto> toServiceDtoList(final List<Service> serviceList) {
        return serviceList.stream()
                .map(this::toServiceDto)
                .collect(Collectors.toList());
    }
}
