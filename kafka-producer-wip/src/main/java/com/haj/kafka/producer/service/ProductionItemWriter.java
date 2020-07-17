package com.haj.kafka.producer.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.haj.kafka.model.TicketMain;
import com.haj.kafka.producer.dto.ProductionTicketMain;
import com.haj.kafka.producer.repository.ProductionRepository;
 
public class ProductionItemWriter<T> implements ItemWriter<T> {

	@Autowired
	private ProductionRepository productionRepository;
	
	@Autowired
	private KafkaTemplate<String, TicketMain> kafkaTemplate;
	
	@Autowired
	private ModelMapper modelMapper;
	
    public void write(List<? extends T> items) throws Exception {
        for (T item : items) {
            System.out.println(item.toString());
            ProductionTicketMain result = productionRepository.save((ProductionTicketMain)item);
            TicketMain message = modelMapper.map(result, TicketMain.class);
    		kafkaTemplate.send("POC-TickectMain", message.getTicketId()+"", message);
        }        
    }
}
