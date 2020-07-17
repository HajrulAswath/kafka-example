package com.haj.kafka.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haj.kafka.consumer.dto.ODSTicketMain;

@Repository
public interface ODSRepository extends JpaRepository<ODSTicketMain, Long> {
}
