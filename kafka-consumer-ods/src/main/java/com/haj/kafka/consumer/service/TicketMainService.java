package com.haj.kafka.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haj.kafka.consumer.dto.ODSTicketMain;
import com.haj.kafka.consumer.dto.ODSTicketNonPrime;
import com.haj.kafka.consumer.repository.ODSNonPrimeRepository;
import com.haj.kafka.consumer.repository.ODSRepository;

@Service
public class TicketMainService {

	@Autowired
	private ODSRepository odsRepository;
	
	@Autowired
	private ODSNonPrimeRepository odsNonPrimeRepository;

	public void save(ODSTicketMain ticketMain) {
		odsRepository.save(ticketMain);
	}
	
	public void save(ODSTicketNonPrime ticketNonPrime) {
		odsNonPrimeRepository.save(ticketNonPrime);
	}

}
