package com.haj.kafka.model;

import lombok.Data;

@Data
public class TicketNonPrime {
	private int ticketId;
	private String source;
	private String destination;
	private double fare;
	private double tax;
}
