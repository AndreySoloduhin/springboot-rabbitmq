package com.example.springboot.publisher;

import com.example.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMqJsonProducer {

	@Value("${rabbitmq.exchange.name}")
	private String  exchange;

	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;

	private static final Logger logger = LoggerFactory.getLogger(RabbitMqJsonProducer.class);

	private final RabbitTemplate rabbitTemplate;

	public RabbitMqJsonProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendJsonMessage(User user) {

		logger.info(String.format("Sending json sent -> %s",user.toString()));
		rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
	}
}
