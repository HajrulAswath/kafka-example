package com.haj.kafka.consumer.messagelistener;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.haj.kafka.consumer.dto.ODSTicketMain;
import com.haj.kafka.consumer.dto.ODSTicketNonPrime;
import com.haj.kafka.consumer.service.TicketMainService;
import com.haj.kafka.model.TicketMain;
import com.haj.kafka.model.TicketNonPrime;

@Service
public class KafkaMessageListener {

	@Autowired
	private TicketMainService ticketMainService;
	
	@Autowired
	private ModelMapper modelMapper;

	@KafkaListener(topics = "POC-TickectMain")
	public void handle(TicketMain message) {
		System.out.println("Received TickectMain : " + message);
		ODSTicketMain tickectMain = modelMapper.map(message, ODSTicketMain.class);
		ticketMainService.save(tickectMain);
	}
	
	@KafkaListener(topics = "POC-TickectNonPrime")
	public void handle(TicketNonPrime message) {
		System.out.println("Received TickectNonPrime : " + message);
		ODSTicketNonPrime tickectMain = modelMapper.map(message, ODSTicketNonPrime.class);
		ticketMainService.save(tickectMain);
	}

}
