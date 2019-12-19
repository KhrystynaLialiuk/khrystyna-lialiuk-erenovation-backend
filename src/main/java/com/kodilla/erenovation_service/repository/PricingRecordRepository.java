package com.kodilla.erenovation_service.repository;

import com.kodilla.erenovation_service.domain.PricingRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricingRecordRepository extends CrudRepository<PricingRecord, Long> {

    List<PricingRecord> findAll();
}
