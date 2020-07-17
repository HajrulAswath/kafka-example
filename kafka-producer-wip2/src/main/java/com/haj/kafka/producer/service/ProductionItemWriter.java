package com.haj.kafka.producer.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.haj.kafka.model.TicketNonPrime;
import com.haj.kafka.producer.dto.ProductionTicketNonPrime;
import com.haj.kafka.producer.repository.ProductionRepository;
 
public class ProductionItemWriter<T> implements ItemWriter<T> {

	@Autowired
	private ProductionRepository productionRepository;
	
	@Autowired
	private KafkaTemplate<String, TicketNonPrime> kafkaTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
    public void write(List<? extends T> items) throws Exception {
        for (T item : items) {
            System.out.println(item.toString());
            ProductionTicketNonPrime result = productionRepository.save((ProductionTicketNonPrime)item);
            TicketNonPrime message = modelMapper.map(result, TicketNonPrime.class);
    		kafkaTemplate.send("POC-TickectNonPrime", "POC-TickectNonPrime", message);
        }        
    }
}
