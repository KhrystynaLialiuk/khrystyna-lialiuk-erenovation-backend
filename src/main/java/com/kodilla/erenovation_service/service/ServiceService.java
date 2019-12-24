package com.kodilla.erenovation_service.service;

import com.kodilla.erenovation_service.data.ServiceDataCreator;
import com.kodilla.erenovation_service.dto.ServiceDto;
import com.kodilla.erenovation_service.mapper.ServiceMapper;
import com.kodilla.erenovation_service.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ServiceService {

    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceDataCreator serviceDataCreator;

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

    public HttpStatus createData() {
        List<com.kodilla.erenovation_service.domain.Service> savedServices = serviceRepository.findAll();
        if (savedServices.size() == 3) {
            return HttpStatus.CREATED;
        } else if (savedServices.size() == 0) {
            serviceDataCreator.createServiceData();
            return HttpStatus.CREATED;
        }
        return HttpStatus.BAD_REQUEST;
    }

    public void deleteData() {
        try {
            serviceRepository.deleteAll();
        } catch (Exception e) {
            log.warn("Services not deleted");
        }
    }
}
