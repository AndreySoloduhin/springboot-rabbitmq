package com.example.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${rabbitmq.queue.name}")
	private String queueName;

	@Value("${rabbitmq.queue.json.name}")
	private String queueJsonName;

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public Queue jsonQueue() {
		return new Queue(queueJsonName);
	}

	//обмен
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchangeName);
	}

	//привязка очереди к обмену с помощью ключа маршрутизации
	@Bean
	public Binding binding() {
		 return BindingBuilder
				 .bind(queue())
				 .to(exchange())
				 .with(routingKey);
	}

	@Bean
	public Binding bindingJson() {
		return BindingBuilder
				.bind(jsonQueue())
				.to(exchange())
				.with(routingJsonKey);
	}
}
