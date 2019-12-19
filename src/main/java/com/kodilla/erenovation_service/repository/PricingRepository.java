package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.Pricing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PricingRepository extends CrudRepository<Pricing, Long> {
    List<Pricing> findAll();
}
