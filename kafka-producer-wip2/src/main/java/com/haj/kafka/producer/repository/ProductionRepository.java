package com.haj.kafka.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haj.kafka.producer.dto.ProductionTicketNonPrime;

@Repository
public interface ProductionRepository extends JpaRepository<ProductionTicketNonPrime, Long> {
}
