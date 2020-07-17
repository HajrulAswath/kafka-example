package com.haj.kafka.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haj.kafka.consumer.dto.ODSTicketNonPrime;

@Repository
public interface ODSNonPrimeRepository extends JpaRepository<ODSTicketNonPrime, Long> {
}
