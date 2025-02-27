package com.example.springboot.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	private static Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

	private RabbitTemplate rabbitTemplate;

	public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void send(String message) {

		LOGGER.info("Sending message: {}", message);
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
	}
}
