package com.haj.kafka.model;

import lombok.Data;

@Data
public class TicketMain {
	private int ticketId;
	private String source;
	private String destination;
	private double fare;
	private double tax;
}
