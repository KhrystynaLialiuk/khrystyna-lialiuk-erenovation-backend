package com.kodilla.erenovation_service.controller;

import com.kodilla.erenovation_service.dto.ServiceDto;
import com.kodilla.erenovation_service.exception.ServiceNotFoundException;
import com.kodilla.erenovation_service.service.ServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/service")
@CrossOrigin("*")
@Slf4j
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping(value = "all")
    public List<ServiceDto> getAllServices() {
        log.info("Searching for all services");
        return serviceService.findAllServices();
    }

    @GetMapping(value = "titles")
    public List<String> getAllServiceTitles() {
        log.info("Searching for all services' titles");
        return serviceService.findAllServiceTitles();
    }

    @GetMapping
    public ServiceDto getServiceById(@RequestParam long serviceId) throws ServiceNotFoundException {
        log.info("Searching for the service with ID {}", serviceId);
        return serviceService.getService(serviceId);
    }
}
