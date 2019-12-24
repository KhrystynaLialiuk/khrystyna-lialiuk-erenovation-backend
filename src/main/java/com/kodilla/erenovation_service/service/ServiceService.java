package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.dto.ServiceDto;
import com.kodilla.erenovation_service.exception.ServiceNotFoundException;
import com.kodilla.erenovation_service.mapper.ServiceMapper;
import com.kodilla.erenovation_service.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceDto> findAllServices() {
        return serviceMapper.toServiceDtoList(serviceRepository.findAll());
    }

    public List<String> findAllServiceTitles() {
        List<ServiceDto> serviceDtoList = findAllServices();
        List<String> serviceTitles = new ArrayList<>();
        for (ServiceDto serviceDto : serviceDtoList) {
            serviceTitles.add(serviceDto.getTitle());
        }
        return serviceTitles;
    }
}
