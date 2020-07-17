package com.haj.kafka.consumer.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ODSTicketMain {
	@Id
	private int ticketId;
	private String source;
	private String destination;
	private double fare;
	private double tax;
}
