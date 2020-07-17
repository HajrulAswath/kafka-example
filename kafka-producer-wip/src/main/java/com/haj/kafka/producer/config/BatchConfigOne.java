package com.haj.kafka.producer.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.haj.kafka.producer.dto.ProductionTicketMain;
import com.haj.kafka.producer.service.ProductionItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfigOne {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean(name = "productionBatchJob")
	public Job productionBatchJob() {
		return jobs.get("productionBatchJob").incrementer(new RunIdIncrementer()).start(productionStep()).build();
	}

	@Bean
	public Step productionStep() {
		return stepBuilderFactory.get("productionStep").<ProductionTicketMain, ProductionTicketMain>chunk(10).reader(readerProduction())
				.writer(writerProduction()).build();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FlatFileItemReader<ProductionTicketMain> readerProduction() {
		FlatFileItemReader<ProductionTicketMain> reader = new FlatFileItemReader<ProductionTicketMain>();
		reader.setResource(new FileSystemResource("input/input-data-production.csv"));
		reader.setLinesToSkip(1);
		reader.setLineMapper(new DefaultLineMapper() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "ticketId", "source", "destination", "fare", "tax" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<ProductionTicketMain>() {
					{
						setTargetType(ProductionTicketMain.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ProductionItemWriter<ProductionTicketMain> writerProduction() {
		return new ProductionItemWriter<>();
	}

}